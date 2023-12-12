package net.studio.estemon.gdx.ashley.avoider.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

import net.studio.estemon.gdx.ashley.avoider.component.BoundsComponent;
import net.studio.estemon.gdx.ashley.avoider.component.MovementComponent;
import net.studio.estemon.gdx.ashley.avoider.component.PlayerComponent;
import net.studio.estemon.gdx.ashley.avoider.component.PositionComponent;
import net.studio.estemon.gdx.ashley.avoider.component.WorldWrapComponent;
import net.studio.estemon.gdx.ashley.avoider.config.GameConfig;

public class EntityFactory {

    private final PooledEngine engine;

    public EntityFactory(PooledEngine engine) {
        this.engine = engine;
    }

    public void addPlayer() {
        float x = GameConfig.WORLD_WIDTH / 2f;
        float y = GameConfig.PLAYER_SIZE;

        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = x;
        position.y = y;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.bounds.set(x, y, GameConfig.PLAYER_BOUNDS_RADIUS);

        MovementComponent movement = engine.createComponent(MovementComponent.class);

        PlayerComponent player = engine.createComponent(PlayerComponent.class);

        WorldWrapComponent worldWrap = engine.createComponent(WorldWrapComponent.class);

        Entity entity = engine.createEntity();
        entity.add(bounds);
        entity.add(movement);
        entity.add(player);
        entity.add(position);
        entity.add(worldWrap);

        engine.addEntity(entity);
    }

    public void addObstacle(float x, float y) {
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.bounds.set(x, y, GameConfig.OBSTACLE_BOUNDS_RADIUS);

        MovementComponent movement = engine.createComponent(MovementComponent.class);
        movement.ySpeed = -GameManager.INSTANCE.getDifficultyLevel().getObstacleSpeed();

        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = x;
        position.y = y;

        Entity entity = engine.createEntity();
        entity.add(bounds);
        entity.add(movement);
        entity.add(position);

        engine.addEntity(entity);
    }
}
