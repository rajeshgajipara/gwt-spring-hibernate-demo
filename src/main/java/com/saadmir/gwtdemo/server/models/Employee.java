package com.saadmir.gwtdemo.server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.saadmir.gwtdemo.client.EmployeeDTO;

@Entity
@Table(
      name = "employee",
      catalog = "gwtdemo",
      uniqueConstraints = { @UniqueConstraint(columnNames = {"id","email"}) }
)
public class Employee implements java.io.Serializable {

  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
  private String city;

  public Employee() {
  }

  public Employee(String firstName, String lastName, String email, String city) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.city = city;
  }

  public Employee(final EmployeeDTO e) {
    this.firstName = e.getFirstName();
    this.lastName = e.getLastName();
    this.email = e.getEmail();
    this.city = e.getCity();
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "firstname", nullable = false, length = 32)
  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Column(name = "lastname", nullable = false, length = 32)
  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column(name = "city", length = 32)
  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Column(name = "email", unique = true, nullable = false, length = 128)
  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "[" + this.lastName + ", " + this.firstName + " | " + this.email + " | " + this.city + "]";
  }

  public EmployeeDTO toDTO(){
    EmployeeDTO dto = new EmployeeDTO();
    dto.setLastName(this.getLastName());
    dto.setFirstName(this.getFirstName());
    dto.setCity(this.getCity());
    dto.setEmail(this.getEmail());
    return dto;
  }
}

