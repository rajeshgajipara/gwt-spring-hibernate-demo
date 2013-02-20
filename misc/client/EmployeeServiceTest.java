package com.saadmir.gwtdemo.client;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class EmployeeServiceTest extends GWTTestCase {
	
	@Override
	public String getModuleName() {
		return "com.saadmir.gwtdemo.client.EmployeeServiceTest";
	}
	
	@Test
	public void testCreateAndFind() {
		final EmployeeServiceAsync svc = GWT.create(EmployeeService.class);
		ServiceDefTarget target = (ServiceDefTarget) svc;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "employee/employee");	
		delayTestFinish(30000);

		final HashMap testData = new HashMap();
		testData.put("employeeCountBefore",0);
		
		final EmployeeDTO ein =   new EmployeeDTO("TestFirst","TestLast","testfirst@test.com","TestCity");

		final AsyncCallback<EmployeeDTO[]> findAfterCreateCallback = new AsyncCallback<EmployeeDTO[]>() {
	        public void onFailure(Throwable caught) {
				fail("find request failure in testCreate after create" + caught.getMessage());
	        }
	        public void onSuccess(EmployeeDTO[] arr) {
		        assertTrue(arr != null);
		        assertTrue(arr.length == ( ((Integer)testData.get("employeeCountBefore")) + 1) );
		        finishTest();
	        }
	    };

	    final AsyncCallback<EmployeeDTO> createCallback = new AsyncCallback<EmployeeDTO>() {
	        public void onFailure(Throwable caught) {
				fail("Request failure: " + caught.getMessage());
	        }
	        public void onSuccess(EmployeeDTO eout) {
		        assertTrue(eout != null);
		        assertTrue(eout.getEmail() == ein.getEmail());
		        assertTrue(eout.getFirstName() == ein.getFirstName());
		        assertTrue(eout.getLastName() == ein.getLastName());
		        assertTrue(eout.getCity() == ein.getCity());
		        svc.find("",findAfterCreateCallback);
	        }
	    };

	    svc.find("", new AsyncCallback<EmployeeDTO[]>() {
	        public void onFailure(Throwable caught) {
				fail("find request failure in testCreate " + caught.getMessage());
	        }
	        public void onSuccess(EmployeeDTO[] arr) {
		        assertTrue(arr != null);
		        testData.put("employeeCountBefore",arr.length);
			    svc.create(ein, createCallback);
	        }
	    });
	}

	@Test
	public void testDelete(){
        assertTrue(true);
	}
}
