package alidoran.design_pattern.java.builder;

class HumanBuilder {
    private HumanModel model;

    HumanBuilder() {
        model = new HumanModel();
    }

    HumanBuilder setSex(String sex){
        model.setSex(sex);
        return this;
    }

    HumanBuilder setName(String name){
        model.setName(name);
        return this;
    }

    HumanBuilder setHeight(int height){
        model.setHeight(height);
        return this;
    }

    HumanBuilder setWeight(float weight){
        model.setWeight(weight);
        return this;
    }

     HumanModel build(){
        return model;
    }
}
