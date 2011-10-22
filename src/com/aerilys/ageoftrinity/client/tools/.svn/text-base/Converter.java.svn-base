package com.aerilys.ageoftrinity.client.tools;

import com.aerilys.ageoftrinity.client.tools.game.AttaquePerso;
import com.aerilys.ageoftrinity.client.tools.game.AttaquePerso.Type;

public class Converter
{

  public static boolean convertIntToBoolean(int value)
  {
    return (value == 1) ? true : false;
  }

  public static boolean convertIntToBoolean(String valeur)
  {
    return (Integer.parseInt(valeur) == 1) ? true : false;
  }

  public static AttaquePerso.Type convertIntToAttaquePersoType(int value)
  {
    return (value == 1) ? Type.distance : Type.contact;
  }

  public static AttaquePerso.Type convertIntToAttaquePersoType(String value)
  {
    return convertIntToAttaquePersoType(Integer.parseInt(value));
  }

  /*
   * Permet d'effectuer un cast d'une chaine en int
   */
  public static int cti(String value)
  {
    return Integer.parseInt(value);
  }

  public static String htmlToText(String toConvert)
  {
    toConvert = toConvert.replace("&eacute;", "é").replace("&egrave;", "è").replace("&agrave;", "à");
    return toConvert;
  }

  public static boolean convertStringToBoolean(String result)
  {
    if (result.toLowerCase() == "true" || result == "1")
      return true;
    else
      return false;
  }

}
