package net.studio.estemon.gdx.ashley.avoider.screen.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;

import net.studio.estemon.gdx.ashley.avoider.ObstacleAvoiderGame;
import net.studio.estemon.gdx.ashley.avoider.screen.menu.MenuScreen;

@Deprecated
public class GameScreenOld implements Screen {

    private final ObstacleAvoiderGame game;
    private final AssetManager assetManager;
    private GameControllerOld controller;
    private GameRendererOld renderer;

    public GameScreenOld(ObstacleAvoiderGame game) {
        this.game = game;
        assetManager = game.getAssetManager();
    }

    @Override
    public void show() {
        controller = new GameControllerOld(game);
        renderer = new GameRendererOld(game.getBatch(), assetManager, controller);
    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        renderer.render(delta);

        // we change screen only AFTER last frame has been rendered. That's
        // why we can check it by controller.isGameOver()
        if (controller.isGameOver()) {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
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
    public void dispose() {
        renderer.dispose();
    }
}
