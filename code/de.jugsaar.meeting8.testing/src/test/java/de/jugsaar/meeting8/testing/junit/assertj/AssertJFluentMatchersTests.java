package de.jugsaar.meeting8.testing.junit.assertj;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import de.jugsaar.meeting8.testing.Version;

/**
 * @author Thomas Darimont
 * @see http://joel-costigliola.github.io/assertj/
 */
public class AssertJFluentMatchersTests {

	private static final Version VERSION = Version.parseVersion("1.2.3");

	@Test
	public void versionShouldBeEqualWithItself() {

		assertThat(VERSION).isEqualTo(new Version(1, 2, 3));
	}

	@Test
	public void versionComponentsShouldCorrespondToThoseFromVersionString() {

		assertThat(VERSION.getMajor()).isEqualTo(1);
		assertThat(VERSION.getMinor()).isEqualTo(2);
		assertThat(VERSION.getPatch()).isEqualTo(3);
	}
}
