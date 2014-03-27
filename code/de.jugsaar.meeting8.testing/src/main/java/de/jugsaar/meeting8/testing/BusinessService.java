package de.jugsaar.meeting8.testing;

import java.util.List;

import javax.inject.Inject;

public class BusinessService {

	@Inject private ProjectRepository projectRepository;

	public BusinessService() {
		this(null);
	}

	public BusinessService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public List<Project> getProjectsByOwner(String name) {
		List<Project> result = projectRepository.findByOwner(name);
		return result;
	}

	public void registerProject(String ownerName, Project project) {
		projectRepository.registerProject(ownerName, project);
	}
}
