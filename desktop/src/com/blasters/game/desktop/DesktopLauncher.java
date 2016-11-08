package com.blasters.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.blasters.game.SuperPlanetBlasters;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SuperPlanetBlasters.WIDTH;
		config.height = SuperPlanetBlasters.HEIGHT;
		config.title = SuperPlanetBlasters.TITLE;
		new LwjglApplication(new SuperPlanetBlasters(), config);
	}
}
