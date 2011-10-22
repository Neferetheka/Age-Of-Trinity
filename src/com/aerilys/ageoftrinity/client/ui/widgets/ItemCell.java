package com.aerilys.ageoftrinity.client.ui.widgets;

import com.aerilys.ageoftrinity.client.models.Item;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class ItemCell extends AbstractCell<Item> {
  @Override
  public void render(com.google.gwt.cell.client.Cell.Context context, Item value,
      SafeHtmlBuilder sb) {
    sb.appendHtmlConstant("<img src=\"" + value.getImage()
        + "\" style=\"width:72px; float:left;\"/>");
    sb.appendHtmlConstant("<h3>" + value.getNom() + "</h3>");
    sb.appendHtmlConstant(value.getPrix()+" <img src=\"gfx/GUI/gold.png\" alt=\"PO\" title=\"Pi&egrave;ces d'or\"/><br/><br/>");
  }
}
