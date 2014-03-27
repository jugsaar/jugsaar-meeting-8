package de.jugsaar.meeting8.testing.junit;

import java.io.Serializable;

import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import com.pobox.cbarham.testhelpers.ComparabilityTestCase;
import com.pobox.cbarham.testhelpers.EqualsHashCodeTestCase;
import com.pobox.cbarham.testhelpers.SerializabilityTestCase;

import de.jugsaar.meeting8.testing.Version;

/**
 * The {@link Enclosed} JUnit Runner allow to run test in nested static inner classes.
 * 
 * @author Thomas Darimont
 */
@RunWith(Enclosed.class)
public class StructuredVersionTests {

	public static class Comparability extends ComparabilityTestCase<Version> {

		@Override
		protected Version createLessInstance() throws Exception {
			return new Version(3, 0, 2, "RELEASE");
		}

		@Override
		protected Version createEqualInstance() throws Exception {
			return new Version(4, 0, 2, "RELEASE");
		}

		@Override
		protected Version createGreaterInstance() throws Exception {
			return new Version(4, 0, 3);
		}
	}

	public static class Serializability extends SerializabilityTestCase {

		@Override
		protected Serializable createInstance() throws Exception {
			return new Version(4, 0, 2, "RELEASE");
		}
	}

	public static class HashCodeAndEquals extends EqualsHashCodeTestCase {

		@Override
		protected Object createInstance() throws Exception {
			return new Version(4, 0, 2, "RELEASE");
		}

		@Override
		protected Object createNotEqualInstance() throws Exception {
			return new Version(4, 0, 1, "RELEASE");
		}
	}
}
