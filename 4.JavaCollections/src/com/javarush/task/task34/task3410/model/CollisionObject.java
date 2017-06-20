package com.javarush.task.task34.task3410.model;

/**
 * Created by ShkerdinVA on 20.06.2017.
 */
public abstract class CollisionObject extends GameObject{
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        boolean collision = false;
        switch (direction) {
            case UP:
                if (this.getX() == gameObject.getX() && this.getY() - Model.FIELD_SELL_SIZE == gameObject.getY()) collision = true;
                break;
            case DOWN:
                if (this.getX() == gameObject.getX() && this.getY() + Model.FIELD_SELL_SIZE == gameObject.getY()) collision = true;
                break;
            case LEFT:
                if (this.getX() - Model.FIELD_SELL_SIZE == gameObject.getX() && this.getY() == gameObject.getY()) collision = true;
                break;
            case RIGHT:
                if (this.getX() + Model.FIELD_SELL_SIZE == gameObject.getX() && this.getY() == gameObject.getY()) collision = true;
                break;
        }
        return collision;
    }
}
