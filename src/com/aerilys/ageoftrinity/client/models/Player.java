package com.aerilys.ageoftrinity.client.models;

import java.util.ArrayList;
import java.util.List;

import com.aerilys.ageoftrinity.client.tools.game.Combattant;

public class Player {

  String pseudo;
  int argent = 0;
  
  List<Perso> listePersos = new ArrayList<Perso>();


  /**
   * @return the argent
   */
  public int getArgent() {
    return argent;
  }

  /**
   * @param argent the argent to set
   */
  public void setArgent(int argent) {
    this.argent = argent;
  }

  /**
   * @return the pseudo
   */
  public String getPseudo() {
    return pseudo;
  }

  /**
   * @param pseudo the pseudo to set
   */
  public void setPseudo(String pseudo) {
    this.pseudo = pseudo;
  }

  /**
   * @return the listePersos
   */
  public List<Perso> getListePersos()
  {
    return listePersos;
  }

  /**
   * @param listePersos the listePersos to set
   */
  public void setListePersos(List<Perso> listePersos)
  {
    this.listePersos = listePersos;
  }
  
  
}
