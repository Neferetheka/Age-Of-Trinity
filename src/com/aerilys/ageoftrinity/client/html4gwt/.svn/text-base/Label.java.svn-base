package com.aerilys.ageoftrinity.client.html4gwt;

import com.google.gwt.user.client.ui.InlineHTML;

public class Label extends InlineHTML {

  public Label(String texte, String forForm, String classe)
  {
    super();
    String label = "<label ";
    if(forForm.length() > 1)
      label += "for=\""+forForm+"\"";
    if(classe.length() > 1)
      label += "class=\""+classe+"\"";
    label +=">";
   
    this.getElement().setInnerHTML(label+texte+"</label>");
    
    
  }
  
  public Label(String texte)
  {
    this(texte, "", "");
  }
  
  public Label(String texte, String forForm)
  {
    this(texte, forForm, "");
  }
}
