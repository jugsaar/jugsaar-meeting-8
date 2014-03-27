package de.jugsaar.meeting8.testing;

import java.io.Serializable;

public class Version implements Comparable<Version>, Serializable {

	private static final long serialVersionUID = 4182310394392219344L;

	private final int major;
	private final int minor;
	private final int patch;
	private final String qualifier;

	public static Version from(Class<?> clazz) {

		Package pkg = clazz.getPackage();

		String versionStr = null;
		do {
			versionStr = pkg.getImplementationVersion();
			pkg = Package.getPackage(pkg.getName().substring(0, pkg.getName().lastIndexOf('.')));
		} while ("".equals(versionStr));

		return parseVersion(versionStr);
	}

	public Version(int major, int minor, int patch) {
		this(major, minor, patch, "");
	}

	public Version(int major, int minor, int patch, String qualifier) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
		this.qualifier = qualifier;
	}

	public boolean is(String versionStr) {
		return compareTo(Version.parseVersion(versionStr)) == 0;
	}

	public boolean newerThan(String versionStr) {
		return compareTo(Version.parseVersion(versionStr)) > 0;
	}

	public boolean olderThan(String versionStr) {
		return compareTo(Version.parseVersion(versionStr)) < 0;
	}

	public static Version parseVersion(String versionStr) {

		int major = 0;
		int minor = 0;
		int patch = 0;
		String qualifier = "";

		String[] versionComponents = versionStr.split("\\.");

		switch (versionComponents.length) {
			case 4:
				qualifier = versionComponents[3].trim();
			case 3:
				patch = Integer.parseInt(versionComponents[2].trim());
			case 2:
				minor = Integer.parseInt(versionComponents[1].trim());
			case 1:
				major = Integer.parseInt(versionComponents[0].trim());
				break;

			default:
				throw new IllegalArgumentException("Cannot parse version: " + versionStr);
		}

		return new Version(major, minor, patch, qualifier);
	}

	public int getMajor() {
		return major;
	}

	public int getMinor() {
		return minor;
	}

	public int getPatch() {
		return patch;
	}

	public String getQualifier() {
		return qualifier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + major;
		result = prime * result + minor;
		result = prime * result + patch;
		result = prime * result + ((qualifier == null) ? 0 : qualifier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Version other = (Version) obj;
		if (major != other.major)
			return false;
		if (minor != other.minor)
			return false;
		if (patch != other.patch)
			return false;
		if (qualifier == null) {
			if (other.qualifier != null)
				return false;
		} else if (!qualifier.equals(other.qualifier))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Version [major=" + major + ", minor=" + minor + ", patch=" + patch
				+ (qualifier != null && qualifier.length() > 0 ? ", qualifier=" + qualifier : "") + "]";
	}

	@Override
	public int compareTo(Version that) {

		if (this.major != that.major) {
			return this.major - that.major;
		}

		if (this.minor != that.minor) {
			return this.minor - that.minor;
		}

		if (this.patch != that.patch) {
			return this.patch - that.patch;
		}

		return 0;
	}
}
