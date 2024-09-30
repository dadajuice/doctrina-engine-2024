package Footprint;

import Doctrina.Canvas;

import java.awt.*;

public class Player {
    private GamePad gamePad;
    private int x;
    private int y;
    private int speed;

    public Player(GamePad gamePad) {
        this.gamePad = gamePad;
        x = 200;
        y = 200;
        speed = 3;
    }

    public void update() {
        if (gamePad.isDownPressed()) {
            y += speed;
        } else if (gamePad.isUpPressed()) {
            y -= speed;
        } else if (gamePad.isLeftPressed()) {
            x -= speed;
        } else if (gamePad.isRightPressed()) {
            x += speed;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawRectangle(x, y, 20, 60, Color.WHITE);
    }

    public Footprint layFootprint() {
        return new Footprint(x, y);
    }
}