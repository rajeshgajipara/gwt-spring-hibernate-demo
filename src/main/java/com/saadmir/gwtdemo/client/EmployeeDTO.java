package com.saadmir.gwtdemo.client;

public class EmployeeDTO implements java.io.Serializable {

  private String firstName;
  private String lastName;
  private String email;
  private String city;

  public EmployeeDTO() {
  }

  public EmployeeDTO(String firstName, String lastName, String email, String city) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.city = city;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String toString() {
    return "[" + this.lastName + ", " + this.firstName + " | " + this.email + " | " + this.city + "]";
  }
}

