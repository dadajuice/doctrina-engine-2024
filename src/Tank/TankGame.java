package Tank;

import Doctrina.Canvas;
import Doctrina.Game;

public class TankGame extends Game {
    private GamePad gamePad;
    private Tank tank;

    @Override
    protected void initialize() {
        gamePad = new GamePad();
        tank = new Tank(gamePad);
    }

    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        tank.update();
    }

    @Override
    protected void draw(Canvas canvas) {
        tank.draw(canvas);
    }
}
