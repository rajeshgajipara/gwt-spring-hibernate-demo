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
    List list = template.find("from Employee where email=?",e.getEmail());
    if (list.isEmpty()) {
      template.save(e);
      return e;
    }

    return (Employee)list.get(0);
  }

  public Employee[] find(String email){
    List list;
    if (email != null && email.length() > 0){
      list = template.find("from employee where email=?",email);
    } else {
      list = template.loadAll(Employee.class);
    }

    Employee[] array = new Employee[list.size()];
    for (int i=0 ; i<list.size() ; i++){
      array[i] = (Employee)list.get(i);
    }
    return array;
  }

  public void update(Employee e){
    template.update(e);
  }

  public void delete(String email){
    List list = template.find("from Employee where email=?",email);
    Employee e = (Employee)list.get(0);
    template.delete(e);
  }
}
