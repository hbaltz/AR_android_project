package com.example.hbaltz.aton.utilities;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by hbaltz on 7/20/2016.
 */
public class Various {

    /**
     * Displays on the screen the CharSequence text
     *
     * @param context: The context of the app
     * @param text: the text that we want to display
     */
    public static void makeToast(Context context, CharSequence text) {
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
