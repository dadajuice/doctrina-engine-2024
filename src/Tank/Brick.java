package Tank;

import Doctrina.Canvas;
import Doctrina.CollidableRepository;
import Doctrina.StaticEntity;

import java.awt.*;

public class Brick extends StaticEntity {
    public Brick(int x, int y) {
        setDimension(16, 16);
        teleport(x, y);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this, Color.WHITE);
    }
}
