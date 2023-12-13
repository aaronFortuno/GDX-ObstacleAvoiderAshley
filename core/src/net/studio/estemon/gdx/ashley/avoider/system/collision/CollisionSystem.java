package net.studio.estemon.gdx.ashley.avoider.system.collision;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;

import net.studio.estemon.gdx.ashley.avoider.common.Mappers;
import net.studio.estemon.gdx.ashley.avoider.component.BoundsComponent;
import net.studio.estemon.gdx.ashley.avoider.component.ObstacleComponent;
import net.studio.estemon.gdx.ashley.avoider.component.PlayerComponent;

public class CollisionSystem extends EntitySystem {

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class,
            BoundsComponent.class
    ).get();

    private static final Family OBSTACLE_FAMILY = Family.all(
            ObstacleComponent.class,
            BoundsComponent.class
    ).get();

    public CollisionSystem() {
    }

    @Override
    public void update(float deltaTime) {
        // size of players array will always be 1 since we have only 1 player
        // this method could be used in situations where are more than 1 player
        ImmutableArray<Entity> players = getEngine().getEntitiesFor(PLAYER_FAMILY);
        ImmutableArray<Entity> obstacles = getEngine().getEntitiesFor(OBSTACLE_FAMILY);

        for (Entity playerEntity : players) {
            for (Entity obstacleEntity : obstacles) {
                ObstacleComponent obstacle = Mappers.OBSTACLE.get(obstacleEntity);

                if (obstacle.hit) {
                    continue;
                }

                if (checkCollision(playerEntity, obstacleEntity)) {
                    obstacle.hit = true;
                    System.out.println("[HIT!]");
                }
            }
        }
    }

    private boolean checkCollision(Entity player, Entity obstacle) {
        BoundsComponent playerBounds = Mappers.BOUNDS.get(player);
        BoundsComponent obstacleBounds = Mappers.BOUNDS.get(obstacle);

        return Intersector.overlaps(playerBounds.bounds, obstacleBounds.bounds);
    }
}
