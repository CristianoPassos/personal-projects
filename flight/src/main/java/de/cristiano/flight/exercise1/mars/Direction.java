package de.cristiano.flight.exercise1.mars;

import lombok.Getter;

import javax.annotation.Nonnull;

public enum Direction {
    NORTH("WEST", "EAST", 0, 1),
    WEST("SOUTH", "NORTH", -1, 0),
    SOUTH("EAST", "WEST", 0, -1),
    EAST("NORTH", "SOUTH", 1, 0);

    private final String left;

    private final String right;

    @Getter
    private final Integer xChange;

    @Getter
    private final Integer yChange;

    Direction(@Nonnull final String left,
              @Nonnull final String right,
              @Nonnull final Integer xChange,
              @Nonnull final Integer yChange) {
        this.left = left;
        this.right = right;
        this.xChange = xChange;
        this.yChange = yChange;
    }

    public Direction getLeft() {
        return valueOf(this.left);
    }

    public Direction getRight() {
        return valueOf(this.right);
    }
}
