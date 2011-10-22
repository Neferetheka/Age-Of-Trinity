package com.aerilys.ageoftrinity.client.tools.game;

import com.aerilys.ageoftrinity.client.tools.Vector;


public class GameEngine {

  public enum TypeAttaque { sword, bow }
  
  public static double getDistanceBetweenCoords(Vector obj1, Vector obj2)
  {
    return Math.abs(obj1.x-obj2.x)+Math.abs(obj1.y-obj2.y);
  }
}
