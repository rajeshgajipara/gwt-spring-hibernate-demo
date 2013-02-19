package com.saadmir.gwtdemo.server.models;

import com.saadmir.gwtdemo.server.models.Employee;

public interface EmployeeDao {
  Employee        create(Employee e);
  Employee[]      find(String s);
  void            update(Employee e);
  void            delete(String email);
}
