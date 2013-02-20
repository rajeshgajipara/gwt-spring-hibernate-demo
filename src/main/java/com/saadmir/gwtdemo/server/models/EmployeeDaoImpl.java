package com.saadmir.gwtdemo.server.models;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;


import com.saadmir.gwtdemo.server.models.Employee;
import com.saadmir.gwtdemo.server.models.EmployeeDao;

public class EmployeeDaoImpl implements EmployeeDao {
  HibernateTemplate template;

  public void setSessionFactory(SessionFactory factory) {
    template=new HibernateTemplate(factory);
  }

  public Employee create(Employee e){
	if (e == null
		|| e.getEmail() == null 
		|| e.getEmail().length() < 1
		|| e.getFirstName() == null 
		|| e.getFirstName().length() < 1
		|| e.getLastName() == null 
		|| e.getLastName().length() < 1
		|| e.getCity() == null  
		|| e.getCity().length() < 1) {
		return (Employee)null;
	}
	
	List<Employee> list = template.find("from Employee where email=?",e.getEmail());
    if (list.isEmpty()) {
      template.save(e);
      return e;
    }

    return (Employee)list.get(0);
  }

  public List<Employee> find(String email){
    if (email != null && email.length() > 0){
      return template.find("from employee where email=?",email);
    }
    
    return template.loadAll(Employee.class);
  }

  public void update(Employee e){
    template.update(e);
  }

  public void delete(String email){
    List<Employee> list = template.find("from Employee where email=?",email);
    template.delete((Employee)list.get(0));
  }
}
