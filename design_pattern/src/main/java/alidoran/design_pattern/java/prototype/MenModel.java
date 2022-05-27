package alidoran.design_pattern.java.prototype;

public class MenModel implements HumanModel,Cloneable{

    String name;

    public MenModel(String name) {
        this.name = name;
    }

    @Override
    public HumanModel cloneModel() {
        try {
            return ((HumanModel) this.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
