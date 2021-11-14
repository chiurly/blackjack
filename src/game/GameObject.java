package game;

import util.Vector2;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameObject {

    private Vector2 anchorPoint;
    private Vector2 absolutePosition;
    private Vector2 position;
    private Vector2 size;
    private Rectangle bounds;

    public GameObject() {
        anchorPoint = new Vector2();
        position = new Vector2();
        size = new Vector2(1, 1);
        updateAbsolutePosition();
        updateBounds();
    }

    public Vector2 getAnchorPoint() {
        return anchorPoint;
    }

    public Vector2 getAbsolutePosition() {
        return absolutePosition;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSize() {
        return size;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setAnchorPoint(Vector2 anchorPoint) {
        this.anchorPoint = anchorPoint;
        updateAbsolutePosition();
        updateBounds();
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        updateAbsolutePosition();
        updateBounds();
    }

    public void setSize(Vector2 size) {
        this.size = size;
        updateAbsolutePosition();
        updateBounds();
    }

    private void updateAbsolutePosition() {
        int x = position.intX() - (int) (size.intX() * anchorPoint.getX());
        int y = position.intY() - (int) (size.intY() * anchorPoint.getY());
        absolutePosition = new Vector2(x, y);
    }

    private void updateBounds() {
        bounds = new Rectangle(absolutePosition.intX(), absolutePosition.intY(), size.intX(), size.intY());
    }

}
