package de.jugsaar.meeting8.testing.junit.fest;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.failBecauseExceptionWasNotThrown;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @author tom
 * @see https://github.com/alexruiz/fest-assert-2.x/wiki
 * @see https://github.com/alexruiz/fest-assert-2.x/wiki/Using-fest-assertions
 */
public class FestExampleTests {

	List<String> items = Arrays.asList("1", "2", "3");

	@Test
	public void foo() {

		try {
			items.get(9); // argggl !
			// if IndexOutOfBoundsException was not thrown, test would fail with message :
			// "Expected IndexOutOfBoundsException to be thrown"
			failBecauseExceptionWasNotThrown(IndexOutOfBoundsException.class);
		} catch (Exception e) {
			assertThat(e).isInstanceOf(IndexOutOfBoundsException.class).hasMessage("9").hasNoCause();
		}
	}
}
