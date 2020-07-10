package de.cristiano.marathon.dcp;

/**
 * Given a chessboard, how would you find whether the king is threatened by the queen?
 */
class Day4 {

    /**
     * Given that no other piece stands between King and Queen.
     * Time Complexity O(1)
     * Space Complexity O(1)
     */
    boolean isCheck(int kingX, int kingY, int queenX, int queenY) {
        return kingX == queenX || kingY == queenY || Math.abs(kingX - queenX) == Math.abs(kingY - queenY);
    }
}
