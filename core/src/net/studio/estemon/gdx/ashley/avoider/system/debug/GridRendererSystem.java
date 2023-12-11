package net.studio.estemon.gdx.ashley.avoider.system.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.studio.estemon.gdx.ashley.avoider.util.ViewportUtils;

public class GridRendererSystem extends EntitySystem {

    // attributes
    private final Viewport viewport;
    private final ShapeRenderer renderer;

    // constructors
    public GridRendererSystem(Viewport viewport, ShapeRenderer renderer) {
        this.viewport = viewport;
        this.renderer = renderer;
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        ViewportUtils.drawGrid(viewport, renderer);
    }
}
