package alidoran.design_pattern.java.builder;

public class HumanModel {
    private String sex;
    private String name;
    private int height;
    private float weight;

    void setSex(String sex) {
        this.sex = sex;
    }

    void setName(String name) {
        this.name = name;
    }

    void setHeight(int height) {
        this.height = height;
    }

    void setWeight(float weight) {
        this.weight = weight;
    }

    public String toString(){
        return sex+ "\n" + name + "\n" + height + "\n" + weight;
    }
}
