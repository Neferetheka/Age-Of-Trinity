package com.aerilys.ageoftrinity.client.ui.panels.mobile;

import com.aerilys.ageoftrinity.client.Main;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ConnexionPanelMobile extends Composite
{

  private static ConnexionPanelMobileUiBinder uiBinder = GWT.create(ConnexionPanelMobileUiBinder.class);

  interface ConnexionPanelMobileUiBinder extends UiBinder<Widget, ConnexionPanelMobile>
  {
  }
  
  @UiField
  InputElement connexionPseudo;
  @UiField
  InputElement connexionMdp;
  @UiField
  Button connexionButton;
  @UiField
  DivElement divPseudo;

  public ConnexionPanelMobile()
  {
    initWidget(uiBinder.createAndBindUi(this));
    
    this.getElement().setId("connexionPanel");
    connexionPseudo.setId("connexionPseudo");
    connexionMdp.setId("connexionMdp");

    String storedPseudo = Main.storeGet("pseudo");
    if (storedPseudo != "")
      connexionPseudo.setValue(storedPseudo);

    if (Window.Location.getParameter("p") != null)
    {
      connexionPseudo.setValue(Window.Location.getParameter("p"));
    }

    if (Window.Location.getParameter("md") != null)
    {
      connexionMdp.setValue(Window.Location.getParameter("md"));
    }
  }
  
  @UiHandler("connexionButton")
  void handleClick(ClickEvent e)
  {
    initConnexion();
  }
  
  public void initConnexion()
  {
    if (checkFields())
    {
      connexionButton.setEnabled(false);
      if (!Main.isLocalhost())
      {
        String result = Main.nativeRequete(Main.HOST + "/API/auth.php?p="
            + connexionPseudo.getValue().trim() + "&md=" + connexionMdp.getValue().trim());

        if (result.contains("*"))
        {
          this.setVisible(false);
          Main.storeSet("pseudo", connexionPseudo.getValue().trim());
          Main._instance.chargementPlayer(connexionPseudo.getValue().trim());
        }
        else
        {
          Main.alert("Pseudo ou mot de passe incorrect", "Erreur de connexion");
          connexionButton.setEnabled(true);
        }
      }
      else
      {
        if(connexionPseudo.getValue().trim().length() > 2)
        {
          Main.storeSet("pseudo", connexionPseudo.getValue().trim());
          Main._instance.chargementPlayer(connexionPseudo.getValue().trim());
        }
      }
    }
  }

  private boolean checkFields()
  {
    if (connexionPseudo.getValue().trim().length() < 2)
    {
      return false;
    }
    if (connexionMdp.getValue().trim().length() < 2)
    {
      return false;
    }
    return true;
  }


}
