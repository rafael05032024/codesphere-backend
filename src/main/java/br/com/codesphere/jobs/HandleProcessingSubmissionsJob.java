package br.com.codesphere.jobs;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.codesphere.entities.SubmissionCompilationEntity;
import br.com.codesphere.entities.SubmissionEntity;
import br.com.codesphere.integration.judge0.Judge0RestClient;
import br.com.codesphere.integration.judge0.dtos.Judge0SubmissionResponseDTO;
import br.com.codesphere.repositories.SubmissionCompilationRepository;
import br.com.codesphere.repositories.SubmissionRepository;
import io.quarkus.runtime.Startup;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Startup
@ApplicationScoped
@Transactional
public class HandleProcessingSubmissionsJob extends AbstractJob {

  @Inject
  SubmissionRepository submissionRepository;

  @Inject
  SubmissionCompilationRepository submissionCompilationRepository;

  @Inject
  @RestClient
  Judge0RestClient judge0;

  @Override
  protected String jobName() {
    return "HPS";
  }

  @Override
  protected void process() {
    List<SubmissionEntity> processingSubmissions = submissionRepository.listByStatus(1);

    System.out.println("[HPS] Processing " + processingSubmissions.size() + " submissions");

    for (int i = 0; i < processingSubmissions.size(); i++) {
      SubmissionEntity submission = processingSubmissions.get(i);

      List<SubmissionCompilationEntity> compilations = submissionCompilationRepository
          .listBySubmissionId(submission.id);

      for (SubmissionCompilationEntity compilation : compilations) {

        Judge0SubmissionResponseDTO response = judge0.findSubmission(compilation.token, true, "*");

        if (response.statusId == 1 || response.statusId == 2) {
          System.out.println(compilation.token + " yet in queue");

          return;
        }

        compilation.memory = response.memory;
        compilation.time = Double.parseDouble(response.time);

        compilation.persist();

        if (response.statusId == 3) {
          if (!response.stdOut.equals(compilation.problemCaseTest.expectedOutput)) {
            System.out.println(compilation.problemCaseTest.expectedOutput + " different from " + response.stdOut);

            submission.status = 3;
            submission.comment = "result not match";

            submission.persist();
          } else {
            submission.status = 2;

            submission.persist();
          }
        } else {
          submission.status = 3;
          submission.comment = "compilation failed";

          submission.persist();
        }
      }

    }
  }

  @Scheduled(every = "10s")
  public void scheduled() {
    execute();
  }

}
