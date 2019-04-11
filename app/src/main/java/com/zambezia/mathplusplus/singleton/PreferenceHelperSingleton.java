package com.zambezia.mathplusplus.singleton;

import android.content.Context;

import com.zambezia.mathplusplus.Enums;
import com.zambezia.mathplusplus.helper.PreferenceHelper;

/**
 * Created by Abdul.Basir on 8/24/2016.
 */
public class PreferenceHelperSingleton {

    private static volatile PreferenceHelper instance = null;

    public static boolean initialize(Context context){

        if (instance == null){
            synchronized(PreferenceHelperSingleton.class) {
                if (instance == null) {
                    instance = new PreferenceHelper(context);
                }
            }
        }
        return true;
    }

    public static PreferenceHelper getInstance()
    {
        synchronized(PreferenceHelperSingleton.class) {
            return instance;
        }
    }

    public static void destroy()
    {
        instance = null;
    }
}
