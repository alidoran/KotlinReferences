package alidoran.design_pattern.java.state.java;

public class CanvasWithoutPattern {
    private final ToolEnumWithoutPatternState currentTool;

    public void mouseDown(){
        if(currentTool == ToolEnumWithoutPatternState.SELECTION)
            System.out.println("Selection Icon");
        else if (currentTool == ToolEnumWithoutPatternState.BRUSH)
            System.out.println("Brush Icon");
        else if (currentTool == ToolEnumWithoutPatternState.ERASER)
            System.out.println("Eraser Icon");
    }

    public void mouseUp(){
        if(currentTool == ToolEnumWithoutPatternState.SELECTION)
            System.out.println("Draw dashed rectangle");
        else if (currentTool == ToolEnumWithoutPatternState.BRUSH)
            System.out.println("Draw a line");
        else if (currentTool == ToolEnumWithoutPatternState.ERASER)
            System.out.println("Erase something");
    }

    public CanvasWithoutPattern(ToolEnumWithoutPatternState currentTool) {
        this.currentTool = currentTool;
    }
}
