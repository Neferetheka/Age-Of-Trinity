package com.aerilys.ageoftrinity.client.models;

public abstract class Item {

  int id;
  String nom;
  String image;
  String description;
  int prix;
  boolean inBoutique = false;
  Race race;
  
  
  public Item() {
    super();
  }


  public Item(int id, String nom, String image, String description, int prix) {
    super();
    this.id = id;
    this.nom = nom;
    this.image = image;
    this.description = description;
    this.prix = prix;
  }


  public Item(int id, String nom, String image, String description, int prix, boolean inBoutique,
      Race race) {
    super();
    this.id = id;
    this.nom = nom;
    this.image = image;
    this.description = description;
    this.prix = prix;
    this.inBoutique = inBoutique;
    this.race = race;
  }


  public Item(int id, String nom, String image, String description, int prix, boolean inBoutique) {
    super();
    this.id = id;
    this.nom = nom;
    this.image = image;
    this.description = description;
    this.prix = prix;
    this.inBoutique = inBoutique;
  }


  /**
   * @return the id
   */
  public int getId() {
    return id;
  }


  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }


  /**
   * @return the nom
   */
  public String getNom() {
    return nom;
  }


  /**
   * @param nom the nom to set
   */
  public void setNom(String nom) {
    this.nom = nom;
  }


  /**
   * @return the image
   */
  public String getImage() {
    return image;
  }


  /**
   * @param image the image to set
   */
  public void setImage(String image) {
    this.image = image;
  }


  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }


  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }


  /**
   * @return the prix
   */
  public int getPrix() {
    return prix;
  }


  /**
   * @param prix the prix to set
   */
  public void setPrix(int prix) {
    this.prix = prix;
  }


  /**
   * @return the inBoutique
   */
  public boolean isInBoutique() {
    return inBoutique;
  }


  /**
   * @param inBoutique the inBoutique to set
   */
  public void setInBoutique(boolean inBoutique) {
    this.inBoutique = inBoutique;
  }


  /**
   * @return the race
   */
  public Race getRace() {
    return race;
  }


  /**
   * @param race the race to set
   */
  public void setRace(Race race) {
    this.race = race;
  }

  
  

  
  
  
}
