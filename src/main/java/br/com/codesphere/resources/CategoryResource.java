package br.com.codesphere.resources;

import br.com.codesphere.services.CategoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject
    CategoryService service;

    @GET
    public Object list() {
        return service.list();
    }
} 
