package de.jugsaar.meeting8.testing.junit.mockito;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

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
public class MockitoDemo2_Given_When_Then_BusinessServiceTest {

	@InjectMocks BusinessService cut = new BusinessService();

	@Mock ProjectRepository projectRepository;

	@Test
	public void testGetProjectByOwner() {

		String ownerName = "whoever";
		List<Project> projects = Arrays.asList(new Project());

		// given
		given(projectRepository.findByOwner(anyString())).willReturn(projects);

		// when
		cut.getProjectsByOwner(ownerName);

		// then
		verify(projectRepository).findByOwner(ownerName);
	}
}
