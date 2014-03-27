package de.jugsaar.meeting8.testing.junit;

import static de.jugsaar.meeting8.testing.junit.TestUtils.getCurrentMethodName;

import org.junit.Assert;
import org.junit.Test;

public class BasicTest {

	@Test
	public void test1() {
		System.out.println(getCurrentMethodName());

		Assert.assertEquals("2+1 should be 3", 3, 1 + 2);
	}
}
