package com.aerilys.ageoftrinity.client.models;

import com.aerilys.ageoftrinity.client.tools.game.AttaquePerso.Type;

public class Arme extends Item {

  Type typeArme;
  int minDegatsBonus = 0;
  int maxDegatsBonus = 0;
  int porteeBonus = 0;
  
  

  public Arme() {
    super();
    // TODO Auto-generated constructor stub
  }
  
  



  public Arme(Type typeArme, int minDegatsBonus, int maxDegatsBonus, int porteeBonus) {
    super();
    this.typeArme = typeArme;
    this.minDegatsBonus = minDegatsBonus;
    this.maxDegatsBonus = maxDegatsBonus;
    this.porteeBonus = porteeBonus;
  }





  public Arme(int id, String nom, String image, String description, int prix, boolean inBoutique,
      Race race, Type typeArme, int minDegatsBonus, int maxDegatsBonus) {
    super(id, nom, image, description, prix, inBoutique, race);

    this.typeArme = typeArme;
    this.minDegatsBonus = minDegatsBonus;
    this.maxDegatsBonus = maxDegatsBonus;
  }



  public Arme(int id, String nom, String image, String description, int prix, boolean inBoutique,
      Type typeArme, int minDegatsBonus, int maxDegatsBonus, int porteeBonus) {
    super(id, nom, image, description, prix, inBoutique);
    this.typeArme = typeArme;
    this.minDegatsBonus = minDegatsBonus;
    this.maxDegatsBonus = maxDegatsBonus;
    this.porteeBonus = porteeBonus;
  }



  public Arme(int id, String nom, String image, String description, int prix,
      Type typeArme, int minDegatsBonus, int maxDegatsBonus, int porteeBonus) {
    super(id, nom, image, description, prix);
    
    this.typeArme = typeArme;
    this.minDegatsBonus = minDegatsBonus;
    this.maxDegatsBonus = maxDegatsBonus;
    this.porteeBonus = porteeBonus;
  }



  /**
   * @return the typeArme
   */
  public Type getTypeArme() {
    return typeArme;
  }



  /**
   * @param typeArme the typeArme to set
   */
  public void setTypeArme(Type typeArme) {
    this.typeArme = typeArme;
  }



  /**
   * @return the minDegatsBonus
   */
  public int getMinDegatsBonus() {
    return minDegatsBonus;
  }



  /**
   * @param minDegatsBonus the minDegatsBonus to set
   */
  public void setMinDegatsBonus(int minDegatsBonus) {
    this.minDegatsBonus = minDegatsBonus;
  }



  /**
   * @return the maxDegatsBonus
   */
  public int getMaxDegatsBonus() {
    return maxDegatsBonus;
  }



  /**
   * @param maxDegatsBonus the maxDegatsBonus to set
   */
  public void setMaxDegatsBonus(int maxDegatsBonus) {
    this.maxDegatsBonus = maxDegatsBonus;
  }



  /**
   * @return the porteeBonus
   */
  public int getPorteeBonus() {
    return porteeBonus;
  }



  /**
   * @param porteeBonus the porteeBonus to set
   */
  public void setPorteeBonus(int porteeBonus) {
    this.porteeBonus = porteeBonus;
  }
  
  
  
}
