package com.mega.man.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mega.man.megaMan;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                
                config.title = "my-gdx-game";
      config.useGL30 = false;
      config.width = 480;
      config.height = 320;
		new LwjglApplication(new megaMan(), config);
	}
}
