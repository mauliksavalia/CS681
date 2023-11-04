package edu.umb.cs681.hw04;


public class Car {
    private String make, model;
    private int mileage, year;
    private float price;

    //Constructor
    public Car(String make, String model, int mileage, int year, float price) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }
    // getter methods
    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public int getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public float getPrice() {
        return price;
    }

    // main method
    public static void main(String[] args){

    }
}




