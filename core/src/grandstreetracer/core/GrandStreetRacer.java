package grandstreetracer.core;

import com.badlogic.gdx.Game;

import grandstreetracer.core.screens.GameScreen;


public class GrandStreetRacer extends Game {

	//declare all of the games screens
	GameScreen gameScreen;

	@Override
	public void create() {
		//load the screens
        gameScreen = new GameScreen(this);

        //call the onCreate function to do all of the stuff it needs to before rendering
        //ie declaring variables, loading images and sounds etc
        gameScreen.onCreate();
        setScreen(gameScreen);
	}


}
