package de.cristiano.domain.constants;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import javax.annotation.Nonnull;

@Getter
public enum Journey {
    CORE("Core"),
    MERCHANT("Merchant"),
    CUSTOMER("Customer");

    @JsonValue
    private String label;

    Journey(@Nonnull final String label) {
        this.label = label;
    }

}
