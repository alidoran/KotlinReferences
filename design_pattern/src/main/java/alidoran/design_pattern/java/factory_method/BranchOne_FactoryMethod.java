package alidoran.design_pattern.java.factory_method;

public class BranchOne_FactoryMethod implements IFactoryMethodBranch {
    @Override
    public int branchNumber() {
        return 1;
    }

    @Override
    public String getText() {
        return "This is branch one";
    }
}
