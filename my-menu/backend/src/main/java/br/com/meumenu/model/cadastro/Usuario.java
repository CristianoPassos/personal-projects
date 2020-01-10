package br.com.meumenu.model.cadastro;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

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
