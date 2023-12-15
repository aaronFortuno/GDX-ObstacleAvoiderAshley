package net.studio.estemon.gdx.ashley.avoider.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import net.studio.estemon.gdx.ashley.avoider.assets.AssetDescriptors;
import net.studio.estemon.gdx.ashley.avoider.assets.RegionNames;
import net.studio.estemon.gdx.ashley.avoider.component.BoundsComponent;
import net.studio.estemon.gdx.ashley.avoider.component.CleanUpComponent;
import net.studio.estemon.gdx.ashley.avoider.component.DimensionComponent;
import net.studio.estemon.gdx.ashley.avoider.component.MovementComponent;
import net.studio.estemon.gdx.ashley.avoider.component.ObstacleComponent;
import net.studio.estemon.gdx.ashley.avoider.component.PlayerComponent;
import net.studio.estemon.gdx.ashley.avoider.component.PositionComponent;
import net.studio.estemon.gdx.ashley.avoider.component.TextureComponent;
import net.studio.estemon.gdx.ashley.avoider.component.WorldWrapComponent;
import net.studio.estemon.gdx.ashley.avoider.config.GameConfig;

public class EntityFactory {

    private final PooledEngine engine;
    private final AssetManager assetManager;
    private final TextureAtlas gamePlayAtlas;

    public EntityFactory(PooledEngine engine, AssetManager assetManager) {
        this.engine = engine;
        this.assetManager = assetManager;
        gamePlayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY_ATLAS);
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

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.PLAYER_SHIP_A);

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.PLAYER_SIZE;
        dimension.height = GameConfig.PLAYER_SIZE;

        Entity entity = engine.createEntity();
        entity.add(bounds);
        entity.add(movement);
        entity.add(player);
        entity.add(position);
        entity.add(worldWrap);
        entity.add(texture);
        entity.add(dimension);

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

        CleanUpComponent cleanUp = engine.createComponent(CleanUpComponent.class);

        ObstacleComponent obstacle = engine.createComponent(ObstacleComponent.class);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.OBSTACLE_LARGE);

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.OBSTACLE_SIZE;
        dimension.height = GameConfig.OBSTACLE_SIZE;

        Entity entity = engine.createEntity();
        entity.add(bounds);
        entity.add(movement);
        entity.add(position);
        entity.add(cleanUp);
        entity.add(obstacle);
        entity.add(texture);
        entity.add(dimension);

        engine.addEntity(entity);
    }

    public void addBackground() {
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.BACKGROUND);

        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = 0;
        position.y = 0;

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.WORLD_WIDTH;
        dimension.height = GameConfig.WORLD_HEIGHT;

        Entity entity = engine.createEntity();
        entity.add(texture);
        entity.add(position);
        entity.add(dimension);

        engine.addEntity(entity);
    }
}
