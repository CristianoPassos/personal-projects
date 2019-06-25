package de.cristiano.domain.constants;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import javax.annotation.Nonnull;

@Getter
public enum DataBase {
    NONE("None"),
    MYSQL("MySQL"),
    REDIS("Redis"),
    DYNAMO_DB("DynamoDB"),
    POSTGRESQL("PostgreSQL"),
    ELASTICSEARCH("Elasticsearch");

    @JsonValue
    private final String label;

    DataBase(@Nonnull final String label) {
        this.label = label;
    }


}
