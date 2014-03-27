package de.jugsaar.meeting8.testing.junit.rules.custom;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class LogTestClass implements TestRule {

	@Override
	public Statement apply(final Statement base, final Description description) {
		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				System.out.printf("Before class: %s%n", description.getClassName());
				try {
					base.evaluate();
				} finally {
					System.out.printf("After class: %s%n", description.getClassName());
				}
			}
		};
	}
}
