package com.aerilys.ageoftrinity.client.ui;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class ChildWindow extends HTMLPanel
{
  /*
   * TODO : rendre abstract
   */
  public ChildWindow(String html)
  {
    super(html);   
    this.setStyleName("childWindow");
    
    HTMLPanel closePanel = new HTMLPanel("New HTML");
    closePanel.setStyleName("closePanel");
    add(closePanel);
    
    Button closeButton = new Button("X");
    closeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        Close();
      }
    });
    closeButton.setStyleName("gwt-Button-perso");
    closePanel.add(closeButton);
    
    this.setVisible(false);
  }
  
  public void Show(HTMLPanel container)
  {
    container.add(this);
    this.setVisible(true);
  }
  
  public void Close()
  {
    this.setVisible(false);
    //this.getParent().removeFromParent();
  }

}
