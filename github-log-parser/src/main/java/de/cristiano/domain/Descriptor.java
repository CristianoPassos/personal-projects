package de.cristiano.domain;

import de.cristiano.domain.constants.DataBase;
import de.cristiano.domain.constants.Journey;
import de.cristiano.domain.constants.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Set;

@Getter
@Builder
public class Descriptor {

    private String name;

    @Singular
    private Set<Journey> journeys;

    private String team;

    private String jira;

    private Type type;

    private DataBase data_base;

    private Slack slack;

}
