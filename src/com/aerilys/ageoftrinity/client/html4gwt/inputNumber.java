package com.aerilys.ageoftrinity.client.html4gwt;

public class inputNumber extends RequiredTextBox{

  int min = 0;
  /**
   * @return the min
   */
  public int getMin() {
    return min;
  }
  /**
   * @param min the min to set
   */
  public void setMin(int min) {
    this.min = min;
    this.getElement().setAttribute("min", min+"");
  }
  int max = 1000;
  
  
    /**
   * @return the max
   */
  public int getMax() {
    return max;
  }
  /**
   * @param max the max to set
   */
  public void setMax(int max) {
    this.max = max;
    this.getElement().setAttribute("max", max+"");
  }
    public inputNumber()
    {
      this.getElement().removeAttribute("type");
      this.getElement().setAttribute("type", "number");
      this.setStyleName("gwt-TextBox-perso");
      this.setMaxLength(10);
      this.getElement().setAttribute("min", min+"");
      this.getElement().setAttribute("max", max+"");
    }
  
}
