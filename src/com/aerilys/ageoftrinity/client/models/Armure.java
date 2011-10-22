package com.aerilys.ageoftrinity.client.models;

public class Armure extends Item{

  int protection;

  

  public Armure() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Armure(int id, String nom, String image, String description, int prix, boolean inBoutique,
      Race race, int protection) {
    super(id, nom, image, description, prix, inBoutique, race);
   this.protection = protection;
  }

  public Armure(int id, String nom, String image, String description, int prix, boolean inBoutique, int protection) {
    super(id, nom, image, description, prix, inBoutique);
    this.protection = protection;
  }

  public Armure(int id, String nom, String image, String description, int prix, int protection) {
    super(id, nom, image, description, prix);
    this.protection = protection;
  }

  /**
   * @return the protection
   */
  public int getProtection() {
    return protection;
  }

  /**
   * @param protection the protection to set
   */
  public void setProtection(int protection) {
    this.protection = protection;
  }
  
  
}
