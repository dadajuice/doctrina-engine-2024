package Viking;

import Doctrina.Canvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class World {
    private static final String MAP_PATH = "images/demo.png";

    private Image background;

    public void load() {
        try {
            background = ImageIO.read(
                    getClass().getClassLoader().getResourceAsStream(MAP_PATH)
            );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            //System.out.println("TOUJOURS EXECUTER");
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawImage(background, 0, -64);
    }
}
