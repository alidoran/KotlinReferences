package android.test.mockito.simple_mockito;

import android.util.Log;

public final class StaticMethodInClass {
    public static void staticMethod(){
        Log.d("TAG", "staticMethod: ");
    }

    public static int staticMethodByReturnValue(){
        return 3;
    }
}