package de.jugsaar.meeting8.testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryProjectRepository implements ProjectRepository {

	Map<String, List<Project>> database = new HashMap<>();

	@Override
	public List<Project> findByOwner(String name) {
		return database.get(name);
	}

	@Override
	public void registerProject(String owner, Project project) {

		database.put(owner, addToList(project, database.get(owner)));
	}

	private List<Project> addToList(Project project, List<Project> list) {

		if (list == null) {
			return Collections.singletonList(project);
		}

		ArrayList<Project> result = new ArrayList<>(list);
		result.add(project);

		return result;
	}
}
