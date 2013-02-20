package com.saadmir.gwtdemo.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import com.saadmir.gwtdemo.client.EmployeeDTO;

@RemoteServiceRelativePath("employee")
public interface EmployeeService extends RemoteService {
  EmployeeDTO     		create(EmployeeDTO e);
  List<EmployeeDTO>   	find(String name);
//  void            update(EmployeeDTO e);
  void            		delete(String email);
}
