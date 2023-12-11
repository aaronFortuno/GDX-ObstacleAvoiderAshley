package net.studio.estemon.gdx.ashley.avoider.common;

import com.badlogic.ashley.core.ComponentMapper;

import net.studio.estemon.gdx.ashley.avoider.component.BoundsComponent;
import net.studio.estemon.gdx.ashley.avoider.component.MovementComponent;

public class Mappers {

    private Mappers() {} // not instantiable

    public static final ComponentMapper<BoundsComponent> BOUNDS =
            ComponentMapper.getFor(BoundsComponent.class);

    public static final ComponentMapper<MovementComponent> MOVEMENT =
            ComponentMapper.getFor(MovementComponent.class);
}
