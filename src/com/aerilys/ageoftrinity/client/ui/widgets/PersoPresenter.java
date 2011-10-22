package com.aerilys.ageoftrinity.client.ui.widgets;

import com.aerilys.ageoftrinity.client.Main;
import com.aerilys.ageoftrinity.client.models.Perso;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.dev.shell.OophmSessionHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;

/*
 * Panel permettant d'afficher un personnage sur le MainPanel
 */
public class PersoPresenter extends HTMLPanel
{
  Perso perso;
  public Image persoImg;
  private InlineHTML lvlCircle;
  private HTMLPanel tooltip;

  public PersoPresenter(Perso perso)
  {
    super("");
    this.setStyleName("PersoPresenter");

    Image sphere = new Image("gfx/GUI/EmptyCircle.png");
    sphere.setAltText("S.");
    sphere.setStyleName("PersoPresenterSphere");
    add(sphere);

    persoImg = new Image((String) null);
    persoImg.addMouseOutHandler(new MouseOutHandler() {
      public void onMouseOut(MouseOutEvent event) {
        hideTooltip();
      }
    });
    persoImg.addMouseOverHandler(new MouseOverHandler() {
      public void onMouseOver(MouseOverEvent event) {
        showTooltip();
      }
    });
    persoImg.setStyleName("PersoPresenterPersoImg");
    add(persoImg);

    this.perso = perso;

    Image blueCircle = new Image("gfx/GUI/blueCircle.png");
    blueCircle.setAltText("Niv.");
    blueCircle.setStyleName("blueCircle");
    add(blueCircle);

    lvlCircle = new InlineHTML("3");
    lvlCircle.setStyleName("lvlCircle");
    add(lvlCircle);

    tooltip = new HTMLPanel("");
    tooltip.setStyleName("ToolTipAbove");
    tooltip.getElement().setId("tooltip"+perso.getNom().replace(" ", ""));
    add(tooltip);

    UpdateUI();
  }

  private void UpdateUI()
  {
    persoImg.setUrl(perso.getClasse().getImage());
    persoImg.setAltText(perso.getNom());
    lvlCircle.setText(perso.getNiveau()+"");
    
    String tooltipContent = "<div class=\"twipsy above\">\r\n            <div class=\"twipsy-arrow\"></div>\r\n            " +
    		"<div class=\"twipsy-inner\"><h5>"+perso.getNom()+"</h5>"+perso.getClasse().getNom()+" "+perso.getRace().getNom()+"<br/>Destin&eacute;e : "+perso.getDestinee().getNom()+"</div>\r\n          </div>";
    tooltip.getElement().setInnerHTML(tooltipContent);
  }

  public void showTooltip()
  {
    Main.runShowEffect("fade", tooltip.getElement().getId());
  }

  public void hideTooltip()
  {
	  Main.runHideEffect("fade", tooltip.getElement().getId());
  }

  /**
   * @return the perso
   */
  public Perso getPerso()
  {
    return perso;
  }

  /**
   * @param perso the perso to set
   */
  public void setPerso(Perso perso)
  {
    this.perso = perso;
    UpdateUI();
  }

}
