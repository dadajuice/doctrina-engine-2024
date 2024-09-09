import javax.swing.*;
import java.awt.*;

public class Game {

    private static final int SLEEP = 25;
    private boolean playing = true;
    private long before;

    private RenderingEngine renderingEngine;
    private int score;
    private Ball ball;

    public Game() {
        renderingEngine = new RenderingEngine();
        ball = new Ball(25);
    }

    public void start() {
        renderingEngine.start();
        updateSyncTime();
        while (playing) {
            update();
            drawOnBuffer(renderingEngine.buildBuffer());
            renderingEngine.drawBufferOnScreen();
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(getSleepTime());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        updateSyncTime();
    }

    private long getSleepTime() {
        long sleep = SLEEP - (System.currentTimeMillis() - before);
        if (sleep < 4) {
            sleep = 4;
        }
        return sleep;
    }

    private void update() {
        ball.update();
        if (ball.hasTouched()) {
            score += 10;
        }
    }

    private void drawOnBuffer(Graphics2D bufferEngine) {
        ball.draw(bufferEngine);

        bufferEngine.setPaint(Color.WHITE);
        bufferEngine.drawString("Score: " + score, 10, 20);
    }

    private void updateSyncTime() {
        before = System.currentTimeMillis();
    }
}
