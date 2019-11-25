package de.cristiano.driver.domainobject.car;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
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
@Table(name = "car_engine")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class CarEngineDO
{
    @Column(nullable = false)
    @DateTimeFormat(iso = DATE_TIME)
    private ZonedDateTime dateCreated;

    @Column(nullable = false)
    private boolean deleted;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(length = 25, nullable = false)
    private String name;


    @PrePersist
    public void prePersist()
    {
        if (dateCreated == null)
        {
            dateCreated = ZonedDateTime.now();
        }
    }

}
