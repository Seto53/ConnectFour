import java.util.Random;
import java.util.Scanner;

public class ConnectFour {

    public static void main(String[] args) {
        Random rand = new Random();
        int gameMode = 0;
        Scanner scanner = new Scanner(System.in);
        ConnectFourGame game = new ConnectFourGame();
        while (game.getGameState() == GameState.PLAYING) {
            System.out.println(game);
            System.out.println();
            switch (game.nextCellValue()) {
                case RED -> System.out.println("Red turn");
                case YELLOW -> System.out.println("Yellow turn");
            }
            CellValue current = game.nextCellValue();
            do {
                try {
                    if (gameMode == 0) {
                        System.out.print("Choose your move: ");
                        int move = Integer.parseInt(scanner.next());
                        game.play(move - 1);
                    } else {
                        int move = rand.nextInt(7);
                        game.play(move);
                    }
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
            case RED_WIN -> System.out.println("Red wins!");
            case YELLOW_WIN -> System.out.println("Yellow wins!");
            case DRAW -> System.out.println("Draw");
        }
    }
}
