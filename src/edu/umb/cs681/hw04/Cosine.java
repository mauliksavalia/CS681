package edu.umb.cs681.hw04;

import java.util.List;
import java.util.stream.IntStream;

public class Cosine implements DistanceMetric {

    @Override
    public double distance(List<Double> p1, List<Double> p2) {

        double dotProduct = 0.0;
        double x1 = 0.0;
        double x2 = 0.0;
        
        dotProduct = 
            IntStream
                .range(0, p1.size())
                .mapToDouble(i -> p1.get(i) * p2.get(i))
                .sum();

        x1 = 
            IntStream
                .range(0, p1.size())
                .mapToDouble(i -> p1.get(i) * p1.get(i))
                .sum();

        x2 = 
            IntStream
                .range(0, p2.size())
                .mapToDouble(i -> p2.get(i) * p2.get(i)) 
                .sum();

        double cosineSimilarity = dotProduct / (Math.sqrt(x1) * Math.sqrt(x2));

        double cosineDistance = 1 - cosineSimilarity;

        return cosineDistance;
    }
    
}