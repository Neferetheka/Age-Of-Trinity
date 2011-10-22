package com.aerilys.ageoftrinity.client.tools.game;

import com.aerilys.ageoftrinity.client.tools.Sprite;
import com.aerilys.ageoftrinity.client.tools.Vector;
import com.google.gwt.user.client.ui.RootPanel;

public class CaseMap extends Sprite {

  public static final int WIDTH_CASE = 72;
  public static final int HEIGHT_CASE = 72;
  Vector coordsNaturel;
  
  public CaseMap(String src, RootPanel rootPanel, Vector coords) {
    super(src, rootPanel);
    
    coordsNaturel = coords;
    calculPosition();
  }
  
  /*
   * Permet de recalculer la position d'une case en coordonnes canvas suivant les coordonnes naturels
   */
  public void calculPosition()
  {
    double x = 0;
    double y = 0;
    
    if(coordsNaturel.x == 1)
    {
      x = (coordsNaturel.x-1)*WIDTH_CASE;
      y = (coordsNaturel.y-1)*HEIGHT_CASE;
    }
    else if(coordsNaturel.x % 2 != 0)
    {
      x = (coordsNaturel.x-1)/2*WIDTH_CASE+(coordsNaturel.x-1)/2*WIDTH_CASE*0.5;
       y = (coordsNaturel.y-1)*HEIGHT_CASE;
    }
    else
    {
      x = (coordsNaturel.x-1)*WIDTH_CASE*0.75;
      /*if(coords.y == 1)
        y = coords.y*HEIGHT_CASE/2;
      else
        y = coords.y*HEIGHT_CASE*0.75;*/
      y = 0.5*HEIGHT_CASE + (coordsNaturel.y-1)*HEIGHT_CASE;
    }
 
    
    Vector position = new Vector(x, y);
    this.setPosition(position);
  }

  /**
   * @return the coordsNaturel
   */
  public Vector getCoordsNaturel() {
    return coordsNaturel;
  }

  /**
   * @param coordsNaturel the coordsNaturel to set
   */
  public void setCoordsNaturel(Vector coordsNaturel) {
    this.coordsNaturel = coordsNaturel;
    calculPosition();
  }

}
