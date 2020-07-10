package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day35Test {

    private Day35 day35 = new Day35();

    @Test
    void find_shouldSucceed() {
        //Given
        int[] afterMiddle = {10, 13, 18, 25, 2, 8};
        int[] inMiddle = {13, 18, 25, 2, 8, 10};
        int[] beforeMiddle = {25, 2, 8, 10, 13, 18};

        //When
        final int pivot_afterMiddle = day35.findPivot(afterMiddle, 0, afterMiddle.length - 1);
        final int pivot_inMiddle = day35.findPivot(inMiddle, 0, afterMiddle.length - 1);
        final int pivot_beforeMiddle = day35.findPivot(beforeMiddle, 0, afterMiddle.length - 1);

        final int afterMiddleSearch = day35.find(afterMiddle, 8);
        final int inMiddleSearch = day35.find(inMiddle, 8);
        final int beforeMiddleSearch = day35.find(beforeMiddle, 8);

        //Then
        assertThat(pivot_afterMiddle, is(3));
        assertThat(pivot_inMiddle, is(2));
        assertThat(pivot_beforeMiddle, is(0));

        assertThat(afterMiddleSearch, is(5));
        assertThat(inMiddleSearch, is(4));
        assertThat(beforeMiddleSearch, is(2));
    }


}
