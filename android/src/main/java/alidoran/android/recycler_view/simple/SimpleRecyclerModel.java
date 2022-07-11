package alidoran.android.recycler_view.simple;

class SimpleRecyclerModel {
    private final int code;
    private final String name;

    public SimpleRecyclerModel(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}