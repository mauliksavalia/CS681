package edu.umb.cs681.hw02;

// import java.time.LocalDate;
// import java.time.Period;
// import java.util.LinkedList;

// public class Person {

//   private String firstName;
//   private String lastName;
//   private LocalDate dob;
  
//   private LinkedList<Dose> doses;

//   enum AgeCat {
//   YOUNG, MID, OLD
//   };

//   public Person(String firstName, String lastName, LocalDate dob) {
//     this.firstName = firstName;
//     this.lastName = lastName;
//     this.dob = dob;
//     this.doses = new LinkedList<>(); 
//   }

//   public String getFirstName() {
// 		return firstName;
// 	}

//   public String getLastName() {
// 		return lastName;
// 	}
//   public int getAge() {
//     // Calculate age based on DOB
//     return Period.between(dob, LocalDate.now()).getYears();
//   }

//   public AgeCat getAgeCat() {
//     int age = getAge();
//     if (age < 25) {
//       return AgeCat.YOUNG;
//     } else if (age < 60) { 
//       return AgeCat.MID;
//     } else {
//       return AgeCat.OLD;
//     }
//   }

//   public void addDose(Dose dose) {
//     doses.add(dose);
//   }
  
//   public LinkedList<Dose> getdoses() {
//     return doses;
//   }

//   public int getvacCounts() {
//     return doses.size();
//   }

//   public int getNumVaccinations() {
//       return 0;
//   }



// }


import java.util.*;
import java.time.*;

public class Person {

  private String firstName;
  private String lastName;
  private LocalDate dob;
  private List<Dose> doses;

  public Person(String firstName, String lastName, LocalDate dob) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = dob;
    this.doses = new ArrayList<>();
  }

  

// Getters and setters
  public String getFirstName() {
    return firstName;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public LocalDate getDob() {
    return dob; 
  }
  
  public void addDose(Dose dose) {
    doses.add(dose);
  }
  
  public List<Dose> getDoses() {
    return doses;
  }

  public int getAge() {
    return Period.between(dob, LocalDate.now()).getYears();
  }
  
  public AgeCat getAgeCat() {
    int age = getAge();
    if (age < 18) {
      return AgeCat.YOUNG;
    } else if (age < 65) { 
      return AgeCat.MID;
    } else {
      return AgeCat.OLD;
    }
  }
  
  public int getVacCount() {
    return doses.size();
  }
}
