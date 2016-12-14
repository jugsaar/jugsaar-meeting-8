package de.jugsaar.meeting8.testing.junit.hamcrest.systemrules;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

/**
 * @author Thomas Darimont
 * @see http://www.stefan-birkner.de/system-rules/
 */
public class SystemRulesExampleCaputreSystemOutTests {

	@Rule public final StandardOutputStreamLog log = new StandardOutputStreamLog();

	@Test
	public void captureOutputStream() {

		System.out.print("hello world");

		assertEquals("hello world", log.getLog());
	}
}
