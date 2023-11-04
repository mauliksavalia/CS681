package edu.umb.cs681.hw04;

import java.util.List;
import java.util.stream.IntStream;

public class Manhattan implements DistanceMetric {

	public double distance(List<Double> p1, List<Double> p2) {

		double manhattanDistance = 
			IntStream
				.range(0, p1.size())
				.mapToDouble(i -> Math.abs(p1.get(i) - p2.get(i)))
				.sum();

		return manhattanDistance;
	}
}
