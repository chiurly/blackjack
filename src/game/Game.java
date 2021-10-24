package game;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Canvas;
import java.awt.Dimension;

public class Game {

    public static final double TARGET_UPDATE_RATE = 1.0 / 60.0;
    public static final Dimension CANVAS_SIZE = new Dimension(640, 480);
    private static Game instance;

    private JFrame frame;
    private Canvas canvas;
    private Input input;
    private GameScene gameScene;
    private boolean running;

    private Game() {
        frame = new JFrame("Blackjack");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setPreferredSize(CANVAS_SIZE);
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        canvas.createBufferStrategy(2);

        input = new Input();
        canvas.addMouseListener(input);

        gameScene = new GameScene();

        frame.setVisible(true);
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void startGameLoop() {
        running = true;
        long startTime;
        long prevStartTime = System.currentTimeMillis();
        double deltaTimeAccumulator = 0.0;

        while (running) {
            startTime = System.currentTimeMillis();
            double deltaTime = (startTime - prevStartTime) / 1000.0;

            deltaTimeAccumulator += deltaTime;
            prevStartTime = startTime;

            if (deltaTimeAccumulator >= TARGET_UPDATE_RATE) {
                while (deltaTimeAccumulator >= TARGET_UPDATE_RATE) {
                    gameScene.update();
                    deltaTimeAccumulator -= TARGET_UPDATE_RATE;
                }
            }

            gameScene.render();
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GameScene getScene() {
        return gameScene;
    }
}
