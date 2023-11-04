package edu.umb.cs681.hw05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Inspection {

  private String businessname;
    private String dbaname;
    private String legalowner;
    private String namelast;
    private String namefirst;
    private int licenseno;
    private LocalDateTime issdttm;
    private LocalDateTime expdttm;
    private String licstatus;
    private String licensecat;
    private String descript;
    private String result;
    private LocalDateTime resultdttm;
    private String violation;
    private String viollevel;
    private String violdesc;
    private LocalDateTime violdttm;
    private String violstatus;
    private LocalDateTime statusdate;
    private String comments;
    private String address;
    private String city;
    private String state;
    private int zip;
    private int property_id;
    private String location;


  public Inspection(String businessname, String dbaname, String legalowner, String namelast, String namefirst, int licenseno, LocalDateTime issdttm, LocalDateTime expdttm, String licstatus, String licensecat, String descript, String result, LocalDateTime resultdttm, String violation, String viollevel, String violdesc, LocalDateTime violdttm, String violstatus, LocalDateTime statusdate, String comments, String address, String city, String state, int zip, int property_id, String location){
    this.businessname = businessname;
    this.dbaname = dbaname;
    this.legalowner = legalowner;
    this.namelast = namelast;
    this.namefirst = namefirst;
    this.licenseno = licenseno;
    this.issdttm = issdttm;
    this.expdttm = expdttm;
    this.licstatus = licstatus;
    this.licensecat = licensecat;
    this.descript = descript;
    this.result = result;
    this.resultdttm = resultdttm;
    this.violation = violation;
    this.viollevel = viollevel;
    this.violdesc = violdesc;
    this.violdttm = violdttm;
    this.violstatus = violstatus;
    this.statusdate = statusdate;
    this.comments = comments;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.property_id = property_id;
    this.location = location;
    }

  public String getBusinessName() {
    return this.businessname;
  }

  public void setBusinessname(String businessname) {
      this.businessname = businessname;
  }

  public String getDbaname() {
    return this.dbaname;
  }

  public void setDbaname(String dbaname) {
      this.dbaname = dbaname;
  }

  public String getlegalowner() {
    return this.legalowner;
  }

  public void setLegalowner(String legalowner){
    this.legalowner=legalowner;
  }

  public String getNamelast() {
    return this.namelast;
  }

  public void setNamelast(String namelast){
    this.namelast=namelast;
  }

  public String getNamefirst() {
    return this.namefirst;
  }

  public void setNamefirst(String namefirst){
    this.namefirst = namefirst;
  }

   public int getLicenseno() {
    return this.licenseno;
  }

  public void setLicenseno(int licenseno){
    this.licenseno = licenseno;
  } 

  public LocalDateTime getIssdttm() {
    return issdttm;
  }
  public void setIssdttm(LocalDateTime issdttm) {
    this.issdttm = issdttm;
  }

  public LocalDateTime getExpdttm() {
    return expdttm;
  }
  public void setExpdttm(LocalDateTime expdttm) {
    this.expdttm = expdttm;
  } 
  public static void main(String[] args) {

        // java.nio.file.Path path = Paths.get("/Users/msavalia/Downloads/CS681/CS681/data/Inspection.csv");

        // try ( Stream<String> lines = Files.lines((java.nio.file.Path) (path))) {
        //     List<List<String>> matrix =
        //      lines
        //       .skip(1)
        //       .map( line -> {
        //         return Stream.of( line.split(",") ) 
        //             .map(value->value.replaceAll("^\"|\"$", "").trim()) 
        //             .collect( Collectors.toList() ); }) 
        //           .collect( Collectors.toList() );

        //   // Filter restaurants in the city of 'BOSTON'
        //   List<String> bostonRestaurants = matrix.stream()
        //   .filter(record -> "BOSTON".equalsIgnoreCase(record.get(21))) // Assuming city is in the 21st column (index 20)
        //   .map(record -> record.get(1)) // Assuming restaurant names are in the second column (index 1)
        //   .collect(Collectors.toList());

        //   System.out.println("\nRestaurants in BOSTON: " + bostonRestaurants);


        // // 2. Extract and Process Specific Columns
        // List<String> restaurantNames = matrix.stream()
        //         .map(record -> record.get(0)) // Assuming restaurant names are in the second column (index 1)
        //         .collect(Collectors.toList());

        // System.out.println("\n Restaurant Names: " + restaurantNames);

        // // 3. Calculate Average Inspection Score
        // OptionalDouble averageScore = matrix.stream()
        //         .mapToDouble(record -> Double.parseDouble(record.get(10))) // Assuming inspection scores are in the 11th column (index 10)
        //         .average();

        // if (averageScore.isPresent()) {
        //     System.out.println("Average Inspection Score: " + averageScore.getAsDouble());
        // } else {
        //     System.out.println("No inspection scores available.");
        // }
        } catch (IOException ex) {
          ex.printStackTrace(); 
        }
    }
}    
    
  