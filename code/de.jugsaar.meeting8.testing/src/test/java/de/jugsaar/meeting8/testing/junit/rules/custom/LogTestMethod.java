package de.jugsaar.meeting8.testing.junit.rules.custom;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class LogTestMethod implements TestRule {

	static {
		Logger.getLogger("").setLevel(Level.FINEST);
		Logger.getLogger("").getHandlers()[0].setLevel(Level.FINEST);
	}

	@Override
	public Statement apply(final Statement base, final Description description) {

		final Logger log = Logger.getLogger(description.getClassName());

		try {

			log.entering(description.getClassName(), description.getMethodName());

			return new Statement() {

				@Override
				public void evaluate() throws Throwable {

					base.evaluate();

					log.exiting(description.getClassName(), description.getMethodName());
				}
			};

		} finally {

		}
	}
}
