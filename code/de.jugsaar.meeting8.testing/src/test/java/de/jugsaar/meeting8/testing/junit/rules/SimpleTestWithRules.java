package de.jugsaar.meeting8.testing.junit.rules;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import de.jugsaar.meeting8.testing.junit.rules.custom.LogTestClass;
import de.jugsaar.meeting8.testing.junit.rules.custom.LogTestMethod;

public class SimpleTestWithRules {

	@ClassRule public static LogTestClass logTestClass = new LogTestClass();

	@Rule public LogTestMethod logTestMethod = new LogTestMethod();

	@Test
	public void test1() {
		Assert.assertEquals("2+1 should be 3", 3, 1 + 2);
		System.err.println("in test");
	}

	@Test
	public void test2() {
		Assert.assertSame(127, 127);
		Assert.assertNotSame(256, 256);
	}
}
