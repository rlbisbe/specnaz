package org.specnaz.impl;

public class SpecRunner {
    private final SpecDescriptor specDescriptor;
    private TreeNode<TestsGroup> rootTestsGroupNode;

    public SpecRunner(Object specInstance) throws SpecsRegistryViolation {
        this.specDescriptor = SpecsRegistry.specFor(specInstance);
    }

    public String name() {
        return specDescriptor.description;
    }

    public TreeNode<TestsGroup> testsPlan() {
        if (rootTestsGroupNode == null)
            rootTestsGroupNode = formulateTestPlan();
        return rootTestsGroupNode;
    }

    public void run(Notifier notifier) {
        new TestsGroupNodeRunner(testsPlan(), notifier).run();
    }

    private TreeNode<TestsGroup> formulateTestPlan() {
        TestsTreeCoreDslBuilder testsTreeCoreDslBuilder = new TestsTreeCoreDslBuilder(name());
        specDescriptor.specClosure.accept(testsTreeCoreDslBuilder);
        return testsTreeCoreDslBuilder.spec();
    }
}
