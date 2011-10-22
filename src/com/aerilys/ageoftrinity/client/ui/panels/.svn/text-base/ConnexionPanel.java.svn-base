package com.aerilys.ageoftrinity.client.ui.panels;

import com.aerilys.ageoftrinity.client.Main;
import com.aerilys.ageoftrinity.client.rpc.AuthService;
import com.aerilys.ageoftrinity.client.rpc.AuthServiceAsync;
import com.aerilys.ageoftrinity.client.ui.ConfirmBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ConnexionPanel extends Composite
{

  private static ConnexionPanelUiBinder uiBinder = GWT.create(ConnexionPanelUiBinder.class);

  interface ConnexionPanelUiBinder extends UiBinder<Widget, ConnexionPanel>
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
  @UiField
  Image loaderImage;
  @UiField
  HTMLPanel errorField;

  public ConnexionPanel()
  {
    initWidget(uiBinder.createAndBindUi(this));
    // setSize("400px", "235px");
    

    this.setStyleName("modal");
    this.getElement().setId("connexionPanel");
    connexionPseudo.setId("connexionPseudo");
    connexionMdp.setId("connexionMdp");
    
    loaderImage.setVisible(false);
    errorField.setVisible(false);
    errorField.getElement().setId("errorField");

    String storedPseudo = Main.storeGet("pseudo");
    if (storedPseudo != "" && storedPseudo != "null" && storedPseudo != null)
      connexionPseudo.setValue(storedPseudo);

    if (Window.Location.getParameter("p") != null)
    {
      connexionPseudo.setValue(Window.Location.getParameter("p"));
    }

    if (Window.Location.getParameter("md") != null)
    {
      connexionMdp.setValue(Window.Location.getParameter("md"));
      // initConnexion();
    }
  }

  @UiHandler("connexionButton")
  void handleClick(ClickEvent e)
  {
    initConnexion();
  }

  public void initConnexion()
  {
    ConfirmBox confirm = new ConfirmBox();
    confirm.show("Ceci est une r&eacute;volution !", "Sujet ?");
    if (checkFields())
    {
      connexionButton.setEnabled(false);
      // connexionButton.setAttribute("style", "opacity:0.5");
      if (!Main.isLocalhost())
      {
        loaderImage.setVisible(true);
        AuthServiceAsync authServiceSvc = GWT.create(AuthService.class);
        
        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>(){

          @Override
          public void onFailure(Throwable caught)
          {
            Main.alert("Erreur lors de l'authentification avec le serveur.");
            loaderImage.setVisible(false);
            Main.runShowEffect("fade", "errorField");
          }

          @Override
          public void onSuccess(Boolean result)
          {
            if (result)
            {
              Main.runHideEffect("drop", "connexionPanel");
              Main.storeSet("pseudo", connexionPseudo.getValue().trim());
              Main._instance.chargementPlayer(connexionPseudo.getValue().trim());
            }
            else
            {
              //Main.alert("Pseudo ou mot de passe incorrect", "Erreur de connexion");
              connexionButton.setEnabled(true);
              loaderImage.setVisible(false);
              Main.runShowEffect("fade", "errorField");
            }
          }
          
        };
        
        authServiceSvc.authenticate(connexionPseudo.getValue().trim(), connexionMdp.getValue().trim(), callback);
        }
      
      else
      {
        if(connexionPseudo.getValue().trim().length() > 2)
        {
          Main.runHideEffect("drop", "connexionPanel");
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
      Main.runUIEffect("shake", "connexionPseudo", 150);
      return false;
    }
    if (connexionMdp.getValue().trim().length() < 2)
    {
      Main.runUIEffect("shake", "connexionMdp", 150);
      return false;
    }
    return true;
  }
}
