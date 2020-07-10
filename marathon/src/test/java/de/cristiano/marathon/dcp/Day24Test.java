package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day24Test {

    @Test
    public void unlockedTree_shouldLock() {
        //Given
        final Day24 a = new Day24('A');
        final Day24 b = new Day24('B');
        final Day24 c = new Day24('C');
        final Day24 d = new Day24('D');
        final Day24 e = new Day24('E');

        a.left = b;
        b.parent = a;

        b.left = c;
        c.parent = b;

        b.right = d;
        d.parent = b;

        a.right = e;
        e.parent = a;

        //When
        final boolean bLock = b.lock();
        final boolean aLock = a.lock();
        final boolean cLock = c.lock();
        final boolean dLock = d.lock();
        final boolean eLock = e.lock();
        final boolean unlock = b.unlock();

        //Then
        assertThat(bLock, is(true));
        assertThat(aLock, is(false));
        assertThat(cLock, is(false));
        assertThat(dLock, is(false));
        assertThat(eLock, is(true));
        assertThat(unlock, is(true));
        assertThat(a.getDecedentLocks(), is(1));
    }
}
