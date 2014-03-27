package de.jugsaar.meeting8.testing;

import java.util.List;

public interface ProjectRepository {

	void registerProject(String owner, Project project);

	List<Project> findByOwner(String name);
}
