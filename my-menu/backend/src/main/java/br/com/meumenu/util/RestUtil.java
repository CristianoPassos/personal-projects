package br.com.meumenu.util;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.StreamingOutput;

@ApplicationScoped
public class RestUtil {

	public static final String CSV_CONTENT_TYPE = "text/csv";
	public static final String JPEG_CONTENT_TYPE = "image/jpeg";
	public static final String XLS_CONTENT_TYPE = "application/vnd.ms-excel";
	public static final String XLSX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public static final String[] ACCEPTED_CONTENT_TYPE = { CSV_CONTENT_TYPE, XLS_CONTENT_TYPE, XLSX_CONTENT_TYPE };
	public static final String[] ACCEPTED_EXCEL_CONTENT_TYPE = { XLS_CONTENT_TYPE, XLSX_CONTENT_TYPE };

	public List<String> getContentType(MultivaluedMap<String, String> header) {
		String contentTypes = header.getFirst(HttpHeaders.CONTENT_TYPE);
		return contentTypes != null ? Arrays.asList(contentTypes.split(";")) : null;
	}

	public Response writeError(String errorMessage, Status status) {
		return writeErrorBuilder(errorMessage, status).build();
	}

	public ResponseBuilder writeErrorBuilder(String errorMessage, Status status) {
		return Response.status(status).entity(errorMessage).type(MediaType.TEXT_PLAIN);
	}

	public Response writeExcel(String nomeArquivo, StreamingOutput stream, Status status) {
		return writeFile(nomeArquivo, XLS_CONTENT_TYPE, false, stream, status);
	}

	public Response writeFile(String nomeArquivo, String contentType, boolean inline, StreamingOutput stream,
			Status status) {
		return Response.status(status).entity(stream).type(contentType).header("Content-Disposition",
				(inline ? "inline" : "attachment") + "; filename=\"" + nomeArquivo + "\"").build();
	}

	public Response writeJpeg(String nomeArquivo, StreamingOutput stream) {
		return writeFile(nomeArquivo, JPEG_CONTENT_TYPE, true, stream, Status.OK);
	}
}
