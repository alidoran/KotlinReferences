package alidoran.design_pattern.java.adapter;

import android.util.Log;

public class AdapterClassOne implements AdapterInterface {

    @Override
    public void methodOne() {
        Log.d("Adapter Tag", "methodOne: Method One Class One");
    }

    @Override
    public void methodTwo() {
        Log.d("Adapter Tag", "methodOne: Method Two Class One");
    }
}
