import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameWindow extends JFrame {

    private static final int SLEEP = 25;

    private JPanel panel;
    private boolean playing = true;
    private BufferedImage bufferedImage;
    private Graphics2D bufferEngine;
    private long before;
    private int score;
    private Ball ball;

    public GameWindow() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Bouncing Balls");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setState(JFrame.NORMAL);

        panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        add(panel);
    }

    public void start() {
        setVisible(true);
        before = System.currentTimeMillis();
        ball = new Ball(25);

        while (playing) {
            bufferedImage = new BufferedImage(800, 600,
                    BufferedImage.TYPE_INT_RGB);
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            bufferEngine = bufferedImage.createGraphics();
            bufferEngine.setRenderingHints(hints);

            update();
            drawOnBuffer();
            drawBufferOnScreen();

            long sleep = SLEEP - (System.currentTimeMillis() - before);
            if (sleep < 4) {
                sleep = 4;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            before = System.currentTimeMillis();
        }
    }

    private void update() {
        ball.update();
        if (ball.hasTouched()) {
            score += 10;
        }
    }

    private void drawOnBuffer() {
        ball.draw(bufferEngine);

        bufferEngine.setPaint(Color.WHITE);
        bufferEngine.drawString("Score: " + score, 10, 20);
    }

    private void drawBufferOnScreen() {
        Graphics2D graphics = (Graphics2D) panel.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, panel);
        Toolkit.getDefaultToolkit().sync();
        graphics.dispose();
    }
}
