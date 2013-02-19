package com.saadmir.gwtdemo.server;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.context.ApplicationContext;

import com.saadmir.gwtdemo.client.EmployeeService;
import com.saadmir.gwtdemo.client.EmployeeDTO;

import com.saadmir.gwtdemo.server.ApplicationContextProvider;
import com.saadmir.gwtdemo.server.models.Employee;
import com.saadmir.gwtdemo.server.models.EmployeeDao;

@SuppressWarnings("serial")
public class EmployeeServiceImpl extends RemoteServiceServlet implements EmployeeService {

  private ApplicationContext ctx = null;

  public EmployeeDTO create(EmployeeDTO dto){
    if (this.ctx == null){
      this.ctx = ApplicationContextProvider.getApplicationContext();
    }

    if (this.ctx != null && this.ctx.containsBean("employeeDao")){
      Employee e = new Employee(dto);
      EmployeeDao dao = (EmployeeDao)ctx.getBean("employeeDao");
      e = dao.create(e);
      return e.toDTO();
    }

    return ((EmployeeDTO) null);
  }

  public EmployeeDTO[] find(String email) {

    EmployeeDTO[] dtos = null;

    if (this.ctx == null){
      System.out.println("setting context in get");
      this.ctx = ApplicationContextProvider.getApplicationContext();
    }

    if (this.ctx != null && this.ctx.containsBean("employeeDao")){
      EmployeeDao dao = (EmployeeDao)ctx.getBean("employeeDao");

      Employee[] employees = dao.find("");
      dtos = new EmployeeDTO[employees.length];
      for (int i = 0 ; i < employees.length ; i++){
        dtos[i] = employees[i].toDTO();
      }
      System.out.println(">>>>>>>>>>>>>>>>>>>>>>" + employees.length);
    }

    return dtos;
  }

  public void delete(String email) {
    if (this.ctx == null){
      System.out.println("setting context in get");
      this.ctx = ApplicationContextProvider.getApplicationContext();
    }

    if (this.ctx != null && this.ctx.containsBean("employeeDao")){
      EmployeeDao dao = (EmployeeDao)ctx.getBean("employeeDao");
      dao.delete(email);
    }
  }
}
