package edu.umb.cs681.hw05;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
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


  public static void main(String[] args)  {
        Path path = Paths.get("data/EarningsReport.csv");

        try (Stream<String> lines = Files.lines(path)) {

            // Parse CSV file
            List<List<String>> dataset = 
                lines.map(line ->
                    Stream.of(line.split(","))
                            .map(value -> value.replaceAll("\"", "").trim()) // Strips commas and double quotations
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());

                    
            // 1.  Mapped data column named "NAME"
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
            

            // 2. Fetch Regular Earnings of all Employees 
            int earningsColumnIndex = 3;    // Index of Columnc 'REGULAR'
            DoubleStream totalEarningsStream = dataset.stream()
                    .mapToDouble(row -> {
                        try {
                            return Double.parseDouble(row.get(earningsColumnIndex).replaceAll(",", ""));
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            return 0.0;
                        }
                    }); 

            double totalEarnings = totalEarningsStream.sum();


            // 3. Fetch the average of total gross earnings for all employees
            int averageindex = 10; // Index of column 'TOTAL_GROSS'
            double totalGrossSum = dataset.stream()
                    .mapToDouble(row -> {
                        try {
                            String totalGrossStr = row.get(averageindex).replaceAll(",", "");
                            return Double.parseDouble(totalGrossStr);
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            return 0.0;
                        }
                    })
                    .sum();

            int numberOfEmployees = dataset.size();
            double averageTotalGross = numberOfEmployees > 0 ? totalGrossSum / numberOfEmployees : 0;


            System.out.println("************Data Processing 1**************\n");
            System.out.println("\nMapped Data of Column 'NAME':\n");
            mappedData.forEach(System.out::println);

            System.out.print("\n************Data Processing 2**************\n");
            System.out.printf("\nSum of Regular Earnings of all Employees: %.2f%n",totalEarnings);

            System.out.print("\n************Data Processing 3**************\n");
            System.out.printf("\nTotal Gross Sum of all Employees: %.2f%n",totalGrossSum);
            System.out.println("Average of Total Gross Earnings of all Employees: $" + averageTotalGross);
            
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }        
    }   
    


     

  