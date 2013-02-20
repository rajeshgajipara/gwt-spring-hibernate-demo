package com.saadmir.gwtdemo.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EmployeeForm {

  final MainPanel appview;
  final Map<String,TextBox> inputs = new HashMap<String,TextBox>();
  final Map<String,HTML> errors = new HashMap<String,HTML>();
  final VerticalPanel panel = new VerticalPanel();
  final private Button closeButton = new Button("&times;");
  final private Button submitButton = new Button("Submit");
  final private Button cancleButton = new Button("Cancel");

  public Panel getPanel(){
    return this.panel;
  }

  public EmployeeForm(MainPanel appv){
    this.appview = appv;

    final VerticalPanel form = new VerticalPanel();
    form.add(this.getInputGroup("Email","email"));
    form.add(this.getInputGroup("First Name","firstName"));
    form.add(this.getInputGroup("Last Name","lastName"));
    form.add(this.getInputGroup("City","city"));

    final HorizontalPanel buttons = new HorizontalPanel();
    buttons.setWidth("100%");
    buttons.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

    final HTML errorBox = new HTML();
    errorBox.setWidth("100%");
    errorBox.setVisible(false);
    errorBox.setStyleName("alert");

    final HorizontalPanel hp = new HorizontalPanel();
    hp.setWidth("60%");
    hp.add(submitButton);
    hp.add(cancleButton);
    buttons.add(hp);

    panel.add(this.getHeader());
    panel.add(form);
    panel.add(errorBox);
    panel.add(buttons);

    panel.setWidth("100%");
    form.setWidth("100%");

    this.closeButton.setStyleName("close");

    this.submitButton.setStyleName("btn");
    this.submitButton.addStyleName("btn-primary");

    this.cancleButton.setStyleName("btn");

    this.closeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        resetInputs();
        appview.showEmployeeTable();
      }
    });

    this.cancleButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        resetInputs();
        appview.showEmployeeTable();
      }
    });

    this.submitButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
          if (validateInputs() == false) {
            errorBox.setText("Please enter valid values for Email, First Name and Last Name");
            errorBox.setVisible(true);
          } else {
            AsyncCallback<EmployeeDTO> callback = new AsyncCallback<EmployeeDTO>() {
              public void onFailure(Throwable caught) {
                //logger.log(Level.SEVERE, "ERROR IN DELETE");
              }

              public void onSuccess(EmployeeDTO e) {
            	if (e != null) {
            		resetInputs();
            		final List<EmployeeDTO> l = new ArrayList<EmployeeDTO>(1);
            		l.add(e);
            		appview.updateEmployeeTable(l);
            		appview.showEmployeeTable();
            	} else {
                    errorBox.setText("An error occured.  Please try again.");
                    errorBox.setVisible(true);            		
            	}
              }
            };

            EmployeeDTO e = new EmployeeDTO();
            e.setEmail(getInputValue("email"));
            e.setFirstName(getInputValue("firstName"));
            e.setLastName(getInputValue("lastName"));
            e.setCity(getInputValue("city"));
            appview.svc.create(e, callback);
          }
      }
    });
  }

  private Panel getHeader(){

    final HorizontalPanel header = new HorizontalPanel();
    header.setWidth("100%");

    final HTML title= new HTML();
    title.setHTML("<h3>Create New Record</h3>");
    title.setWidth("70%");

    header.add(title);
    header.add(this.closeButton);
    return header;
  }

  private String getInputValue(final String id){
    if (this.inputs.containsKey(id) == false) return "";

    final TextBox tb = (TextBox)this.inputs.get(id);
    return tb.getText();
  }

  private Panel getInputGroup(final String label, final String id){
    final HorizontalPanel hp = new HorizontalPanel();
    final Label l = new Label(label);
    final TextBox tb = new TextBox();
    tb.setName(id);
    hp.add(l);
    hp.add(tb);
    final HTML err = new HTML();
    hp.add(err);

    this.inputs.put(id,tb);
    this.errors.put(id,err);

    tb.addBlurHandler(new BlurHandler() {
      public void onBlur(BlurEvent event) {
        if (tb.getText().length() < 1){
          err.setHTML("<span class='badge badge-important'>&times;</span>");
        } else {
          err.setHTML("");
        }
      }
    });
    hp.setWidth("100%");
    l.setWidth("100px");
    tb.setWidth("300px");
    err.setWidth("100px");
    return hp;
  }

  private Boolean validateInputs(){
    for (Object k : this.inputs.keySet()) {
      TextBox tb = (TextBox)this.inputs.get(k);
      if (tb.getText().length() < 1) return false;
    }
    return true;
  }

  private void resetInputs(){
    for (Object tb : this.inputs.values()) {
      ((TextBox)tb).setText("");
    }
    for (Object o : this.errors.values()) {
      ((HTML)o).setHTML("");
    }
  }
}

