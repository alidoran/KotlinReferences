package alidoran.design_pattern.java.factory_method;

public class FactoryClass_FactoryMethod implements IFactoryMethod {


    @Override
    public IFactoryMethodBranch create() {
        return new BranchOne_FactoryMethod();
    }

    @Override
    public IFactoryMethodBranch create(String selectedBranch) {
        if (selectedBranch.equals("BranchOne"))
            return new BranchOne_FactoryMethod();
        else if (selectedBranch.equals("BranchTwo"))
            return new BranchTwo_FactoryMethod();
        else return null;
    }
}
