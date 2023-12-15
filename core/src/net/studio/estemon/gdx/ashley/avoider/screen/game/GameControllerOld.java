package net.studio.estemon.gdx.ashley.avoider.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import net.studio.estemon.gdx.ashley.avoider.ObstacleAvoiderGame;
import net.studio.estemon.gdx.ashley.avoider.assets.AssetDescriptors;
import net.studio.estemon.gdx.ashley.avoider.common.GameManager;
import net.studio.estemon.gdx.ashley.avoider.config.DifficultyLevel;
import net.studio.estemon.gdx.ashley.avoider.config.GameConfig;
import net.studio.estemon.gdx.ashley.avoider.entity.Background;
import net.studio.estemon.gdx.ashley.avoider.entity.Obstacle;
import net.studio.estemon.gdx.ashley.avoider.entity.Player;

@SuppressWarnings("ALL")
@Deprecated
public class GameControllerOld {

    // constants

    // attributes
    private Player player;
    private final Array<Obstacle> obstacles = new Array<>();
    private Background background;

    private float obstacleTimer;
    private float scoreTimer;

    private int lives = GameConfig.LIVES_START;
    private int score;
    private int displayScore;

    private Pool<Obstacle> obstaclePool;
    private Sound hit;

    private final AssetManager assetManager;

    // constructor
    public GameControllerOld(ObstacleAvoiderGame game) {
        assetManager = game.getAssetManager();
        init();
    }

    private void init() {
        // create player
        player = new Player();

        // initial player position
        // set starting x position at center
        float startPlayerX = GameConfig.WORLD_WIDTH / 2 - (GameConfig.PLAYER_SIZE / 2);
        // set starting y at bottom of the screen
        float startPlayerY = 1 - GameConfig.PLAYER_SIZE / 2;
        player.setPosition(startPlayerX, startPlayerY);

        // create obstacle pool
        obstaclePool = Pools.get(Obstacle.class, 40);

        // create background
        background = new Background();
        background.setPosition(0, 0);
        background.setSize(
                GameConfig.WORLD_WIDTH * GameConfig.WORLD_RATIO_ASPECT,
                GameConfig.WORLD_HEIGHT
        );

        hit = assetManager.get(AssetDescriptors.HIT_SOUND);
    }

    // public methods
    public void update(float delta) {
        // not wrapping inside alive because we want to be
        // able to inspect the world and control camera even if the game is over
        if (isGameOver()) {
            return;
        }

        updatePlayer();
        updateObstacles(delta);
        updateScore(delta);
        updateDisplayScore(delta);
        if (isPlayerCollidingWithObstacle()) {
            lives--;

            if (isGameOver()) {
                // GameManager.INSTANCE.updateHighscore(score);
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Array<Obstacle> getObstacles() {
        return obstacles;
    }

    public Background getBackground() { return background; }

    public int getLives() {
        return lives;
    }

    public int getDisplayScore() {
        return displayScore;
    }

    public boolean isGameOver() {
        return lives <= 0;
    }


    // private methods
    private boolean isPlayerCollidingWithObstacle() {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.isNotHit() && obstacle.isPlayerColliding(player)) {
                hit.play();
                return true;
            }
        }
        return false;
    }

    private void updatePlayer() {
        float xSpeed = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) { xSpeed = GameConfig.MAX_PLAYER_X_SPEED; }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) { xSpeed = -GameConfig.MAX_PLAYER_X_SPEED; }

        player.setX(player.getX() + xSpeed);
        blockPlayerFromLeavingTheWorld();
    }

    private void blockPlayerFromLeavingTheWorld() {
        // 2 alternatives
        float playerX = MathUtils.clamp(player.getX(),
                0, // when only with ShapeRenderer: player.getWidth() / 2,
                GameConfig.WORLD_WIDTH - player.getWidth()
        );

        player.setPosition(playerX, player.getY());
    }
    private void updateObstacles(float delta) {
        for (Obstacle obstacle : obstacles) {
            obstacle.update();
        }

        createNewObstacle(delta);
        removePassedObstacles();
    }

    private void createNewObstacle(float delta) {
        obstacleTimer += delta;
        if (obstacleTimer >= GameConfig.OBSTACLE_SPAWN_TIME) {
            float min = 0f - GameConfig.OBSTACLE_SIZE / 2;
            float max = GameConfig.WORLD_WIDTH - GameConfig.OBSTACLE_SIZE / 2;
            float obstacleX = MathUtils.random(min, max);
            float obstacleY = GameConfig.WORLD_HEIGHT;

            Obstacle obstacle = obstaclePool.obtain();

            DifficultyLevel difficultyLevel = GameManager.INSTANCE.getDifficultyLevel();
            obstacle.setYSpeed(difficultyLevel.getObstacleSpeed());
            obstacle.setPosition(obstacleX, obstacleY);

            obstacles.add(obstacle);
            obstacleTimer = 0f;
        }
    }

    private void removePassedObstacles() {
        if (obstacles.size > 0) {
            Obstacle first = obstacles.first();

            float minObstacleY = -GameConfig.OBSTACLE_SIZE;

            if (first.getY() < minObstacleY) {
                obstacles.removeValue(first, true);
                obstaclePool.free(first);
            }
        }
    }

    private void updateScore(float delta) {
        scoreTimer += delta;
        if (scoreTimer >= GameConfig.SCORE_MAX_TIME) {
            score += MathUtils.random(1, 5);
            scoreTimer = 0f;
        }
    }

    private void updateDisplayScore(float delta) {
        if (displayScore < score) {
            displayScore = Math.min(score,
                    displayScore + (int) (60 * delta)
            );
        }
    }

}
