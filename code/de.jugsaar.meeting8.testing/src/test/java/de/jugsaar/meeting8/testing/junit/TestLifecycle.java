package de.jugsaar.meeting8.testing.junit;

import static de.jugsaar.meeting8.testing.junit.TestUtils.*;
import org.junit.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestLifecycle {
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println(getCurrentMethodName());
	}

	@AfterClass
	public static void afterClass() {
		System.out.println(getCurrentMethodName());
	}

	@Before
	public void beforeTest() {
		System.out.println(getCurrentMethodName());
	}

	@After
	public void afterTest() {
		System.out.println(getCurrentMethodName());
	}

	@Test
	public void test1() {
		System.out.println(getCurrentMethodName());
		
		Assert.assertEquals("2+1 should be 3",3, 1+2);
		
	}

	@Test
	public void test2() {
		System.out.println(getCurrentMethodName());
		
		Assert.assertSame(127, 127);
		Assert.assertNotSame(256, 256);
	}
}
