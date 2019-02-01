package com.chesterlsy.mhwassistant;

import android.app.Application;
import android.content.Context;

/**
 * App: MonsterHunterWorldAssistant
 * Author: Siyi Liu
 * Created: 2019-02-01 17:07
 * Description:
 */
public class MonsterHunterWorldAssistantApplication extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }
}
