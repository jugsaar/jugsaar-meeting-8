package de.jugsaar.meeting8.testing;

public class Fibonacci {

	public static int compute(int n) {

		if (n < 0) {
			throw new IllegalArgumentException("n must be positive!");
		}

		switch (n) {
			case 0:
				return 0;
			case 1:
			case 2:
				return 1;
			default:
				return compute(n - 2) + compute(n - 1);
		}
	}
}
