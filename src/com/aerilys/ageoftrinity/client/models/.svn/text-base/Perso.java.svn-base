package com.aerilys.ageoftrinity.client.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.aerilys.ageoftrinity.client.tools.DataContainer;
import com.aerilys.ageoftrinity.client.tools.game.Combattant;
import com.aerilys.ageoftrinity.shared.PersoEntity;

public class Perso implements Serializable
{
  public String user;
  Player player;
  Combattant combattant;

  String nom;
  Race race;
  Classe classe;
  Destinee destinee;
  String biographie;
  int niveau;

  Arme armeActu;
  Armure armureActu;
  ObjectMagique objetActu;

  public Perso()
  {
    ;
  }

  public Perso(String nom, Race race, Classe classe, Destinee destinee, String biographie,
      int niveau)
  {
    this();
    this.nom = nom;
    this.race = race;
    this.classe = classe;
    this.destinee = destinee;
    this.biographie = biographie;
    this.niveau = niveau;
  }

  public Perso(String nom, String biographie, int niveau)
  {
    this();
    this.nom = nom;
    this.biographie = biographie;
    this.niveau = niveau;
  }

  /*
   * Permet de créer le combattant à partir du personnage
   */
  public void initCombattant()
  {
/* demarrer ici la creation du combattant*/
  }

  /*
   * Permet de recréer le personnage à partir de sa javabean d'entité
   */
  public static Perso getFromEntity(PersoEntity entity)
  {
    Perso tmp = new Perso(entity.getNom(), entity.getBiographie(), entity.getNiveau());
    tmp.setRace(DataContainer.getInstance().getListeRaces().get(entity.getIndexRace()-1));
    tmp.setClasse(DataContainer.getInstance().getListeClasses().get(entity.getIndexClasse()-1));
    tmp.setDestinee(DataContainer.getInstance().getListeDestinee().get(entity.getIndexDestinee()-1));

    return tmp;
  }

  /**
   * @return the player
   */
  public Player getPlayer()
  {
    return player;
  }

  /**
   * @param player the player to set
   */
  public void setPlayer(Player player)
  {
    this.player = player;
  }

  /**
   * @return the combattant
   */
  public Combattant getCombattant()
  {
    return combattant;
  }

  /**
   * @param combattant the combattant to set
   */
  public void setCombattant(Combattant combattant)
  {
    this.combattant = combattant;
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
   * @return the race
   */
  public Race getRace()
  {
    return race;
  }

  /**
   * @param race the race to set
   */
  public void setRace(Race race)
  {
    this.race = race;
  }

  /**
   * @return the classe
   */
  public Classe getClasse()
  {
    return classe;
  }

  /**
   * @param classe the classe to set
   */
  public void setClasse(Classe classe)
  {
    this.classe = classe;
  }

  /**
   * @return the destinee
   */
  public Destinee getDestinee()
  {
    return destinee;
  }

  /**
   * @param destinee the destinee to set
   */
  public void setDestinee(Destinee destinee)
  {
    this.destinee = destinee;
  }

  /**
   * @return the biographie
   */
  public String getBiographie()
  {
    return biographie;
  }

  /**
   * @param biographie the biographie to set
   */
  public void setBiographie(String biographie)
  {
    this.biographie = biographie;
  }

  /**
   * @return the niveau
   */
  public int getNiveau()
  {
    return niveau;
  }

  /**
   * @param niveau the niveau to set
   */
  public void setNiveau(int niveau)
  {
    this.niveau = niveau;
  }

  /**
   * @return the armeActu
   */
  public Arme getArmeActu()
  {
    return armeActu;
  }

  /**
   * @param armeActu the armeActu to set
   */
  public void setArmeActu(Arme armeActu)
  {
    this.armeActu = armeActu;
  }

  /**
   * @return the armureActu
   */
  public Armure getArmureActu()
  {
    return armureActu;
  }

  /**
   * @param armureActu the armureActu to set
   */
  public void setArmureActu(Armure armureActu)
  {
    this.armureActu = armureActu;
  }

  /**
   * @return the objetActu
   */
  public ObjectMagique getObjetActu()
  {
    return objetActu;
  }

  /**
   * @param objetActu the objetActu to set
   */
  public void setObjetActu(ObjectMagique objetActu)
  {
    this.objetActu = objetActu;
  }

}
