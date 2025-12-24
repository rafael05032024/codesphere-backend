package br.com.codesphere.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.codesphere.dtos.CreateProblemDTO;
import br.com.codesphere.dtos.ProblemCaseTestDTO;
import br.com.codesphere.dtos.ProblemDTO;
import br.com.codesphere.dtos.ProblemListDTO;
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
  public void create(CreateProblemDTO request) throws ApplicationException {
    CategoryEntity category = categoryRepository.findById(request.categoryId);

    if (Objects.isNull(category)) {
      throw new ApplicationException("Categoria inexistente", 404);
    }

    UserEntity user = userRepository.findById(request.userId);
    ProblemEntity problem = new ProblemEntity();

    String formattedTemplate = StringUtils
        .encodeBase64(StringUtils.removeAllNewLines(request.templateHtml));

    problem.author = user;
    problem.templateHtml = formattedTemplate;
    problem.timeLimit = request.timeLimit;
    problem.category = category;
    problem.title = request.title;

    problemRepository.persist(problem);

    for (int i = 0; i < request.testCases.size(); i++) {
      ProblemCaseTestEntity problemCaseTest = new ProblemCaseTestEntity();
      ProblemCaseTestDTO problemCaseTestDTO = request.testCases.get(i);

      problemCaseTest.input = StringUtils.encodeBase64(problemCaseTestDTO.input);
      problemCaseTest.expectedOutput = StringUtils.encodeBase64(problemCaseTestDTO.expectedOutput);
      problemCaseTest.problem = problem;

      problemCaseTestRepository.persist(problemCaseTest);
    }

  }

  public ProblemListDTO listByCategory(long categoryId) {
    List<ProblemEntity> problems = problemRepository.findByCategoryId(categoryId);
    List<ProblemDTO> problemList = new ArrayList<>();
    

    problems.forEach((p) -> {
      ProblemDTO problem = new ProblemDTO(p.id, StringUtils.decodeBase64(p.templateHtml), p.title);

      problemList.add(problem);
    });
    
    return new ProblemListDTO(problemList);
  }

}
