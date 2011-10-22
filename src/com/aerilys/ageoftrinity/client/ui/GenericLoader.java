package com.aerilys.ageoftrinity.client.ui;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHTML;

public class GenericLoader extends HTMLPanel
{

  public GenericLoader()
  {
    super("");
    setStyleName("loaderPanel");
    this.getElement().setId("loaderPanel");

    Image image = new Image("gfx/GUI/circleAOT.png");
    image.setAltText("Chargement...");
    add(image);

    Image image_1 = new Image("gfx/GUI/sphere_humain.png");
    image_1.setStyleName("loaderBlueSphere");
    image_1.setAltText("Chargement...");
    add(image_1);

    InlineHTML loaderLabel = new InlineHTML("Chargement en cours...");
    loaderLabel.setStyleName("loaderLabel");
    add(loaderLabel);
    
  }

}
