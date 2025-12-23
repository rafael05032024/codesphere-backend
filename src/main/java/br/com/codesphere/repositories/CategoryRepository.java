package br.com.codesphere.repositories;

import java.util.List;

import br.com.codesphere.entities.CategoryEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<CategoryEntity> {

    public List<CategoryEntity> listAllOrderedByCreatedAt() {
        return find("order by createdAt desc").list();
    }
}
