package net.studio.estemon.gdx.ashley.avoider.entity;

import net.studio.estemon.gdx.ashley.avoider.config.GameConfig;

public class Player extends GameObjectBase {

    public Player() {
        super(GameConfig.PLAYER_BOUNDS_RADIUS);
        setSize(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);
    }
}
