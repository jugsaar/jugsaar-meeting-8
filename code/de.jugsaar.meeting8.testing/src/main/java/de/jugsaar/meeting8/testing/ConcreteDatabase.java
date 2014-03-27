package de.jugsaar.meeting8.testing;

import java.io.IOException;

public class ConcreteDatabase implements Database {

	private boolean initialized;

	@Override
	public void close() throws IOException {
		if (!initialized) {
			throw new IllegalStateException("Not initialized");
		}
	}

	@Override
	public void initialize() {

		if (initialized) {
			throw new IllegalStateException("Already initialized");
		}

		initialized = true;
	}

	@Override
	public String getData() {

		if (!initialized) {
			throw new IllegalStateException("Not initialized");
		}

		return "DATA";
	}

}
