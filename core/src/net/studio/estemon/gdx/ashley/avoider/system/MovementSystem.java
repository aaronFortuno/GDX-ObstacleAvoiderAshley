package net.studio.estemon.gdx.ashley.avoider.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import net.studio.estemon.gdx.ashley.avoider.common.Mappers;
import net.studio.estemon.gdx.ashley.avoider.component.MovementComponent;
import net.studio.estemon.gdx.ashley.avoider.component.PlayerComponent;
import net.studio.estemon.gdx.ashley.avoider.component.PositionComponent;

import java.util.Map;

public class MovementSystem extends IteratingSystem{

    private static final Family FAMILY = Family.all(
            PlayerComponent.class,
            MovementComponent.class
    ).get();

    public MovementSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);
        MovementComponent movement = Mappers.MOVEMENT.get(entity);

        position.x += movement.xSpeed;
        position.y += movement.ySpeed;
    }
}
