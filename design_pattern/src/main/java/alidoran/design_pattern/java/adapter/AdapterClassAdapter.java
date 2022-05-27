package alidoran.design_pattern.java.adapter;

public class AdapterClassAdapter implements AdapterInterface {

    private AdapterClassTwo classTwo;

    public AdapterClassAdapter() {
        classTwo = new AdapterClassTwo();
    }

    @Override
    public void methodOne() {
        classTwo.methodOne();
    }

    @Override
    public void methodTwo() {
        classTwo.methodTwo();
    }
}
