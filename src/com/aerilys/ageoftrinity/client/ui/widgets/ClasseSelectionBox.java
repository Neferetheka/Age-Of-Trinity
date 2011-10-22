package com.aerilys.ageoftrinity.client.ui.widgets;

import com.aerilys.ageoftrinity.client.Main;
import com.aerilys.ageoftrinity.client.models.Classe;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;

public class ClasseSelectionBox extends HTMLPanel{
  
  public Classe classe;
  Image imageClasse;
  HTML labelClasse;
  
  public ClasseSelectionBox() {
    super("");
    this.setStyleName("raceSelectionBox");
    
    imageClasse = new Image((String) null);
    add(imageClasse);
    
    labelClasse = new HTML();
    add(labelClasse);
  }
  
  public void mouseOver()
  {
    Main.runAddClassEffect("raceSelectionBoxSelected", this.getElement().getId());
  }
  
  public void setClasse(Classe classe)
  {
    this.classe = classe;
    
    imageClasse.setAltText(classe.getNom());
    imageClasse.setUrl(classe.getImage());
    labelClasse.setText(classe.getNom());
    
    this.getElement().setId("raceSelectionBox"+classe.getNom());
  }

  /**
   * @return the imageClasse
   */
  public Image getImageClasse() {
    return imageClasse;
  }

  /**
   * @param imageClasse the imageClasse to set
   */
  public void setImageClasse(Image imageClasse) {
    this.imageClasse = imageClasse;
  }
}
