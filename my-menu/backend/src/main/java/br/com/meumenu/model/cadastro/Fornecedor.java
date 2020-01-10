package br.com.meumenu.model.cadastro;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

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
