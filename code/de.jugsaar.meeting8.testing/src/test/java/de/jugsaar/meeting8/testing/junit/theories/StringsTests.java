package de.jugsaar.meeting8.testing.junit.theories;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.google.common.base.Strings;

@RunWith(Theories.class)
public class StringsTests {

	@DataPoints public static String[] testStrings = { "", "x", "xx" };
	@DataPoints public static int[] minLengths = { 0, 1, 2, 3 };

	@Theory
	public void test_padStart(String s, int minLength) {

		System.out.printf("Executing test with String: %s and min length: %s%n", s, minLength);

		String p = Strings.padStart(s, minLength, '-');

		assertThat(p, endsWith(s));
		assertThat(p.length(), is(not(lessThan(minLength))));

		String padding = p.substring(0, p.length() - s.length());
		assertThat(padding, is(equalTo(Strings.repeat("-", padding.length()))));
	}
}
