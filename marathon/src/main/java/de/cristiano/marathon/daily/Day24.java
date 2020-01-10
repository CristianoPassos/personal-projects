package de.cristiano.marathon.daily;

import static java.util.Objects.nonNull;

/**
 * Implement locking in a binary tree. A binary tree node can be locked or unlocked only if all of its descendants or ancestors are not locked.
 */
public class Day24 {

    final char value;
    Day24 parent;
    Day24 left;
    Day24 right;
    private boolean locked;
    private int decedentLocks = 0;

    public Day24(char value) {
        this.value = value;
    }

    boolean lock() {
        if (alreadyLocked()) {
            return false;
        }

        locked = true;
        if (nonNull(parent)) {
            parent.incrementParentsLock();
        }

        return true;
    }

    private void incrementParentsLock() {
        if (nonNull(parent)) {
            parent.incrementParentsLock();
        }

        decedentLocks++;
    }

    private void decrementParentsLock() {
        if (nonNull(parent)) {
            parent.decrementParentsLock();
        }

        decedentLocks--;
    }

    private boolean alreadyLocked() {
        return decedentLocks > 0 || (parent != null && parent.isAnyParentLocked());
    }

    private boolean isAnyParentLocked() {
        if (parent == null || locked) {
            return locked;
        }

        return parent.isAnyParentLocked();
    }

    boolean unlock() {
        if (alreadyLocked()) {
            return false;
        }

        locked = true;
        if (nonNull(parent)) {
            parent.decrementParentsLock();
        }
        return true;
    }

    public int getDecedentLocks() {
        return decedentLocks;
    }

    @Override
    public String toString() {
        return Character.toString(value);
    }
}
