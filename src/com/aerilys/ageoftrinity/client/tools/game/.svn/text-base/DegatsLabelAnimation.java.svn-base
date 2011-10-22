package com.aerilys.ageoftrinity.client.tools.game;

import com.aerilys.ageoftrinity.client.tools.Vector;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;

public class DegatsLabelAnimation {

  String texte;
  Vector position;
  float rotation;
  Vector scale;
  int actualTime;
  int timeBeforeEnd = 1000;
  double opacity = 1;
  CssColor color = CssColor.make("rgba(255,0,0,1)");
  double fontSize = 16;

  public DegatsLabelAnimation(String texte, Vector position, float rotation, Vector scale) {
    super();
    this.texte = texte;
    this.position = position;
    this.rotation = rotation;
    this.scale = scale;
  }
  
  

  public DegatsLabelAnimation(String texte, Vector position) {
    super();
    this.texte = texte;
    this.position = position;
  }



  /**
   * @return the actualTime
   */
  public int getActualTime() {
    return actualTime;
  }

  /**
   * @param actualTime the actualTime to set
   */
  public void setActualTime(int actualTime) {
    this.actualTime = actualTime;
  }

  /**
   * @return the timeBeforeEnd
   */
  public int getTimeBeforeEnd() {
    return timeBeforeEnd;
  }

  /**
   * @param timeBeforeEnd the timeBeforeEnd to set
   */
  public void setTimeBeforeEnd(int timeBeforeEnd) {
    this.timeBeforeEnd = timeBeforeEnd;
  }

  public void update() {
    if (actualTime < timeBeforeEnd) {
      this.actualTime += GameTime.FRAMERATE;
      this.position.y -= 2;
      this.opacity -= 0.02;
    }
  }

  public void draw(Context2d context) {
    if (actualTime < timeBeforeEnd) {
      context.save();
      color = CssColor.make("rgba(255,0,0," + opacity + ")");
      context.setFillStyle(color);
      context.setFont("bold " + fontSize + "px sans-serif");
      context.setShadowOffsetX(5);
      context.setShadowOffsetY(5);
      context.setShadowBlur(5);
      context.setShadowColor("rgba(0, 0, 0, " + opacity / 2 + ")");
      context.fillText(this.texte, position.x, position.y);
      context.restore();
    }
  }
}
