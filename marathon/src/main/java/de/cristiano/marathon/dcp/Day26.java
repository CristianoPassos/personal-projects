package de.cristiano.marathon.dcp;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Given a singly linked list and an integer k, remove the kth last element from the list.
 * k is guaranteed to be smaller than the length of the list.
 */
public class Day26 {

    int size;

    Element first;

    void addElement(int number) {
        final Element element = new Element(number);

        if (isNull(first)) {
            first = element;
        } else {
            Element nextPosition = first;

            while (nonNull(nextPosition.next)) {
                nextPosition = nextPosition.next;
            }

            nextPosition.next = element;
        }

        size++;
    }

    public int removeKth(int k) {
        int positionToRemove;

        if ((size / k) > 1) {
            positionToRemove = k * (size / k);
        } else {
            positionToRemove = k;
        }

        if (positionToRemove == 1) {
            int removed = first.value;
            first = first.next;
            size--;
            return removed;
        }

        if (positionToRemove == size) {
            return removeLastElement();
        }

        return removeElement(positionToRemove);
    }

    private int removeElement(int positionToRemove) {
        Element previousKth = null;
        Element currentElement = first;

        for (int index = 1; index < positionToRemove; index++) {
            previousKth = currentElement;
            currentElement = currentElement.next;
        }

        assert previousKth != null;
        previousKth.next = currentElement.next;
        size--;

        return currentElement.value;
    }

    private int removeLastElement() {
        Element currentElement = first;

        for (int index = 2; index < size; index++) {
            currentElement = currentElement.next;
        }

        int removedValue = currentElement.next.value;
        currentElement.next = null;
        size--;

        return removedValue;
    }

    static class Element {
        final int value;
        Element next;

        public Element(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }

}
