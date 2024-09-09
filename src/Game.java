import java.awt.*;

public abstract class Game {

    private static final int SLEEP = 25;
    private boolean playing = true;
    private long before;
    private RenderingEngine renderingEngine;

    protected abstract void initialize();
    protected abstract void update();
    protected abstract void drawOnBuffer(Graphics2D bufferEngine);

    public Game() {
        renderingEngine = new RenderingEngine();
        initialize();
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

    private void updateSyncTime() {
        before = System.currentTimeMillis();
    }
}
