package de.cristiano.farm.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "field_condition")
public class FieldCondition {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "field_id")
    private Long fieldId;

    private LocalDate day;

    private Specialisation specialisation;

}
