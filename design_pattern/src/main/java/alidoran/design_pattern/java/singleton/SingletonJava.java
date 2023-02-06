package alidoran.design_pattern.java.singleton;


import static android.content.ContentValues.TAG;

import android.util.Log;

public class SingletonJava {
    private static SingletonJava instance;

    public static SingletonJava getInstance() {
        if (instance == null)
            instance = new SingletonJava();
        return instance;
    }

    public void sampleMethod(){
        Log.d(TAG, "sampleMethod: ");
    }
}
