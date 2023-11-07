package edu.umb.cs681.hw01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Car {
	private String model, make;
	private int mileage, year;
	private float price;
	private int dominationCount = 0;

	public Car(String model, String make, int mileage, int year, float price) {
		this.model = model;
		this.make = make;
		this.mileage = mileage;
		this.year = year;
		this.price = price;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	public int getMileage() {
		return mileage;
	}

	public float getPrice() {
		return price;
	}

	public int getDominationCount() {
		return dominationCount;
	}

	public void setDominationCount(ArrayList<Car> CarsData) {
	for (int i = 0; i < CarsData.size(); i++) {

		if ((this.mileage <= CarsData.get(i).mileage) 
				&& (this.price <= CarsData.get(i).price)
				&& (this.year >= CarsData.get(i).year)) {

			if ((this.mileage < CarsData.get(i).mileage)
					|| (this.price < CarsData.get(i).price)
					|| (this.year > CarsData.get(i).year)) {
				this.dominationCount++;
				}
			}
		}
	}
	
	public static void main(String[] args) {

		ArrayList<Car> CarsData = new ArrayList<>();

		CarsData.add(new Car("RAV4", "Toyota", 5000, 2018, 30000));
		CarsData.add(new Car("Harrier", "TATA", 55000, 2023, 45000));
		CarsData.add(new Car("XUV700", "Mahindra", 30000, 2021, 80000));
		CarsData.add(new Car("M5", "BMW", 15000, 2022, 68000));
        CarsData.add(new Car("GWeagon", "Mercedes", 25000, 2014, 95000));
        CarsData.add(new Car("Seltos", "Kia", 60000, 2019, 40000));

		// &&&&&&&&&&&&&&&&&&&&& Price Comparator &&&&&&&&&&&&&&&&&&&&&&&&

		System.out.println("***********************************");
		System.out.println("         Price Comparator");
		System.out.println("");
		System.out.println("LOW Group of Price Comparator (Price < 60000):");
		System.out.println("");
		Map<Boolean, List<Car>> PriceCategory = 
				CarsData.stream()
					.sorted(Comparator.comparing(Car::getPrice))
					.collect(Collectors.partitioningBy(
								(Car car) -> car.getPrice() < 60000.0F));

		double averageOfLowPrice = PriceCategory.get(true).stream().mapToDouble(Car::getPrice).average().orElse(Double.NaN);
		double highestOfLowPrice = PriceCategory.get(true).stream().mapToDouble(Car::getPrice).max().orElse(Double.NaN);
		double lowestOfLowPrice = PriceCategory.get(true).stream().mapToDouble(Car::getPrice).min().orElse(Double.NaN);
		int countOfLowPrice = (int) PriceCategory.get(true).stream().count();

		System.out.println("Average Value of Cars LowGroup Price Category: " + averageOfLowPrice);
		System.out.println("Highest Value of Cars LowGroup Price Category: " + highestOfLowPrice);
		System.out.println("Lowest Value of Cars LowGroup Price Category: " + lowestOfLowPrice);
		System.out.println("Count of Cars LowGroup Price Category: " + countOfLowPrice);

		System.out.println("");
		System.out.println("HIGH Group of Price Comparator (Price >= 60000):");
		System.out.println("");

		double averageOfHighPrice = PriceCategory.get(false).stream().mapToDouble(Car::getPrice).average().orElse(Double.NaN);
		double highestOfHighPrice = PriceCategory.get(false).stream().mapToDouble(Car::getPrice).max().orElse(Double.NaN);
		double lowestOfHighPrice = PriceCategory.get(false).stream().mapToDouble(Car::getPrice).min().orElse(Double.NaN);
		int countOfHighPrice = (int) PriceCategory.get(false).stream().count();	

		System.out.println("Average Value of Cars HighGroup Price Category: " + averageOfHighPrice);
		System.out.println("Highest Value of Cars HighGroup Price Category: " + highestOfHighPrice);
		System.out.println("Lowest Value of Cars HighGroup Price Category: " + lowestOfHighPrice);
		System.out.println("Count of Cars HighGroup Price Category: " + countOfHighPrice);

		// &&&&&&&&&&&&&&&&&&&&& Year Comparator &&&&&&&&&&&&&&&&&&&&&&&&

		System.out.println("");
		System.out.println("***********************************");
		System.out.println("         Year Comparator");
		System.out.println("");
		System.out.println("LOW Group of Year Comparator (Year < 2020):");
		System.out.println("");

		Map<Boolean, List<Car>> YearCategory = 
				CarsData.stream()
					.sorted(Comparator.comparing(Car::getYear))
					.collect(Collectors.partitioningBy(
								(Car car) -> car.getYear() < 2020));

		int averageOfLowYear = (int) YearCategory.get(true).stream().mapToDouble(Car::getYear).average().orElse(Double.NaN);
		int highestOfLowYear = (int) YearCategory.get(true).stream().mapToDouble(Car::getYear).max().orElse(Double.NaN);
		int lowestOfLowYear = (int) YearCategory.get(true).stream().mapToDouble(Car::getYear).min().orElse(Double.NaN);
		int countOfLowYear = (int) YearCategory.get(true).stream().count();

		System.out.println("Average Value of Cars LowGroup Year Category: " + averageOfLowYear);
		System.out.println("Highest Value of Cars LowGroup Year Category: " + highestOfLowYear);
		System.out.println("Lowest Value of Cars LowGroup Year Category: " + lowestOfLowYear);
		System.out.println("Count of Cars LowGroup Year Category: " + countOfLowYear);

		System.out.println("");
		System.out.println("HIGH Group of Year Comparator (Year >= 2020):");
		System.out.println("");

		int averageOfHighYear = (int) YearCategory.get(false).stream().mapToDouble(Car::getYear).average().orElse(Double.NaN);
		int highestOfHighYear = (int) YearCategory.get(false).stream().mapToDouble(Car::getYear).max().orElse(Double.NaN);
		int lowestOfHighYear = (int) YearCategory.get(false).stream().mapToDouble(Car::getYear).min().orElse(Double.NaN);
		int countOfHighYear = (int) YearCategory.get(false).stream().count();

		System.out.println("Average Value of Cars HighGroup Year Category: " + averageOfHighYear);
		System.out.println("Highest Value of Cars HighGroup Year Category: " + highestOfHighYear);
		System.out.println("Lowest Value of Cars HighGroup Year Category: " + lowestOfHighYear);
		System.out.println("Count of Cars HighGroup Year Category: " + countOfHighYear);


		// &&&&&&&&&&&&&&&&&&&&& Mileage Comparator &&&&&&&&&&&&&&&&&&&&&&&&

		System.out.println("");
		System.out.println("***********************************");
		System.out.println("       Mileage Comparator");
		System.out.println("");
		System.out.println("LOW Group of Mileage Comparator (Mileage < 30000):");
		System.out.println("");

		Map<Boolean, List<Car>> MileageCategory = 
				CarsData.stream()
					.sorted(Comparator.comparing(Car::getMileage))
					.collect(Collectors.partitioningBy(
								(Car car) -> car.getMileage() < 30000.0F));

		double averageOfLowMileage = MileageCategory.get(true).stream().mapToDouble(Car::getMileage).average().orElse(Double.NaN);
		double highestOfLowMileage = MileageCategory.get(true).stream().mapToDouble(Car::getMileage).max().orElse(Double.NaN);
		double lowestOfLowMileage = MileageCategory.get(true).stream().mapToDouble(Car::getMileage).min().orElse(Double.NaN);
		int countOfLowMileage = (int) MileageCategory.get(true).stream().count();

		System.out.println("Average Value of Cars LowGroup Mileage Category: " + averageOfLowMileage);
		System.out.println("Highest Value of Cars LowGroup Mileage Category: " + highestOfLowMileage);
		System.out.println("Lowest Value of Cars LowGroup Mileage Category: " + lowestOfLowMileage);
		System.out.println("Count of Cars LowGroup Mileage Category: " + countOfLowMileage);

		System.out.println("");
		System.out.println("HIGH Group of Mileage Comparator (Mileage >= 30000):");
		System.out.println("");

		double averageOfHighMileage = MileageCategory.get(false).stream().mapToDouble(Car::getMileage).average().orElse(Double.NaN);
		double highestOfHighMileage = MileageCategory.get(false).stream().mapToDouble(Car::getMileage).max().orElse(Double.NaN);
		double lowestOfHighMileage = MileageCategory.get(false).stream().mapToDouble(Car::getMileage).min().orElse(Double.NaN);
		int countOfHighMileage = (int) MileageCategory.get(false).stream().count();

		System.out.println("Average Value of Cars HighGroup Mileage Category: " + averageOfHighMileage);
		System.out.println("Highest Value of Cars HighGroup Mileage Category: " + highestOfHighMileage);
		System.out.println("Lowest Value of Cars HighGroup Mileage Category: " + lowestOfHighMileage);
		System.out.println("Count of Cars HighGroup Mileage Category: " + countOfHighMileage);

		// &&&&&&&&&&&&&&&&&&&&& Domination Count Comparator &&&&&&&&&&&&&&&&&&&&&&&&

		System.out.println("");
		System.out.println("***********************************");
		System.out.println("   Domination Count Comparator");
		System.out.println("");
		System.out.println("LOW Group of Domination Count Comparator (Domination Count < 0.5):");
		System.out.println("");

		for (Car car : CarsData) {
			car.setDominationCount(CarsData);
		}

		Map<Boolean, List<Car>> DominationCountCategory = 
				CarsData.stream()
					.sorted(Comparator.comparing(Car::getDominationCount))
					.collect(Collectors.partitioningBy(
								(Car car) -> car.getDominationCount() < 0.5));

		double averageOfLowDominationCount = DominationCountCategory.get(true).stream().mapToDouble(Car::getDominationCount).average().orElse(Double.NaN);
		double highestOfLowDominationCount = DominationCountCategory.get(true).stream().mapToDouble(Car::getDominationCount).max().orElse(Double.NaN);
		double lowestOfLowDominationCount = DominationCountCategory.get(true).stream().mapToDouble(Car::getDominationCount).min().orElse(Double.NaN);
		double countOfLowDominationCount = DominationCountCategory.get(true).stream().count();

		System.out.println("Average Value of Cars LowGroup Domination Count Category: " + averageOfLowDominationCount);
		System.out.println("Highest Value of Cars LowGroup Domination Count Category: " + highestOfLowDominationCount);
		System.out.println("Lowest Value of Cars LowGroup Domination Count Category: " + lowestOfLowDominationCount);
		System.out.println("Count of Cars LowGroup Domination Count Category: " + countOfLowDominationCount);

		System.out.println("");
		System.out.println("HIGH Group of Domination Count Comparator (Domination Count >= 0.5):");
		System.out.println("");

		double averageOfHighDominationCount = DominationCountCategory.get(false).stream().mapToDouble(Car::getDominationCount).average().orElse(Double.NaN);
		double highestOfHighDominationCount = DominationCountCategory.get(false).stream().mapToDouble(Car::getDominationCount).max().orElse(Double.NaN);
		double lowestOfHighDominationCount = DominationCountCategory.get(false).stream().mapToDouble(Car::getDominationCount).min().orElse(Double.NaN);
		double countOfHighDominationCount = DominationCountCategory.get(false).stream().count();

		System.out.println("Average Value of Cars HighGroup Domination Count Category: " + averageOfHighDominationCount);
		System.out.println("Highest Value of Cars HighGroup Domination Count Category: " + highestOfHighDominationCount);
		System.out.println("Lowest Value of Cars HighGroup Domination Count Category: " + lowestOfHighDominationCount);
		System.out.println("Count of Cars HighGroup Domination Count Category: " + countOfHighDominationCount);
	}
}
