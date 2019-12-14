package br.com.meumenu.model.cardapio;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@XmlRootElement
@Table(name = "Variacoes")
@Data
@EqualsAndHashCode(of = "id")
@XmlAccessorType(XmlAccessType.NONE)
public class Variacao {

	@XmlElement
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@XmlElement
	@NotNull
	private Integer peso;
	@XmlElement
	@NotNull
	private BigDecimal preco;
	@XmlElement
	@NotNull
	private String tamanho;
	@ManyToOne
	@JoinColumn(name = "refeicao_id")
	private Refeicao refeicao;
}
