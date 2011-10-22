package com.aerilys.ageoftrinity.client.html4gwt;

import com.google.gwt.user.client.ui.TextBox;

public class RequiredTextBox extends TextBox {

  boolean required = false;
  
  /**
   * @return the required
   */
  public boolean isRequired() {
    return required;
  }

  /**
   * @param required the required to set
   */
  public void setRequired(boolean required) {
    this.required = required;
    if(required)
      this.getElement().setAttribute("required", "required");
  }
  
  String id;

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
    this.getElement().removeAttribute("id");
    this.getElement().setAttribute("id", id);
  }
  
  String placeHolder;

  /**
   * @return the placeHolder
   */
  public String getPlaceHolder() {
    return placeHolder;
  }

  /**
   * @param placeHolder the placeHolder to set
   */
  public void setPlaceHolder(String placeHolder) {
    this.placeHolder = placeHolder;
    this.getElement().removeAttribute("placeholder");
    this.getElement().setAttribute("placeholder", placeHolder);
  }

  public RequiredTextBox()
  {
    
  }
}
