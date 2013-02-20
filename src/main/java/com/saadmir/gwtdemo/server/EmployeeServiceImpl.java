package com.saadmir.gwtdemo.server;


import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.context.ApplicationContext;

import com.saadmir.gwtdemo.client.EmployeeService;
import com.saadmir.gwtdemo.client.EmployeeDTO;

import com.saadmir.gwtdemo.server.ApplicationContextProvider;
import com.saadmir.gwtdemo.server.models.Employee;
import com.saadmir.gwtdemo.server.models.EmployeeDao;

@SuppressWarnings("serial")
public class EmployeeServiceImpl extends RemoteServiceServlet implements EmployeeService {

  private ApplicationContext ctx;
  private EmployeeDao dao; 
  
  private EmployeeDao getDao(){
	  if (this.dao != null) return this.dao;
	  
	  if (this.ctx == null){
		  this.ctx = ApplicationContextProvider.getApplicationContext();
	  }

	  if (this.ctx != null && this.ctx.containsBean("employeeDao")){
		  this.dao = (EmployeeDao)ctx.getBean("employeeDao");
	  }
	  
	  return this.dao;
  }

  public EmployeeDTO create(EmployeeDTO dto){
    if (this.getDao() != null){
      Employee e = new Employee(dto);
      e = this.getDao().create(e);
      if (e != null) {
    	  return e.toDTO();
      }
    }
    return ((EmployeeDTO) null);
  }

  public List<EmployeeDTO> find(String email) {
    if (this.getDao() != null){
      final List<Employee> employees = dao.find("");
      if (employees != null){
    	  final List<EmployeeDTO> dtos = new ArrayList<EmployeeDTO>();
    	  for (Employee e : employees) {
    		  dtos.add(e.toDTO());
    	  }
      	return dtos;
      }
    }

    return (List<EmployeeDTO>)null;
  }

  public void delete(String email) {
    if (this.getDao() != null && email != null && email.length() > 0){
      this.getDao().delete(email);
    }
  }
}
