package de.jugsaar.meeting8.testing.junit;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class IgnoredTests {

	@Test
	public void test1() {
		Assert.assertEquals("2+1 should be 3", 3, 1 + 2);
	}

	@Test
	@Ignore
	public void test2() {
		Assert.assertEquals("2+1 should be 3", 3, 1 + 2);
	}

	// @Test
	public void test3() {
		Assert.assertEquals("2+1 should be 3", 3, 1 + 2);
	}
}
