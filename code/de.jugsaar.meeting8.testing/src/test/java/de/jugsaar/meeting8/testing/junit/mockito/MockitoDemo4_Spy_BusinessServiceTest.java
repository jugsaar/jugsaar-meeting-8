package de.jugsaar.meeting8.testing.junit.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.noMoreInteractions;

import java.util.List;

import org.junit.Test;

import de.jugsaar.meeting8.testing.BusinessService;
import de.jugsaar.meeting8.testing.InMemoryProjectRepository;
import de.jugsaar.meeting8.testing.Project;
import de.jugsaar.meeting8.testing.ProjectRepository;

/**
 * @author tom
 * @see http://www.buschmais.de/wp-content/uploads/2013/03/Unit-Tests_mit_Mockito.pdf
 */

public class MockitoDemo4_Spy_BusinessServiceTest {

	ProjectRepository projectRepository = spy(new InMemoryProjectRepository());

	BusinessService cut = new BusinessService(projectRepository);

	@Test
	public void shouldFindProjectAfterRegistration() {

		String ownerName = "bubu";
		Project project = new Project();

		cut.registerProject(ownerName, project);
		List<Project> result = cut.getProjectsByOwner(ownerName);

		// Whitbox-Tests
		verify(projectRepository, //
				noMoreInteractions() // only(), never(), times(n), atLeastOnce(),
		).findByOwner(ownerName);

		// herkšmmliche Blackbox-Tests
		assertEquals(1, result.size());
		assertEquals(result.get(0), project);
	}
}
