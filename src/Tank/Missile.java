package Tank;

import Doctrina.Canvas;
import Doctrina.CollidableRepository;
import Doctrina.Direction;
import Doctrina.MovableEntity;

import java.awt.*;

public class Missile extends MovableEntity {
    private final Direction tankDirection;

    public Missile(Tank tank) {
        tankDirection = tank.getDirection();
        initialize(tank);
        CollidableRepository.getInstance().registerEntity(this);
    }

    private void initialize(Tank tank) {
        setSpeed(5);

        if (tankDirection == Direction.RIGHT) {
            setDimension(8, 4);

            // x = tankX + tankWidth
            // y = tankY + tankHeight/2 + missileHeight/2
            teleport(
                    tank.getX() + tank.getWidth() + 1,
                    tank.getY() + tank.getHeight()/2 - height/2
            );
        } else if (tankDirection == Direction.LEFT) {
            setDimension(8, 4);
            teleport(tank.getX() - 9, tank.getY() + 15 - 2);
        } else if (tankDirection == Direction.DOWN) {
            setDimension(4, 8);
            teleport(tank.getX() + 15 - 2, tank.getY() + 30 + 1);
        }  else if (tankDirection == Direction.UP) {
            setDimension(4, 8);
            teleport(tank.getX() + 15 - 2, tank.getY() - 9);
        }
    }

    @Override
    public void update() {
        move(tankDirection);
        if (x >= 820) {
            x = -20;
        }
        if (y >= 620) {
            y = -20;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this, Color.YELLOW);
    }
}
