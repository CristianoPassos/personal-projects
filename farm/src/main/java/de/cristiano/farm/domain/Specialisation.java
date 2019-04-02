package de.cristiano.farm.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Specialisation {

    RICE("Rice"), CORN("Corn");

    private final String name;
}
