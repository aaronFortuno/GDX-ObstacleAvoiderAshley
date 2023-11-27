package net.studio.estemon.gdx.ashley.avoider.screen.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;

import net.studio.estemon.gdx.ashley.avoider.ObstacleAvoiderGame;
import net.studio.estemon.gdx.ashley.avoider.screen.menu.MenuScreen;

public class GameScreen implements Screen {

    private final ObstacleAvoiderGame game;
    private final AssetManager assetManager;

    public GameScreen(ObstacleAvoiderGame game) {
        this.game = game;
        assetManager = game.getAssetManager();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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

    }
}
