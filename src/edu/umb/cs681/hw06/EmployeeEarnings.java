package edu.umb.cs681.hw06;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeEarnings {

    private String name, department_name, title;
    private Double regular,retro, other, overtime, injured, detail, quinn_education, total_gross, postal;
  
    public EmployeeEarnings(String name, String department_name, String title, Double regular, Double retro, Double other, Double overtime, Double injured, Double detail, Double quinn_education, Double total_gross, Double postal) {
              this.name = name;
              this.department_name = department_name;
              this.title = title;
              this.regular = regular;
              this.retro = retro;
              this.other = other;
              this.overtime = overtime;
              this.injured = injured;
              this.detail = detail;
              this.quinn_education = quinn_education;
              this.total_gross = total_gross;
              this.postal = postal;
              }
  
  public EmployeeEarnings() {
  }
  
  // Getter and Setter methods
  public String getName() {
  return name;
  }
  
  public String getDepartment_name() {
  return department_name;
  }
  
  public String getTitle() {
  return title;
  }
  
  public Double getRegular() {
  return regular;
  }
  
  public Double getRetro() {
  return retro;
  }
  
  public Double getOther() {
  return other;
  }
  
  public Double getOvertime() {
  return overtime;
  }
  
  public Double getInjured() {
  return injured;
  }
  
  public Double getDetail() {
  return detail;
  }
  
  public Double getQuinn_education() {
  return quinn_education;
  }
  
  public Double getTotal_gross() {
  return total_gross;
  }
  
  public Double getPostal() {
  return postal;
  }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("data/EarningsReport.csv");

        try (Stream<String> lines = Files.lines(path)) {
            // Parse CSV and store data as List<List<String>>
            List<List<String>> dataset = 
                lines.map(line ->
                    Stream.of(line.split(","))
                            .map(value -> value.replaceAll("\"", "").trim()) // Strips commas and double quotations
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());

        // Create runnable instances for each type of processing
        Thread thread1 = new Thread(() -> {
            int mapColumnIndex = 0; // Replace with the index of the column you want to map
                List<String> mappedData = dataset.stream()
                        .map(row -> {
                            try {
                                return row.get(mapColumnIndex);
                            } catch (IndexOutOfBoundsException e) {
                                // Handle incorrect or missing data gracefully
                                return "N/A";
                            }
                        })
                        .collect(Collectors.toList());

        System.out.println("\nMapped Data:");
        mappedData.forEach(System.out::println);
        });

        Thread thread2 = new Thread(() -> {
            int earningsColumnIndex = 4; 
                double totalEarnings = dataset.stream()
                        .mapToDouble(row -> {
                            try {
                                return Double.parseDouble(row.get(earningsColumnIndex).replaceAll(",", ""));
                            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                return 0.0;
                            }
                        })
                            .sum();
        System.out.println("\nTotal Earnings: " + totalEarnings); 
        });

        Thread thread3 = new Thread(() -> {
            int averageindex = 11; // Index of the total gross column
                double totalGrossSum = dataset.stream()
                        .mapToDouble(row -> {
                            try {
                                String totalGrossStr = row.get(averageindex).replaceAll(",", ""); 
                                return Double.parseDouble(totalGrossStr);
                            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                // Handle incorrect or missing data gracefully
                                return 0;
                            }
                        })
                        .sum();
        
        
        int numberOfEmployees = dataset.size();
        double averageTotalGross = numberOfEmployees > 0 ? totalGrossSum / numberOfEmployees : 0;
        System.out.println("Average Total Gross Earnings: $" + averageTotalGross);
        });

        // Start threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Join threads and collect results
        
        thread1.join();
        thread2.join();
        thread3.join();
} 
    catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
}