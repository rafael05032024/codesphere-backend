package br.com.codesphere.resources;

import java.util.List;

import br.com.codesphere.dtos.CategoryListItemDTO;
import br.com.codesphere.services.CategoryService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/category")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject
    CategoryService service;

    @GET
    public List<CategoryListItemDTO> list() {
        return service.list();
    }
}
