package net.studio.estemon.gdx.ashley.avoider.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import net.studio.estemon.gdx.ashley.avoider.common.Mappers;
import net.studio.estemon.gdx.ashley.avoider.component.BoundsComponent;
import net.studio.estemon.gdx.ashley.avoider.component.PositionComponent;

public class BoundsSystem extends IteratingSystem {

    public static final Family FAMILY = Family.all(
            BoundsComponent.class,
            PositionComponent.class
    ).get();

    public BoundsSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoundsComponent bounds = Mappers.BOUNDS.get(entity);
        PositionComponent position = Mappers.POSITION.get(entity);

        bounds.bounds.x = position.x;
        bounds.bounds.y = position.y;
    }
}
