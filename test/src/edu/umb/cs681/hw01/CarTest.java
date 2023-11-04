package edu.umb.cs681.hw01;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CarTest {

    private static ArrayList<Car> usedCarsData = new ArrayList<>();

	@BeforeAll
    public static void setUp(){
		    usedCarsData = new TestFixtureInitializer().CarList();
	    };


// Price Comparator Test Cases

        @Test
            public void CheckPriceComparatorLowGroupLowestValue() {
                final double expected = 30000;
                Map<Boolean, List<Car>> LowGroup = usedCarsData.stream().sorted(
                                                Comparator.comparing(Car::getPrice)).collect(
                                                Collectors.partitioningBy((Car car) -> car.getPrice() < 50000.0F));

                double lowest = LowGroup.get(true).stream().mapToDouble(Car::getPrice).min().getAsDouble();
                assertEquals(expected, lowest);
            }


        @Test
            public void CheckPriceComparatorLowGroupHighestValue() {
                final double expected = 45000;
                Map<Boolean, List<Car>> LowGroup = usedCarsData.stream().sorted(
                                                Comparator.comparing(Car::getPrice)).collect(
                                                Collectors.partitioningBy((Car car) -> car.getPrice() < 50000));

                double highest = LowGroup.get(true).stream().mapToDouble(Car::getPrice).max().getAsDouble();
                assertEquals(expected, highest);
            }


        @Test
            public void CheckPriceComparatorLowGroupAverageValue() {
                final double expected = 38333.333333333336;
                Map<Boolean, List<Car>> LowGroup = usedCarsData.stream().sorted(
                                                Comparator.comparing(Car::getPrice)).collect(
                                                Collectors.partitioningBy((Car car) -> car.getPrice() < 50000));

                double average = LowGroup.get(true).stream().mapToDouble(Car::getPrice).average().getAsDouble();
                assertEquals(expected, average);
            }

        @Test
            public void CheckPriceComparatorLowGroupCountValue() {
                final int expected = 3;
                Map<Boolean, List<Car>> LowGroup = usedCarsData.stream().sorted(
                                                Comparator.comparing(Car::getPrice)).collect(
                                                Collectors.partitioningBy((Car car) -> car.getPrice() < 50000));

                int count = (int) LowGroup.get(true).stream().count();
                assertEquals(expected, count);
            }
        
        @Test
            public void CheckPriceComparatorHighGroupLowestValue() {
                final double expected = 68000;
                Map<Boolean, List<Car>> HighGroup = usedCarsData.stream().sorted(
                                                 Comparator.comparing(Car::getPrice)).collect(
                                                 Collectors.partitioningBy((Car car) -> car.getPrice() < 50000));

                double lowest = HighGroup.get(false).stream().mapToDouble(Car::getPrice).min().getAsDouble();
                assertEquals(expected, lowest);
            }


        @Test
            public void CheckPriceComparatorHighGroupHighestValue() {
                final double expected = 95000;
                Map<Boolean, List<Car>> HighGroup = usedCarsData.stream().sorted(
                                                Comparator.comparing(Car::getPrice)).collect(
                                                Collectors.partitioningBy((Car car) -> car.getPrice() < 50000));

                 double highest = HighGroup.get(false).stream().mapToDouble(Car::getPrice).max().getAsDouble();
                  assertEquals(expected, highest);
            }

        @Test
            public void CheckPriceComparatorHighGroupAverageValue() {
                final double expected = 81000;
                Map<Boolean, List<Car>> HighGroup = usedCarsData.stream().sorted(
                                                Comparator.comparing(Car::getPrice)).collect(
                                                Collectors.partitioningBy((Car car) -> car.getPrice() < 50000));

                double average = HighGroup.get(false).stream().mapToDouble(Car::getPrice).average().getAsDouble();
                assertEquals(expected, average);
            }

        @Test
            public void CheckPriceComparatorHighGroupCountValue() {
                final int expected = 3;
                Map<Boolean, List<Car>> HighGroup = usedCarsData.stream().sorted(
                                                Comparator.comparing(Car::getPrice)).collect(
                                                Collectors.partitioningBy((Car car) -> car.getPrice() < 50000));

                int count = (int) HighGroup.get(true).stream().count();
                assertEquals(expected, count);
            }


// Mileage Comparator Test Cases

        @Test
            public void CheckMileageComparatorLowGroupLowestValue() {
                final double expected = 15000;
                Map<Boolean, List<Car>> LowGroup = usedCarsData.stream().sorted(
                                                Comparator.comparing(Car::getMileage)).collect(
                                                Collectors.partitioningBy((Car car) -> car.getMileage() < 30000.0F));

                double lowest = LowGroup.get(true).stream().mapToDouble(Car::getMileage).min().getAsDouble();
                assertEquals(expected, lowest);
            }

         @Test
            public void CheckMileageComparatorLowGroupHighestValue() {
                final double expected = 25000;
                Map<Boolean, List<Car>> LowGroup = usedCarsData.stream().sorted(
                                                Comparator.comparing(Car::getMileage)).collect(
                                                Collectors.partitioningBy((Car car) -> car.getMileage() < 30000.0F));

                 double highest = LowGroup.get(true).stream().mapToDouble(Car::getMileage).max().getAsDouble();
                  assertEquals(expected, highest);
            }

        @Test
            public void CheckMileageComparatorLowGroupAverageValue() {
                final double expected = 20000;
                Map<Boolean, List<Car>> LowGroup = usedCarsData.stream().sorted(
                                                Comparator.comparing(Car::getMileage)).collect(
                                                Collectors.partitioningBy((Car car) -> car.getMileage() < 30000.0F));

                double average = LowGroup.get(true).stream().mapToDouble(Car::getMileage).average().getAsDouble();
                assertEquals(expected, average);
            }

        @Test
            public void CheckMileageComparatorLowGroupCountValue() {
                final int expected = 2;
                Map<Boolean, List<Car>> LowGroup = usedCarsData.stream().sorted(
                                                Comparator.comparing(Car::getMileage)).collect(
                                                Collectors.partitioningBy((Car car) -> car.getMileage() < 30000.0F));

                int count = (int) LowGroup.get(true).stream().count();
                assertEquals(expected, count);
             }

        @Test
            public void CheckMileageComparatorHighGroupLowestValue() {
                final double expected = 30000;
                Map<Boolean, List<Car>> HighGroup = usedCarsData.stream().sorted(
                                                 Comparator.comparing(Car::getMileage)).collect(
                                                 Collectors.partitioningBy((Car car) -> car.getMileage() < 30000.0F));

                double lowest = HighGroup.get(false).stream().mapToDouble(Car::getMileage).min().getAsDouble();
                assertEquals(expected, lowest);
            }
        
        @Test
            public void CheckMileageComparatorHighGroupHighestValue() {
                final double expected = 60000;
                Map<Boolean, List<Car>> HighGroup = usedCarsData.stream().sorted(
                                                 Comparator.comparing(Car::getMileage)).collect(
                                                 Collectors.partitioningBy((Car car) -> car.getMileage() < 30000.0F));

                double highest = HighGroup.get(false).stream().mapToDouble(Car::getMileage).max().getAsDouble();
                assertEquals(expected, highest);
            }
        
        @Test
            public void CheckMileageComparatorHighGroupAverageValue() {
                final double expected = 45000;
                Map<Boolean, List<Car>> HighGroup = usedCarsData.stream().sorted(
                                                Comparator.comparing(Car::getMileage)).collect(
                                                Collectors.partitioningBy((Car car) -> car.getMileage() < 30000.0F));

                double average = HighGroup.get(false).stream().mapToDouble(Car::getMileage).average().getAsDouble();
                assertEquals(expected, average);
            }

        @Test
            public void CheckMileageComparatorHighGroupCountValue() {
                final int expected = 4;
                Map<Boolean, List<Car>> HighGroup = usedCarsData.stream().sorted(
                                                Comparator.comparing(Car::getMileage)).collect(
                                                Collectors.partitioningBy((Car car) -> car.getMileage() < 30000.0F));

                int count = (int) HighGroup.get(false).stream().count();
                assertEquals(expected, count);
             }

 // Year Comparator Test Cases

        @Test
            public void CheckYearComparatorLowGroupValues(){
                Map<Boolean, List<Car>> LowGroup = usedCarsData.stream().sorted(
                        Comparator.comparing(Car::getYear)).collect(
                        Collectors.partitioningBy((Car car) -> car.getYear() < 2020));

                double min = LowGroup.get(true).stream().mapToDouble(Car::getYear).min().getAsDouble();
                double max = LowGroup.get(true).stream().mapToDouble(Car::getYear).max().getAsDouble();
                double average = LowGroup.get(true).stream().mapToDouble(Car::getYear).average().getAsDouble();
                double count = LowGroup.get(true).stream().count();
                double[] values = {min, max, average, count};
                double[] expected = {2014, 2019, 2017, 3};
                assertArrayEquals(expected, values);
            }

        @Test
            public void CheckYearComparatorHighGroupValues(){
                Map<Boolean, List<Car>> LowGroup = usedCarsData.stream().sorted(
                        Comparator.comparing(Car::getYear)).collect(
                        Collectors.partitioningBy((Car car) -> car.getYear() < 2020));

                double min = LowGroup.get(false).stream().mapToDouble(Car::getYear).min().getAsDouble();
                double max = LowGroup.get(false).stream().mapToDouble(Car::getYear).max().getAsDouble();
                double average = LowGroup.get(false).stream().mapToDouble(Car::getYear).average().getAsDouble();
                double count = LowGroup.get(false).stream().count();
                double[] values = {min, max, average, count};
                double[] expected = {2021, 2023, 2022, 3};
                assertArrayEquals(expected, values);
                
            }

    // Domination Count Comparator Test Cases

    @Test
    public void verifyParetoBasedHighPartitionStats(){
        double[] expected = {0, 0, 0, 6};
        Map<Boolean, List<Car>> LowGroup = usedCarsData.stream().sorted(
                                        Comparator.comparing(Car::getDominationCount)).collect(
                                        Collectors.partitioningBy((Car car) -> car.getDominationCount() < 1));

        double min = LowGroup.get(true).stream().mapToDouble(Car::getDominationCount).min().getAsDouble();
        double max = LowGroup.get(true).stream().mapToDouble(Car::getDominationCount).max().getAsDouble();
        double average = LowGroup.get(true).stream().mapToDouble(Car::getDominationCount).average().getAsDouble();
        double count = LowGroup.get(true).stream().count();
        double[] actual = {min, max, average, count};
        
        assertArrayEquals(expected, actual);
    }
}