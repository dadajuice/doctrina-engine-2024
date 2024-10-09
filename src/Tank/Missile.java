package Tank;

import Doctrina.Canvas;
import Doctrina.Direction;
import Doctrina.MovableEntity;

import java.awt.*;

public class Missile extends MovableEntity {
    private final Direction tankDirection;

    public Missile(Tank tank) {
        setDimension(5, 5);
        setSpeed(5);
        teleport(tank.getX(), tank.getY());
        
        tankDirection = tank.getDirection();
    }

    @Override
    public void update() {
        move(tankDirection);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this, Color.YELLOW);
    }
}
