package edu.umb.cs681.hw04;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Distance {
	public static double get(List<Double> p1, List<Double> p2) {
		return get(p1, p2, new Euclidean());
	}

	public static double get(List<Double> p1, List<Double> p2, DistanceMetric metric) {
		return metric.distance(p1, p2);
	}

	public static List<List<Double>> matrix(List<List<Double>> points) {
		return matrix(points, new Euclidean());
	}

	public static List<List<Double>> matrix(List<List<Double>> points, DistanceMetric metric) {
		// This method is not that efficient; there is a room for performance
		// improvement by, for example, taking advantage of the symmetric nature
		// of a distance matrix. But, let's not worry about that here.   
		int numOfPoints = points.size();
		List<List<Double>> distanceMatrix = initDistanceMatrix(numOfPoints);

			IntStream
				.range(0, numOfPoints)
				.forEach(i -> {
					IntStream
						.range(i+1, numOfPoints)
						.forEach (j-> {
							double distance = metric.distance(points.get(i), points.get(j));
							distanceMatrix.get(i).set(j, distance);
							distanceMatrix.get(j).set(i, distance);
						});
					// List<Double> current = points.get(i);
					// for (int j = i + 1; j < numOfPoints; j++) {
					// 	List<Double> peer = points.get(j);
					// 	double distance = metric.distance(current, peer);

					// 	// Set the distance in both the (i, j) and (j, i) positions
					// 	distanceMatrix.get(i).set(j, distance);
					// 	distanceMatrix.get(j).set(i, distance);
					// }
				});

		return distanceMatrix;
	}

	private static List<List<Double>> initDistanceMatrix(int numOfPoints) {
		List<List<Double>> distanceMatrix = new ArrayList<>(numOfPoints);
		for(int i = 0; i < numOfPoints; i++) {
			Double[] vector = new Double[numOfPoints];
			Arrays.fill(vector, 0.0);
			distanceMatrix.add(Arrays.asList(vector));
		}
		return distanceMatrix;
	}

	public static void main(String[] args) {
		int numPoints = 1500;
        int numDimensions = 150;

        List<List<Double>> points = generateRandomPoints(numPoints, numDimensions);

		System.out.println("");

        List<List<Double>> euclidean_Distance = Distance.matrix(points, new Euclidean());
        double euclidean_result = euclidean_Distance.get(0).get(1);
		System.out.println("Euclidean Distance: " + euclidean_result);

		List<List<Double>> manhattan_Distance = Distance.matrix(points, new Manhattan());
		double manhattan_result = manhattan_Distance.get(0).get(1);
		System.out.println("Manhattan Distance: " + manhattan_result);

		List<List<Double>> cosine_Distance = Distance.matrix(points, new Cosine());
		double cosine_result = cosine_Distance.get(0).get(1);
		System.out.println("Cosine Distance: " + cosine_result);


    }

	private static List<List<Double>> generateRandomPoints(int numPoints, int numDimensions) {
		List<List<Double>> points = new ArrayList<>();
		for (int i = 0; i < numPoints; i++) {
			List<Double> point = new ArrayList<>();
			for (int j = 0; j < numDimensions; j++) {
				point.add(Math.random());
			}
			points.add(point);
		}
		return points;
	}

}
