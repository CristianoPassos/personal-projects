package br.com.meumenu.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.meumenu.data.RefeicaoRepository;
import br.com.meumenu.model.cardapio.Refeicao;
import br.com.meumenu.service.CarpadioService;

@Path("/fornecedor/{fornecedor:[0-9]*}/cardapio")
@RequestScoped
@Stateful
public class CardapioRest extends AbstractRest {

	@Inject
	private RefeicaoRepository repository;

	@Inject
	CarpadioService registration;

	@PathParam("fornecedor")
	private int idFornecedor;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarCarpadio() {
		List<Refeicao> refeicoes = repository.findByFornecedor(idFornecedor);
		return Response.ok(refeicoes).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Refeicao refeicao) {
		Response.ResponseBuilder builder = null;

		try {
			registration.register(refeicao, idFornecedor);

			builder = Response.ok().entity(refeicao);
		} catch (ConstraintViolationException ce) {
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (Exception e) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete() {
		Response.ResponseBuilder builder = null;

		try {

			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (Exception e) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Refeicao refeicao) {
		Response.ResponseBuilder builder = null;

		try {
			registration.update(refeicao);

			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (Exception e) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}
}
