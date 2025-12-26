package br.com.codesphere.services;

import java.util.ArrayList;
import java.util.List;

import br.com.codesphere.dtos.CategoryListItemDTO;
import br.com.codesphere.entities.CategoryEntity;
import br.com.codesphere.repositories.CategoryRepository;
import br.com.codesphere.repositories.ProblemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CategoryService {

	@Inject
	CategoryRepository categoryRepository;

	@Inject
	ProblemRepository problemRepository;

	public List<CategoryListItemDTO> list() {

		List<CategoryEntity> categories = categoryRepository.listAllOrderedByCreatedAt();
		List<CategoryListItemDTO> list = new ArrayList<>();

		for (CategoryEntity category : categories) {
			long totalProblems = problemRepository.countByCategoryId(category.id);

			CategoryListItemDTO item = new CategoryListItemDTO(category.id, category.title, category.description,
					totalProblems);

			list.add(item);
		}

		return list;
	}
}
