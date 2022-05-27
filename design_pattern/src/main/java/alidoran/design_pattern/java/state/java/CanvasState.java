package alidoran.design_pattern.java.state.java;

public class CanvasState {
    private final ToolJavaState currentTool;

    public void mouseDown(){
        currentTool.mouseDown();
    }

    public void mouseUp(){
        currentTool.mouseUp();
    }

    public CanvasState(ToolJavaState currentTool) {
        this.currentTool = currentTool;
    }
}
