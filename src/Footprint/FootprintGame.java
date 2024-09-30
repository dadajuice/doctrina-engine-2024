package Footprint;

import Doctrina.Canvas;
import Doctrina.Game;

import java.awt.*;

public class FootprintGame extends Game {
    private GamePad gamePad;
    private Player player;

    @Override
    protected void initialize() {
        gamePad = new GamePad();
        player = new Player(gamePad);
    }

    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        player.update();
    }

    @Override
    protected void draw(Canvas canvas) {
        canvas.drawRectangle(0, 0, 800, 600, Color.BLUE);
        player.draw(canvas);
    }
}
