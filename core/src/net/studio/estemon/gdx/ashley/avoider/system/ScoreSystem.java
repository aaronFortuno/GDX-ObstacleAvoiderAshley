package net.studio.estemon.gdx.ashley.avoider.system;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;

import net.studio.estemon.gdx.ashley.avoider.common.GameManager;
import net.studio.estemon.gdx.ashley.avoider.config.GameConfig;

public class ScoreSystem extends IntervalSystem {

    public ScoreSystem() {
        super(GameConfig.SCORE_MAX_TIME);
    }

    @Override
    protected void updateInterval() {
        GameManager.INSTANCE.updateScore(MathUtils.random(1, 5));
    }
}
