package br.com.meumenu.model.cadastro;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Usuarios")
@XmlRootElement
@Data
@EqualsAndHashCode(of = "id")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private Integer ddd;
	@NotNull
	private Integer telefone;
	@NotEmpty
	private String nome;
	// @Embedded
	// private Endereco enderecoEntrega;
}
