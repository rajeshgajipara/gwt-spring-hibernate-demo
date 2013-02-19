package com.saadmir.gwtdemo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainPanel {

  final EmployeeServiceAsync svc = GWT.create(EmployeeService.class);

  final private VerticalPanel panel = new VerticalPanel();
  final private EmployeeTable employeeTable;
  final private EmployeeForm  employeeForm;

  public MainPanel(){

    this.employeeTable = new EmployeeTable(this);
    this.employeeForm = new EmployeeForm(this);

    this.panel.setWidth("100%");
    this.panel.setStyleName("center");
    this.showEmployeeTable();
  }

  public void updateEmployeeTable(EmployeeDTO[] employees){
    this.employeeTable.updateTable(employees);
  }

  public void showEmployeeTable(){
    this.panel.remove(this.employeeForm.getPanel());
    this.panel.add(this.employeeTable.getPanel());
  }

  public void showEmployeeForm(){
    this.panel.remove(this.employeeTable.getPanel());
    this.panel.add(this.employeeForm.getPanel());
  }

  public Panel getPanel(){
    return this.panel;
  }
}

