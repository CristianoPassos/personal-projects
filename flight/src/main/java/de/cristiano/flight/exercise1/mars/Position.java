package de.cristiano.flight.exercise1.mars;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;

@NoArgsConstructor
@EqualsAndHashCode
public class Position {

    @Getter
    public Integer X = 0;

    @Getter
    public Integer Y = 0;

    public Position(@Nonnull final Integer X, @Nonnull final Integer Y) {
        this.X = X;
        this.Y = Y;
    }

    public Position add(@Nonnull final Integer xChange, @Nonnull final Integer yChange) {
        return new Position(this.X += xChange, this.Y += yChange);
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", X, Y);
    }
}
