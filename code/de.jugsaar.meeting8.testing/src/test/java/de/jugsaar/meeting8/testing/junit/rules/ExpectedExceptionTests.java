package de.jugsaar.meeting8.testing.junit.rules;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExpectedExceptionTests {

	static class Component {
		public static int version = 1;

		public String method() {

			if (version == 1) {
				throw new RuntimeException("Bug in version 1");
			}

			return "ok";
		}
	}

	@Rule public ExpectedException expected = ExpectedException.none();

	Component component = new Component();

	@Test
	public void foo_with_traditional_exception_handling() {

		Object[] array = { 1, 2, 3 };

		try {

			assertThat(array[2], is(notNullValue()));
			fail("Should have trown AIOOBE!");

		} catch (Exception ex) {

			assertThat(ex, is(instanceOf(ArrayIndexOutOfBoundsException.class)));
		}
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void foo_with_test_expected_exception() {

		Object[] array = { 1 };
		assertThat(array[2], is(notNullValue()));
	}

	@Test
	public void foo_with_expected_exception() {

		Object[] array = {};

		expected.expect(ArrayIndexOutOfBoundsException.class);
		expected.expectMessage("2");

		assertThat(array[2], is(notNullValue()));
	}

	@Test
	public void testForBugInVersion1() {

		assumeThat(component.version, is(not(1)));

		expected.expect(RuntimeException.class);
		expected.expectMessage("Bug");

		assertThat(component.method(), is("ok"));
	}
}
