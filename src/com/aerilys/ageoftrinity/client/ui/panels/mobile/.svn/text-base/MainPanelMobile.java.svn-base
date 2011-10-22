package com.aerilys.ageoftrinity.client.ui.panels.mobile;

import java.util.List;

import com.aerilys.ageoftrinity.client.Main;
import com.aerilys.ageoftrinity.client.models.Perso;
import com.aerilys.ageoftrinity.client.tools.DataContainer;
import com.aerilys.ageoftrinity.client.ui.widgets.PersoPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Image;

public class MainPanelMobile extends Composite
{

  private static MainPanelMobileUiBinder uiBinder = GWT.create(MainPanelMobileUiBinder.class);

  interface MainPanelMobileUiBinder extends UiBinder<Widget, MainPanelMobile>
  {
  }
  
  
  @UiField HTMLPanel panelPersos;
  @UiField HTMLPanel panelGame;
  @UiField HTMLPanel panelBoutique;
  @UiField HTMLPanel panelCarte;
  @UiField HTMLPanel panelProfil;
  @UiField HTMLPanel panelDeconnexion;
  @UiField Image imagePersos;
  @UiField Image imageGame;
  @UiField Image imageBoutique;
  @UiField Image imageCarte;
  @UiField Image imageProfil;
  @UiField Image imageDeconnexion;


  protected boolean isOrientationPortrait;

  public MainPanelMobile()
  {
    initWidget(uiBinder.createAndBindUi(this));
    
    this.getElement().setId("mainPanel");


    Window.addResizeHandler(new ResizeHandler() {
      public void onResize(ResizeEvent event) {
        if (isOrientationPortrait != Main.calculateOrientationPortrait()) {
          isOrientationPortrait = !isOrientationPortrait;
          adjustOrientation(isOrientationPortrait);
        }
      }
    });
  }



  /*
   * Méthode d'ajustement de l'UI suivant l'orientation
   */
  protected void adjustOrientation(boolean isOrientationPortrait2)
  {
    // TODO Auto-generated method stub    
  }

  @UiHandler("imageGame")
  void onImageGameClick(ClickEvent event) {
    Main._instance.transition("Game");
  }
}
