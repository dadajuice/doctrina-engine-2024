package Tank;

import Doctrina.Canvas;
import Doctrina.ControllableEntity;
import Doctrina.MovementController;

import java.awt.*;

public class Tank extends ControllableEntity {
    private int cooldown = 0;

    public Tank(MovementController controller) {
        super(controller);
        setDimension(30, 30);
        setSpeed(2);
        teleport(300, 300);
    }

    public Missile fire() {
        cooldown = 40;
        return new Missile(this);
    }

    public boolean canFire() {
        return cooldown == 0;
    }

    @Override
    public void update() {
        super.update();
        moveWithController();
        if (!canFire()) {
            cooldown--;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this, Color.GREEN);

        //float alpha = (cooldown / 40f);
        //float cooldownWidth = alpha * width;
        int cooldownWidth = (cooldown * width) / 40;
        canvas.drawRectangle(x, y - 5, cooldownWidth, 2, Color.RED);

        if (hasMoved()) {
            drawHitBox(canvas);
        }
    }
}
