package com.aerilys.ageoftrinity.client.ui.panels;

import java.util.ArrayList;

import com.aerilys.ageoftrinity.client.tools.Vector;
import com.aerilys.ageoftrinity.client.tools.game.AttaquePerso;
import com.aerilys.ageoftrinity.client.tools.game.CaseMap;
import com.aerilys.ageoftrinity.client.tools.game.DegatsLabelAnimation;
import com.aerilys.ageoftrinity.client.tools.game.GameEngine;
import com.aerilys.ageoftrinity.client.tools.game.GameTime;
import com.aerilys.ageoftrinity.client.tools.game.Combattant;
import com.aerilys.ageoftrinity.client.tools.game.ia.Agent;
import com.aerilys.ageoftrinity.client.ui.AttaqueDialog;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.dom.client.Touch;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.GestureStartEvent;
import com.google.gwt.event.dom.client.GestureStartHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

public class GamePanel extends HTMLPanel
{

  Canvas canvas;
  Context2d context;
  static final int canvasHeight = 536;
  static final int canvasWidth = 720;
  final CssColor redrawColor = CssColor.make("rgba(255,255,255,1)");
  Combattant actualPerso;
  ArrayList<CaseMap> listeCases = new ArrayList<CaseMap>();
  ArrayList<Combattant> listePersos = new ArrayList<Combattant>();
  ArrayList<DegatsLabelAnimation> listeDegatsLabelAnimation = new ArrayList<DegatsLabelAnimation>();
  int mouseX, mouseY;
  CaseMap maskMap;

  Label actualPersoPvLabel;
  Label actualPersoPtsMvtLabel;
  InlineHTML actualPersoName;
  Image actualPersoInfosImage;
  HTMLPanel logPanel;
  ScrollPanel scrollPanel;

  public GamePanel()
  {
    super("");

    canvas = Canvas.createIfSupported();

    if (canvas == null)
    {
      RootPanel.get().add(new Label(
          "Votre navigateur ne supporte pas la balise Canvas. Veuillez le mettre &agrave; jour."));
      return;
    }

    canvas.setStyleName("mainCanvas");
    canvas.setWidth(canvasWidth + "px");
    canvas.setCoordinateSpaceWidth(canvasWidth);

    canvas.setHeight(canvasHeight + "px");
    canvas.setCoordinateSpaceHeight(canvasHeight);

    context = canvas.getContext2d();

    final Timer timer = new Timer()
    {
      @Override
      public void run()
      {
        update();
        draw();
      }
    };
    timer.scheduleRepeating(GameTime.FRAMERATE);

    String terrain = "floor";
    for (int i = 1; i < 14; i++)
    {
      for (int j = 1; j < 8; j++)
      {
        if (i % 2 == 0)
          terrain = "floor";
        else
          terrain = "cloud";
        listeCases.add(new CaseMap("gfx/maps/" + terrain + ".png", RootPanel.get(),
            new Vector(i, j)));

      }
    }

    maskMap = new CaseMap("gfx/maps/fog.png", RootPanel.get(), new Vector(-1, -1));

    Combattant elfe = new Combattant("Galaad", "gfx/units/elves/archer", "gfx/units/elves/archer-idle-1.png",
        RootPanel.get(), listeCases.get(0), 4);
    elfe.getListeAttaques().add(
        new AttaquePerso("Attaque &agrave; l'&eacute;p&eacute;e",
            "gfx/icons/attaques/sword-elven.png", 2, 1, 2));
    elfe.getListeAttaques().add(
        new AttaquePerso("Attaque &agrave; l'arc", "gfx/icons/attaques/bow-elven.png", 4, 2, 1, 2));
    listePersos.add(elfe);
    Combattant fencer = new Combattant("Fencer", "gfx/units/humans/fencer",
        "gfx/units/humans/fender-idle-1.png", RootPanel.get(), listeCases.get(6), 4);
    fencer.getIdleAnimation().setIndexMaxAnimation(7);
    fencer.getIdleAnimation().setUpdateFrameRate(150);
    fencer.getSwordAnimation().setIndexMaxAnimation(9);
    fencer.getSwordAnimation().setSrcBase("attack");
    fencer.getListeAttaques().add(
        new AttaquePerso("Danse mortelle", "gfx/icons/attaques/greatsword-human.png", 3, 1, 4));
    fencer.setIA(true);
    elfe.setIA(false);
    listePersos.add(fencer);
    actualPerso = elfe;

    HTMLPanel canvasContainer = new HTMLPanel("");
    this.add(canvasContainer);
    canvasContainer.getElement().setId("canvasContainer");
    canvasContainer.add(canvas);

    HTMLPanel sideContainer = new HTMLPanel("");
    sideContainer.getElement().setId("sideContainer");
    this.add(sideContainer);

    HTMLPanel actualPersoInfos = new HTMLPanel("");
    actualPersoInfos.setStyleName("actualPersoInfos");
    sideContainer.add(actualPersoInfos);

    actualPersoName = new InlineHTML("<h3>Galaad</h3>");
    actualPersoInfos.add(actualPersoName);

    actualPersoInfosImage = new Image("gfx/units/elves/archer-idle-1.png");
    actualPersoInfosImage.setAltText("Personnage selectionn\u00E9");
    actualPersoInfosImage.setStyleName("actualPersoInfosImage");
    actualPersoInfos.add(actualPersoInfosImage);
    actualPersoInfosImage.setSize("72px", "72px");

    Grid grid = new Grid(2, 2);
    actualPersoInfos.add(grid);
    grid.setSize("70px", "68px");

    Image image = new Image("gfx/icons/ball-magenta.png");
    image.setAltText("Points de vie");
    image.getElement().setAttribute("title", "Points de vie");
    grid.setWidget(0, 0, image);
    image.setSize("32px", "32px");

    actualPersoPvLabel = new Label("140/140");
    grid.setWidget(0, 1, actualPersoPvLabel);

    Image image_1 = new Image("gfx/icons/ball-green.png");
    image_1.getElement().setAttribute("title", "Points de mouvement");
    image_1.setAltText("Points de mouvement");
    grid.setWidget(1, 0, image_1);
    image_1.setSize("32px", "32px");

    actualPersoPtsMvtLabel = new Label("2/4");
    grid.setWidget(1, 1, actualPersoPtsMvtLabel);

    Button btnFinDuTour = new Button("Fin du tour");
    sideContainer.add(btnFinDuTour);
    btnFinDuTour.setStyleName("gwt-Button-perso");

    scrollPanel = new ScrollPanel();
    scrollPanel.setStyleName("scrollLogPanel");
    sideContainer.add(scrollPanel);

    logPanel = new HTMLPanel("");
    scrollPanel.setWidget(logPanel);
    logPanel.setSize("100%", "100%");

    btnFinDuTour.addClickHandler(new ClickHandler()
    {
      public void onClick(ClickEvent event)
      {
        actualPerso.finDuTour();
        nextTurn();
      }
    });

    initHandlers();
  }

  /**
   * Met à jour la logique du jeu
   */
  public void update()
  {
    GameTime.Update();
    for (Combattant combattant : listePersos)
    {
      combattant.update();
    }
    // if(actualPerso.getPtsMouvementActuel() == 0)
    // nextTurn();
    for (int i = 0; i < listeDegatsLabelAnimation.size(); i++)
    {
      if (listeDegatsLabelAnimation.get(i).getActualTime() >= listeDegatsLabelAnimation.get(i).getTimeBeforeEnd())
        listeDegatsLabelAnimation.remove(i);
      else
        listeDegatsLabelAnimation.get(i).update();
    }

    if (actualPerso.isIA())
    {
      if (actualPerso.getDestination() == null
          && actualPerso.getPtsMouvementActuel() == actualPerso.getPtsMouvementTotal())
      {
        Vector action = Agent.Agir(actualPerso, listePersos, listeCases);
        if(action.x != actualPerso.getCoords().x || 
            action.y != actualPerso.getCoords().y)
        { 
          logIntoScreen(action.x+"/"+action.y);
          CaseMap caseMap = findByCoordsNatural(action);
          if(caseMap != null)
            ManageDeplacement(caseMap);
          else
            logIntoScreen("La case est null !");
        }
      }

      if (actualPerso.getDestination() == null)
      {
        AttaquePerso attaqueMax = actualPerso.getListeAttaques().get(0);
        Combattant cible = Agent.GetCloserPerso(actualPerso, listePersos, listeCases);

        double distanceBetween = GameEngine.getDistanceBetweenCoords(actualPerso.getCoords(),
            cible.getCoords());

        for (AttaquePerso attaque : actualPerso.getListeAttaques())
        {
          if (attaque.getCoutPA() <= actualPerso.getPtsMouvementActuel()
              && attaque.getCoutPA() > attaqueMax.getCoutPA()
              && distanceBetween <= attaque.getPortee())
            attaqueMax = attaque;
        }

        if (attaqueMax != null && distanceBetween <= attaqueMax.getPortee())
          attaqueClick(cible, attaqueMax);
        
        nextTurn();
      }  
    }
  }

  /**
   * Met à jour l'affichage du canvas
   */
  public void draw()
  {
    clear();
    for (CaseMap caseMap : listeCases)
      caseMap.draw(context);

    if (maskMap.getCoordsNaturel().x > 0)
    {
      // System.out.println(maskMap.getPosition().x+"/"+maskMap.getPosition().y);
      maskMap.draw(context);
    }

    for (Combattant combattant : listePersos)
      combattant.draw(context);
    // actualPerso.draw(context);

    for (DegatsLabelAnimation animation : listeDegatsLabelAnimation)
      animation.draw(context);
  }

  /*
   * Déclenche le début d'un nouveau tour en passant la main au prochain
   * personnage dans l'ordre d'initiative
   */
  public void nextTurn()
  {
    int index = listePersos.indexOf(actualPerso);
    if (index + 1 >= listePersos.size())
      index = 0;
    else
      index++;
    actualPerso = listePersos.get(index);
    actualPerso.debutNouveauTour();
    majPanelInformations(actualPerso);
    logIntoScreen("Nouveau tour !");
  }

  /*
   * Clean la surface de dessin avec du ... Blanc \o/
   */
  public void clear()
  {
    context.setFillStyle(redrawColor);
    context.fillRect(0, 0, canvasWidth, canvasHeight);
  }

  /*
   * Permet de déterminer sur quelle case on a cliqué à partir des coordonnées
   * X/Y (relatives au canvas)
   */
  public CaseMap findByCaseByCoords(double x, double y)
  {
    CaseMap clickedCase = null;
    for (CaseMap caseMap : listeCases)
    {
      if (x < caseMap.getPosition().x + 72)
      {
        if (y < caseMap.getPosition().y + 72)
        {
          clickedCase = caseMap;
          break;
        }
      }
    }

    return clickedCase;
  }

  public CaseMap findByCoordsNatural(Vector coords)
  {
    CaseMap clickedCase = null;
    for (CaseMap caseMap : listeCases)
    {
      if (caseMap.getCoordsNaturel().x == coords.x && caseMap.getCoordsNaturel().y == coords.y)
        clickedCase = caseMap;
    }

    return clickedCase;
  }

  /*
   * Met à jour le panneau latéral en fonction des infos d'un personnage
   */
  public void majPanelInformations(Combattant persoToDisplay)
  {
    actualPersoName.setHTML("<h3>" + persoToDisplay.getNom() + "</h3>");
    actualPersoInfosImage.setUrl(persoToDisplay.getSrcBase() + "-idle-1.png");
    actualPersoPtsMvtLabel.setText(persoToDisplay.getPtsMouvementActuel() + "/"
        + persoToDisplay.getPtsMouvementTotal());
    actualPersoPvLabel.setText(persoToDisplay.getPvActu() + "/" + persoToDisplay.getPvTotal());
  }

  /*
   * Permet d'ajouter un texte au log des infos dans la sidebar
   */
  public void logIntoScreen(String log)
  {
    logIntoScreen(log, "silver");
  }

  /*
   * Permet d'ajouter un texte au log des infos dans la sidebar
   */
  public void logIntoScreen(String log, String color)
  {
    logPanel.add(new InlineHTML("<span style=\"color:" + color + "\">" + log + "</span><br/>"));
    scrollPanel.setVerticalScrollPosition(scrollPanel.getMaximumVerticalScrollPosition());
  }

  /*
   * Permet d'initialiser les events handler du Canvas
   */
  void initHandlers()
  {

    canvas.addClickHandler(new ClickHandler()
    {
      @Override
      public void onClick(ClickEvent event)
      {
        // On va déterminer sur quelle case le clic a été fait
        if (!actualPerso.isIA())
        {
          CaseMap clickedCase = null;
          for (CaseMap caseMap : listeCases)
          {
            if (event.getRelativeX(canvas.getElement()) < caseMap.getPosition().x + 72)
            {
              if (event.getRelativeY(canvas.getElement()) < caseMap.getPosition().y + 72)
              {
                clickedCase = caseMap;
                break;
              }
            }
          }

          if (clickedCase != null)
            System.out.println("Click sur la case : " + clickedCase.getCoordsNaturel().x + "/"
                + clickedCase.getCoordsNaturel().y);
          // actualPerso.setPosition(clickedCase.getPosition());

          ManageDeplacement(clickedCase);
        }
      }
    });

    canvas.addMouseMoveHandler(new MouseMoveHandler()
    {
      public void onMouseMove(MouseMoveEvent event)
      {
        mouseX = event.getRelativeX(canvas.getElement());
        mouseY = event.getRelativeY(canvas.getElement());
        CaseMap clickedCase = findByCaseByCoords(mouseX, mouseY);
        if (clickedCase != null)
        {
          Combattant cible = null;
          for (Combattant combattant : listePersos)
          {
            if (combattant.equals(actualPerso))
              continue;
            if (combattant.getCoords().x == clickedCase.getCoordsNaturel().x
                && combattant.getCoords().y == clickedCase.getCoordsNaturel().y)
              cible = combattant;
          }

          if (cible == null)
          {
            if (GameEngine.getDistanceBetweenCoords(clickedCase.getCoordsNaturel(),
                actualPerso.getCoords()) > actualPerso.getPtsMouvementActuel()
                && !caseMapBusy(clickedCase))
            {
              maskMap.setCoordsNaturel(clickedCase.getCoordsNaturel());
            }
            else
              maskMap.setCoordsNaturel(new Vector(-1, -1));

            /* On remet le perso actuel à jour sur l'affichage */
            majPanelInformations(actualPerso);
          }
          else
          {
            maskMap.setCoordsNaturel(new Vector(-1, -1));
            majPanelInformations(cible);
          }
        }
      }
    });

    /*
     * canvas.addMouseOutHandler(new MouseOutHandler() { public void
     * onMouseOut(MouseOutEvent event) { mouseX = -200; mouseY = -200; } });
     */

    canvas.addTouchMoveHandler(new TouchMoveHandler()
    {
      public void onTouchMove(TouchMoveEvent event)
      {
        event.preventDefault();
        if (event.getTouches().length() > 0)
        {
          Touch touch = event.getTouches().get(0);
          mouseX = touch.getRelativeX(canvas.getElement());
          mouseY = touch.getRelativeY(canvas.getElement());
        }
        event.preventDefault();
      }
    });

    canvas.addTouchEndHandler(new TouchEndHandler()
    {
      public void onTouchEnd(TouchEndEvent event)
      {
        event.preventDefault();
        mouseX = -200;
        mouseY = -200;
      }
    });

    canvas.addGestureStartHandler(new GestureStartHandler()
    {
      public void onGestureStart(GestureStartEvent event)
      {
        event.preventDefault();
      }
    });
  }

  /*
   * Permet de gèrer une action sur une case
   */
  protected void ManageDeplacement(CaseMap clickedCase)
  {
    logIntoScreen("Click on " + clickedCase.getCoordsNaturel().x + "/"
        + clickedCase.getCoordsNaturel().y);

    // On va verifier si la case est occupée ou non. Si ce n'est pas le cas
    // on se déplace
    Combattant cible = null;
    for (Combattant combattant : listePersos)
    {
      if (combattant.equals(actualPerso))
        continue;
      if (combattant.getCoords().x == clickedCase.getCoordsNaturel().x
          && combattant.getCoords().y == clickedCase.getCoordsNaturel().y)
        cible = combattant;
    }
    if (cible != null)
    {
      // alert("Vous avez vis&eacute; une cible !");
      final AttaqueDialog attaqueDialog = new AttaqueDialog();
      attaqueDialog.addCloseHandler(new CloseHandler<PopupPanel>()
      {
        @Override
        public void onClose(CloseEvent<PopupPanel> event)
        {
          if (attaqueDialog.attaquePerso != null)
          {
            attaqueClick(attaqueDialog.cible, attaqueDialog.attaquePerso);
          }
        }
      });
      attaqueDialog.show(actualPerso, cible);
    }
    else
    {
      actualPerso.seDeplacer(clickedCase);
    }

    majPanelInformations(actualPerso);
  }

  /*
   * Permet de déterminer si un personnage se trouve sur une case ou non
   */
  public boolean caseMapBusy(CaseMap map)
  {
    for (Combattant combattant : listePersos)
    {
      if (combattant.getCoords() == map.getCoordsNaturel())
        return true;
    }
    return false;
  }

  public void attaqueClick(Combattant cible, AttaquePerso attaque)
  {
    int dg = actualPerso.attaquer(cible, attaque);
    listeDegatsLabelAnimation.add(new DegatsLabelAnimation(dg + "", new Vector(
        cible.getPosition().x + Combattant.WIDTH_UNIT / 2, cible.getPosition().y)));
    logIntoScreen("<em>" + actualPerso.getNom() + "</em> attaque <em>" + cible.getNom()
        + "</em>. Il subit " + dg + " d&eacute;g&acirc;t(s) !", "red");
  }
}
