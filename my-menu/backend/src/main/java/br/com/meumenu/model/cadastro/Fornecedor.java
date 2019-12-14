package br.com.meumenu.model.cadastro;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Fornecedores")
@XmlRootElement
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Fornecedor(Integer id) {
		super();
		this.id = id;
	}
}
