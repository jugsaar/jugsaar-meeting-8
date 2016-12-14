package de.jugsaar.meeting8.testing.junit;

import static de.jugsaar.meeting8.testing.Version.parseVersion;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.jugsaar.meeting8.testing.Version;

public class NamingConventionsVersionTests {

	static final Version VERSION_SPRING_4_0_2_RELEASE = new Version(4, 0, 2, "RELEASE");
	static final String VERSION_STRING_SPRING_4_0_2_RELEASE = "4.0.2.RELEASE";

	@Before
	public void setup() {

	}

	@Test
	public void versionQuality() {

		assertEquals(parseVersion(VERSION_STRING_SPRING_4_0_2_RELEASE), VERSION_SPRING_4_0_2_RELEASE);
	}
}
