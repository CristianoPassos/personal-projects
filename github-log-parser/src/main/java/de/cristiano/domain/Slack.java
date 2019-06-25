package de.cristiano.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Slack {
    private String channel;

    private String group;
}
