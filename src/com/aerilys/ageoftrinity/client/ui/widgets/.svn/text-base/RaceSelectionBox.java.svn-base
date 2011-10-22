package com.aerilys.ageoftrinity.client.ui.widgets;

import com.aerilys.ageoftrinity.client.Main;
import com.aerilys.ageoftrinity.client.models.Race;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;

public class RaceSelectionBox extends HTMLPanel {

  public Race race;
  Image imageRace;
  HTML labelRace;
  
  public RaceSelectionBox() {
    super("");
    this.setStyleName("raceSelectionBox");
    
    imageRace = new Image((String) null);
    add(imageRace);
    
    labelRace = new HTML("Nom");
    add(labelRace);
  }
  
  public void mouseOver()
  {
    Main.runAddClassEffect("raceSelectionBoxSelected", this.getElement().getId());
  }
  
  public void setRace(Race race)
  {
    this.race = race;
    
    imageRace.setAltText(race.getNom());
    imageRace.setUrl(race.getImage());
    labelRace.setText(race.getNom());
    
    this.getElement().setId("raceSelectionBox"+race.getNom());
  }

  /**
   * @return the imageClasse
   */
  public Image getImageRace() {
    return imageRace;
  }

  /**
   * @param imageClasse the imageClasse to set
   */
  public void setImageRace(Image imageRace) {
    this.imageRace = imageRace;
  }

}
