package br.com.meumenu.model.cardapio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.meumenu.model.cadastro.Fornecedor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "Refeicoes")
@XmlRootElement
@Data
@EqualsAndHashCode(of = "id")
@ToString(includeFieldNames = true, exclude = { "variacoes", "fornecedor" })
public class Refeicao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	private String titulo;
	@NotEmpty
	private String descricao;
	@NotEmpty
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "refeicao")
	private List<Variacao> variacoes;
	@ManyToOne
	@NotNull
	private Fornecedor fornecedor;
}
