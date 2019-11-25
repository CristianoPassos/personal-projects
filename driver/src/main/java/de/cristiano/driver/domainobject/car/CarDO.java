package de.cristiano.driver.domainobject.car;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@Data
@Entity
@Builder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@Table(
    name = "car",
    uniqueConstraints = @UniqueConstraint(name = "uc_uuid", columnNames = {"uuid"})
)
public class CarDO
{
    @Column(nullable = false)
    private Boolean convertible;

    @Column(nullable = false)
    @DateTimeFormat(iso = DATE_TIME)
    private ZonedDateTime dateCreated;

    @Column(nullable = false)
    private Boolean deleted;

    @Column
    private Long driverId;

    //Foreign key constraint should be provided by the Data Base Model.
    @Column
    private Long engineTypeId;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 15, nullable = false)
    private String licensePlate;

    //Foreign key constraint should be provided by the Data Base Model.
    @Column
    private Long manufacturerId;

    private Short rating;

    @Column(nullable = false)
    private Integer seatCount;

    @Column(length = 8, nullable = false)
    private String uuid;

    @Version
    private Integer version;


    @PrePersist
    public void prePersist()
    {
        if (dateCreated == null)
        {
            dateCreated = ZonedDateTime.now();
        }
    }
}
