public class Game {
    private String[][] GAME_BOARD = { { "     ", "     ", "     " }, { "     ", "     ", "     " },
            { "     ", "     ", "     " } };
    private String CURRENT_PLAYER = "X";
    private String WINNER = null;
    private final String PLAYER_1_MOVE = "X";
    private final String PLAYER_2_MOVE = "Y";

    private int MOVE_AMT = 0;
    private int MAX_MOVE_AMT = 9;

    public Game() {
    }

    public boolean setMove(int x, int y, String playerName) {
        String input = "   " + playerName + "   ";
        int X_COR = x - 1;
        int Y_COR = y - 1;

        // Check player turn
        // if (!playerName.equals(CURRENT_PLAYER))
        // return false;
        isPlayerTurn(playerName);

        // Check selected position
        if (GAME_BOARD[X_COR][Y_COR].equals(PLAYER_1_MOVE) || GAME_BOARD[x - 1][y - 1].equals(PLAYER_2_MOVE))
            return false;

        // Set value for selected position
        GAME_BOARD[X_COR][Y_COR] = input;

        checkWin(X_COR, Y_COR, input);
        setNextPlayer();

        MOVE_AMT++;

        return true;
    }

    private String checkWin(int x, int y, String input) {
        checkDraw();
        checkRow(x, input);
        checkColumn(y, input);
        checkDiagonal(x, y, input);

        return WINNER;
    }

    private void checkRow(int x, String input) {
        if (GAME_BOARD[x][0].equals(input) && GAME_BOARD[x][1].equals(input) && GAME_BOARD[x][2].equals(input))
            WINNER = CURRENT_PLAYER;
    }

    private void checkColumn(int y, String input) {
        if (GAME_BOARD[0][y].equals(input) && GAME_BOARD[1][y].equals(input) && GAME_BOARD[2][y].equals(input))
            WINNER = CURRENT_PLAYER;
    }

    private void checkDiagonal(int x, int y, String input) {
        if (x == y || x == (3 - y)) {
            if (GAME_BOARD[0][0].equals(input) && GAME_BOARD[2][2].equals(input))
                WINNER = CURRENT_PLAYER;
            if (GAME_BOARD[0][2].equals(input) && GAME_BOARD[2][0].equals(input))
                WINNER = CURRENT_PLAYER;
        }
    }

    private void checkDraw() {
        if (MOVE_AMT == MAX_MOVE_AMT)
            WINNER = "Draw";
    }

    private void setNextPlayer() {
        if (CURRENT_PLAYER.equals("X"))
            CURRENT_PLAYER = "O";
        else if (CURRENT_PLAYER.equals("O"))
            CURRENT_PLAYER = "X";
    }

    private boolean isPlayerTurn(String playerName) {
        if (playerName.equals(CURRENT_PLAYER))
            return true;
        return false;
    }

    public String getWinner() {
        return WINNER;
    }

    @Override
    public String toString() {
        String display = "";
        for (String[] row : GAME_BOARD) {
            for (String cell : row) {
                display += "|" + cell ;
            }
            display += "|\n-------------------\n";
        }

        return display;
    }

}
