package de.jugsaar.meeting8.testing;

import java.io.Closeable;

public interface Database extends Closeable {

	void initialize();

	String getData();
}
