package de.jugsaar.meeting8.testing.junit.rules;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
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
	public void foo() {

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
