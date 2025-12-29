package br.com.codesphere.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.codesphere.dtos.CreateProblemDTO;
import br.com.codesphere.dtos.ProblemCaseTestDTO;
import br.com.codesphere.dtos.ProblemDetailDTO;
import br.com.codesphere.dtos.ProblemListDTO;
import br.com.codesphere.dtos.ProblemListItemDTO;
import br.com.codesphere.entities.CategoryEntity;
import br.com.codesphere.entities.ProblemCaseTestEntity;
import br.com.codesphere.entities.ProblemEntity;
import br.com.codesphere.entities.UserEntity;
import br.com.codesphere.exception.ApplicationException;
import br.com.codesphere.repositories.CategoryRepository;
import br.com.codesphere.repositories.ProblemCaseTestRepository;
import br.com.codesphere.repositories.ProblemRepository;
import br.com.codesphere.repositories.UserRepository;
import br.com.codesphere.utils.StringUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProblemService {

  @Inject
  ProblemCaseTestRepository problemCaseTestRepository;

  @Inject
  ProblemRepository problemRepository;

  @Inject
  UserRepository userRepository;

  @Inject
  CategoryRepository categoryRepository;

  @Transactional
  public void create(CreateProblemDTO request, Long userId) throws ApplicationException {
    CategoryEntity category = categoryRepository.findById(request.categoryId);

    if (Objects.isNull(category)) {
      throw new ApplicationException("Categoria inexistente", 404);
    }

    UserEntity user = userRepository.findById(userId);
    ProblemEntity problem = new ProblemEntity();

    problem.author = user;
    problem.description = request.descriptionText;
    problem.inputText = request.inputText;
    problem.outputText = request.outputText;
    problem.timeLimit = request.timeLimit;
    problem.category = category;
    problem.title = request.title;

    problemRepository.persist(problem);

    for (int i = 0; i < request.testCases.size(); i++) {
      ProblemCaseTestEntity problemCaseTest = new ProblemCaseTestEntity();
      ProblemCaseTestDTO body = request.testCases.get(i);

      problemCaseTest.input = StringUtils.encodeBase64(body.input);
      problemCaseTest.expectedOutput = StringUtils.encodeBase64(body.expectedOutput);
      problemCaseTest.problem = problem;
      problemCaseTest.isExample = body.isExample;

      problemCaseTestRepository.persist(problemCaseTest);
    }
  }

  public ProblemListDTO listByCategory(long categoryId) {
    List<ProblemEntity> problems = problemRepository.findByCategoryId(categoryId);
    long total = problemRepository.countByCategoryId(categoryId);
    List<ProblemListItemDTO> problemList = new ArrayList<>();

    problems.forEach((problem) -> {
      problemList.add(new ProblemListItemDTO(problem.title, problem.id));
    });

    return new ProblemListDTO(problemList, total);
  }

  public ProblemDetailDTO findById(long id) throws ApplicationException {
    ProblemEntity problem = problemRepository.findById(id);

    if (Objects.isNull(problem)) {
      throw new ApplicationException("Problema não encontrado!", 404);
    }

    return new ProblemDetailDTO(problem.id, problem.timeLimit, problem.description, problem.inputText,
        problem.outputText,
        problem.title,
        problem.author.name, problem.category.title, problem.category.id);
  }

}
