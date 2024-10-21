package Doctrina;

import java.awt.*;

public class Collision {
    private final MovableEntity entity;

    public Collision(MovableEntity entity) {
        this.entity = entity;
    }

    public int getAllowedSpeed(Direction direction) {
        return switch (direction) {
            case LEFT -> getAllowedLeftSpeed();
            case RIGHT -> getAllowedRightSpeed();
            case UP -> getAllowedUpSpeed();
            case DOWN -> getAllowedDownSpeed();
        };
    }

    private int getAllowedUpSpeed() {
        return distance(other -> entity.y - (other.y + other.height));
    }

    private int getAllowedDownSpeed() {
        return distance(other -> other.y - (entity.y + entity.height));
    }

    private int getAllowedLeftSpeed() {
        return distance(other -> entity.x - (other.x + other.width));
    }

    private int getAllowedRightSpeed() {
        return distance(other -> other.x - (entity.x + entity.width));
    }

    private int distance(DistanceCalculator calculator) {
        Rectangle hitBox = entity.getHitBox();
        int allowedDistance = entity.getSpeed();
        for (StaticEntity other : CollidableRepository.getInstance()) {
            if (hitBox.intersects(other.getBounds())) {
                allowedDistance = Math.min(allowedDistance, calculator.calculateWith(other));
            }
        }
        return allowedDistance;
    }

    public interface DistanceCalculator {
        int calculateWith(StaticEntity other);
    }
}
