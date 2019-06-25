package de.cristiano.domain.constants;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import javax.annotation.Nonnull;

@Getter
public enum Type {
    AGGREGATION("Aggregation"),
    TOOL("Tool"),
    SERVICE("Service"),
    INFRASTRUCTURE("Infrastructure");

    @JsonValue
    private String label;

    Type(@Nonnull final String label) {
        this.label = label;
    }

}
