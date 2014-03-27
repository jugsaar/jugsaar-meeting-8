package de.jugsaar.meeting8.testing.junit;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import de.jugsaar.meeting8.testing.Fibonacci;

@RunWith(Parameterized.class)
public class ParameterizedTests {

	@Parameter(0) public int input;
	@Parameter(1) public int expected;

	@Parameters(name = "{index}: fib<{0}>={1}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] { { 0, 0 }, { 1, 1 }, { 2, 1 }, { 3, 2 }, { 4, 3 }, { 5, 5 }, { 6, 8 } });
	}

	@Test
	public void test() {
		assertEquals(expected, Fibonacci.compute(input));
	}
}
