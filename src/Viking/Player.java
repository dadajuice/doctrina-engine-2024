package Viking;

import Doctrina.Canvas;
import Doctrina.ControllableEntity;
import Doctrina.MovementController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends ControllableEntity {
    private static final String SPRITE_PATH = "images/player.png";

    private BufferedImage image;
    private Image[] rightFrames;
    private Image[] leftFrames;
    private Image[] upFrames;
    private Image[] downFrames;

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
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawImage(downFrames[1], x, y);
    }
}
