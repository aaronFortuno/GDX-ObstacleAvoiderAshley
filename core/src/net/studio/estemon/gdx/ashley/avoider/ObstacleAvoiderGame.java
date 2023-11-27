package net.studio.estemon.gdx.ashley.avoider;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.studio.estemon.gdx.ashley.avoider.screen.loading.LoadingScreen;

public class ObstacleAvoiderGame extends Game {

	private AssetManager assetManager;
	private SpriteBatch batch;

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void create() {
		assetManager = new AssetManager();
		batch = new SpriteBatch();

		setScreen(new LoadingScreen(this));
	}

	@Override
	public void dispose() {
		assetManager.dispose();
		batch.dispose();
	}
}
