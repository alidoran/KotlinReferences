package alidoran.design_pattern.java.state.java;

import android.util.Log;

public class BrushToolJavaState implements ToolJavaState {
    @Override
    public void mouseUp() {
        Log.d("Status", "BrushTool Mouse Up");
    }

    @Override
    public void mouseDown() {
        Log.d("Status", "BrushTool Mouse Up");
    }
}
