package com.aerilys.ageoftrinity.client.tools.game;

import java.util.ArrayList;
import java.util.Random;

import com.aerilys.ageoftrinity.client.models.Classe;
import com.aerilys.ageoftrinity.client.models.Destinee;
import com.aerilys.ageoftrinity.client.models.Race;
import com.aerilys.ageoftrinity.client.tools.AnimatedSprite;
import com.aerilys.ageoftrinity.client.tools.Vector;
import com.aerilys.ageoftrinity.client.tools.game.AttaquePerso.Type;
import com.aerilys.ageoftrinity.client.tools.game.GameEngine.TypeAttaque;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.ui.RootPanel;

public class Combattant extends AnimatedSprite {

  public enum PersoState {
    idle, walk, sword, bow
  }

  public PersoState persoState = PersoState.idle;
  public static final int WIDTH_UNIT = 72;
  public static final int HEIGHT_UNIT = 72;

  String nom;
  Race race;
  Classe classe;
  Destinee destinee;
  int niveau;
  
  int pvTotal = 10;
  int pvActu = pvTotal;
  int armure = 1;

  Vector coords = new Vector(1, 1);
  CaseMap destination;
  int timeSinceLastUpdate = 0;
  int ptsMouvementTotal = 4;
  int ptsMouvementActuel = 4;
  String srcBase;
  boolean isIA = false;

  Animation idleAnimation = new Animation("idle", 6, 200, true);
  Animation bowAnimation = new Animation("bow", 4, 150, true);
  Animation swordAnimation = new Animation("sword", 4, 100, true);

  ArrayList<AttaquePerso> listeAttaques = new ArrayList<AttaquePerso>();

 

  public Combattant(String src, RootPanel rootPanel) {
    super(src, rootPanel);
  }

  public Combattant(String nom, String srcBase, String src, RootPanel rootPanel, CaseMap caseMap,
      int ptsMouvement) {
    super(src, rootPanel);
    this.nom = nom;
    this.setPosition(caseMap.getPosition());
    this.coords = caseMap.getCoordsNaturel();
    this.ptsMouvementTotal = ptsMouvement;
    this.ptsMouvementActuel = this.ptsMouvementTotal;
    this.srcBase = srcBase;
  }
  
  

  public Combattant(String src, RootPanel rootPanel, String nom, Race race, Classe classe,
      Destinee destinee, String biographie)
  {
    super(src, rootPanel);
    this.nom = nom;
    this.race = race;
    this.classe = classe;
    this.destinee = destinee;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.aerilys.canvas.client.tools.Sprite#update()
   */
  public void update() {
    super.update();
    timeSinceLastUpdate += GameTime.FRAMERATE;

    if (persoState == PersoState.idle) {
      // System.out.println("Etat idle. Play : "+idleAnimation.inPlay);
      if (idleAnimation.inPlay)
        idleAnimation.update(this);
      else if (new Random().nextInt(1000) < 5)
        idleAnimation.play();
    } else if (persoState == PersoState.bow) {
      bowAnimation.update(this);
      if (!bowAnimation.inPlay) {
        persoState = PersoState.idle;
        idleAnimation.play();
      }
    } else if (persoState == PersoState.sword) {
      swordAnimation.update(this);
      if (!swordAnimation.inPlay) {
        persoState = PersoState.idle;
        idleAnimation.play();
      }
    }

    // Mise à jour déplacement
    if (timeSinceLastUpdate > 200 && destination != null) {
      timeSinceLastUpdate = 0;
      System.out.println("Coords : " + this.coords.x + "/" + coords.y + " - Destination : "
          + destination.getCoordsNaturel().x + "/" + destination.getCoordsNaturel().y);
      float rate = 0.75f;
      float rateY = 0.5f;

      // Déplacement sur X (et éventuellement en Y)
      if (this.coords.x != destination.getCoordsNaturel().x) {
        System.out.println("Deplacement X!");
        if (this.coords.x < destination.getCoordsNaturel().x)
          this.coords.x += 1;
        else {
          this.coords.x -= 1;
          rate *= -1;
        }

        if (this.coords.x % 2 == 0 && this.coords.y > destination.getCoordsNaturel().y) {
          rateY *= -1;
          this.coords.y += 1;
        } else if (this.coords.x % 2 != 0 && this.coords.y == destination.getCoordsNaturel().y)
          rateY *= -1;

        this.setPosition(new Vector(this.getPosition().x + WIDTH_UNIT * rate, this.getPosition().y
            + HEIGHT_UNIT * rateY));
        calculCoords();
      }

      // Déplacement uniquement sur Y
      else if (this.coords.y != destination.getCoordsNaturel().y) {
        rate = 1f;
        System.out.println("Deplacement Y!");
        if (this.coords.y < destination.getCoordsNaturel().y)
          this.coords.y += 1;
        else {
          this.coords.y -= 1;
          rate *= -1;
        }
        this.setPosition(new Vector(this.getPosition().x, this.getPosition().y + HEIGHT_UNIT * rate));
        calculCoords();
      } else {
        destination = null;
        persoState = PersoState.idle;
      }
    }
  }

  /*
   * Permet d'afficher le personnage
   * 
   * @see
   * com.aerilys.canvas.client.tools.Sprite#draw(com.google.gwt.canvas.dom.client
   * .Context2d)
   */
  public void draw(Context2d context) {
    super.draw(context);
  }

  /*   *** ACTIONS *** */

  public boolean seDeplacer(CaseMap caseMap) {
    // double difference = Math.abs(caseMap.getCoordsNaturel().x-
    // this.coords.x)+ Math.abs(caseMap.getCoordsNaturel().y- this.coords.y);
    double difference = GameEngine.getDistanceBetweenCoords(caseMap.getCoordsNaturel(), this.coords);
    if (difference <= this.ptsMouvementActuel) {
      destination = caseMap;
      timeSinceLastUpdate = 0;
      this.changerImage(this.srcBase + "-idle-1.png");
      persoState = PersoState.walk;
      ptsMouvementActuel -= difference;
      return true;
    }
    return false;
  }

  public int attaquer(Combattant cible, AttaquePerso attaque) {
    TypeAttaque type;
    if (attaque.type == Type.distance)
      type = TypeAttaque.bow;
    else
      type = TypeAttaque.sword;

    if (type == TypeAttaque.bow) {
      this.persoState = PersoState.bow;
      bowAnimation.play();
    } else if (type == TypeAttaque.sword) {
      this.persoState = PersoState.sword;
      swordAnimation.play();
    }

    if (cible.getCoords().x >= this.getCoords().x)
      this.setScale(new Vector(1, 1));
    else
      this.setScale(new Vector(-1, 1));

    this.setPtsMouvementActuel(this.getPtsMouvementActuel() - attaque.getCoutPA());
    int dg = new Random().nextInt(attaque.getMaxDegats() - attaque.getMinDegats() + 1)
        + attaque.getMinDegats();
    return cible.perdrePv(dg);
  }

  /*   *** CALCUL *** */

  private int perdrePv(int degats) {
    degats -= this.armure;
    if (degats < 0)
      degats = 0;
    this.pvActu -= degats;
    if (this.pvActu < 0)
      this.pvActu = 0;

    System.out.println(this.getNom() + " subit " + degats + " degats. Il lui reste " + this.pvActu
        + "/" + this.pvTotal);
    return degats;
  }

  public void calculCoords() {
    double x;
    double y;
    x = Math.floor(this.getPosition().x / (WIDTH_UNIT * 0.75));
    if (this.getPosition().x % (WIDTH_UNIT * 0.75) > 0)
      x += 1;
    x += 1;

    if (x % 2 != 0)
      y = Math.floor(this.getPosition().y / HEIGHT_UNIT) + 1;
    else
      y = Math.floor((this.getPosition().y + HEIGHT_UNIT / 2) / HEIGHT_UNIT);

    System.out.println("Vous etes en " + x + "/" + y);
    this.setCoords(new Vector(x, y));
  }

  /*   *** GESTION DES TOURS *** */

  public void debutNouveauTour() {
    this.ptsMouvementActuel = this.ptsMouvementTotal;
  }

  public void finDuTour() {
    this.ptsMouvementActuel = 0;
  }

  /*   *** GETTERS/SETTERS *** */

  /**
   * @return the coords
   */
  public Vector getCoords() {
    return coords;
  }

  /**
   * @param coords the coords to set
   */
  public void setCoords(Vector coords) {
    this.coords = coords;
  }

  /**
   * @return the timeSinceLastUpdate
   */
  public int getTimeSinceLastUpdate() {
    return timeSinceLastUpdate;
  }

  /**
   * @param timeSinceLastUpdate the timeSinceLastUpdate to set
   */
  public void setTimeSinceLastUpdate(int timeSinceLastUpdate) {
    this.timeSinceLastUpdate = timeSinceLastUpdate;
  }

  /**
   * @return the ptsMouvementTotal
   */
  public int getPtsMouvementTotal() {
    return ptsMouvementTotal;
  }

  /**
   * @param ptsMouvementTotal the ptsMouvementTotal to set
   */
  public void setPtsMouvementTotal(int ptsMouvementTotal) {
    this.ptsMouvementTotal = ptsMouvementTotal;
  }

  /**
   * @return the ptsMouvementActuel
   */
  public int getPtsMouvementActuel() {
    return ptsMouvementActuel;
  }

  /**
   * @param ptsMouvementActuel the ptsMouvementActuel to set
   */
  public void setPtsMouvementActuel(int ptsMouvementActuel) {
    this.ptsMouvementActuel = ptsMouvementActuel;
  }

  /**
   * @return the idleAnimation
   */
  public Animation getIdleAnimation() {
    return idleAnimation;
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
   * @param idleAnimation the idleAnimation to set
   */
  public void setIdleAnimation(Animation idleAnimation) {
    this.idleAnimation = idleAnimation;
  }

  /**
   * @return the bowAnimation
   */
  public Animation getBowAnimation() {
    return bowAnimation;
  }

  /**
   * @param bowAnimation the bowAnimation to set
   */
  public void setBowAnimation(Animation bowAnimation) {
    this.bowAnimation = bowAnimation;
  }

  /**
   * @return the swordAnimation
   */
  public Animation getSwordAnimation() {
    return swordAnimation;
  }

  /**
   * @param swordAnimation the swordAnimation to set
   */
  public void setSwordAnimation(Animation swordAnimation) {
    this.swordAnimation = swordAnimation;
  }

  /**
   * @return the srcBase
   */
  public String getSrcBase() {
    return srcBase;
  }

  /**
   * @param srcBase the srcBase to set
   */
  public void setSrcBase(String srcBase) {
    this.srcBase = srcBase;
  }

  /**
   * @return the listeAttaques
   */
  public ArrayList<AttaquePerso> getListeAttaques() {
    return listeAttaques;
  }

  /**
   * @param listeAttaques the listeAttaques to set
   */
  public void setListeAttaques(ArrayList<AttaquePerso> listeAttaques) {
    this.listeAttaques = listeAttaques;
  }

  /**
   * @return the pvTotal
   */
  public int getPvTotal() {
    return pvTotal;
  }

  /**
   * @param pvTotal the pvTotal to set
   */
  public void setPvTotal(int pvTotal) {
    this.pvTotal = pvTotal;
  }

  /**
   * @return the pvActu
   */
  public int getPvActu() {
    return pvActu;
  }

  /**
   * @param pvActu the pvActu to set
   */
  public void setPvActu(int pvActu) {
    this.pvActu = pvActu;
  }

  /**
   * @return the armure
   */
  public int getArmure() {
    return armure;
  }

  /**
   * @param armure the armure to set
   */
  public void setArmure(int armure) {
    this.armure = armure;
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
   * @return the isIA
   */
  public boolean isIA()
  {
    return isIA;
  }

  /**
   * @param isIA the isIA to set
   */
  public void setIA(boolean isIA)
  {
    this.isIA = isIA;
  }

  /**
   * @return the destination
   */
  public CaseMap getDestination()
  {
    return destination;
  }

  /**
   * @param destination the destination to set
   */
  public void setDestination(CaseMap destination)
  {
    this.destination = destination;
  }

}
