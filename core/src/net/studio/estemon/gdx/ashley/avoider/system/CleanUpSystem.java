package net.studio.estemon.gdx.ashley.avoider.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import net.studio.estemon.gdx.ashley.avoider.common.Mappers;
import net.studio.estemon.gdx.ashley.avoider.component.CleanUpComponent;
import net.studio.estemon.gdx.ashley.avoider.component.PositionComponent;
import net.studio.estemon.gdx.ashley.avoider.config.GameConfig;

public class CleanUpSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            CleanUpComponent.class,
            PositionComponent.class
    ).get();

    public CleanUpSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);

        if (position.y < -GameConfig.OBSTACLE_SIZE) {
            getEngine().removeEntity(entity);
        }
    }
}
