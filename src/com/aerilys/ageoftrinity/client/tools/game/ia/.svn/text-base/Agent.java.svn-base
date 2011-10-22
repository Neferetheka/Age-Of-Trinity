package com.aerilys.ageoftrinity.client.tools.game.ia;

import java.util.ArrayList;

import com.aerilys.ageoftrinity.client.Main;
import com.aerilys.ageoftrinity.client.tools.Vector;
import com.aerilys.ageoftrinity.client.tools.game.CaseMap;
import com.aerilys.ageoftrinity.client.tools.game.GameEngine;
import com.aerilys.ageoftrinity.client.tools.game.Combattant;

public abstract class Agent
{
  /*
   * Permet de déterminer le comportement d'un agent dans un environnement donné
   * L'agent correspond à l'entité captant l'environnement et y réagissant La
   * liste des persos et la liste des cases correspondent à la liste des cases
   */
  public static Vector Agir(Combattant agent, ArrayList<Combattant> listePersos, ArrayList<CaseMap> listeCases)
  {
    Vector cible = new Vector(agent.getCoords().x, agent.getCoords().y);

    /*
     * La cible la plus proche
     */
    Combattant closerPerso = null;

    /* On va chercher l'ennemi le plus proche */
    double minDifference = 1000;
    for (Combattant combattant : listePersos)
    {
      if (combattant != agent)
      {
        double difference = GameEngine.getDistanceBetweenCoords(agent.getCoords(),
            combattant.getCoords());
        if (difference < minDifference)
        {
          minDifference = difference;
          closerPerso = combattant;
        }
      }
    }
    if (minDifference >= 1000 || closerPerso == null)
      return agent.getCoords();

    // On va d'abord calculer le cout d'un déplacement en X
    double differenceX = Math.abs(closerPerso.getCoords().x - agent.getCoords().x);

    double coutPA = (differenceX > agent.getPtsMouvementActuel()) ? agent.getPtsMouvementActuel()
        : differenceX;

    if (closerPerso.getCoords().x > agent.getCoords().x)
      cible.x += coutPA;
    else
      cible.x -= coutPA;
    
    

    double differenceY = Math.abs(closerPerso.getCoords().y - agent.getCoords().y);

    if (differenceY != 0)
    {
      coutPA = (differenceY > agent.getPtsMouvementActuel()) ? agent.getPtsMouvementActuel()
          : differenceY;
      coutPA -= Math.ceil(differenceX / 2);

      if (closerPerso.getCoords().y > agent.getCoords().y)
        cible.y += coutPA;
      else
        cible.y -= coutPA;
    }
    
    if(cible.x == closerPerso.getCoords().x
        && cible.y == closerPerso.getCoords().y)
    {
      if(agent.getCoords().x > closerPerso.getCoords().x)
        cible.x+=1;
      else
      {
        if(closerPerso.getCoords().x > 1)
          cible.x -= 1;
        else
          cible.x += 1;
      }
    }
    
    return cible;
  }
  
  public static Combattant GetCloserPerso(Combattant agent, ArrayList<Combattant> listePersos, ArrayList<CaseMap> listeCases)
  {
    /*
     * La cible la plus proche
     */
    Combattant closerPerso = null;

    /* On va chercher l'ennemi le plus proche */
    double minDifference = 1000;
    for (Combattant combattant : listePersos)
    {
      if (combattant != agent)
      {
        double difference = GameEngine.getDistanceBetweenCoords(agent.getCoords(),
            combattant.getCoords());
        if (difference < minDifference)
        {
          minDifference = difference;
          closerPerso = combattant;
        }
      }
    }
    
    return closerPerso;
  }
}
