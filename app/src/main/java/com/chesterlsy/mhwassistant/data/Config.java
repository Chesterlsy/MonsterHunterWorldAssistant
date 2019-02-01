package com.chesterlsy.mhwassistant.data;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.chesterlsy.mhwassistant.MonsterHunterWorldAssistantApplication;

/**
 * App: MonsterHunterWorldAssistant
 * Author: Siyi Liu
 * Created: 2019-02-01 16:54
 * Description: This is data locally stored in config.xml
 */
public class Config {

    private static volatile Config configInstance;

    private Context context;
    private SharedPreferences sharedPreferences;

    private Config(Context context) {
        if (configInstance != null) {
            throw new RuntimeException("Use getInstance() method to get the single configInstance of this class.");
        } else {
            this.context = context;
            sharedPreferences = context.getSharedPreferences(Constant.Config.CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        }
    }

    public static Config getInstance() {

        //Double check locking pattern
        if (configInstance == null) { //1st check

            //the synchronized block will be executed only when the globalDataInstance is null and prevent unnecessary synchronization once the instance variable is initialized.
            synchronized (Config.class) { //2nd check
                if (configInstance == null) { //if there is no globalDataInstance available... create new one
                    configInstance = new Config(MonsterHunterWorldAssistantApplication.getAppContext());
                }
            }
        }
        return configInstance;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
