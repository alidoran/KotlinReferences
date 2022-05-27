package alidoran.design_pattern.java.state.java;

import android.util.Log;

public class SelectionToolJavaState implements ToolJavaState {
    @Override
    public void mouseUp() {
        Log.d("State_pattern", "SelectionTool Mouse Up");
    }

    @Override
    public void mouseDown() {
        Log.d("State_pattern", "SelectionTool Mouse Down");
    }
}
