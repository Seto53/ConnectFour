import java.util.Scanner;

public class ConnectFour {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ConnectFourGame game = new ConnectFourGame();
        while (game.getGameState() == GameState.PLAYING) {
            System.out.println(game);
            System.out.println();
            switch (game.nextCellValue()) {
                case RED -> System.out.println("X turn");
                case YELLOW -> System.out.println("O turn");
            }
            CellValue current = game.nextCellValue();
            do {
                try {
                    int move = Integer.parseInt(scanner.next());
                    game.play(move - 1);
                } catch (IllegalArgumentException e) {
                    System.out.println("Choose position between 1-7.");
                } catch (IllegalStateException e) {
                    System.out.println("Column is full.");
                }
            } while (current == game.nextCellValue());
        }
        System.out.println(game);
        System.out.println();
        switch (game.getGameState()) {
            case RED_WIN -> System.out.println("X wins!");
            case YELLOW_WIN -> System.out.println("O wins!");
            case DRAW -> System.out.println("Draw");
        }
    }
}
