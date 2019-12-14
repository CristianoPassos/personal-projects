package br.com.meumenu.rest;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.StreamingOutput;

import org.jxls.template.SimpleExporter;

import br.com.meumenu.data.PedidoRepository;
import br.com.meumenu.excel.PedidoExcel;
import br.com.meumenu.util.RestUtil;

@Path("/fornecedor/{fornecedor:[0-9]*}/pedido/")
@RequestScoped
@Stateful
public class PedidoRest extends AbstractRest {

	@Inject
	private RestUtil restUtil;

	@Inject
	private PedidoRepository repository;

	@PathParam("fornecedor")
	private int idFornecedor;

	@Path("exportar")
	@Produces(RestUtil.XLSX_CONTENT_TYPE)
	@GET
	public Response exportarPedidos() throws FileNotFoundException {
		List<PedidoExcel> listaPedido = repository.findAllByDate(LocalDate.now());
		List<String> headers = Arrays.asList("Tamanho", "Observação", "Cliente", "Telefone", "Refeição");
		StreamingOutput saidaExcel = output -> new SimpleExporter().gridExport(headers, listaPedido,
				"tamanho, observacao, cliente, telefone, refeicao", output);
		return this.restUtil.writeExcel("produtos-Netshoes.xls", saidaExcel, Status.OK);
	}

}
