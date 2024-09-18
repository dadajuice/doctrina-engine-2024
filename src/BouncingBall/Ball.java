package BouncingBall;

import Doctrina.Canvas;

import java.awt.*;
import java.util.Random;

public class Ball {
    public static final int DEFAULT_SPEED = 5;

    private int radius;
    private int speed;
    private int x;
    private int y;
    private int dx;
    private int dy;

    public Ball(int radius, int speed) {
        this.radius = radius;
        this.speed = speed;
        initializePosition();
    }

    public Ball(int radius) {
        this(radius, DEFAULT_SPEED);
    }

    public void update() {
        x += dx;
        y += dy;
        if (hasTouchVertical()) {
            dy *= -1;
        }
        if (hasTouchHorizontal()) {
            dx *= -1;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, Color.RED);
    }

    public boolean hasTouched() {
        return hasTouchVertical()
                || hasTouchHorizontal();
    }

    private void initializePosition() {
        x = randomNumber(radius * 2, 800 - radius * 2);
        y = randomNumber(radius * 2, 600 - radius * 2);
        dx = randomNumber(0, 1) == 0 ? speed : -speed;
        dy = randomNumber(0, 1) == 0 ? speed : -speed;
    }

    private boolean hasTouchVertical() {
        return y <= radius || y >= 600 - radius;
    }

    private boolean hasTouchHorizontal() {
        return x <= radius || x >= 800 - radius;
    }

    private int randomNumber(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }
}
