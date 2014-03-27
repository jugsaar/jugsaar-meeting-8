package de.jugsaar.meeting8.testing.junit;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeThat;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import de.jugsaar.meeting8.testing.ConcreteDatabase;
import de.jugsaar.meeting8.testing.Database;
import de.jugsaar.meeting8.testing.Version;

public class AssumptionTests {

	Database database = new ConcreteDatabase();

	public static final Version springVersion = Version.from(ApplicationContext.class);

	/**
	 * -DdatabaseAvailable=false
	 */
	@Test
	public void getDataMayThrowIfDbNotAvailable() {

		System.out.println(springVersion);

		// Wenn wir mit version 4.0.2 laufen welche den bekannten Bug X hat, wollen wir abbrechen.
		assumeFalse(springVersion.toString(), springVersion.is("4.0.2")); // AssuptionViolationException
		// assumeTrue(Boolean.getBoolean("databaseAvailable"));

		assertNotNull(database.getData());
	}

	@Test
	public void osxSpecificTest() {

		assumeThat(System.getProperty("os.name"), startsWith("Mac"));

		assertTrue("hipster OS rules", false);
	}
}
