package alidoran.design_pattern.java.factory_method;

public interface IFactoryMethod {

    IFactoryMethodBranch create();

    IFactoryMethodBranch create(String selectedBranch);
}
