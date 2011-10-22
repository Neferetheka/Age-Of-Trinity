package com.aerilys.ageoftrinity.client.ui.panels;

import sun.awt.image.PixelConverter.Bgrx;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.aerilys.ageoftrinity.client.Main;
import com.aerilys.ageoftrinity.client.rpc.DataService;
import com.aerilys.ageoftrinity.client.rpc.DataServiceAsync;
import com.aerilys.ageoftrinity.client.tools.DataContainer;
import com.aerilys.ageoftrinity.client.tools.game.AttaquePerso;
import com.aerilys.ageoftrinity.client.ui.ConfirmBox;
import com.aerilys.ageoftrinity.client.ui.widgets.ClasseSelectionBox;
import com.aerilys.ageoftrinity.client.ui.widgets.ItemCell;
import com.aerilys.ageoftrinity.client.ui.widgets.RaceSelectionBox;
import com.aerilys.ageoftrinity.client.models.Arme;
import com.aerilys.ageoftrinity.client.models.Armure;
import com.aerilys.ageoftrinity.client.models.Destinee;
import com.aerilys.ageoftrinity.client.models.Item;
import com.aerilys.ageoftrinity.client.models.ObjectMagique;
import com.aerilys.ageoftrinity.client.models.Race;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.FlexTable;
import com.aerilys.ageoftrinity.client.ui.widgets.ItemIcon;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;
import com.aerilys.ageoftrinity.client.html4gwt.RequiredTextBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.aerilys.ageoftrinity.client.ui.widgets.DestineeCell;
import com.aerilys.ageoftrinity.shared.PersoEntity;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class CreationPersoPanel extends HTMLPanel {

  RaceSelectionBox selectedRaceBox;
  ClasseSelectionBox selectedClasseBox;
  private HTMLPanel raceInfosTitre;
  private HTMLPanel raceInfosDescription;
  private HTMLPanel raceInfosDescriptionTexte;
  private Image raceInfosDescriptionImageImg;
  HTMLPanel actualPanel;
  private HTMLPanel racePanel;
  private HTMLPanel classePanel;
  private ClasseSelectionBox classe1SelectionBox;
  private ClasseSelectionBox classe2SelectionBox;
  private ClasseSelectionBox classe3SelectionBox;
  private HTMLPanel classeInfosTitre;
  private HTMLPanel classeInfosDescription;
  private HTMLPanel classeInfosDescriptionTexte;
  private HTMLPanel classeInfosDescriptionImage;
  private Image classeInfosDescriptionImg;
  private HTMLPanel itemPanel;
  private CellList<Item> itemListBox;
  private Label itemDetailsTitle;
  private HTMLPanel itemDetailsDescription;
  private ItemIcon itemIconeArmeActu;
  private ItemIcon itemIconeArmureActu;
  private ItemIcon itemIconeObjMagActu;
  private Label labelPrixActu;
  private HTMLPanel bgPanel;
  private HTMLPanel bgPanelRecap;
  private HTMLPanel itemRecapPerso;
  private Label destinLabel;
  private HTMLPanel destinDescription;

  int argentActu;
  int prixActu = 0;
  private Item actualSelectedItem;
  /*
   * Index du type d'équipement affiché
   */
  private int itemPanelActualSelection = 0; // On affiche les armes par défaut
  private Destinee actualSelectedDestinee;
  private TextArea bgBiographie;
  private RequiredTextBox bgNamePerso;
  private Button btnInvocation;
  

  public CreationPersoPanel() {
    super("");
    setStyleName("creationPersoPanel");
    setSize("1000px", "550px");
    argentActu = DataContainer.getInstance().getPlayer().getArgent();

    HTMLPanel titrePanel = new HTMLPanel("<h1>Creation du personnage</h1>");
    add(titrePanel);

    racePanel = new HTMLPanel("");
    add(racePanel);

    HorizontalPanel horizontalPanel = new HorizontalPanel();
    horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    horizontalPanel.setStyleName("raceTable");
    racePanel.add(horizontalPanel);

    final RaceSelectionBox humainSelectionBox = new RaceSelectionBox();
    //humainSelectionBox.setStyleName("raceSelectionBoxSelected", true);
    humainSelectionBox.getImageRace().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        //Main.runRemoveClassEffect("raceSelectionBoxSelected", selectedRaceBox.getElement().getId());
        selectedRaceBox = humainSelectionBox;
        //Main.runAddClassEffect("raceSelectionBoxSelected", selectedRaceBox.getElement().getId());
        selectionRace();
      }
    });
    horizontalPanel.add(humainSelectionBox);

    final RaceSelectionBox elfeSelectionBox = new RaceSelectionBox();
    elfeSelectionBox.getImageRace().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        //Main.runRemoveClassEffect("raceSelectionBoxSelected", selectedRaceBox.getElement().getId());
        selectedRaceBox = elfeSelectionBox;
        //Main.runAddClassEffect("raceSelectionBoxSelected", selectedRaceBox.getElement().getId());
        selectionRace();
      }
    });
    horizontalPanel.add(elfeSelectionBox);

    RaceSelectionBox nainSelectionBox = new RaceSelectionBox();
    horizontalPanel.add(nainSelectionBox);

    HTMLPanel racePanelInfos = new HTMLPanel("");
    racePanelInfos.setStyleName("racePanelInfos");
    racePanel.add(racePanelInfos);

    raceInfosTitre = new HTMLPanel("New HTML");
    raceInfosTitre.setStyleName("raceInfosTitre");
    racePanelInfos.add(raceInfosTitre);
    raceInfosTitre.setSize("", "");

    raceInfosDescription = new HTMLPanel("");
    raceInfosDescription.setStyleName("raceInfosDescription");
    racePanelInfos.add(raceInfosDescription);
    raceInfosDescription.setSize("", "");

    raceInfosDescriptionTexte = new HTMLPanel("New HTML");
    raceInfosDescriptionTexte.setStyleName("raceInfosDescriptionTexte");
    raceInfosDescription.add(raceInfosDescriptionTexte);
    raceInfosDescriptionTexte.setSize("\r\n", "");

    HTMLPanel raceInfosDescriptionImage = new HTMLPanel("");
    raceInfosDescriptionImage.setStyleName("raceInfosDescriptionImage");
    raceInfosDescription.add(raceInfosDescriptionImage);
    raceInfosDescriptionImage.setSize("\r\n", "");

    raceInfosDescriptionImageImg = new Image((String) null);
    raceInfosDescriptionImage.add(raceInfosDescriptionImageImg);

    /* *** CLASSEPANEL *** */

    classePanel = new HTMLPanel("");
    classePanel.setVisible(false);
    add(classePanel);

    HorizontalPanel classeTable = new HorizontalPanel();
    classeTable.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    classeTable.setStyleName("classeTable");
    classePanel.add(classeTable);

    classe1SelectionBox = new ClasseSelectionBox();
    classe1SelectionBox.setStyleName("classeSelectionBox");
    //classe1SelectionBox.setStyleName("classeSelectionBoxSelected", true);
    classe1SelectionBox.getImageClasse().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        //Main.runRemoveClassEffect("classeSelectionBoxSelected",
        //    selectedClasseBox.getElement().getId());
        selectedClasseBox = classe1SelectionBox;
        //Main.runAddClassEffect("classeSelectionBoxSelected", selectedClasseBox.getElement().getId());
        selectionClasse();
      }
    });
    classeTable.add(classe1SelectionBox);

    classe2SelectionBox = new ClasseSelectionBox();
    classe2SelectionBox.setStyleName("classeSelectionBox");
    classe2SelectionBox.getImageClasse().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        //Main.runRemoveClassEffect("classeSelectionBoxSelected",
        //    selectedClasseBox.getElement().getId());
        selectedClasseBox = classe2SelectionBox;
        //Main.runAddClassEffect("classeSelectionBoxSelected", selectedClasseBox.getElement().getId());
        selectionClasse();
      }
    });
    classeTable.add(classe2SelectionBox);

    classe3SelectionBox = new ClasseSelectionBox();
    classe3SelectionBox.setStyleName("classeSelectionBox");
    classe3SelectionBox.getImageClasse().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        //Main.runRemoveClassEffect("classeSelectionBoxSelected",
        //    selectedClasseBox.getElement().getId());
        selectedClasseBox = classe3SelectionBox;
        //Main.runAddClassEffect("classeSelectionBoxSelected", selectedClasseBox.getElement().getId());
        selectionClasse();
      }
    });
    classeTable.add(classe3SelectionBox);

    selectedClasseBox = classe1SelectionBox;

    HTMLPanel classePanelInfos = new HTMLPanel("");
    classePanelInfos.setStyleName("classePanelInfos");
    classePanel.add(classePanelInfos);

    classeInfosTitre = new HTMLPanel("");
    classeInfosTitre.setStyleName("classeInfosTitre");
    classePanelInfos.add(classeInfosTitre);
    classeInfosTitre.setSize("", "");

    classeInfosDescription = new HTMLPanel("");
    classeInfosDescription.setStyleName("classeInfosDescription");
    classePanelInfos.add(classeInfosDescription);
    classeInfosDescription.setSize("", "");

    classeInfosDescriptionTexte = new HTMLPanel("New HTML");
    classeInfosDescriptionTexte.setStyleName("classeInfosDescriptionTexte");
    classeInfosDescription.add(classeInfosDescriptionTexte);
    classeInfosDescriptionTexte.setSize("\r\n", "");

    classeInfosDescriptionImage = new HTMLPanel("");
    classeInfosDescriptionImage.setStyleName("classeInfosDescriptionImage");
    classeInfosDescription.add(classeInfosDescriptionImage);
    classeInfosDescriptionImage.setSize("\r\n", "");

    classeInfosDescriptionImg = new Image("gfx/GUI/sphere_<dynamic>.png");
    classeInfosDescriptionImg.setAltText((String) null);
    classeInfosDescriptionImage.add(classeInfosDescriptionImg);

    HTMLPanel classePanelButtonsPanel = new HTMLPanel("");
    classePanelButtonsPanel.setStyleName("classePanelButtonsPanel");
    classePanel.add(classePanelButtonsPanel);
    classePanelButtonsPanel.setSize("", "");

    Button btnNewButton = new Button("Pr\u00E9c\u00E9dent");
    btnNewButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        transition("classeBack");
      }
    });
    btnNewButton.setStyleName("btn");
    classePanelButtonsPanel.add(btnNewButton);

    Button button = new Button("Suivant");
    button.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        transition("classe");
      }
    });
    button.setStyleName("btn");
    classePanelButtonsPanel.add(button);

    itemPanel = new HTMLPanel("");
    itemPanel.setStyleName("itemPanel");
    itemPanel.setVisible(false);
    this.getElement().setId("itemPanel");
    add(itemPanel);

    itemRecapPerso = new HTMLPanel("");
    itemRecapPerso.setStyleName("itemRecapPerso");
    itemPanel.add(itemRecapPerso);

    Image itemImageRecap = new Image((String) null);
    itemRecapPerso.add(itemImageRecap);

    InlineHTML itemRecapLabel = new InlineHTML("<br/>Archer Elfe");
    itemRecapPerso.add(itemRecapLabel);

    HTMLPanel itemOptions = new HTMLPanel("");
    itemOptions.setStyleName("itemOptions");
    itemPanel.add(itemOptions);

    FlexTable flexTable = new FlexTable();
    itemOptions.add(flexTable);

    itemIconeArmeActu = new ItemIcon();
    flexTable.setWidget(0, 0, itemIconeArmeActu);

    Image itemSelectItem = new Image("gfx/GUI/left_arrow-button-active.png");
    itemSelectItem.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        selectItemAndPutItInInventory();
      }
    });
    itemSelectItem.setAltText("Ajouter");
    itemSelectItem.setStyleName("itemPanelSelect");
    flexTable.setWidget(0, 1, itemSelectItem);

    Image itemSwitchArme = new Image("gfx/GUI/sword.png");
    itemSwitchArme.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        setItemPanelActualSelection(0);
      }
    });
    itemSwitchArme.setStyleName("itemPanelSwitch");
    flexTable.setWidget(0, 2, itemSwitchArme);

    itemIconeArmureActu = new ItemIcon();
    flexTable.setWidget(1, 0, itemIconeArmureActu);

    Image itemSwitchArmure = new Image("gfx/GUI/armor.png");
    itemSwitchArmure.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        setItemPanelActualSelection(1);
      }
    });
    
    labelPrixActu = new Label("");
    flexTable.setWidget(1, 1, labelPrixActu);
    itemSwitchArmure.setStyleName("itemPanelSwitch");
    flexTable.setWidget(1, 2, itemSwitchArmure);

    itemIconeObjMagActu = new ItemIcon();
    flexTable.setWidget(2, 0, itemIconeObjMagActu);

    Image itemDeselectItem = new Image("gfx/GUI/right_arrow-button-active.png");
    itemDeselectItem.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        deselectItemAndPutItInInventory();
      }
    });
    itemDeselectItem.setStyleName("itemPanelSelect");
    itemDeselectItem.setAltText("Supprimer");
    flexTable.setWidget(2, 1, itemDeselectItem);

    Image itemSwitchObjMag = new Image("gfx/GUI/objMag.png");
    itemSwitchObjMag.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        setItemPanelActualSelection(2);
      }
    });
    itemSwitchObjMag.setStyleName("itemPanelSwitch");
    flexTable.setWidget(2, 2, itemSwitchObjMag);

    HTMLPanel itemListPanel = new HTMLPanel("");
    itemListPanel.setStyleName("itemListPanel");
    itemPanel.add(itemListPanel);

    ScrollPanel scrollPanel = new ScrollPanel();
    scrollPanel.setStyleName("itemScrollPanel");
    itemListPanel.add(scrollPanel);
    scrollPanel.setWidth("\r\n");

    itemListBox = new CellList<Item>(new ItemCell());
    scrollPanel.setWidget(itemListBox);
    itemListBox.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
    itemListBox.setFocus(true);
    itemListBox.setStyleName("GALD-WON itemListBox");
    final SingleSelectionModel<Item> selectionModel = new SingleSelectionModel<Item>();
    itemListBox.setSelectionModel(selectionModel);

    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
      public void onSelectionChange(SelectionChangeEvent event) {
        Item selected = selectionModel.getSelectedObject();
        if (selected != null) {
          displayItemInLabel(selected);
        }
      }
    });

    HTMLPanel itemDetails = new HTMLPanel("");
    itemDetails.setStyleName("itemDetails");
    itemListPanel.add(itemDetails);

    itemDetailsTitle = new Label("New label");
    itemDetailsTitle.setStyleName("itemDetailsTitle");
    itemDetails.add(itemDetailsTitle);

    itemDetailsDescription = new HTMLPanel("New HTML");
    itemDetailsDescription.setStyleName("itemDetailsDescription");
    itemDetails.add(itemDetailsDescription);

    HTMLPanel itemPanelButtons = new HTMLPanel("");
    itemPanelButtons.setStyleName("itemPanelButtons");
    itemPanel.add(itemPanelButtons);
    itemPanelButtons.setSize("", "");

    Button button_1 = new Button("Pr\u00E9c\u00E9dent");
    button_1.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        transition("itemBack");
      }
    });
    button_1.setStyleName("btn");
    itemPanelButtons.add(button_1);

    Button button_2 = new Button("Suivant");
    button_2.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        transition("item");
      }
    });
    button_2.setStyleName("btn");
    itemPanelButtons.add(button_2);

    bgPanel = new HTMLPanel("");
    bgPanel.setStyleName("bgPanel");
    bgPanel.setVisible(false);
    add(bgPanel);
    
    HTMLPanel bgPanelInfos = new HTMLPanel("");
    bgPanelInfos.setStyleName("bgPanelInfos");
    bgPanel.add(bgPanelInfos);
    bgPanelInfos.setSize("", "");
    
    bgPanelRecap = new HTMLPanel("");
    bgPanelInfos.add(bgPanelRecap);
    
    HTMLPanel bgPanelNom = new HTMLPanel("<br/>\r\n<strong>Nom du personnage</strong><br/>");
    bgPanelInfos.add(bgPanelNom);
    
    bgNamePerso = new RequiredTextBox();
    bgNamePerso.addValueChangeHandler(new ValueChangeHandler<String>() {
      public void onValueChange(ValueChangeEvent<String> event) {
        if(bgNamePerso.getText().length() < 3)
          btnInvocation.getElement().setAttribute("style", "opacity:0.5;");
        else
          btnInvocation.getElement().setAttribute("style", "opacity:1.0;");
      }
    });
    bgNamePerso.setStyleName("xlarge");
    bgNamePerso.setId("bgNamePerso");
    bgNamePerso.setPlaceHolder("Entrez un nom...");
    bgPanelNom.add(bgNamePerso);
    
    HTMLPanel bgPanelBiographie = new HTMLPanel("<br/><br/>\r\n<strong>Biographie</strong><br/>");
    bgPanelInfos.add(bgPanelBiographie);
    bgPanelBiographie.setWidth("");
    
    bgBiographie = new TextArea();
    bgBiographie.setCharacterWidth(50);
    bgBiographie.setVisibleLines(10);
    bgPanelBiographie.add(bgBiographie);
    
    HTMLPanel bgPanelDestin = new HTMLPanel("");
    bgPanelDestin.setStyleName("bgPanelDestin");
    bgPanel.add(bgPanelDestin);
    
    final SingleSelectionModel<Destinee> selectionModelDestinee = new SingleSelectionModel<Destinee>();

    selectionModelDestinee.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {   
      @Override
      public void onSelectionChange(SelectionChangeEvent event) {
        Destinee destinee = selectionModelDestinee.getSelectedObject();
        if(destinee != null)
          displayDestineeInLabel(destinee);
      }
    });
    
    ScrollPanel bgPanelScroll = new ScrollPanel();
    bgPanelDestin.add(bgPanelScroll);
    bgPanelScroll.setStyleName("bgPanelScroll");
    
    CellList<Destinee> listBoxDestin = new CellList<Destinee>(new DestineeCell());
    bgPanelScroll.setWidget(listBoxDestin);
    listBoxDestin.setSize("", "");
    listBoxDestin.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
    listBoxDestin.setFocus(true);
    listBoxDestin.setStyleName("GALD-WON destineeListBox");
    listBoxDestin.setRowCount(DataContainer.getInstance().getListeDestinee().size());
    listBoxDestin.setRowData(DataContainer.getInstance().getListeDestinee());
    listBoxDestin.setSelectionModel(selectionModelDestinee);
    listBoxDestin.getSelectionModel().setSelected(DataContainer.getInstance().getListeDestinee().get(0), true);
    
    HTMLPanel bgPanelDestinDetails = new HTMLPanel("");
    bgPanelDestinDetails.setStyleName("bgPanelDestinDetails");
    bgPanelDestin.add(bgPanelDestinDetails);
    
    destinLabel = new Label("Mon Destin");
    destinLabel.setStyleName("destinLabel");
    bgPanelDestinDetails.add(destinLabel);
    
    destinDescription = new HTMLPanel("DestinDescription");
    destinDescription.setStyleName("destinDescription");
    bgPanelDestinDetails.add(destinDescription);
    
    HTMLPanel bgPanelButtons = new HTMLPanel("");
    bgPanelButtons.setStyleName("bgPanelButtons");
    bgPanel.add(bgPanelButtons);
    
    Button button_3 = new Button("Pr\u00E9c\u00E9dent");
    button_3.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        transition("bgBack");
      }
    });
    button_3.setStyleName("btn");
    bgPanelButtons.add(button_3);
    
    btnInvocation = new Button("Invocation !");
    btnInvocation.getElement().setAttribute("style", "opacity:0.5;");
    btnInvocation.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        invocationClick();
      }
    });
    btnInvocation.setText("Invocation !");
    btnInvocation.setStyleName("btn primary");
    bgPanelButtons.add(btnInvocation);
    this.getElement().setId("creationPersoPanel");

    
    
    /* *** CHARGEMENT DES RACES *** */
    humainSelectionBox.setRace(DataContainer.getInstance().getListeRaces().get(0));
    elfeSelectionBox.setRace(DataContainer.getInstance().getListeRaces().get(1));

    selectedRaceBox = humainSelectionBox;

    HTMLPanel racePanelButtonsPanel = new HTMLPanel("");
    racePanelButtonsPanel.setStyleName("racePanelButtonsPanel");
    racePanel.add(racePanelButtonsPanel);
    racePanelButtonsPanel.setSize("", "");

    Button racePanelButton = new Button("Suivant");
    racePanelButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        transition("race");
      }
    });
    racePanelButtonsPanel.add(racePanelButton);

    actualPanel = racePanel;

    racePanelButton.setStyleName("btn");
    Main.runAddClassEffect("raceSelectionBoxSelected", selectedRaceBox.getElement().getId());
    selectionRace();
  }





  /*
   * Permet de switcher d'un panel à un autre
   */
  protected void transition(String fromPanel) 
  {
    if (fromPanel == "race") {
      racePanel.setVisible(false);
      classePanel.setVisible(true);
      chargementClasseOnUI(selectedRaceBox.race);
      this.setPrixActu(getPrixActu()+100);
    } 
    else if (fromPanel == "classeBack")
    {
      racePanel.setVisible(true);
      classePanel.setVisible(false);
    } 
    else if (fromPanel == "classe") 
    {
      this.setPrixActu(200);
      initItems();
      itemPanel.setVisible(true);
      classePanel.setVisible(false);
      itemIconeArmeActu.setItem(null);
      itemIconeArmureActu.setItem(null);
      itemIconeObjMagActu.setItem(null);
    } 
    else if (fromPanel == "itemBack")
    {
      itemPanel.setVisible(false);
      classePanel.setVisible(true);
    }
    else if (fromPanel == "item")
    {
      initBg();
      itemPanel.setVisible(false);
      bgPanel.setVisible(true);
    }
    else if (fromPanel == "bgBack")
    {
      itemPanel.setVisible(true);
      bgPanel.setVisible(false);
    }
  }


  
  /* *** RACE *** */
  
  /*
   * Permet de mettre à jour l'UI suivant la race selectionnée
   */
  public void selectionRace() {
    raceInfosTitre.getElement().setInnerHTML(selectedRaceBox.race.getNom());
    raceInfosDescriptionTexte.getElement().setInnerHTML(selectedRaceBox.race.getDescription());
    raceInfosDescriptionImageImg.setAltText(selectedRaceBox.race.getNom());
    raceInfosDescriptionImageImg.setUrl("gfx/GUI/sphere_" + selectedRaceBox.race.getNom() + ".png");
  }

  
  /* *** CLASSE *** */
  
  /*
   * Permet de mettre à jour l'UI suivant la classe selectionnée
   */
  public void selectionClasse() {
    classeInfosTitre.getElement().setInnerHTML(selectedClasseBox.classe.getNom());
    classeInfosDescriptionTexte.getElement().setInnerHTML(selectedClasseBox.classe.getDescription());
    classeInfosDescriptionImg.setAltText(selectedClasseBox.classe.getNom());
    classeInfosDescriptionImg.setUrl("gfx/GUI/sphere_" + selectedClasseBox.classe.getNom() + ".png");
  }

  /*
   * permet de mettre à jour la liste des classes suivant la race selectionnée
   */
  public void chargementClasseOnUI(Race selectedRace) {
    selectedClasseBox = classe1SelectionBox;
    switch (selectedRace.getId()) {
      case 1: // Humain
        classe1SelectionBox.setClasse(DataContainer.getInstance().getListeClasses().get(0));
        classe2SelectionBox.setClasse(DataContainer.getInstance().getListeClasses().get(1));
        classe3SelectionBox.setClasse(DataContainer.getInstance().getListeClasses().get(2));
        break;
      case 2: // Elfe
        classe1SelectionBox.setClasse(DataContainer.getInstance().getListeClasses().get(3));
        classe2SelectionBox.setClasse(DataContainer.getInstance().getListeClasses().get(4));
        classe3SelectionBox.setClasse(DataContainer.getInstance().getListeClasses().get(5));
        break;
    }

    selectionClasse();
  }
  
  
  /* *** ITEMS *** */
  
  /*
   * Permet d'initialiser le panel des items suivant la classe/race associée
   */
  public void initItems() {
    ((Image) ((HTMLPanel) itemPanel.getWidget(0)).getWidget(0)).setUrl(selectedClasseBox.classe.getImage());
    ((InlineHTML) ((HTMLPanel) itemPanel.getWidget(0)).getWidget(1)).getElement().setInnerHTML(
        "<br/><strong>" + selectedClasseBox.classe.getNom() + " " + selectedRaceBox.race.getNom()
            + "</strong>");

    setItemPanelActualSelection(0);
  }


  /**
   * @param itemPanelActualSelection the itemPanelActualSelection to set
   */
  public void setItemPanelActualSelection(int itemPanelActualSelection) {
    this.itemPanelActualSelection = itemPanelActualSelection;
    switch (this.itemPanelActualSelection) {
      default:
        this.itemListBox.setRowCount(DataContainer.getInstance().getListeArmes().size());
        this.itemListBox.setRowData(DataContainer.getInstance().getListeArmes());
        itemListBox.getSelectionModel().setSelected(
            DataContainer.getInstance().getListeArmes().get(0), true);
        break;
      case 1:
        this.itemListBox.setRowCount(DataContainer.getInstance().getListeArmures().size());
        this.itemListBox.setRowData(DataContainer.getInstance().getListeArmures());
        itemListBox.getSelectionModel().setSelected(
            DataContainer.getInstance().getListeArmures().get(0), true);
        break;
      case 2:
        this.itemListBox.setRowCount(DataContainer.getInstance().getListeObjetsMagiques().size());
        this.itemListBox.setRowData(DataContainer.getInstance().getListeObjetsMagiques());
        itemListBox.getSelectionModel().setSelected(
            DataContainer.getInstance().getListeObjetsMagiques().get(0), true);
        break;
    }
  }

  /*
   * Permet d'afficher les détails d'un item suivant la sélection dans la
   * listbox
   */
  public void displayItemInLabel(Item selectedItem) {
    itemDetailsTitle.getElement().setInnerHTML(selectedItem.getNom());
    String details = "<em>" + selectedItem.getDescription() + "</em><br/>";
    details += "<strong>Prix : </strong>" + selectedItem.getPrix()
        + " <img src=\"gfx/GUI/gold.png\" alt=\"PO\" title=\"Pi&egrave;ces d'or\"/><br/>";
    /* Details suivant le type (armure/arme/obj mag) */
    switch (itemPanelActualSelection) {
      case 0:
        Arme arme = (Arme) selectedItem;
        details += "<strong>Type : </strong>" + arme.getTypeArme().toString() + "<br/>";
        details += "<strong>Degat(s) suppl&eacute;mentaire(s) : </strong>+"
            + arme.getMinDegatsBonus() + "/+" + arme.getMaxDegatsBonus() + "<br/>";
        details += "<strong>Port&eacute;e suppl&eacute;mentaire : </strong>"
            + arme.getPorteeBonus() + "<br/>";
        break;
      case 1:
        Armure armure = (Armure) selectedItem;
        details += "<strong>protection : </strong>" + armure.getProtection() + "<br/>";
        break;
      case 2:
        @SuppressWarnings("unused")
        ObjectMagique objmag = (ObjectMagique) selectedItem;
        // details
        // +="<strong>protection : </strong>"+armure.getProtection()+"<br/>";
        break;
    }
    itemDetailsDescription.getElement().setInnerHTML(details);
    actualSelectedItem = selectedItem;
  }

  /*
   * Permet d'ajouter l'item actuellement selectionné dans l'inventaire
   */
  protected void selectItemAndPutItInInventory() {
    if (actualSelectedItem != null) {
      switch (itemPanelActualSelection) {
        case 0: // Armes
          if(itemIconeArmeActu.getItem() != null)
            this.setPrixActu(getPrixActu()-itemIconeArmeActu.getItem().getPrix());
          itemIconeArmeActu.setItem(actualSelectedItem);
          break;
        case 1: // Armures
          if(itemIconeArmureActu.getItem() != null)
            this.setPrixActu(getPrixActu()-itemIconeArmureActu.getItem().getPrix());
          itemIconeArmureActu.setItem(actualSelectedItem);
          break;
        case 2: // Objets magiques
          if(itemIconeObjMagActu.getItem() != null)
            this.setPrixActu(getPrixActu()-itemIconeObjMagActu.getItem().getPrix());
          itemIconeObjMagActu.setItem(actualSelectedItem);
          break;
      }
      this.setPrixActu(getPrixActu()+actualSelectedItem.getPrix());
    }
  }

  /*
   * Permet d'enlever l'item actuellement selectionné dans l'inventaire
   */
  protected void deselectItemAndPutItInInventory() {
    switch (itemPanelActualSelection) {
      case 0: // Armes
        this.setPrixActu(getPrixActu() - itemIconeArmeActu.getItem().getPrix());
        itemIconeArmeActu.setItem(null);
        break;
      case 1: // Armures
        this.setPrixActu(getPrixActu() - itemIconeArmureActu.getItem().getPrix());
        itemIconeArmureActu.setItem(null);
        break;
      case 2: // Objets magiques
        this.setPrixActu(getPrixActu() - itemIconeObjMagActu.getItem().getPrix());
        itemIconeObjMagActu.setItem(null);
        break;
    }
  }
  
  
  
  /* *** BG *** */
  
  
  private void initBg() {
    bgPanelRecap.getElement().setInnerHTML(itemRecapPerso.getElement().getInnerHTML());
  }

 
  protected void displayDestineeInLabel(Destinee destinee) {   
    destinLabel.getElement().setInnerHTML("Destin&eacute;e du "+destinee.getNom());
    String details = "<em>" + destinee.getDescription() + "</em><br/>";
    details += "<strong>Effet : </strong>"+destinee.getEffet()+"<br/>";
    details += "<strong>Prix : </strong>" + destinee.getPrix()
        + " <img src=\"gfx/GUI/gold.png\" alt=\"PO\" title=\"Pi&egrave;ces d'or\"/><br/>";
    destinDescription.getElement().setInnerHTML(details);
    actualSelectedDestinee = destinee;
  }
  
  
  
  
  
  protected void invocationClick() {
    String nomPerso = bgNamePerso.getText();
   if(nomPerso.length() < 3)
     Main.alert("Le nom de votre personnage doit faire au moins trois caract&egrave;res !", "Erreur");
   else
   {
     final int prix = prixActu+actualSelectedDestinee.getPrix();
     final ConfirmBox confirm = Main.confirm("Voulez-vous vraiment invoquer "+nomPerso+" pour "+prix+" pi&egrave;ces d'or ?");
     confirm.addCloseHandler(new CloseHandler<PopupPanel>() {
      @Override
      public void onClose(CloseEvent<PopupPanel> event) {
        if(confirm.dialogResult)
        {
          Main.addLoader();
          String uri = Main.HOST
              + "/API/perso.php?m=1&n="+bgNamePerso.getText()+"&r="+selectedRaceBox.race.getId()+"&c="+selectedClasseBox.classe.getId()+
              "&d="+actualSelectedDestinee.getId()+"&b="+bgBiographie.getText().trim();
          if(itemIconeArmeActu.getItem() != null)
            uri += "&ia="+itemIconeArmeActu.getItem().getId();
          if(itemIconeArmureActu.getItem() != null)
            uri += "&iar="+itemIconeArmureActu.getItem().getId();
          if(itemIconeObjMagActu.getItem() != null)
            uri += "&io="+itemIconeObjMagActu.getItem().getId();
          
          PersoEntity persoEntity = new PersoEntity();
          persoEntity.setNom(bgNamePerso.getText().trim());
          persoEntity.setIndexRace(selectedRaceBox.race.getId());
          persoEntity.setIndexClasse(selectedClasseBox.classe.getId());
          persoEntity.setIndexDestinee(actualSelectedDestinee.getId());
          persoEntity.setBiographie(bgBiographie.getText().trim());
          
          DataServiceAsync dataService = GWT.create(DataService.class);
          
          dataService.createPerso(persoEntity, new AsyncCallback<Boolean>()
          {
            
            @Override
            public void onSuccess(Boolean result)
            {
              Main.alert("Invocation de "+bgNamePerso.getText()+" r&eacute;ussie !", "Invocation");
              DataContainer.getInstance().chargementPersos(null);
              Main._instance.transition("MainPanel");
              DataContainer.getInstance().ModifArgent(-prix);
            }
            
            @Override
            public void onFailure(Throwable caught)
            {
              Main.alertWebError();
            }
          });      
        }
      }
    });
   }
  }
  
  /* *** GET/SET *** */
  

  /**
   * @return the prixActu
   */
  public int getPrixActu() {
    return prixActu;
  }

  /**
   * @param prixActu the prixActu to set
   */
  public void setPrixActu(int prixActu) {
    this.prixActu = prixActu;
    labelPrixActu.getElement().setInnerHTML(prixActu+" <img src=\"gfx/GUI/gold.png\" class=\"labelPrix\" title=\"Pi&egrave;ces d'or\" alt=\"PO\"/>");
  }
}
