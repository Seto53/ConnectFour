public class ConnectFourGame {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private final CellValue[] board;
    private GameState gameState;
    private int level;

    public ConnectFourGame() {
        board = new CellValue[42];
        for (int i = 0; i < 42; i++) {
            board[i] = CellValue.EMPTY;
        }
        gameState = GameState.PLAYING;
    }

    public CellValue nextCellValue() {
        return (level % 2 == 0) ? CellValue.RED : CellValue.YELLOW;
    }

    public void play(int m) {
        if (m < 0 || m > 6) {
            throw new IllegalArgumentException("Illegal position");
        }
        if (gameState != GameState.PLAYING) {
            throw new IllegalStateException("Game finished");
        }
        for (int i = 5; i >= 0; i--) {
            if (board[m + (i * 7)] == CellValue.EMPTY) {
                board[m + (i * 7)] = nextCellValue();
                updateGameState(m + (i * 7));
                level++;
                return;
            }
        }
        throw new IllegalStateException("Column is full");
    }

    private void updateGameState(int m) {

        if (level == 41) {
            gameState = GameState.DRAW;
            return;
        }

        int r = (int) Math.ceil((double) (m + 1) / 7);
        int count = 0;

        //Check horizontal
        for (int i = (r - 1) * 7; i < r * 7; i++) {
            if (board[i] == board[m]) {
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                setWinner(board[m]);
                return;
            }
        }

        int c = (m + 1) % 7;
        if (c == 0) {
            c = 7;
        }
        count = 0;

        //Check vertical
        for (int i = c - 1; i < (c - 1) + 36; i = i + 7) {
            if (board[i] == board[m]) {
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                setWinner(board[m]);
                return;
            }
        }

        //TODO
        //Check diagonal
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 42; i++) {
            if (i % 7 == 0) {
                res.append("\n");
            } else {
                res.append("|");
            }
            if (board[i] == CellValue.RED) {
                res.append(ANSI_RED + "\uD83D\uDD34" + ANSI_RESET);
            } else if (board[i] == CellValue.YELLOW) {
                res.append(ANSI_YELLOW + "\uD83D\uDD34" + ANSI_RESET);
            } else {
                res.append(ANSI_WHITE + "\uD83D\uDD34" + ANSI_RESET);
            }
        }
        return res.toString();
    }

    private void setWinner(CellValue cell) {
        if (cell == CellValue.RED) {
            gameState = GameState.RED_WIN;
        } else {
            gameState = GameState.YELLOW_WIN;
        }
    }

    public GameState getGameState() {
        return gameState;
    }
}
