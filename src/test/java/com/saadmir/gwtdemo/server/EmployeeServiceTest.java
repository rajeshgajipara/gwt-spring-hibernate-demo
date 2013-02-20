package com.saadmir.gwtdemo.server;


import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.saadmir.gwtdemo.server.models.Employee;
import com.saadmir.gwtdemo.server.models.EmployeeDao;

public class EmployeeServiceTest extends TestCase {

	@Test
	public void testValidCreateAndFind() {
		final ClassPathXmlApplicationContext appContext =
		        new ClassPathXmlApplicationContext("applicationContext.xml");

		final EmployeeDao dao = (EmployeeDao)appContext.getBean("employeeDao");
        final Employee ein =   new Employee("TestFirst","TestLast","testfirst@test.com","TestCity");
  		
  		List<Employee> list = dao.find("");
  		assertTrue(list != null);
  		final Integer employeeCountBefore = list.size(); 
  		final Employee eout = dao.create(ein);
        assertTrue(eout != null);
        assertTrue(eout.getEmail() == ein.getEmail());
        assertTrue(eout.getFirstName() == ein.getFirstName());
        assertTrue(eout.getLastName() == ein.getLastName());
        assertTrue(eout.getCity() == ein.getCity());
  		list = dao.find("");
  		assertTrue(list != null);        
  		assertTrue(list.size() == (employeeCountBefore + 1));
  		dao.delete(eout.getEmail());
  		list = dao.find("");
  		assertTrue(list != null);        
  		assertTrue(list.size() == employeeCountBefore);  		
	}

	public void testInvalidCreateAndFind() {
		final ClassPathXmlApplicationContext appContext =
		        new ClassPathXmlApplicationContext("applicationContext.xml");

		final EmployeeDao dao = (EmployeeDao)appContext.getBean("employeeDao");
        final Employee ein =   new Employee("","TestLast","testfirst@test.com","TestCity");

  		List<Employee> list = dao.find("");
  		assertTrue(list != null);
  		final Integer employeeCountBefore = list.size(); 
  		final Employee eout = dao.create(ein);
        assertTrue(eout == null);
  		list = dao.find("");
  		assertTrue(list != null);        
  		assertTrue(list.size() == employeeCountBefore);  		
	}
}
