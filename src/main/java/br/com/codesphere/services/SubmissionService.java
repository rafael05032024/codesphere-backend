package br.com.codesphere.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.codesphere.dtos.CreateSubmissionDTO;
import br.com.codesphere.dtos.CreateSubmissionResponseDTO;
import br.com.codesphere.dtos.SubmissionDetailDTO;
import br.com.codesphere.dtos.SubmissionListDTO;
import br.com.codesphere.dtos.SubmissionListItemDTO;
import br.com.codesphere.entities.LanguageEntity;
import br.com.codesphere.entities.ProblemEntity;
import br.com.codesphere.entities.SubmissionCompilationEntity;
import br.com.codesphere.entities.SubmissionEntity;
import br.com.codesphere.entities.UserEntity;
import br.com.codesphere.exception.ApplicationException;
import br.com.codesphere.repositories.LanguageRepository;
import br.com.codesphere.repositories.ProblemRepository;
import br.com.codesphere.repositories.SubmissionCompilationRepository;
import br.com.codesphere.repositories.SubmissionRepository;
import br.com.codesphere.repositories.UserRepository;
import br.com.codesphere.utils.StringUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SubmissionService {

  @Inject
  SubmissionRepository submissionRepository;

  @Inject
  ProblemRepository problemRepository;

  @Inject
  UserRepository userRepository;

  @Inject
  LanguageRepository languageRepository;

  @Inject
  SubmissionCompilationRepository submissionCompilationRepository;

  @Transactional
  public CreateSubmissionResponseDTO create(CreateSubmissionDTO request, Long userId) throws ApplicationException {
    ProblemEntity problem = problemRepository.findById(request.problemId);

    if (Objects.isNull(problem)) {
      throw new ApplicationException("Problema não encontrado!", 404);
    }

    LanguageEntity language = languageRepository.findById(request.languageId);

    if (Objects.isNull(language)) {
      throw new ApplicationException("Linguagem não encontrada!", 404);
    }

    UserEntity user = userRepository.findById(userId);

    SubmissionEntity submission = new SubmissionEntity();

    submission.problem = problem;
    submission.user = user;
    submission.language = language;
    submission.sourceCode = request.sourceCode;
    submission.status = 0;

    submissionRepository.persist(submission);

    return new CreateSubmissionResponseDTO(submission.id);
  }

  public SubmissionListDTO listByUserId(Long userId) {
    List<SubmissionEntity> submissions = submissionRepository.listByUserId(userId);
    List<SubmissionListItemDTO> list = new ArrayList<>();

    submissions.forEach(submission -> {
      SubmissionListItemDTO item = new SubmissionListItemDTO(submission.id, submission.problem.title,
          submission.problem.id, submission.status, submission.language.name, submission.createdAt);

      list.add(item);
    });

    return new SubmissionListDTO(list);
  }

  public SubmissionDetailDTO findById(long submissionId, long userId) throws ApplicationException {
    SubmissionEntity submission = submissionRepository.findByIdAndUserId(userId, submissionId);

    if (Objects.isNull(submission)) {
      throw new ApplicationException("Registro não entrado!", 404);
    }

    List<SubmissionCompilationEntity> compilations = this.submissionCompilationRepository
        .listBySubmissionId(submissionId);

    double totalMemory = 0;
    double totalTime = 0;

    for (SubmissionCompilationEntity c : compilations) {
      totalMemory += Objects.isNull(c.memory) ? 0 : c.memory;
      totalTime += Objects.isNull(c.time) ? 0 : c.time;
    }

    double averageMemory = totalMemory / compilations.size();
    double averageTime = totalTime / compilations.size();
    int size = StringUtils.getSizeInBytes(submission.sourceCode);

    return new SubmissionDetailDTO(submission.sourceCode, submission.id, submission.status, submission.language.name,
        submission.comment, submission.problem.id, submission.problem.title, averageMemory, averageTime, size);
  }

}
