package edu.umb.cs681.hw04;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceTest {

    @Test
    public void testLargeDistanceMatrix() {
        int numPoints = 1000;
        int numDimensions = 100;

        List<List<Double>> points = generateRandomPoints(numPoints, numDimensions);
        List<List<Double>> distanceMatrix = Distance.matrix(points, new Euclidean());

        double distance = distanceMatrix.get(0).get(1);
        assertEquals(distance, Distance.get(points.get(0), points.get(1), new Euclidean()), 0.001);
    }

    private List<List<Double>> generateRandomPoints(int numPoints, int numDimensions) {
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
//In this JUnit test case, we generate 1000 points, each with 100 dimensions, and calculate the distance matrix using the Distance class. You can then add your specific assertions to test the correctness of the distance calculations.





