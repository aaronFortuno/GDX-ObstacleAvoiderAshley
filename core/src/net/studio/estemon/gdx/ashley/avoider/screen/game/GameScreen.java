package net.studio.estemon.gdx.ashley.avoider.screen.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.studio.estemon.gdx.ashley.avoider.ObstacleAvoiderGame;
import net.studio.estemon.gdx.ashley.avoider.assets.AssetDescriptors;
import net.studio.estemon.gdx.ashley.avoider.common.EntityFactory;
import net.studio.estemon.gdx.ashley.avoider.common.GameManager;
import net.studio.estemon.gdx.ashley.avoider.config.GameConfig;
import net.studio.estemon.gdx.ashley.avoider.screen.menu.MenuScreen;
import net.studio.estemon.gdx.ashley.avoider.system.BoundsSystem;
import net.studio.estemon.gdx.ashley.avoider.system.CleanUpSystem;
import net.studio.estemon.gdx.ashley.avoider.system.HudRenderSystem;
import net.studio.estemon.gdx.ashley.avoider.system.MovementSystem;
import net.studio.estemon.gdx.ashley.avoider.system.ObstacleSpawnSystem;
import net.studio.estemon.gdx.ashley.avoider.system.PlayerSystem;
import net.studio.estemon.gdx.ashley.avoider.system.WorldWrapSystem;
import net.studio.estemon.gdx.ashley.avoider.system.collision.CollisionSystem;
import net.studio.estemon.gdx.ashley.avoider.system.debug.DebugCameraSystem;
import net.studio.estemon.gdx.ashley.avoider.system.debug.DebugRenderSystem;
import net.studio.estemon.gdx.ashley.avoider.system.debug.GridRendererSystem;
import net.studio.estemon.gdx.ashley.avoider.util.GdxUtils;

public class GameScreen implements Screen {

    private final ObstacleAvoiderGame game;
    private final AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Viewport hudViewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;
    private EntityFactory factory;

    public GameScreen(ObstacleAvoiderGame game) {
        this.game = game;
        assetManager = game.getAssetManager();
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        renderer = new ShapeRenderer();
        engine = new PooledEngine();
        factory = new EntityFactory(engine);

        BitmapFont font = assetManager.get(AssetDescriptors.FONT);

        engine.addSystem(new DebugCameraSystem(camera,
                GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y
        ));
        engine.addSystem(new PlayerSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new WorldWrapSystem(viewport));
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new ObstacleSpawnSystem(factory));
        engine.addSystem(new CleanUpSystem());
        engine.addSystem(new CollisionSystem());

        engine.addSystem(new GridRendererSystem(viewport, renderer));
        engine.addSystem(new DebugRenderSystem(viewport, renderer));

        engine.addSystem(new HudRenderSystem(hudViewport, game.getBatch(), font));

        factory.addPlayer();
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() { renderer.dispose(); }
}
