package edu.umb.cs681.hw02;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

public static void main(String[] args) {


//   // Generate 1000+ random Person instances
//   List<Person> people = generateRandomPeople(numPeople);

//   // Vaccination rate for each age category
//   Map<AgeCat, Double> vacRatesByAge = people.stream()
//     .collect(Collectors.groupingBy(Person::getAgeCat, 
//         Collectors.summingDouble(p -> p.getVacCount()/people.size()*100)));

//   // Avg vac count for each age category
//   Map<AgeCat, Double> avgVacByAge = people.stream()
//     .collect(Collectors.groupingBy(Person::getAgeCat,
//         Collectors.averagingDouble(Person::getVacCount)));

//   // Avg age of unvaccinated
//   Map<Boolean, Double> vaxPartition = people.stream()
//     .collect(Collectors.partitioningBy(p -> p.getVacCount() > 0,
//         Collectors.averagingDouble(Person::getAge)));

//   double avgUnvacAge = vaxPartition.get(false);

//   // Print output
//   System.out.println("Vaccination Rates:");
//   vacRatesByAge.forEach((k, v) -> System.out.println(k + ": " + v));
  
//   System.out.println("\nAvg Vacs:");
//   avgVacByAge.forEach((k, v) -> System.out.println(k + ": " + v));  

//   System.out.println("\nAvg age unvaccinated: " + avgUnvacAge);

// }

// Method to generate random Person instances
// public static List<Person> generateRandomPeople(int numPeople) {
  
//   List<Person> people = new ArrayList<>();
//   for (int i = 0; i < numPeople; i++) {
//         List<Double> point = new ArrayList<>();
//       // Logic to generate random Person instances
//       people.add((Person) point);
//     }
//   return people; 
//   } 

        LinkedList<Person> people = generateRandomPeople(1500);

        // Calculate vaccination rate for each age category
        Map<Person.AgeCat, Double> vaccinationRateByAgeCategory = people.stream()
                .collect(Collectors.groupingBy(
                        p -> getAgeCat(p.getAge()),
                    Collectors.averagingDouble(p -> p.getDoses().size())));

        System.out.println("Vaccination Rate by Age Category:");
        
        vaccinationRateByAgeCategory.forEach((AgeCat, avgDoseCount) ->
                System.out.println(AgeCat + ": " + avgDoseCount));

        // Calculate the average number of vaccinations administered in each age category
        // (already calculated in vaccinationRateByAgeCategory)

        // Calculate the average age of people who have never been vaccinated
        // double avgAgeOfUnvaccinated = people.stream()
        //         .filter(p -> p.getDoses().isEmpty())
        //         .collect(Collectors.averagingInt(Person::getAge));

        // System.out.println("Average Age of Unvaccinated People: " + avgAgeOfUnvaccinated);
    }

    // private static LinkedList<Person> generateRandomPeople(int count) {
    //     LinkedList<Person> people = new LinkedList<>();
    //     Random random = new Random();

    //     for (int i = 0; i < count; i++) {
    //         int year = random.nextInt(123) + 1900; // Random year between 1900 and 2022
    //         int month = random.nextInt(12) + 1; // Random month between 1 and 12
    //         int day = random.nextInt(28) + 1; // Random day between 1 and 28 (for simplicity, assuming all months have 28 days)

    //         LocalDate dob = LocalDate.of(year, month, day);
    //         String firstName = "FirstName" + i;
    //         String lastName = "LastName" + i;
    //         Person person = new Person(firstName, lastName, dob);

    //         // Randomly assign 0 to 3 doses
    //         int doseCount = random.nextInt(4);
    //         for (int j = 0; j < doseCount; j++) {
    //             String vacProductName = "Vaccine" + (j + 1);
    //             String lotNumber = "Lot" + (j + 1);
    //             LocalDate date = LocalDate.now().minusDays(random.nextInt(365)); // Random date within the last year
    //             String vacSite = "Site" + (j + 1);
    //             Dose dose = new Dose(vacProductName, lotNumber, date, vacSite);
    //             person.addDose(dose);
    //         }

    //         people.add(person);
    //     }

    //     return people;
    // }

    private static LinkedList<Person> generateRandomPeople(int count) {
        LinkedList<Person> people = new LinkedList<>();
        Random random = new Random();
        String[] firstNames = {"Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Hannah"};
        String[] lastNames = {"Smith", "Johnson", "Brown", "Davis", "Wilson", "Lee", "Garcia"};

        for (int i = 0; i < count; i++) {
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            LocalDate dob = LocalDate.of(1960 + random.nextInt(30), random.nextInt(12) + 1, random.nextInt(28) + 1);

            LinkedList<Dose> doses = new LinkedList<>();
            int numDoses = random.nextInt(5);
            for (int j = 0; j < numDoses; j++) {
                doses.add(new Dose("Vaccine" + j, "Lot" + j, LocalDate.now().minusDays(random.nextInt(365)), "Site" + j));
            }

            Person person = new Person(firstName, lastName, dob);
            people.add(person);
        }

        return people;
    }

}

