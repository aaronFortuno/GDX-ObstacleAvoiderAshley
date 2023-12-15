package net.studio.estemon.gdx.ashley.avoider.system;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;

import net.studio.estemon.gdx.ashley.avoider.common.EntityFactory;
import net.studio.estemon.gdx.ashley.avoider.config.GameConfig;

public class ObstacleSpawnSystem extends IntervalSystem {

    private final EntityFactory factory;

    public ObstacleSpawnSystem(EntityFactory factory) {
        super(GameConfig.OBSTACLE_SPAWN_TIME);
        this.factory = factory;
    }

    @Override
    protected void updateInterval() {
        float min = 0;
        float max = GameConfig.WORLD_WIDTH - GameConfig.OBSTACLE_SIZE;

        float obstacleX = MathUtils.random(min, max);
        float obstacleY = GameConfig.WORLD_HEIGHT;

        factory.addObstacle(obstacleX, obstacleY);
    }
}
