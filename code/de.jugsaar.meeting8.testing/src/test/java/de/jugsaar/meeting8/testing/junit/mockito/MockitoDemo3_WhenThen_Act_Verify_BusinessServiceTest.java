package de.jugsaar.meeting8.testing.junit.mockito;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.jugsaar.meeting8.testing.BusinessService;
import de.jugsaar.meeting8.testing.Project;
import de.jugsaar.meeting8.testing.ProjectRepository;

/**
 * @author tom
 * @see http://www.buschmais.de/wp-content/uploads/2013/03/Unit-Tests_mit_Mockito.pdf
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoDemo3_WhenThen_Act_Verify_BusinessServiceTest {

	@InjectMocks BusinessService cut = new BusinessService();

	@Mock ProjectRepository projectRepository;

	@Test
	public void testGetProjectByOwner() {

		String ownerName = "whoever";
		List<Project> projects = Arrays.asList(new Project());

		// when-then
		when(projectRepository.findByOwner(anyString())).thenReturn(projects);

		// act
		cut.getProjectsByOwner(ownerName);

		// verify
		verify(projectRepository).findByOwner(ownerName);
	}
}
