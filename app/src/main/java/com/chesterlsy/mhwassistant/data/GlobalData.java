package com.chesterlsy.mhwassistant.data;

import java.io.Serializable;

/**
 * App: MonsterHunterWorldAssistant
 * Author: Siyi Liu
 * Created: 2019-01-30 17:05
 * Description:
 *      1. The synchronized block will be executed only when the globalDataInstance is null and prevent unnecessary synchronization once the instance variable is initialized.
 *
 *      2. Without volatile modifier it’s possible for another thread in Java to see half initialized state of globalDataInstance variable,
 *      but with volatile variable guaranteeing happens-before relationship,
 *      all the write will happen on volatile globalDataInstance before any read of globalDataInstance variable.
 *
 *      3. To prevent creation of another instance when we deserialize the singleton,
 *      we have to provide the implementation of readResolve() method.
 *      readResolve() replaces the object read from the stream.
 *      This ensures that nobody can create another instance by serializing and deserializing the singleton.
 */
public class GlobalData implements Serializable, Cloneable {
    private static volatile GlobalData globalDataInstance;

    /**
     * private constructor
     */
    private GlobalData() {

        //Prevent form the reflection api.
        if (globalDataInstance != null) {
            throw new RuntimeException("Use getInstance() method to get the single globalDataInstance of this class.");
        }
    }

    public static GlobalData getInstance() {

        //Double check locking pattern
        if (globalDataInstance == null) { //1st check

            //the synchronized block will be executed only when the globalDataInstance is null and prevent unnecessary synchronization once the instance variable is initialized.
            synchronized (GlobalData.class) { //2nd check
                if (globalDataInstance == null) { //if there is no globalDataInstance available... create new one
                    globalDataInstance = new GlobalData();
                }
            }
        }
        return globalDataInstance;
    }

    /**
     * This method is called immediately after an object of this class is deserialized.
     * @return This method returns the singleton instance.
     */
    protected GlobalData readResolve() {
        return getInstance();
    }

    /**
     * Whenever user will try to create clone of singleton object, it will throw exception and hence our class remains singleton.
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
