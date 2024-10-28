package Viking;

import Doctrina.Canvas;
import Doctrina.ControllableEntity;
import Doctrina.Direction;
import Doctrina.MovementController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends ControllableEntity {
    private static final String SPRITE_PATH = "images/player.png";
    private static final int ANIMATION_SPEED = 8;

    private BufferedImage image;
    private Image[] rightFrames;
    private Image[] leftFrames;
    private Image[] upFrames;
    private Image[] downFrames;

    private int currentAnimationFrame = 1;
    private int nextFrame = ANIMATION_SPEED;
    private int animationStep = 1; // 1 or -1 (reverse) for animation cycle

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        setSpeed(3);
        load();
    }

    private void load() {
        loadSpriteSheet();
        loadAnimationFrames();
    }

    private void loadAnimationFrames() {
        downFrames = new Image[3];
        downFrames[0] = image.getSubimage(0, 128, width, height);
        downFrames[1] = image.getSubimage(32, 128, width, height);
        downFrames[2] = image.getSubimage(64, 128, width, height);

        leftFrames = new Image[3];
        leftFrames[0] = image.getSubimage(0, 160, width, height);
        leftFrames[1] = image.getSubimage(32, 160, width, height);
        leftFrames[2] = image.getSubimage(64, 160, width, height);

        rightFrames = new Image[3];
        rightFrames[0] = image.getSubimage(0, 192, width, height);
        rightFrames[1] = image.getSubimage(32, 192, width, height);
        rightFrames[2] = image.getSubimage(64, 192, width, height);

        upFrames = new Image[3];
        upFrames[0] = image.getSubimage(0, 224, width, height);
        upFrames[1] = image.getSubimage(32, 224, width, height);
        upFrames[2] = image.getSubimage(64, 224, width, height);
    }

    private void loadSpriteSheet() {
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update() {
        super.update();
        moveWithController();

        if (hasMoved()) {
            --nextFrame;
            if (nextFrame == 0) {
                currentAnimationFrame += animationStep;
                if (currentAnimationFrame == 0 || currentAnimationFrame >= leftFrames.length - 1) {
                    animationStep *= -1;
                }
                nextFrame = ANIMATION_SPEED;
            }
        } else {
            currentAnimationFrame = 1; // Idle state
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (getDirection() == Direction.DOWN) {
            canvas.drawImage(downFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.UP) {
            canvas.drawImage(upFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.RIGHT) {
            canvas.drawImage(rightFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.LEFT) {
            canvas.drawImage(leftFrames[currentAnimationFrame], x, y);
        }
    }
}
