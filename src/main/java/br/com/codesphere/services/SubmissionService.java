package br.com.codesphere.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.codesphere.dtos.CreateSubmissionDTO;
import br.com.codesphere.dtos.CreateSubmissionResponseDTO;
import br.com.codesphere.dtos.SubmissionDTO;
import br.com.codesphere.entities.ProblemEntity;
import br.com.codesphere.entities.SubmissionEntity;
import br.com.codesphere.entities.UserEntity;
import br.com.codesphere.exception.ApplicationException;
import br.com.codesphere.repositories.ProblemRepository;
import br.com.codesphere.repositories.SubmissionRepository;
import br.com.codesphere.repositories.UserRepository;
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

  @Transactional
  public CreateSubmissionResponseDTO create(CreateSubmissionDTO request, Long userId) throws ApplicationException {
    ProblemEntity problem = problemRepository.findById(request.problemId);

    if (Objects.isNull(problem)) {
      throw new ApplicationException("Problema não encontrado!", 404);
    }

    UserEntity user = userRepository.findById(userId);

    SubmissionEntity submission = new SubmissionEntity();

    submission.problem = problem;
    submission.user = user;
    submission.sourceCode = request.sourceCode;
    submission.status = 0;

    submissionRepository.persist(submission);

    return new CreateSubmissionResponseDTO(submission.id);
  }

  public List<SubmissionDTO> listByUserId(Long userId) {
    List<SubmissionEntity> dbSubmissions = submissionRepository.listByUserId(userId);
    List<SubmissionDTO> submissionsList = new ArrayList<>();

    dbSubmissions.forEach(s -> {
      submissionsList.add(new SubmissionDTO(s.sourceCode, s.id, s.status, s.language.name));
    });

    return submissionsList;
  }

}
