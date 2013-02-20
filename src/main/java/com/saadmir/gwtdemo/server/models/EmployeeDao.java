package com.saadmir.gwtdemo.server.models;

import java.util.List;

import com.saadmir.gwtdemo.server.models.Employee;

public interface EmployeeDao {
  Employee      		create(Employee e);
  List<Employee>      	find(String s);
  void          		update(Employee e);
  void          		delete(String email);
}
