package net.studio.estemon.gdx.ashley.avoider.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class ObstacleComponent implements Component, Pool.Poolable {

    public boolean hit;

    @Override
    public void reset() {
        hit = false;
    }
}
