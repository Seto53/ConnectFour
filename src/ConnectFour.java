public class ConnectFour {

    public static void main(String[] args){
        ConnectFourGame game = new ConnectFourGame();
        System.out.println(game.gameState);
        System.out.println(game);
        game.play(3);
        System.out.println(game.gameState);
        System.out.println(game);
        game.play(6);
        System.out.println(game.gameState);
        System.out.println(game);
        game.play(0);
        System.out.println(game.gameState);
        System.out.println(game);
        game.play(6);
        System.out.println(game.gameState);
        System.out.println(game);
        game.play(1);
        System.out.println(game.gameState);
        System.out.println(game);
        game.play(6);
        System.out.println(game.gameState);
        System.out.println(game);
        game.play(2);
        System.out.println(game.gameState);
        System.out.println(game);
    }
}
