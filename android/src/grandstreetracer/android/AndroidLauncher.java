package grandstreetracer.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

import grandstreetracer.core.GrandStreetRacer;

public class AndroidLauncher extends AndroidFragmentApplication {
	/*@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Haxball(), config);
		int mUIFlag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				| View.SYSTEM_UI_FLAG_LOW_PROFILE
				| View.SYSTEM_UI_FLAG_FULLSCREEN
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
		getWindow().getDecorView().setSystemUiVisibility(mUIFlag);
	}*/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		return initializeForView(new GrandStreetRacer(), config);
	}
}
