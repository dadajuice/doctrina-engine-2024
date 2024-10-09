package Tank;

import Doctrina.Canvas;
import Doctrina.StaticEntity;

import java.awt.*;

public class Brick extends StaticEntity {
    public Brick(int x, int y) {
        setDimension(16, 16);
        teleport(x, y);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this, Color.WHITE);
    }
}
