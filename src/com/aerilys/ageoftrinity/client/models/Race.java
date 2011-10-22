package com.aerilys.ageoftrinity.client.models;

public class Race {
  int id;
  String nom;
  String description;
  String image;
  
  public Race(int id, String nom, String description, String image) {
    super();
    this.id = id;
    this.nom = nom;
    this.description = description;
    this.image = image;
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
  
  
}
