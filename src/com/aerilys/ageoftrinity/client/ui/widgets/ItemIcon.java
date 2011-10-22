package com.aerilys.ageoftrinity.client.ui.widgets;

import com.aerilys.ageoftrinity.client.Main;
import com.aerilys.ageoftrinity.client.models.Item;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;

public class ItemIcon extends HTMLPanel {

  Item item;
  private Image itemIcone;
  
  public ItemIcon() {
    super("");
    this.setStyleName("itemIcone");
    itemIcone = new Image((String) null);
    
    itemIcone.addTouchStartHandler(new TouchStartHandler() {
      public void onTouchStart(TouchStartEvent event) {
        if(item != null)
          Main.alertBox(item.getNom());
      }
    });
    add(itemIcone);
    this.itemIcone.setUrl("gfx/items/blank-attack.png");
  }
  /**
   * @return the item
   */
  public Item getItem() {
    return item;
  }
  /**
   * @param item the item to set
   */
  public void setItem(Item item)
  {
    this.item = item;
    if(item == null)
      this.itemIcone.setUrl("gfx/items/blank-attack.png");
    else
    {
      this.setVisible(true);
      this.itemIcone.setAltText(this.item.getNom());
      this.itemIcone.setUrl(this.item.getImage());
      this.itemIcone.getElement().setAttribute("title", this.item.getNom());     
    }
  }
}
