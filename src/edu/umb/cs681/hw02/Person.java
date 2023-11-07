package edu.umb.cs681.hw02;

import java.util.*;
import java.time.*;

public class Person {

  private String firstName;
  private String lastName;
  private LocalDate dob;
  private LinkedList<Dose> doses;

  public Person(String firstName, String lastName, LocalDate dob, LinkedList<Dose> doses) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = dob;
    this.doses  = doses;
  }

// Getters Methods 
  public String getFirstName() {
    return firstName;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public LocalDate getDob() {
    return dob; 
  }
   
  public int getAge() {
    return Period.between(dob, LocalDate.now()).getYears();
  }
  
  public AgeCat getAgeCat() {
    int age = getAge();
    if (age < 21) {
      return AgeCat.YOUNG;
    } 
    else if (age < 55) { 
      return AgeCat.MID;
    } 
    else {
      return AgeCat.OLD;
    }
  }
  
  public LinkedList<Dose> getDoses() {
    return doses;
  }

  public int getVacCount() {
    return doses.size();
  }
}
