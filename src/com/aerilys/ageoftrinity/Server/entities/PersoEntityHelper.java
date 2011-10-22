package com.aerilys.ageoftrinity.Server.entities;

import java.util.Map;

import com.aerilys.ageoftrinity.client.models.Perso;
import com.aerilys.ageoftrinity.client.tools.DataContainer;
import com.aerilys.ageoftrinity.shared.PersoEntity;
import com.google.appengine.api.datastore.Entity;

public abstract class PersoEntityHelper
{
  public static Entity SaveAsEntity(PersoEntity perso, int raceId, int classeId, int destineeId)
  {
    Entity entity = new Entity("Perso");
    entity.setProperty("user", perso.user);
    entity.setProperty("nom", perso.getNom());
    entity.setProperty("race", raceId);
    entity.setProperty("classe", classeId);
    entity.setProperty("destinee", destineeId);
    entity.setProperty("biographie", perso.getBiographie());
    entity.setProperty("niveau", perso.getNiveau());
    return entity;
  }

  public static Entity SaveAsEntity(PersoEntity perso)
  {
   Entity entity = new Entity("Perso");
   entity.setProperty("user", perso.user);
   entity.setProperty("nom", perso.getNom());
   entity.setProperty("race", perso.getIndexRace());
   entity.setProperty("classe", perso.getIndexClasse());
   entity.setProperty("destinee", perso.getIndexDestinee());
   entity.setProperty("biographie", perso.getBiographie());
   entity.setProperty("niveau", perso.getNiveau());
   
   return entity;
  }
  

  public static PersoEntity GetProperties(Entity entity)
  {
    PersoEntity perso = new PersoEntity();
    Map<String, Object> properties = entity.getProperties();
    perso.setNom((String) properties.get("nom"));
    perso.setIndexRace(((Long)properties.get("race")).intValue());
    perso.setIndexClasse(((Long)properties.get("classe")).intValue());
    perso.setIndexDestinee(((Long)properties.get("destinee")).intValue());
    perso.setBiographie((String) properties.get("biographie"));
    perso.setNiveau(((Long)properties.get("niveau")).intValue());
    return perso;
  }
}
