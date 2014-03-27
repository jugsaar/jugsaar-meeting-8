package de.jugsaar.meeting8.testing.junit.mockito;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import de.jugsaar.meeting8.testing.BusinessService;
import de.jugsaar.meeting8.testing.InMemoryProjectRepository;
import de.jugsaar.meeting8.testing.Project;
import de.jugsaar.meeting8.testing.ProjectRepository;

/**
 * @author tom
 * @see http://www.buschmais.de/wp-content/uploads/2013/03/Unit-Tests_mit_Mockito.pdf
 */
public class MockitoDemo6_Spy_ArgCaptor_BusinessServiceTest {

	ProjectRepository projectRepository = spy(new InMemoryProjectRepository());

	BusinessService cut = new BusinessService(projectRepository);

	@Test
	public void registerProject() {

		String ownerName = "bubu";
		Project project = new Project();

		cut.registerProject(ownerName, project);

		ArgumentCaptor<String> ownerNameCaptor = ArgumentCaptor.forClass(String.class);

		// Whitbox-Tests
		verify(projectRepository, only()).registerProject(ownerNameCaptor.capture(), any(Project.class));

		// Vorsicht, mockito hat sich die referenz auf den übergebenen Parameter gemerkt - der Parameter könnte
		// zwischenzeitlich verändert worden sein. Immutable FTW!
		assertThat(ownerNameCaptor.getValue(), is(ownerName));

	}
}
