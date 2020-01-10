package br.com.meumenu.model.cadastro;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Endereco {
    private Integer numero;
    private Integer cep;
    private String logradouro;
}
