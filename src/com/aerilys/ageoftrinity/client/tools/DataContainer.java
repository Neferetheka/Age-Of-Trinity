package com.aerilys.ageoftrinity.client.tools;

import java.util.ArrayList;
import java.util.List;

import com.aerilys.ageoftrinity.client.Main;
import com.aerilys.ageoftrinity.client.models.*;
import com.aerilys.ageoftrinity.client.rpc.DataService;
import com.aerilys.ageoftrinity.client.rpc.DataServiceAsync;
import com.aerilys.ageoftrinity.client.tools.game.AttaquePerso.Type;
import com.aerilys.ageoftrinity.client.ui.Loader;
import com.aerilys.ageoftrinity.client.ui.panels.MainPanel;
import com.aerilys.ageoftrinity.shared.PersoEntity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.XMLParser;

/*
 * Classe de stockage de toutes les données. Basée sur le pattern Singleton
 */
public class DataContainer
{
  private static DataContainer _instance;

  public static DataContainer getInstance()
  {
    if (_instance == null)
      _instance = new DataContainer();
    return _instance;
  }

  Player player = new Player();

  ArrayList<Race> listeRaces = new ArrayList<Race>();
  ArrayList<Classe> listeClasses = new ArrayList<Classe>();

  ArrayList<Arme> listeArmes = new ArrayList<Arme>();
  ArrayList<Armure> listeArmures = new ArrayList<Armure>();
  ArrayList<ObjectMagique> listeObjetsMagiques = new ArrayList<ObjectMagique>();
  ArrayList<Destinee> listeDestinee = new ArrayList<Destinee>();
  ArrayList<Competence> listeCompetences = new ArrayList<Competence>();

  public DataContainer()
  {
    if (Main.isLocalhost())
    {

      listeRaces.add(new Race(
          1,
          "Humain",
          "Dein Syria per speciosam interpatet diffusa planitiem. hanc nobilitat Antiochia, mundo cognita civitas, cui non certaverit alia advecticiis ita adfluere copiis et internis, et Laodicia et Apamia itidemque Seleucia iam inde a primis auspiciis florentissimae Dein Syria per speciosam interpatet diffusa planitiem. hanc nobilitat Antiochia, mundo cognita civitas,"
              + " cui non certaverit alia advecticiis ita adfluere copiis et internis, et Laodicia et Apamia itidemque Seleucia iam inde a primis auspiciis florentissimae ",
          "gfx/units/humans/fencer-idle-1.png"));
      listeRaces.add(new Race(2, "Elfe", "Les elfes sont &eacute;l&eacute;gants.",
          "gfx/units/elves/archer-idle-1.png"));

      listeClasses.add(new Classe(1, "Recrue", "N00B spotted !", "gfx/units/humans/bowman.png"));
      listeClasses.add(new Classe(2, "Garde", "Un garde de la nation du feu ?",
          "gfx/units/humans/pikeman.png"));
      listeClasses.add(new Classe(3, "Epeiste", "Un guerrier avec une &eacute;p&eacute;e !",
          "gfx/units/humans/fencer-idle-1.png"));

      listeClasses.add(new Classe(4, "Guerrier", "Guerrier elfe", "gfx/units/elves/avenger.png"));
      listeClasses.add(new Classe(5, "Archer", "Archer elfe", "gfx/units/elves/archer-idle-1.png"));
      listeClasses.add(new Classe(6, "Druide", "Druide", "gfx/units/elves/shaman.png"));

      listeArmes.add(new Arme(
          1,
          "Ep&eacute;e de bois",
          "gfx/items/woodensword.png",
          "Une &eacute;p&eacute;e de bois dont la r&eacute;sistance n'a d'&eacute;gale que la laideur.",
          5, true, Type.contact, 0, 1, 0));

      listeArmes.add(new Arme(
          1,
          "Ep&eacute;e de bois",
          "gfx/items/woodensword.png",
          "Une &eacute;p&eacute;e de bois dont la r&eacute;sistance n'a d'&eacute;gale que la laideur.",
          5, true, Type.contact, 0, 1, 0));

      listeArmes.add(new Arme(2, "Arbal&egrave;te lourde", "gfx/items/crossbow-human.png",
          "Tenez-vous &agrave; vos carreaux, &ccedil;a pourrait vous sauver la vie !", 8, true,
          Type.distance, 1, 3, 2));

      listeArmes.add(new Arme(2, "Arbal&egrave;te lourde", "gfx/items/crossbow-human.png",
          "Tenez-vous &agrave; vos carreaux, &ccedil;a pourrait vous sauver la vie !", 8, true,
          Type.distance, 1, 3, 2));

      listeArmes.add(new Arme(3, "Fouet d'explorateur", "gfx/items/whip.png",
          "Si vous &ecirc;tes un Lord Anglais, moi je suis Mickey Mouse !", 15, true, Type.contact,
          0, 1, 0));

      listeArmes.add(new Arme(3, "Fouet d'explorateur", "gfx/items/whip.png",
          "Si vous &ecirc;tes un Lord Anglais, moi je suis Mickey Mouse !", 15, true, Type.contact,
          0, 1, 0));

      listeArmures.add(new Armure(1, "Armure de cuir", "gfx/items/cuirass_leather.png",
          "Une armure en cuir de canard", 5, 1));

      listeDestinee.add(new Destinee(
          1,
          "Meneur",
          "Fier combattant, vous savez aussi remotiver vos alli&eacute;s &agrave; grands coups de cris !",
          "+1 armure &agrave; tous les alli&eacute;s &agrave; une distance de 2 ou moins", "gfx/Destin/Meneur.png",30));
      listeDestinee.add(new Destinee(2, "Sage",
          "Vous &ecirc;tes le plus sage de votre village !",
          "+5% d'exp&eacute;rience &agrave; chaque combat", "gfx/Destin/Sage.png", 50));
      listeDestinee.add(new Destinee(3, "Fortun&eacute;",
          "La fortune sourit aux audacieux !", "+5% d'or &agrave; chaque victoire en combat", "gfx/Destin/Fortune.png", 50));
    }
  }

  /*
   * Permet de charger la liste des persos du joueur
   */
  public void chargementPersos(final Loader loader)
  {
    player.getListePersos().clear();
    DataServiceAsync dataService = GWT.create(DataService.class);
    
    dataService.getPersos(new AsyncCallback<List<PersoEntity>>()
    {    
      @Override
      public void onSuccess(List<PersoEntity> result)
      {
        for(PersoEntity entity : result)
        {
          player.getListePersos().add(Perso.getFromEntity(entity));
        }
        
        if (Main._instance.actualPage == "MainPanel")
        {
          ((MainPanel) Main._instance.bord.getWidget(0)).bindPersosInListbox();
        }
        if (loader != null)
          loader.setStep(8);
      }
      
      @Override
      public void onFailure(Throwable caught)
      {
        loader.loaderError();
      }
    });
    
  }

  public void ModifArgent(int somme)
  {
    this.player.setArgent(this.player.getArgent() + somme);
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
   * @return the listeRaces
   */
  public ArrayList<Race> getListeRaces()
  {
    return listeRaces;
  }

  /**
   * @param listeRaces the listeRaces to set
   */
  public void setListeRaces(ArrayList<Race> listeRaces)
  {
    this.listeRaces = listeRaces;
  }

  /**
   * @return the listeClasses
   */
  public ArrayList<Classe> getListeClasses()
  {
    return listeClasses;
  }

  /**
   * @param listeClasses the listeClasses to set
   */
  public void setListeClasses(ArrayList<Classe> listeClasses)
  {
    this.listeClasses = listeClasses;
  }

  /**
   * @return the listeArmes
   */
  public ArrayList<Arme> getListeArmes()
  {
    return listeArmes;
  }

  /**
   * @param listeArmes the listeArmes to set
   */
  public void setListeArmes(ArrayList<Arme> listeArmes)
  {
    this.listeArmes = listeArmes;
  }

  /**
   * @return the listeArmures
   */
  public ArrayList<Armure> getListeArmures()
  {
    return listeArmures;
  }

  /**
   * @param listeArmures the listeArmures to set
   */
  public void setListeArmures(ArrayList<Armure> listeArmures)
  {
    this.listeArmures = listeArmures;
  }

  /**
   * @return the listeObjetsMagiques
   */
  public ArrayList<ObjectMagique> getListeObjetsMagiques()
  {
    return listeObjetsMagiques;
  }

  /**
   * @param listeObjetsMagiques the listeObjetsMagiques to set
   */
  public void setListeObjetsMagiques(ArrayList<ObjectMagique> listeObjetsMagiques)
  {
    this.listeObjetsMagiques = listeObjetsMagiques;
  }

  /**
   * @return the listeDestinee
   */
  public ArrayList<Destinee> getListeDestinee()
  {
    return listeDestinee;
  }

  /**
   * @param listeDestinee the listeDestinee to set
   */
  public void setListeDestinee(ArrayList<Destinee> listeDestinee)
  {
    this.listeDestinee = listeDestinee;
  }

}
