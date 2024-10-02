package Tank;

import Doctrina.Canvas;
import Doctrina.ControllableEntity;
import Doctrina.MovementController;

import java.awt.*;

public class Tank extends ControllableEntity {
    public Tank(MovementController controller) {
        super(controller);
        setDimension(30, 30);
        setSpeed(2);
        teleport(300, 300);
    }

    @Override
    public void update() {
        moveWithController();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this, Color.GREEN);
    }
}
