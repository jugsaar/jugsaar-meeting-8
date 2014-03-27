package de.jugsaar.meeting8.testing.junit.mockito;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.isA;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.hamcrest.Matcher;
import org.junit.Test;

import de.jugsaar.meeting8.testing.BusinessService;
import de.jugsaar.meeting8.testing.InMemoryProjectRepository;
import de.jugsaar.meeting8.testing.Project;
import de.jugsaar.meeting8.testing.ProjectRepository;

/**
 * @author tom
 * @see http://www.buschmais.de/wp-content/uploads/2013/03/Unit-Tests_mit_Mockito.pdf
 */

public class MockitoDemo5_Spy_CustomMatcher_BusinessServiceTest {

	ProjectRepository projectRepository = spy(new InMemoryProjectRepository());

	BusinessService cut = new BusinessService(projectRepository);

	@Test
	public void registerProject() {

		String ownerName = "bubu";
		Project project = new Project();

		cut.registerProject(ownerName, project);

		// Whitbox-Tests
		verify(projectRepository, only()).registerProject(argThat(isAStringThatContains("bu")), any(Project.class));
	}

	private Matcher<String> isAStringThatContains(String part) {
		return allOf(isA(String.class), containsString(part));
	}
}
