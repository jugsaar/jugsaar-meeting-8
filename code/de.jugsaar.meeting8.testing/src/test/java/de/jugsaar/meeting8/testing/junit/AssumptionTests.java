package de.jugsaar.meeting8.testing.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

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

		assumeTrue(springVersion.toString(), springVersion.is("4.0.1"));
		// assumeTrue(Boolean.getBoolean("databaseAvailable"));

		assertNotNull(database.getData());
	}
}
