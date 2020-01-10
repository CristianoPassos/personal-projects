package br.com.meumenu.model.cardapio;

import br.com.meumenu.model.cadastro.Fornecedor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "Refeicoes")
@XmlRootElement
@Data
@EqualsAndHashCode(of = "id")
@ToString(includeFieldNames = true, exclude = {"variacoes", "fornecedor"})
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
