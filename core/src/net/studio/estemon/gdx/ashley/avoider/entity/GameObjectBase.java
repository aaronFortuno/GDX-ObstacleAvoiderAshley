package net.studio.estemon.gdx.ashley.avoider.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public abstract class GameObjectBase {

    private float x;
    private float y;
    private float width = 1;
    private float height = 1;
    private final Circle bounds;

    public GameObjectBase(float boundsRadius) {
        bounds = new Circle(x, y, boundsRadius * 3 / 4);
    }

    public void drawDebug(ShapeRenderer renderer) {
        renderer.circle(bounds.x, bounds.y, bounds.radius, 30);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        updateBounds();
    }

    public float getX() { return x; }

    public void setX(float x) {
        this.x = x;
        updateBounds();
    }
    public void setY(float y) {
        this.y = y;
        updateBounds();
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        updateBounds();
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getY() { return y; }

    public void updateBounds() {
        float halfWidth = width / 2;
        float halfHeight = height / 2;
        bounds.setPosition(x + halfWidth, y + halfHeight);
    }

    public Circle getBounds() {
        return bounds;
    }
}
