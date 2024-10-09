package Tank;

import Doctrina.Canvas;
import Doctrina.Game;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TankGame extends Game {
    private GamePad gamePad;
    private Tank tank;
    private ArrayList<Missile> missiles;
    private ArrayList<Brick> bricks;

    @Override
    protected void initialize() {
        gamePad = new GamePad();
        tank = new Tank(gamePad);
        missiles = new ArrayList<>();

        bricks = new ArrayList<>();
        bricks.add(new Brick(500, 100));
        bricks.add(new Brick(500, 116));
        bricks.add(new Brick(500, 132));
        bricks.add(new Brick(484, 148));
        bricks.add(new Brick(500, 164));
    }

    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        if (gamePad.isFirePressed() && tank.canFire()) {
            missiles.add(tank.fire());
        }
        tank.update();
        for (Missile missile : missiles) {
            missile.update();
        }
    }

    @Override
    protected void draw(Canvas canvas) {
        tank.draw(canvas);
        for (Missile missile : missiles) {
            missile.draw(canvas);
        }
        for (Brick brick : bricks) {
            brick.draw(canvas);
        }
    }
}
