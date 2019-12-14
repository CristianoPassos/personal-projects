package de.cristiano.farm.repository;

import de.cristiano.farm.domain.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Long> {

}
