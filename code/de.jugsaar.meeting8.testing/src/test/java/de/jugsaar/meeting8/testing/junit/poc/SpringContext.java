package de.jugsaar.meeting8.testing.junit.poc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.util.ReflectionUtils;

public class SpringContext implements TestRule {

	private Class<?> testClass;

	private volatile boolean initilized;

	public SpringContext() {
		this(null);
	}

	public SpringContext(Class<?> testClass) {
		this.testClass = testClass;
	}

	@Override
	public Statement apply(final Statement base, Description description) {

		if (initilized) {
			return base;
		}

		initilized = true;

		invokeSpringRunner(base, description);

		return noop();
	}

	private void invokeSpringRunner(final Statement base, Description description) {

		try {
			Field runNotifierField = ReflectionUtils.findField(base.getClass(), "val$notifier");
			ReflectionUtils.makeAccessible(runNotifierField);
			Object runNotifier = ReflectionUtils.getField(runNotifierField, base);

			ExtendedSpringJUnit4ClassRunner runner = new ExtendedSpringJUnit4ClassRunner(
					this.testClass != null ? this.testClass : description.getTestClass());

			runner.run(RunNotifier.class.cast(runNotifier));
		} catch (InitializationError e) {
			throw new RuntimeException(e);
		}
	}

	private Statement noop() {
		return new Statement() {

			@Override
			public void evaluate() throws Throwable {}
		};
	}

	static class ExtendedSpringJUnit4ClassRunner extends SpringJUnit4ClassRunner {

		public ExtendedSpringJUnit4ClassRunner(Class<?> clazz) throws InitializationError {
			super(clazz);

			getTestContextManager().getTestExecutionListeners().add(new NestedContextTestExecutionListener());
		}
	}

	static class NestedContextTestExecutionListener extends AbstractTestExecutionListener {

		@Override
		public void beforeTestMethod(TestContext testContext) throws Exception {

			Use use = testContext.getTestMethod().getAnnotation(Use.class);
			if (use != null && use.value().length > 0) {

				AnnotationConfigApplicationContext ctxt = new AnnotationConfigApplicationContext();
				ctxt.setParent(testContext.getApplicationContext());

				for (Class<?> additionalConfigClass : use.value()) {
					ctxt.register(additionalConfigClass);
				}

				ctxt.refresh();

				ctxt.getAutowireCapableBeanFactory().autowireBean(testContext.getTestInstance());
			}
		}
	}

	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface Use {

		Class<?>[] value() default {};
	}
}
