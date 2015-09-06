package grandstreetracer.android.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

import grandstreetracer.android.dialog.CloseDialog;
import grandstreetracer.android.support.ICallback;

import java.util.Objects;

/**
 * Created by temhemka on 15.08.2015.
 */
public class GamePaneActivity extends BaseActivity implements AndroidFragmentApplication.Callbacks{

    @Override
    public void onCreate(Bundle savedInstanceSate) {
        super.onCreate(savedInstanceSate);
        setContentView(grandstreetracer.android.R.layout.game_layout);
    }

    @Override
    public void exit() {

    }

    @Override
    public void onBackPressed() {
        CloseDialog closeDialog = new CloseDialog();
        closeDialog.setCallback(new ICallback() {
            @Override
            public void execute(Objects... args) {
                GamePaneActivity.this.finish();
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        closeDialog.show(fm, "close_dialog");
    }
}
