package edu.umb.cs681.hw03;

import java.util.ArrayList;

public class TestFixtureInitializer {

    public ArrayList<Car> CarList(){

        ArrayList<Car> usedCarsData = new ArrayList<>();

		usedCarsData.add(new Car("RAV4", "Toyota", 35000, 2018, 30000));
		usedCarsData.add(new Car("Harrier", "TATA", 55000, 2023, 45000));
		usedCarsData.add(new Car("XUV700", "Mahindra", 30000, 2019, 80000));
		usedCarsData.add(new Car("M5", "BMW", 15000, 2022, 68000));
        usedCarsData.add(new Car("GWeagon", "Mercedes", 25000, 2014, 95000));
        usedCarsData.add(new Car("Seltos", "Kia", 60000, 2019, 40000));

        return usedCarsData;
    };
}
