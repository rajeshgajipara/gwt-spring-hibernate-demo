package com.saadmir.gwtdemo.client;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EmployeeTable {

  final MainPanel appview;
  final private VerticalPanel panel = new VerticalPanel();
  final private Grid table = new Grid(1,5);
  final private Set employees = new HashSet();

  public EmployeeTable(MainPanel appv){
    this.appview = appv;

    this.panel.add(this.addButton());
    this.setupTable();
    this.panel.add(this.table);

    this.panel.setWidth("100%");
    this.panel.setSpacing(25);
    this.panel.addStyleName("center");


    AsyncCallback<EmployeeDTO[]> callback = new AsyncCallback<EmployeeDTO[]>() {
      public void onFailure(Throwable caught) {
        // TODO: Do something with errors.
        //logger.log(Level.SEVERE, "200 ERROR");
      }

      public void onSuccess(EmployeeDTO[] result) {
        updateTable(result);
      }
    };

    this.appview.svc.find("", callback);
  }

  public Panel getPanel(){
    return this.panel;
  }

  private Button addButton(){
    final Button button = new Button("Create New Record");
    button.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        appview.showEmployeeForm();
      }
    });
    button.setWidth("100%");
    button.setStyleName("btn");
    button.addStyleName("btn-block");
    button.addStyleName("btn-primary");
    return button;
  }

  public void updateTable(EmployeeDTO[] es) {
    for (int i = 0; i < es.length; i++) {
      if (this.employees.contains(es[i].getEmail()) == false){
        this.employees.add(es[i].getEmail());
        this.addRow(es[i]);
      }
    }
    this.styleRows();
  }

  private void styleRows(){
    final int row = this.table.getRowCount();
    for (int i = 1 ; i <  row ; i++){
      if ((i % 2) == 0) {
        this.table.getRowFormatter().removeStyleName(i, "tableRow-odd");
        this.table.getRowFormatter().addStyleName(i, "tableRow-even");
      } else {
        this.table.getRowFormatter().removeStyleName(i, "tableRow-even");
        this.table.getRowFormatter().addStyleName(i, "tableRow-odd");
      }
    }
  }

  private void addRow(final EmployeeDTO e) {
    final int row = this.table.getRowCount();
    this.table.resizeRows(row+1);
    this.table.setText(row, 0, e.getEmail());
    this.table.setText(row, 1, e.getFirstName());
    this.table.setText(row, 2, e.getLastName());
    this.table.setText(row, 3, e.getCity());

    final Button removeButton = new Button("&times;");
    removeButton.setStyleName("close");
    removeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {
          public void onFailure(Throwable caught) {
            //logger.log(Level.SEVERE, "ERROR IN DELETE");
          }

          public void onSuccess(Void v) {
            table.removeRow(row);
            employees.remove(e.getEmail());
            styleRows();
          }
        };
        appview.svc.delete(e.getEmail(), callback);
      }
    });

    this.table.setWidget(row, 4, removeButton);
    this.table.getRowFormatter().addStyleName(row, "tableRow");
  }


  private void setupTable(){
    this.table.setWidth("100%");
    this.table.addStyleName("table");
    this.table.addStyleName("table-center");
    this.table.addStyleName("center");
    this.table.setBorderWidth(2);

    this.table.setWidget(0, 0, new Label("Email"));
    this.table.getCellFormatter().setWidth(0, 0, "200px");

    this.table.setWidget(0, 1, new Label("First Name") );
    this.table.getCellFormatter().setWidth(0, 1, "100px");

    this.table.setWidget(0, 2, new Label("Last Name"));
    this.table.getCellFormatter().setWidth(0, 2, "100px");

    this.table.setWidget(0, 3, new Label("City"));
    this.table.getCellFormatter().setWidth(0, 3, "100px");

    this.table.getCellFormatter().setWidth(0, 4, "25px");

    this.table.getRowFormatter().addStyleName(0, "tableRow");
    this.table.getRowFormatter().addStyleName(0, "tableRow-header");
  }
}
