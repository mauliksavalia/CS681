package edu.umb.cs681.hw03;

import java.util.ArrayList;

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

	public void setDominationCount(ArrayList<Car> usedCarsData) {
	for (int i = 0; i < usedCarsData.size(); i++) {

		if ((this.mileage <= usedCarsData.get(i).mileage) && (this.price <= usedCarsData.get(i).price)
				&& (this.year >= usedCarsData.get(i).year)) {

			if ((this.mileage < usedCarsData.get(i).mileage)
					|| (this.price < usedCarsData.get(i).price)
					|| (this.year > usedCarsData.get(i).year)) {
				this.dominationCount++;
				}
			}
		}
	}
	
	public static void main(String[] args) {

		ArrayList<Car> CarsData = new ArrayList<>();

		CarsData.add(new Car("RAV4", "Toyota", 35000, 2018, 30000));
		CarsData.add(new Car("Harrier", "TATA", 55000, 2023, 45000));
		CarsData.add(new Car("XUV700", "Mahindra", 30000, 2019, 80000));
		CarsData.add(new Car("M5", "BMW", 15000, 2022, 68000));
        CarsData.add(new Car("GWeagon", "Mercedes", 25000, 2014, 95000));
        CarsData.add(new Car("Seltos", "Kia", 60000, 2019, 40000));

		double averagePriceOfCars = CarsData.stream()
				.map(Car::getPrice)
				.reduce	(new CarPriceResultHolder(), 
						(result, price) -> {
											result.setAverage((result.getNumCarExamined() * result.getAverage() + price) / (result.getNumCarExamined() + 1));
											result.setNumCarExamined(result.getNumCarExamined() + 1);
											return result;},
						(finalResult, intermediateResult) -> finalResult
					  ).getAverage();

		System.out.println("Average Price of Cars: " + averagePriceOfCars);
	}
}