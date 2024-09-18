package Doctrina;

import java.awt.event.KeyEvent;

public class MovementController extends Controller {

    private int upKey = KeyEvent.VK_UP;
    private int downKey = KeyEvent.VK_DOWN;
    private int leftKey = KeyEvent.VK_LEFT;
    private int rightKey = KeyEvent.VK_RIGHT;

    public MovementController() {
        bindKey(upKey);
        bindKey(downKey);
        bindKey(leftKey);
        bindKey(rightKey);
    }

    public boolean isLeftPressed() {
        return isKeyPressed(leftKey);
    }

    public boolean isRightPressed() {
        return isKeyPressed(rightKey);
    }

    public boolean isUpPressed() {
        return isKeyPressed(upKey);
    }

    public boolean isDownPressed() {
        return isKeyPressed(downKey);
    }

    public boolean isMoving() {
        return isDownPressed() || isUpPressed()
                || isLeftPressed() || isRightPressed();
    }

    public void setDownKey(int keyCode) {
        unbindKey(downKey);
        bindKey(keyCode);
        this.downKey = keyCode;
    }

    public void setUpKey(int keyCode) {
        unbindKey(upKey);
        bindKey(keyCode);
        this.upKey = keyCode;
    }

    public void setLeftKey(int keyCode) {
        unbindKey(leftKey);
        bindKey(keyCode);
        this.leftKey = keyCode;
    }

    public void setRightKey(int keyCode) {
        unbindKey(rightKey);
        bindKey(keyCode);
        this.rightKey = keyCode;
    }
}
