package com.aerilys.ageoftrinity.client.tools.game;

public class GameTime {
  public static final int FRAMERATE = 25;
  public static int timeSinceBegin = 0;
  
  public static void Update()
  {
    timeSinceBegin+=FRAMERATE;
  }
}
