package de.jugsaar.meeting8.testing.junit.mockito;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class MockitoDemo7_Stubbing {

	// ReturnsMoreEmptyValues
	// ReturnsSmartNulls
	// ReturnsMocks
	// ReturnsDeepStubs

	@Test
	public void deepStubbing() throws SQLException {

		Statement stmt = mock(Statement.class, RETURNS_DEEP_STUBS);
		ResultSet rsMock = stmt.executeQuery(startsWith("select *"));

		when(rsMock.next()).thenReturn(true, false);
		when(rsMock.getString("bubu")).thenReturn("42");

		ResultSet rs = stmt.executeQuery("select * from bubu");
		while (rs.next()) {
			assertThat(rs.getString("bubu"), is("42"));
		}
	}
}
