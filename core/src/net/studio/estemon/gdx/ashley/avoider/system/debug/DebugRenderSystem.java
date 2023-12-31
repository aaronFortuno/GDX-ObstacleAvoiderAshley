package net.studio.estemon.gdx.ashley.avoider.system.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.studio.estemon.gdx.ashley.avoider.common.Mappers;
import net.studio.estemon.gdx.ashley.avoider.component.BoundsComponent;

public class DebugRenderSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(BoundsComponent.class).get();

    private final Viewport viewport;
    private final ShapeRenderer renderer;

    public DebugRenderSystem(Viewport viewport, ShapeRenderer renderer) {
        super(FAMILY);
        this.viewport = viewport;
        this.renderer = renderer;
    }

    @Override
    public void update(float deltaTime) {
        Color oldColor = renderer.getColor().cpy();

        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);

        super.update(deltaTime);

        renderer.end();
        renderer.setColor(oldColor);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoundsComponent bc = Mappers.BOUNDS.get(entity);
        renderer.circle(bc.bounds.x, bc.bounds.y, bc.bounds.radius, 30);
    }
}
