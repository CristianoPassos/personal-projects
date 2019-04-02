package de.cristiano.farm.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "field_coordinates")
public class Coordinate {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "field_id")
    private Long fieldId;

    private Long lat;

    private Long lon;

}
