package com.aerilys.ageoftrinity.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class ConfirmBox extends PopupPanel {
  
  HTMLPanel modalBody;
  public boolean dialogResult = false;
  private HTMLPanel modalHeader;
  
  public ConfirmBox()
  {
    super();
    setStyleName("modal");
    setWidth("560px");
    //this.center();
    this.setModal(false);
    this.setAutoHideEnabled(true);
    this.setAnimationEnabled(true);
    //this.setGlassEnabled(true);
    this.getElement().setId("confirmBox");
    
    HTMLPanel container = new HTMLPanel("");
    this.add(container);
    modalHeader = new HTMLPanel("<img src=\"gfx/units/elves/shaman.png\" alt=\"Un genre d'elfe\" title=\"Un genre d'elfe\"/> <h3><span id=\"confirmTitle\">Message</span></h3>\r\n");
    modalHeader.setStyleName("modal-header");
    container.add(modalHeader);
    
    modalBody = new HTMLPanel("");
    modalBody.setStyleName("modal-body");
    container.add(modalBody);
    
    HTMLPanel modalFooter = new HTMLPanel("");
    modalFooter.setStyleName("modal-footer");
    container.add(modalFooter);
    
    Button validButton = new Button("Valider");
    modalFooter.add(validButton);
    validButton.setStyleName("btn primary");
    
    Button annulerButton = new Button("Annuler");
    modalFooter.add(annulerButton);
    annulerButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        dialogResult = false;
        close();
      }
    });
    annulerButton.setStyleName("btn");
    validButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        dialogResult = true;
        close();
      }
    });
  }
  
  public void show(String message, String title)
  {
    modalHeader.getElementById("confirmTitle").setInnerHTML(title);
    modalBody.getElement().setInnerHTML(message);
    super.show();
    this.getElement().setAttribute("style", "");
  }

  protected void close() {
    this.hide();
    
  }
}
