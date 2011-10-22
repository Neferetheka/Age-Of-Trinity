package com.aerilys.ageoftrinity.client.ui;

import java.util.ArrayList;

import com.aerilys.ageoftrinity.client.tools.game.AttaquePerso;
import com.aerilys.ageoftrinity.client.tools.game.GameEngine;
import com.aerilys.ageoftrinity.client.tools.game.Combattant;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class AttaqueDialog extends DialogBox {

  Image attaquantImage;
  Image defenseurImage;
  Label labelAttaque;
  CellList<AttaquePerso> listBox;
  public AttaquePerso attaquePerso;
  public Combattant cible;

  public AttaqueDialog() {
    setStyleName("attaqueDialogBox");
    this.center();
    this.setModal(true);
    this.setAnimationEnabled(true);

    HTMLPanel panel = new HTMLPanel("");
    setWidget(panel);
    panel.setSize("100%", "100%");

    Button closeButton = new Button("");
    closeButton.addKeyDownHandler(new KeyDownHandler() {
      public void onKeyDown(KeyDownEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE)
          close();
      }
    });
    closeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        close();
      }
    });
    closeButton.setStyleName("closeDiv");
    panel.add(closeButton);
    closeButton.setSize("25px", "25px");

    HTMLPanel header = new HTMLPanel("");
    header.setStyleName("attaqueDialogBoxHeader");
    panel.add(header);

    HorizontalPanel horizontalPanel = new HorizontalPanel();
    header.add(horizontalPanel);

    attaquantImage = new Image("gfx/units/elves/archer-idle-1.png");
    attaquantImage.setAltText("Attaquant");
    horizontalPanel.add(attaquantImage);
    attaquantImage.setSize("72px", "72px");

    labelAttaque = new Label("New label");
    labelAttaque.setStyleName("attaqueDialogLabel");
    horizontalPanel.add(labelAttaque);
    labelAttaque.setSize("230px", "100%");

    defenseurImage = new Image((String) null);
    defenseurImage.setAltText("Defenseur");
    horizontalPanel.add(defenseurImage);
    defenseurImage.setSize("72px", "72px");

    listBox = new CellList<AttaquePerso>(new AttaquePersoCell());
    listBox.setStyleName("attaqueDialogListBox");
    listBox.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

    final SingleSelectionModel<AttaquePerso> selectionModel = new SingleSelectionModel<AttaquePerso>();
    listBox.setSelectionModel(selectionModel);

    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
      public void onSelectionChange(SelectionChangeEvent event) {
        AttaquePerso selected = selectionModel.getSelectedObject();
        if (selected != null) {

        }
      }
    });

    panel.add(listBox);

    Button validerButton = new Button("Valider");
    validerButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        if (selectionModel.getSelectedObject() != null) {
          attaquePerso = selectionModel.getSelectedObject();
          close();
        }
      }
    });
    validerButton.setStyleName("gwt-Button-perso");
    validerButton.getElement().setId("attaqueDialogBoxValiderButton");
    panel.add(validerButton);
    
    this.setVisible(false);
  }

  public void show(Combattant attaquant, Combattant defenseur) {
    attaquantImage.setUrl(attaquant.getSrcBase() + "-idle-1.png");
    defenseurImage.setUrl(defenseur.getSrcBase() + "-idle-1.png");
    labelAttaque.setText(attaquant.getNom() + " attaque " + defenseur.getNom());

    double distanceBetween = GameEngine.getDistanceBetweenCoords(attaquant.getCoords(),
        defenseur.getCoords());

    ArrayList<AttaquePerso> listeAttaquesPossibles = new ArrayList<AttaquePerso>();
    for (AttaquePerso attaque : attaquant.getListeAttaques()) {
      if (attaque.getCoutPA() <= attaquant.getPtsMouvementActuel()
          && distanceBetween <= attaque.getPortee())
        listeAttaquesPossibles.add(attaque);
    }

    if (listeAttaquesPossibles.size() > 0) {
      listBox.setRowCount(listeAttaquesPossibles.size());
      listBox.setRowData(0, listeAttaquesPossibles);

      cible = defenseur;
      System.out.println(listeAttaquesPossibles.size());
      this.setVisible(true);
      super.show();
    }
    else
      super.hide();
  }

  public void close() {
    super.hide();
  }
}

class AttaquePersoCell extends AbstractCell<AttaquePerso> {
  @Override
  public void render(com.google.gwt.cell.client.Cell.Context context, AttaquePerso value,
      SafeHtmlBuilder sb) {
    sb.appendHtmlConstant("<img src=\"" + value.getSrcIcone()
        + "\" style=\"width:72px; float:left;\"/>");
    sb.appendHtmlConstant("<h3>" + value.getNom() + "</h3>");
    sb.appendHtmlConstant("<strong>Cout : </strong>" + value.getCoutPA());
    sb.appendHtmlConstant("<br/><strong>D&eacute;g&acirc;ts : </strong>" + value.getMinDegats()
        + "/" + value.getMaxDegats());
  }
}
