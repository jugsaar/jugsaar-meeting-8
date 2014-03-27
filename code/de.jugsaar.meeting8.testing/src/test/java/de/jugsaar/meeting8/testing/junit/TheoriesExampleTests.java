package de.jugsaar.meeting8.testing.junit;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import de.jugsaar.meeting8.testing.Fibonacci;

@RunWith(Theories.class)
public class TheoriesExampleTests {

	@DataPoint public static int first = 1;
	@DataPoint public static int second = 2;
	@DataPoint public static int thrid = 3;

	@Theory
	public void subsequentFibonacciNumbersAreInAscendingOrder(int n) {

		/*
		 *  If any of the assumptions fail, the data point is silently ignored. 
		 *  If all of the assumptions pass, but an assertion fails, the test fails.
		 */

		System.out.println("test with: " + n);

		assumeThat(n, is(not(1)));
		assumeThat(n, is(not(2)));

		System.out.println("assert for: " + n);

		assertThat(Fibonacci.compute(n), is(greaterThan(Fibonacci.compute(n - 1))));

	}
}
