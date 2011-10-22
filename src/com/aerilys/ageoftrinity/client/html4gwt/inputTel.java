package com.aerilys.ageoftrinity.client.html4gwt;

public class inputTel extends RequiredTextBox {

  public inputTel()
  {
      this.getElement().removeAttribute("type");
      this.getElement().setAttribute("type", "tel");
      this.setStyleName("gwt-TextBox-perso");
      this.setMaxLength(50);
  }
}
