package br.com.meumenu.rest;

import br.com.meumenu.data.FornecedorRepository;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/fornecedor")
@RequestScoped
@Stateful
public class FornecedorRest {

    @Inject
    private FornecedorRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        return Response.ok(repository.findAll()).build();
    }
}
