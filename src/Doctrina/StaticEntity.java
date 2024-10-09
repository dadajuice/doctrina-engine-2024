package Doctrina;

import java.awt.*;

public abstract class StaticEntity {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public abstract void draw(Canvas canvas);

    public void teleport(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean intersectWith(StaticEntity entity) {
        return getBounds().intersects(entity.getBounds());
    }

    protected Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
