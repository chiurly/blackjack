import game.Game;

public class Launcher {
    public static void main(String[] args) {
        Game game = Game.getInstance();
        game.startGameLoop();
    }
}
