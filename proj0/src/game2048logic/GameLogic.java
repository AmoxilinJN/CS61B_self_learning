package game2048logic;

import game2048rendering.Side;
import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /** Moves the given tile up as far as possible, subject to the minR constraint.
     *
     * @param board the current state of the board
     * @param r     the row number of the tile to move up
     * @param c -   the column number of the tile to move up
     * @param minR  the minimum row number that the tile can land in, e.g.
     *              if minR is 2, the moving tile should move no higher than row 2.
     * @return      if there is a merge, returns the 1 + the row number where the merge occurred.
     *              if no merge occurs, then return minR.
     */
    public static int moveTileUpAsFarAsPossible(int[][] board, int r, int c, int minR) {
        // TODO: Fill this in in tasks 2, 3, 4
        while(r!=0&&board[r-1][c]==0&&r>minR){
            board[r-1][c]=board[r][c];
            board[r][c]=0;
            r-=1;
        }

        if(r!=0&&board[r][c]==board[r-1][c]&&r>minR){
            board[r-1][c]*=2;
            board[r][c]=0;
            return r;
        }else{
            return minR;
        }


    }

    /**
     * Modifies the board to simulate the process of tilting column c
     * upwards.
     *
     * @param board     the current state of the board
     * @param c         the column to tilt up.
     */
    public static void tiltColumn(int[][] board, int c) {
        // TODO: fill this in in task 5
        int x,minR=0;
        for(int r=1;r<board.length;r++){
            if((x=GameLogic.moveTileUpAsFarAsPossible(board,r,c,minR))!=minR){
                minR=x;
            }
        }

        return;
    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board     the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        // TODO: fill this in in task 6
        for(int c=0;c<board.length;c++){
            GameLogic.tiltColumn(board,c);
        }

        return;
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        // TODO: fill this in in task 7
        if (side == Side.NORTH) {
            GameLogic.tiltUp(board);
            return;
        } else if (side == Side.EAST) {
            MatrixUtils.rotateLeft(board);
            GameLogic.tiltUp(board);
            MatrixUtils.rotateRight(board);
            return;
        } else if (side == Side.SOUTH) {
            MatrixUtils.rotateLeft(board);
            MatrixUtils.rotateLeft(board);
            GameLogic.tiltUp(board);
            MatrixUtils.rotateRight(board);
            MatrixUtils.rotateRight(board);
            return;
        } else if (side == Side.WEST) {
            MatrixUtils.rotateRight(board);
            GameLogic.tiltUp(board);
            MatrixUtils.rotateLeft(board);
            return;
        } else {
            System.out.println("Invalid side specified");
        }
    }
}
