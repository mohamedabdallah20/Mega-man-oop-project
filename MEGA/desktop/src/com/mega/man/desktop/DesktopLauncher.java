package com.mega.man.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mega.man.megaMan;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title="mega man";
                config.width=1500;
                config.height=1000;
                config.fullscreen=false;
                config.useGL30=true;
		new LwjglApplication(new megaMan(), config);
	}
}
