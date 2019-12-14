package br.com.meumenu.model.cadastro;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Endereco {
	private Integer numero;
	private Integer cep;
	private String logradouro;
}
