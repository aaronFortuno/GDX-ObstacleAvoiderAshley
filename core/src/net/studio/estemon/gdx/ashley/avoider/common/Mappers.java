package net.studio.estemon.gdx.ashley.avoider.common;

import com.badlogic.ashley.core.ComponentMapper;

import net.studio.estemon.gdx.ashley.avoider.component.BoundsComponent;
import net.studio.estemon.gdx.ashley.avoider.component.MovementComponent;
import net.studio.estemon.gdx.ashley.avoider.component.ObstacleComponent;
import net.studio.estemon.gdx.ashley.avoider.component.PositionComponent;

public class Mappers {

    private Mappers() {} // not instantiable

    public static final ComponentMapper<BoundsComponent> BOUNDS =
            ComponentMapper.getFor(BoundsComponent.class);

    public static final ComponentMapper<MovementComponent> MOVEMENT =
            ComponentMapper.getFor(MovementComponent.class);

    public static final ComponentMapper<PositionComponent> POSITION =
            ComponentMapper.getFor(PositionComponent.class);

    public static final ComponentMapper<ObstacleComponent> OBSTACLE =
            ComponentMapper.getFor(ObstacleComponent.class);
}
