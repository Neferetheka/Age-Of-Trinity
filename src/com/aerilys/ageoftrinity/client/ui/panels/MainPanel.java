package com.aerilys.ageoftrinity.client.ui.panels;

import java.util.List;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.aerilys.ageoftrinity.client.Main;
import com.aerilys.ageoftrinity.client.models.Classe;
import com.aerilys.ageoftrinity.client.models.Destinee;
import com.aerilys.ageoftrinity.client.models.Perso;
import com.aerilys.ageoftrinity.client.models.Race;
import com.aerilys.ageoftrinity.client.tools.DataContainer;
import com.aerilys.ageoftrinity.client.ui.ChildWindow;
import com.aerilys.ageoftrinity.client.ui.widgets.PersoPresenter;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHTML;

public class MainPanel extends HTMLPanel
{
  Perso selectedPerso;
  private HTMLPanel charactersPanel;
  private HTMLPanel detailsName;
  private HTMLPanel detailInfos;
  private HTMLPanel detailItems;
  private HTMLPanel detailsBiographie;
  private Image tileAdd;
  private HTMLPanel detailArmeActu;
  private HTMLPanel detailArmureActu;
  private HTMLPanel detailObjetActu;
  private HTMLPanel addPanel;
  private InlineHTML addLabel;
  private HTMLPanel deletePanel;
  private Image image;
  private InlineHTML deleteLabel;
  private HTMLPanel itemPanel;
  private Image image_1;
  private InlineHTML itemLabel;
  private Image bgImage;
  private HTMLPanel sharePanel;
  private Image image_2;
  private InlineHTML nlnhtmlinviterUnAmi;

  public MainPanel()
  {
    super("");
    // Main.log("Main Panel!");
    setSize("1000px", "");
    this.getElement().setId("mainPanel");
    charactersPanel = new HTMLPanel("");
    charactersPanel.setStyleName("characterPanel");
    add(charactersPanel);

    HTMLPanel infosPanel = new HTMLPanel("");
    infosPanel.setStyleName("infosPanel");
    add(infosPanel);
    infosPanel.setWidth("");

    HTMLPanel detailsCharacter = new HTMLPanel("");
    detailsCharacter.setStyleName("detailsCharacter");
    infosPanel.add(detailsCharacter);

    detailsName = new HTMLPanel("");
    detailsName.setStyleName("detailsName");
    detailsCharacter.add(detailsName);

    detailInfos = new HTMLPanel("Infos");
    detailInfos.setStyleName("detailInfos");
    detailsCharacter.add(detailInfos);

    detailItems = new HTMLPanel("");
    detailItems.setStyleName("detailItems");
    detailsCharacter.add(detailItems);

    detailArmeActu = new HTMLPanel("");
    detailItems.add(detailArmeActu);

    detailArmureActu = new HTMLPanel("");
    detailItems.add(detailArmureActu);

    detailObjetActu = new HTMLPanel("");
    detailItems.add(detailObjetActu);

    detailsBiographie = new HTMLPanel("");
    detailsBiographie.setStyleName("detailsBiographie");
    detailsCharacter.add(detailsBiographie);

    HTMLPanel optionsPanel = new HTMLPanel("");
    optionsPanel.setStyleName("optionsPanel");
    infosPanel.add(optionsPanel);
    
    addPanel = new HTMLPanel("");
    optionsPanel.add(addPanel);
    
        tileAdd = new Image("gfx/icons/icon-hostgame.png");
        tileAdd.setAltText("Recruter un personnage");
        addPanel.add(tileAdd);
        tileAdd.addClickHandler(new ClickHandler()
        {
          public void onClick(ClickEvent event)
          {
            Main._instance.transition("CreationPersoPanel");
          }
        });
        tileAdd.setStyleName("Tile");
        
        addLabel = new InlineHTML("<h2>Recruter un personnage</h2>\r\nPlus on est de guerriers, plus on peut taper !");
        addPanel.add(addLabel);
        
        deletePanel = new HTMLPanel("");
        optionsPanel.add(deletePanel);
        
        image = new Image("gfx/GUI/Tiles/die.png");
        image.setStyleName("Tile");
        image.setAltText("Supprimer le personnage");
        deletePanel.add(image);
        
        deleteLabel = new InlineHTML("<h2>Supprimer le personnage</h2>\r\nMais non, il ne va pas mourir. Enfin... Pas vraiment !");
        deletePanel.add(deleteLabel);
        
        itemPanel = new HTMLPanel("");
        optionsPanel.add(itemPanel);
        
        image_1 = new Image("gfx/GUI/Tiles/dummy.png");
        image_1.setStyleName("Tile");
        image_1.setAltText("Equipements");
        itemPanel.add(image_1);
        
        itemLabel = new InlineHTML("<h2>Equiper le personnage</h2>\r\nC'est pas la taille de l'arme qui compte ? Vous \u00EAtes s\u00FBr ?");
        itemPanel.add(itemLabel);
        
        sharePanel = new HTMLPanel("");
        optionsPanel.add(sharePanel);
        
        image_2 = new Image("gfx/icons/icon-serverother.png");
        image_2.setStyleName("Tile");
        image_2.setAltText("Equipements");
        sharePanel.add(image_2);
        
        nlnhtmlinviterUnAmi = new InlineHTML("<h2>Inviter un ami</h2>\r\nTaper &agrave; deux, c'est toujours mieux ! Enfin, il parait...");
        sharePanel.add(nlnhtmlinviterUnAmi);
        
        bgImage = new Image("gfx/GUI/portraits/shyde.png");
        bgImage.setStyleName("bgBordImage");
        infosPanel.add(bgImage);

    bindPersosInListbox();
  }

  protected void selectionChanged(Perso perso)
  {
    selectedPerso = perso;
    detailsName.getElement().setInnerHTML(perso.getNom().toUpperCase());
    SafeHtmlBuilder sb = new SafeHtmlBuilder();
    sb.appendHtmlConstant("<img class=\"listBoxPersosImg\" src=\"" + perso.getClasse().getImage()
        + "\" alt=\"" + perso.getClasse().getNom() + "\" /> " + "<h3>" + perso.getClasse().getNom()
        + " " + perso.getRace().getNom() + "</h3>" + "<br/>Niveau " + perso.getNiveau()
        + "<br/>Destin&eacute;e : " + perso.getDestinee().getNom() + "");
    detailInfos.getElement().setInnerHTML(sb.toSafeHtml().asString());

    detailsBiographie.getElement().setInnerHTML(perso.getBiographie());

    if (perso.getArmeActu() != null)
    {
      String detailsArme = "<img src=\"" + perso.getArmeActu().getImage()
          + "\" style=\"width:36px; float:left;\"/>";
      detailsArme += "<h3>" + perso.getArmeActu().getNom() + "</h3>";

      detailArmeActu.getElement().setInnerHTML(detailsArme);
    }

    if (perso.getArmureActu() != null)
    {
      String detailsArmure = "<img src=\"" + perso.getArmureActu().getImage()
          + "\" style=\"width:36px; float:left;\"/>";
      detailsArmure += "<h3>" + perso.getArmureActu().getNom() + "</h3>";

      detailArmureActu.getElement().setInnerHTML(detailsArmure);
    }

    if (perso.getObjetActu() != null)
    {
      String detailsObjet = "<img src=\"" + perso.getObjetActu().getImage()
          + "\" style=\"width:36px; float:left;\"/>";
      detailsObjet += "<h3>" + perso.getObjetActu().getNom() + "</h3>";

      detailObjetActu.getElement().setInnerHTML(detailsObjet);
    }
  }

  public void bindPersosInListbox()
  {
    List<Perso> listePersos = DataContainer.getInstance().getPlayer().getListePersos();

    if (Main.isLocalhost() && listePersos.size() == 0)
    {
      listePersos.add(new Perso("Test", DataContainer.getInstance().getListeRaces().get(0),
          DataContainer.getInstance().getListeClasses().get(0),
          DataContainer.getInstance().getListeDestinee().get(0), "Biographie", 1));

      listePersos.add(new Perso("Test 2", DataContainer.getInstance().getListeRaces().get(1),
          DataContainer.getInstance().getListeClasses().get(1),
          DataContainer.getInstance().getListeDestinee().get(0), "Biographie", 1));
      
      listePersos.add(new Perso("Galaad", DataContainer.getInstance().getListeRaces().get(1),
          DataContainer.getInstance().getListeClasses().get(3),
          DataContainer.getInstance().getListeDestinee().get(0), "Biographie", 1));
      
      listePersos.add(new Perso("Aryanne", DataContainer.getInstance().getListeRaces().get(1),
          DataContainer.getInstance().getListeClasses().get(4),
          DataContainer.getInstance().getListeDestinee().get(0), "Biographie", 1));
      
      listePersos.add(new Perso("Neferetheka", DataContainer.getInstance().getListeRaces().get(1),
          DataContainer.getInstance().getListeClasses().get(5),
          DataContainer.getInstance().getListeDestinee().get(0), "Biographie de Nef'", 1));
      
      listePersos.add(new Perso("Jag", DataContainer.getInstance().getListeRaces().get(0),
          DataContainer.getInstance().getListeClasses().get(2),
          DataContainer.getInstance().getListeDestinee().get(0), "Biographie", 1));
    }
    charactersPanel.clear();
    for (Perso perso : listePersos)
    {
      final PersoPresenter presenter = new PersoPresenter(perso);
      presenter.persoImg.addClickHandler(new ClickHandler()
      {
        @Override
        public void onClick(ClickEvent event)
        {
          selectionChanged(presenter.getPerso());;
        }
      });

      charactersPanel.add(presenter);
    }
    
    if(listePersos.size() > 0)
      selectionChanged(listePersos.get(0));
  }
}
