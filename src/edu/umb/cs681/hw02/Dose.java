package edu.umb.cs681.hw02;
import java.time.LocalDate;

public class Dose {
  
  private String vacProductName;
  private String lotNumber;
  private LocalDate date;
  private String vacSite;

  public Dose(String vacProductName, String lotNumber, LocalDate date, String vacSite) {
    this.vacProductName = vacProductName;
    this.lotNumber = lotNumber;
    this.date = date;
    this.vacSite = vacSite;
  }

  public String getVacProductName() {
    return vacProductName;
  }

  public void setVacProductName(String vacProductName) {
    this.vacProductName = vacProductName;
  }

  public String getLotNumber() {
    return lotNumber;
  }

  public void setLotNumber(String lotNumber) {
    this.lotNumber = lotNumber;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date; 
  }

  public String getVacSite() {
    return vacSite;
  }

  public void setVacSite(String vacSite) {
    this.vacSite = vacSite;
  }

}
