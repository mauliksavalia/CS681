package edu.umb.cs681.hw02;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.time.Period;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {

        LinkedList<Person> peopleList = generateRandomPeople(1500);

        System.out.println("Total Count of Randomly Genrated People: 1500\n");
    
        long fullyVacCount = peopleList.stream()
                                    .filter(p ->
                                            p.getAge() >= 18 && 
                                            p.getVacCount() >= 3)
                                    .count();

        float vacRate = (float) fullyVacCount / peopleList.size() * 100;
        System.out.println("Fully Vaccinated People rate aged 18 or above: " + vacRate + "%\n");

        Map<AgeCat, Double> vacRateByAgeCat = CheckVaccinationRateByAgeCategory(peopleList);
        System.out.println("Vaccination Rate(Fully Vaccinated People) by Age Category: \n" + vacRateByAgeCat + "\n");

        Map<AgeCat, Double> AvgVacInEachAgeCat = CheckAvgVaccinationInEachAgeCategory(peopleList);
        System.out.println("Average vaccinations administered in each age category: \n" + AvgVacInEachAgeCat + "\n");

        double AvgAgeOfUnVaccinated = CheckAvgAgeOfUnvaccinatedPeople(peopleList);
        System.out.println("Average age of the people who have never been vaccinated: " + AvgAgeOfUnVaccinated + " yr\n");
    }

    public static Map<AgeCat, Double> CheckVaccinationRateByAgeCategory(LinkedList<Person> peopleList) {
        Map<AgeCat, Double> vacRateByAgeCat = 
                peopleList.stream()
                    .collect(Collectors.groupingBy(
                                Person::getAgeCat, 
                                Collectors.averagingDouble(p -> 
                                    p.getVacCount() >= 3 ? 1 : 0)));
        return vacRateByAgeCat;
    }

    public static Map<AgeCat, Double> CheckAvgVaccinationInEachAgeCategory(LinkedList<Person> peopleList) {
        Map<AgeCat, Double> AvgVacInEachAgeCat = 
                peopleList.stream()
                    .collect(Collectors.groupingBy(
                                Person::getAgeCat,
                                Collectors.averagingInt(Person::getVacCount)));
        return AvgVacInEachAgeCat;
    }

    public static Double CheckAvgAgeOfUnvaccinatedPeople(LinkedList<Person> peopleList) {
        Map<Boolean, Double> VacCountOfAll = 
                peopleList.stream()
                    .collect(Collectors.partitioningBy(
                                p -> p.getVacCount() > 0,
                                Collectors.averagingInt(
                                    p -> Period.between(p.getDob(), LocalDate.now()).getYears())));

        return VacCountOfAll.get(false);
    }

    private static LinkedList<Person> generateRandomPeople(int count) {
        LinkedList<Person> people = new LinkedList<>();
        
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String firstName = "FirstName_" + (i+1);
            String lastName = "LastName_" + i+1;
            LocalDate dob = LocalDate.now().minusYears(random.nextInt(125) + 1); // assign date from today to last 125 years
            LinkedList<Dose> doses = new LinkedList<>();
            Person person = new Person(firstName, lastName, dob, doses);

            int numDoses = random.nextInt(5);   // assign 0 to 4 doses randomly
            for (int j = 0; j < numDoses; j++) {
                String vacProductName = "Vaccine_" + (j + 1);
                String lotNumber = "Lot_" + (j + 1);
                LocalDate date = LocalDate.now().minusDays(random.nextInt(730)); // assign date of vaccinations randomly from last 2 years 
                String vacSite = "Site_" + (j + 1);
                Dose dose = new Dose(vacProductName, lotNumber, date, vacSite);
                doses.add(dose);
            }
            people.add(person);
        }
        return people;
    }
}
