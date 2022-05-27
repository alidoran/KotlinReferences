package alidoran.design_pattern.java.factory_method;

public class BranchTwo_FactoryMethod implements IFactoryMethodBranch {
    @Override
    public int branchNumber() {
        return 2;
    }

    @Override
    public String getText() {
        return "This is branch two";
    }
}
