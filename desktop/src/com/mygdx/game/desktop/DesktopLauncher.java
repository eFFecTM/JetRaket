package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.JetRaket;

/**
 * Created by Thomas Janssen & Jan De Laet
 */

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = JetRaket.WIDTH;
		config.height = JetRaket.HEIGHT;
		config.title = JetRaket.TITLE;
		config.vSyncEnabled = false;
		new LwjglApplication(new JetRaket(), config);

	}
}
