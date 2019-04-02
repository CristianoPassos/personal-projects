package de.cristiano.farm.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "fields")
public class Field {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
