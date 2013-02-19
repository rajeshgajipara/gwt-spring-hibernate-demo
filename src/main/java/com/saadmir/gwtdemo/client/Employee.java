package com.saadmir.gwtdemo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Employee implements EntryPoint {

  //Logger logger = Logger.getLogger("SaadLogger");
  final private MainPanel mainPanel = new MainPanel();
  public void onModuleLoad() {
    RootPanel.get("main").add(this.mainPanel.getPanel());
  }
}

