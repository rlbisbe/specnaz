package org.specnaz.impl;

import org.specnaz.utils.TestClosure;

import static java.lang.String.format;

public final class SingleExceptionTestCase extends SingleTestCase {
    private final Class<? extends Throwable> expectedException;
    private final TestClosure testBody;

    public SingleExceptionTestCase(Class<? extends Throwable> expectedException,
                                   String description, TestClosure testBody) {
        super(description);
        this.expectedException = expectedException;
        this.testBody = testBody;
    }

    @Override
    public Throwable exercise() {
        Throwable resultingException = SingleTestCase.invokeCallback(testBody);
        if (resultingException == null)
            return new AssertionError("Expected exception: " + expectedException.getName());
        if (!expectedException.isAssignableFrom(resultingException.getClass()))
            return new AssertionError(format("Unexpected exception, expected: %s but was: %s",
                    expectedException.getName(), resultingException.getClass().getName()));
        return null;
    }
}
