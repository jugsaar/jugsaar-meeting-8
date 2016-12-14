package de.jugsaar.meeting8.testing.junit.poc;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import de.jugsaar.meeting8.testing.junit.poc.SpringContext.Use;
import de.jugsaar.meeting8.testing.junit.poc.SpringJunit4RunnerRuleTests.Nested.SpecialConfig;
import de.jugsaar.meeting8.testing.junit.rules.custom.LogTestMethod;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class SpringJunit4RunnerRuleTests {

	@ClassRule public static SpringContext springContext = new SpringContext();

	@Rule public LogTestMethod logTestMethod = new LogTestMethod();

	@Autowired Foo foo;

	@Test
	public void test1_should_use_ConcreteFoo() {

		Assert.assertNotNull(foo);
		Assert.assertTrue(foo instanceof ConcreteFoo);
	}

	@Test
	@Use(SpecialConfig.class)
	public void test2_should_use_SpecialFoo() {

		Assert.assertNotNull(foo);
		Assert.assertTrue(foo instanceof SpecialFoo);
	}

	@Test
	public void test3_should_use_ConcreteFoo() {

		Assert.assertNotNull(foo);
		Assert.assertTrue(foo instanceof ConcreteFoo);
	}

	static interface Foo {}

	static class ConcreteFoo implements Foo {};

	static class SpecialFoo implements Foo {}

	@Configuration
	static class Config {

		@Bean
		public Foo foo() {
			return new ConcreteFoo();
		}
	}

	// Not visible for the test directly
	public static class Nested {

		@Configuration
		public static class SpecialConfig extends Config {

			@Bean
			public Foo foo() {
				return new SpecialFoo();
			}
		}
	}
}
