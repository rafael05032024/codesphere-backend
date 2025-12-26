package br.com.codesphere.jobs;

import java.util.List;
import java.util.Objects;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.codesphere.entities.ProblemCaseTestEntity;
import br.com.codesphere.entities.SubmissionCompilationEntity;
import br.com.codesphere.entities.SubmissionEntity;
import br.com.codesphere.integration.judge0.Judge0RestClient;
import br.com.codesphere.integration.judge0.dtos.Judge0SubmissionRequestDTO;
import br.com.codesphere.integration.judge0.dtos.Judge0TokenDTO;
import br.com.codesphere.repositories.ProblemCaseTestRepository;
import br.com.codesphere.repositories.SubmissionCompilationRepository;
import br.com.codesphere.repositories.SubmissionRepository;
import io.quarkus.runtime.Startup;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

/**
 * Busca as submissões criadas, status igual 0, depois busca os casos de teste
 * do problema e, para cada caso de teste, envia para o judge0, obtém o token de
 * resposta guarda na tabela submission_compilation, e atualiza o status da
 * submissão
 */
@Startup
@ApplicationScoped
@Transactional
public class HandleCreatedSubmissionsJob extends AbstractJob {

	@Inject
	SubmissionRepository submissionRepository;

	@Inject
	ProblemCaseTestRepository problemCaseTestRepository;

	@Inject
	SubmissionCompilationRepository submissionCompilationRepository;

	@Inject
	@RestClient
	Judge0RestClient judge0;

	@Override
	protected String jobName() {
		return "hcs";
	}

	@Override
	protected void process() {

		List<SubmissionEntity> submissions = submissionRepository.listByStatus(0);

		if (submissions.isEmpty()) {
			System.out.println("[HCS] Nothing to process");
			return;
		}

		System.out.println("Submetendo " + submissions.size() + " códigos para compilação");

		for (int i = 0; i < submissions.size(); i++) {
			SubmissionEntity submission = submissions.get(i);

			List<ProblemCaseTestEntity> testCases = problemCaseTestRepository
					.listByProblemId(submission.problem.id);

			for (int j = 0; j < testCases.size(); j++) {

				List<SubmissionCompilationEntity> compilations = submissionCompilationRepository
						.listBySubmissionId(submission.id);

				ProblemCaseTestEntity testCase = testCases.get(j);

				boolean isEmpty = compilations.stream()
						.filter((c) -> Objects.equals(c.problemCaseTest.id, testCase.id)).findFirst().isEmpty();

				if (!isEmpty) {
					System.out.println("Jumping case test " + testCase.id);

					continue;
				}

				System.out.println("Submitting teste case " + testCase.id);

				Judge0SubmissionRequestDTO request = new Judge0SubmissionRequestDTO(52, submission.sourceCode,
						testCase.input);

				Judge0TokenDTO response = judge0.createSubmission(true, false, request);

				SubmissionCompilationEntity submissionCompilationEntity = new SubmissionCompilationEntity();

				submissionCompilationEntity.submission = submission;
				submissionCompilationEntity.problemCaseTest = testCase;
				submissionCompilationEntity.token = response.token;

				submissionCompilationRepository.persist(submissionCompilationEntity);
			}

			submission.status = 1;

			submission.persist();
		}
	}

	@Scheduled(every = "5s")
	public void scheduled() {
		execute();
	}

}
