package de.jugsaar.meeting8.testing.junit;

public class TestUtils {
	public static String getCurrentMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
}
