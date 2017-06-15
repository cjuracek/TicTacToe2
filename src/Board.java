/**
 * Created by cole.juracek on 6/12/2017.
 */
public class Board {

    private char[][] grid = new char[3][3];
    private int numPieces = 0;

    public Board() {

    }

    public Board(char[][] grid) {
        this.grid = grid;
    }

    @Override
    public String toString() {
        String boardString = "";

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                boardString += "[" + grid[i][j] + "]";
            }
            boardString += "\n";
        }

        return boardString;
    }

    public char[][] getGrid() {
        return grid;
    }

    public int getNumPieces() {
        return numPieces;
    }

    public void setNumPieces(int num) {
        numPieces = num;
    }
}
