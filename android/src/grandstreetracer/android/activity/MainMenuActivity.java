package grandstreetracer.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by temhemka on 15.08.2015.
 */
public class MainMenuActivity extends BaseActivity {



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(grandstreetracer.android.R.layout.main_menu);
    }

    public void onBtnClicked(View view){
        switch (view.getId()){
            case grandstreetracer.android.R.id.start_the_game:
                Intent intent = new Intent(MainMenuActivity.this, GamePaneActivity.class);
                startActivity(intent);
                break;
            case grandstreetracer.android.R.id.quit:
                finish();
                break;
        }
    }
}
