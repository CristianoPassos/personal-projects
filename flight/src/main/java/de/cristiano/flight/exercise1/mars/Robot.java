package de.cristiano.flight.exercise1.mars;
import lombok.Getter;

public class Robot {

    @Getter
    private Direction direction = Direction.NORTH;

    @Getter
    private Position position = new Position();

    public void moveForward() {
        this.position = position.add(direction.getXChange(), direction.getYChange());
    }

    public void turnLeft() {
        this.direction = direction.getLeft();
    }

    public void turnRight() {
        this.direction = direction.getRight();
    }

}
