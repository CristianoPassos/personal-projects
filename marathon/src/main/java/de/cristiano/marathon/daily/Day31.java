package de.cristiano.marathon.daily;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day31 {

    List<Operation> editDistance(String one, String two) {
        final List<Operation> operations = new ArrayList<>();
        final int diff = one.length() - two.length();

        if (diff > 0) {
            final int offset = one.length() - diff;
            for (int index = offset; index < one.length(); index++) {
                operations.add(new Operation(Operation.Value.DELETE, one.charAt(index), index));
            }

            operations.addAll(calculateDifference(one, two, offset));

        } else if (diff < 0) {
            final int offset = two.length() + diff;
            for (int index = offset; index < two.length(); index++) {
                operations.add(new Operation(Operation.Value.INSERT, two.charAt(index), index));
            }

            operations.addAll(calculateDifference(one, two, offset));

        } else {
            operations.addAll(calculateDifference(one, two, one.length()));
        }

        return operations;
    }

    private List<Operation> calculateDifference(String one, String two, int upperLimit) {
        final List<Operation> operations = new ArrayList<>();

        for (int index = 0; index < upperLimit; index++) {
            if (one.charAt(index) != two.charAt(index)) {
                operations.add(new Operation(Operation.Value.SUBSTITUTE, two.charAt(index), index));
            }
        }

        return operations;
    }

    static class Operation {
        final Value value;
        final Character character;
        final int position;

        public Operation(Value value, Character character, int position) {
            this.value = value;
            this.character = character;
            this.position = position;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Operation operation = (Operation) o;
            return position == operation.position &&
                    value == operation.value &&
                    character.equals(operation.character);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, character, position);
        }

        @Override
        public String
        toString() {
            return "Operation{" +
                    "value=" + value +
                    ", character=" + character +
                    ", position=" + position +
                    '}';
        }

        enum Value {INSERT, DELETE, SUBSTITUTE}
    }
}
