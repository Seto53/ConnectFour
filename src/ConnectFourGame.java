public class ConnectFourGame {

    private final CellValue[] board;

    private int level;

    public GameState gameState;

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

    public boolean play(int m) {
        if (m < 0 || m > 6) {
            throw new IllegalStateException("Illegal position");
        }
        if (gameState != GameState.PLAYING) {
            throw new IllegalStateException("Game finished");
        }
        for (int i = 5; i >= 0; i--) {
            if (board[m + (i * 7)] == CellValue.EMPTY) {
                board[m + (i * 7)] = nextCellValue();
                updateGameState(m + (i * 7));
                level++;
                return true;
            }
        }
        return false;
    }

    public void updateGameState(int m) {
        //FIXME
        //TODO
        if (isFull()) {
            gameState = GameState.DRAW;
            return;
        }
        int r = (int) Math.ceil((double) m / 7);

        if (m + 3 <= (r * 7 - 1)) {
            int c = 0;
            for (int i = m + 1; i < m + 4; i++) {
                if (board[i] == board[m]) {
                    c++;
                }
                else{
                    break;
                }
            }
            if (c == 3) {
                setWinner(board[m]);
                return;
            }
        }

        if (m - 3 >= ((r - 1) * 7)) {
            int c = 0;
            for (int i = m - 1; i > m - 4; i--) {
                if (board[i] == board[m]) {
                    c++;
                }
                else{
                    break;
                }
            }
            if (c == 3) {
                setWinner(board[m]);
                return;
            }
        }

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
                res.append("X");
            } else if (board[i] == CellValue.YELLOW) {
                res.append("O");
            } else {
                res.append(" ");
            }
        }
        return res.toString();
    }

    public boolean isFull() {
        for (int i = 0; i < 42; i++) {
            if (board[i] == CellValue.EMPTY) {
                return false;
            }
        }
        return true;
    }

    public void setWinner(CellValue cell) {
        if (cell == CellValue.RED) {
            gameState = GameState.RED_WIN;
        } else {
            gameState = GameState.YELLOW_WIN;
        }
    }
}
