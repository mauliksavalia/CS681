package edu.umb.cs681.hw06;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeEarnings {

    private String name, department_name, title;
    private Double regular,retro, other, overtime, injured, detail, quinn_education, total_gross, postal;
  
    private static double avgTotalGrossValue;
    private static double totalEarnings;

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

  // Getter methods
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

            // Parse CSV file
            List<List<String>> dataset = 
                lines.map(line ->
                    Stream.of(line.split(","))
                            .map(value -> value.replaceAll("\"", "").trim()) 
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());

        // Data Processing 1 : Mapping the Data
        Runnable mappingTask = () -> {
            int mapColumnIndex = 0;     // Index of Column 'NAME'
            List<String> mappedData = dataset.stream()
                    .map(row -> {
                        try {
                            return row.get(mapColumnIndex);
                        } catch (IndexOutOfBoundsException e) {
                            return "N/A";
                        }
                    })
                    .collect(Collectors.toList());
        
        mappedData.forEach(System.out::println);
        };

        Thread thread1 = new Thread(mappingTask);

        // Data Processing 2 : Sum of Regular Earnings 
        Runnable totalEarningsStream = () -> {
            int earningsColumnIndex = 3;    // Index of Columnc 'REGULAR'
            totalEarnings = dataset.stream()
                    .mapToDouble(row -> {
                        try {
                            return Double.parseDouble(row.get(earningsColumnIndex).replaceAll(",", ""));
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            return 0.0;
                        }
                    })
                    .sum();
        };

        Thread thread2 = new Thread(totalEarningsStream);

        // Avg of Total Gross Earnings
        Runnable avgTotalGross = () -> {
            int averageindex = 10;          // Index of column 'TOTAL_GROSS'
            double totalGrossSum = dataset.stream()
                    .mapToDouble(row -> {
                        try {
                            String totalGrossStr = row.get(averageindex).replaceAll(",", "");
                            return Double.parseDouble(totalGrossStr);
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            return 0;
                        }
                    })
                    .sum();

            int numberOfEmployees = dataset.size();
            avgTotalGrossValue = numberOfEmployees > 0 ? totalGrossSum / numberOfEmployees : 0;
            System.out.printf("\nTotal Gross Sum of all Employees: %.2f%n",totalGrossSum);
        };

        Thread thread3 = new Thread(avgTotalGross);
        
        // Start threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Join threads and collect results
        thread1.join();
        thread2.join();
        thread3.join();

        
        System.out.printf("\nMapped Data of Column 'NAME'\n", mappingTask);

        System.out.print("\n************Data Processing 2**************\n");
        System.out.printf("\nSum of Regular Earnings of all Employees: %.2f\n", totalEarnings);

        System.out.print("\n************Data Processing 3**************\n");
        System.out.println("\nAverage of Total Gross Earnings of all Employees: $" + avgTotalGrossValue);
} 
    catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
}