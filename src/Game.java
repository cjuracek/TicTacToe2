/**
 * Created by cole.juracek on 6/12/2017.
 */
public class Game {

    private Board board = new Board();
    public enum GameState {X_MOVE, O_MOVE, X_WIN, O_WIN, DRAW}
    private GameState gameState;

    public Game() {
        gameState = GameState.X_MOVE;
    }

    public Game(Board board) {
        this.board = board;
        gameState = GameState.O_MOVE;
    }

    public boolean isValidMove(int row, int col) {
        if((row > 2) || (row < 0) || (col > 2) || (col < 0)) {
            return false;
        }
        if((board.getGrid()[row][col] == 'X') || (board.getGrid()[row][col] == 'O')) {
            return false;
        }
        return true;
    }

    /**
     * Adds the current piece to the board, checks for finishing conditions
     * in game state, then switches players.
     * @param row: the x position (0-2) of the piece to add
     * @param col: the y position (0-2) of the piece to add
     */
    public void addPiece(int row, int col) {
        char pieceToAdd = (gameState == GameState.X_MOVE) ? 'X' : 'O';
        board.getGrid()[row][col] = pieceToAdd;
        board.setNumPieces(board.getNumPieces() + 1);
    }

    /**
     * Switch players with ternary operator.
     */
    public void switchPlayers() {
        gameState = (gameState == GameState.X_MOVE) ? GameState.O_MOVE : GameState.X_MOVE;
    }

    /**
     * Checks to see whether a win or draw has occurred, and updates
     * the game state appropriately
     */
    public boolean isGameOver() {
        if(hasWinOccurred()) {
            gameState = (gameState == GameState.X_MOVE) ? GameState.X_WIN : GameState.O_WIN;
            return true;
        }
        else if(hasDrawOccurred()) {
            gameState = GameState.DRAW;
            return true;
        }
        else {
            return false;
        }
    }

    // ****************************
    // START LOGIC FOR WIN CHECKING

    /**
     * Check for a win by hard-testing all possibilities
     * (2 diagonals, 3 horizontals, 3 verticals)
     */
    private boolean hasWinOccurred() {
        if(board.getNumPieces() < 5) {
            return false;
        }
        else {
            return (isRowWin() || isColWin() || isDiagWin());
        }
    }

    private boolean isRowWin() {
        return (isRowWin(0) || isRowWin(1) || isRowWin(2));
    }

    private boolean isRowWin(int rowToCheck) {
        char[][] grid = board.getGrid();
        return ((grid[rowToCheck][0] == grid[rowToCheck][1]) &&
                 grid[rowToCheck][1] == grid[rowToCheck][2]);
    }

    private boolean isColWin() {
        return (isColWin(0) || isColWin(1) || isColWin(2));
    }

    private boolean isColWin(int colToCheck) {
        char[][] grid = board.getGrid();
        return ((grid[0][colToCheck] == grid[1][colToCheck]) &&
                 grid[1][colToCheck] == grid[2][colToCheck]);
    }

    private boolean isDiagWin() {
        char[][] grid = board.getGrid();
        boolean diagOne = ((grid[0][0] == grid[1][1]) && (grid[1][1] == grid[2][2]));
        boolean diagTwo = ((grid[0][2] == grid[1][1]) && (grid[1][1] == grid[2][0]));

        return (diagOne || diagTwo);
    }

    // END LOGIC FOR WIN CHECKING
    // **************************

    /**
     * Check to see if a draw has occurred by checking the number
     * of pieces on the board
     * @return
     */
    private boolean hasDrawOccurred() {
        return (board.getNumPieces() == 9);
    }

    /**
     * Print the result of the game to the screen
     */
    public void displayGameResult() {
        switch (gameState) {
            case X_WIN:
                System.out.println("X is the winner! Congratulations!"); break;
            case O_WIN:
                System.out.println("O is the winner! Congratulations!"); break;
            case DRAW:
                System.out.println("The game is a draw!"); break;
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board brd) {
        board = brd;
    }
}
