package com.aerilys.ageoftrinity.client.ui;

import java.util.Random;

import com.aerilys.ageoftrinity.client.Main;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class AlertBox extends Composite
{
  private static AlertBoxUiBinder uiBinder = GWT.create(AlertBoxUiBinder.class);

  interface AlertBoxUiBinder extends UiBinder<Widget, AlertBox>
  {
  }

  @UiField
  SpanElement titleElement;
  @UiField
  Button validerButton;
  @UiField
  SpanElement bodyElement;

  public AlertBox()
  {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void show(String message)
  {
    this.show(message, "Message");
  }

  public void show(String message, String title)
  {
    int rand = new Random().nextInt(1000);
    this.getElement().setId("AlertBox" + rand);
    this.titleElement.setInnerHTML(title);
    this.bodyElement.setInnerHTML(message);

    RootPanel.get().add(this);
    Main.runShowEffect("fade", this.getElement().getId());
  }

  @UiHandler("validerButton")
  void onValiderButtonClick(ClickEvent event)
  {
    this.close();
  }

  public void close()
  {
    //Main.runHideEffect("fade", this.getElement().getId());
    RootPanel.get().remove(this);
  }
}
