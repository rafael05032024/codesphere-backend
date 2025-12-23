package br.com.codesphere.services;

import br.com.codesphere.repositories.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CategoryService {

    @Inject
    CategoryRepository repository;


    public Object list() {
        return repository.listAllOrderedByCreatedAt();
    }
} 
