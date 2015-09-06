package grandstreetracer.android.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import grandstreetracer.android.support.ICallback;

/**
 * Created by temhemka on 16.08.2015.
 */
public class CloseDialog extends DialogFragment {

    private ICallback callback;

    public void setCallback(ICallback callback){
        this.callback = callback;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(grandstreetracer.android.R.string.are_you_sure_about_exiting_the_game)
                .setPositiveButton(grandstreetracer.android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(callback != null){
                            callback.execute();
                        }
                    }
                })
                .setNegativeButton(grandstreetracer.android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}