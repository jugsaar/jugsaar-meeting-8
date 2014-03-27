package de.jugsaar.meeting8.testing.junit;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class TimeoutTests {

	@Rule public Timeout timout = new Timeout((int) TimeUnit.SECONDS.toMillis(5));

	@Test(timeout = 3000)
	public void test0() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test(timeout = 3000)
	public void test00() throws Exception {
		TimeUnit.SECONDS.sleep(2);
	}

	@Test(timeout = 3000)
	public void test1() {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
	}

	@Test(timeout = 10)
	public void test2() {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
	}

	@Test(timeout = 7000)
	public void test3() {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(6));
	}
}
