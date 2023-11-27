package net.studio.estemon.gdx.ashley.avoider;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import net.studio.estemon.gdx.ashley.avoider.ObstacleAvoiderGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("AshleyObstacleAvoider");
		new Lwjgl3Application(new ObstacleAvoiderGame(), config);
	}
}
