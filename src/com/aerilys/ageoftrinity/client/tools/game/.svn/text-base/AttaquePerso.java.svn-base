package com.aerilys.ageoftrinity.client.tools.game;

public class AttaquePerso
{

  String nom;
  String srcIcone;

  public enum Type
  {
    contact, distance
  }

  public Type type;
  boolean isMagic = false;
  int portee = 1;
  int coutPA = 1;
  int minDegats;
  int maxDegats;

  /*
   * Contructeur des attaques de corps à corps
   */
  public AttaquePerso(String nom, String srcIcone, int coutPA, int minDegats, int maxDegats)
  {
    super();
    this.nom = nom;
    this.srcIcone = srcIcone;
    this.type = Type.contact;
    this.coutPA = coutPA;
    this.minDegats = minDegats;
    this.maxDegats = maxDegats;
  }

  /*
   * Constructeur des attaques à distance
   */
  public AttaquePerso(String nom, String srcIcone, int portee, int coutPA, int minDegats,
      int maxDegats)
  {
    super();
    this.nom = nom;
    this.srcIcone = srcIcone;
    this.type = Type.distance;
    this.portee = portee;
    this.coutPA = coutPA;
    this.minDegats = minDegats;
    this.maxDegats = maxDegats;
  }

  /*
   * Constructeur des attaques magiques
   */
  public AttaquePerso(String nom, String srcIcone, Type type, boolean isMagic, int portee,
      int coutPA, int minDegats, int maxDegats)
  {
    super();
    this.nom = nom;
    this.srcIcone = srcIcone;
    this.type = type;
    this.isMagic = isMagic;
    this.portee = portee;
    this.coutPA = coutPA;
    this.minDegats = minDegats;
    this.maxDegats = maxDegats;
  }

  /**
   * @return the nom
   */
  public String getNom()
  {
    return nom;
  }

  /**
   * @param nom the nom to set
   */
  public void setNom(String nom)
  {
    this.nom = nom;
  }

  /**
   * @return the srcIcone
   */
  public String getSrcIcone()
  {
    return srcIcone;
  }

  /**
   * @param srcIcone the srcIcone to set
   */
  public void setSrcIcone(String srcIcone)
  {
    this.srcIcone = srcIcone;
  }

  /**
   * @return the type
   */
  public Type getType()
  {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(Type type)
  {
    this.type = type;
  }

  /**
   * @return the isMagic
   */
  public boolean isMagic()
  {
    return isMagic;
  }

  /**
   * @param isMagic the isMagic to set
   */
  public void setMagic(boolean isMagic)
  {
    this.isMagic = isMagic;
  }

  /**
   * @return the portee
   */
  public int getPortee()
  {
    return portee;
  }

  /**
   * @param portee the portee to set
   */
  public void setPortee(int portee)
  {
    this.portee = portee;
  }

  /**
   * @return the coutPA
   */
  public int getCoutPA()
  {
    return coutPA;
  }

  /**
   * @param coutPA the coutPA to set
   */
  public void setCoutPA(int coutPA)
  {
    this.coutPA = coutPA;
  }

  /**
   * @return the minDegats
   */
  public int getMinDegats()
  {
    return minDegats;
  }

  /**
   * @param minDegats the minDegats to set
   */
  public void setMinDegats(int minDegats)
  {
    this.minDegats = minDegats;
  }

  /**
   * @return the maxDegats
   */
  public int getMaxDegats()
  {
    return maxDegats;
  }

  /**
   * @param maxDegats the maxDegats to set
   */
  public void setMaxDegats(int maxDegats)
  {
    this.maxDegats = maxDegats;
  }

}
