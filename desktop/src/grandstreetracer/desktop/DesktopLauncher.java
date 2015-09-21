package grandstreetracer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import grandstreetracer.core.GrandStreetRacer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Grand Street Racer";
		cfg.width = 800;
		cfg.height = 600;
		new LwjglApplication(new GrandStreetRacer(), cfg);
	}
}
