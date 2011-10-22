package com.aerilys.ageoftrinity.client;

import com.aerilys.ageoftrinity.client.tools.Converter;
import com.aerilys.ageoftrinity.client.tools.DataContainer;
import com.aerilys.ageoftrinity.client.tools.clientfactory.ClientFactory;
import com.aerilys.ageoftrinity.client.ui.*;
import com.aerilys.ageoftrinity.client.ui.panels.ConnexionPanel;
import com.aerilys.ageoftrinity.client.ui.panels.CreationPersoPanel;
import com.aerilys.ageoftrinity.client.ui.panels.GamePanel;
import com.aerilys.ageoftrinity.client.ui.panels.MainPanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Main implements EntryPoint
{

  /*
   * Chaine de connexion de base au serveur
   */
  public static final String HOST = "http://www.aot.legendarts.fr";

  public static final String HOSTGAE = "http://ageoftrinity.appspot.com";

  public static Main _instance;
  public HTMLPanel bord;
  String historyToken;

  private HTMLPanel mainContainer;
  public String actualPage = "init";

  private ClientFactory clientFactory;

  public void onModuleLoad()
  {
    _instance = this;
    clientFactory = GWT.create(ClientFactory.class);

    RootPanel rootPanel = RootPanel.get();

    mainContainer = new HTMLPanel("");
    mainContainer.getElement().setId("mainContainer");

    HTMLPanel mainHeader;
    if (!isMobile())
      mainHeader = new HTMLPanel(
          "  <div class=\"topbar\" role=\"header\">\r\n   <div class=\"container fixed\">\r\n        <h3><a href=\"#\" id=\"topbarLogoLink\"><img src=\"gfx/GUI/logos/plainLogo220.png\" alt=\"Age Of trinity\"/></a></h3>\r\n        <ul id=\"topbarMenu\">\r\n          <li class=\"active\"><a href=\"#MainPanel\">Sanctuaire</a></li>\r\n          <li><a href=\"#Game\">Champ de bataille</a></li>\r\n          <li><a href=\"#Boutique\">Echoppe</a></li>\r\n          <li><a href=\"#\">Mappemonde</a></li>\r\n        </ul>\r\n\r\n        <ul id=\"topbarDropdown\" class=\"nav secondary-nav\">\r\n          <li class=\"menu\">\r\n            <a href=\"#\" class=\"menu\">Profil</a>\r\n            <ul class=\"menu-dropdown\" style=\"display: none; \">\r\n              <li><a href=\"#\">Mon compte</a></li>\r\n              <li><a href=\"#\">Succ&egrave;s</a></li>\r\n              <li class=\"divider\"></li>\r\n              <li><a href=\"#\">D&eacute;connexion</a></li>\r\n            </ul>\r\n          </li>\r\n        </ul>\r\n      </div>\r\n  </div>");
    else
    {
      mainHeader = new HTMLPanel(
          "<div class=\"topbar\" role=\"header\"><span class=\"title\">Age Of Trinity</span></div>");
    }

    mainHeader.getElement().setId("mainHeader");
    rootPanel.add(mainHeader);

    HTMLPanel profilSideBar = new HTMLPanel("Profil");
    profilSideBar.setStyleName("profilSideBar");
    rootPanel.add(profilSideBar);
    rootPanel.add(mainContainer);

    HTMLPanel main = new HTMLPanel("");
    mainContainer.add(main);
    main.getElement().setId("main");
    main.getElement().setAttribute("role", "main");

    HTMLPanel header = new HTMLPanel("");
    header.getElement().setId("header");
    main.add(header);

    bord = new HTMLPanel("");
    bord.getElement().setId("bord");
    main.add(bord);

    HTMLPanel footer = new HTMLPanel("<h6>Aerilys 2011</h6>");
    footer.getElement().setId("footer");
    main.add(footer);

    mainContainer.add(clientFactory.GetConnexionPanel());

    HTMLPanel statsSideBar = new HTMLPanel("Stats");
    rootPanel.add(statsSideBar);
    statsSideBar.setStyleName("statsSideBar");
    // transition("GamePanel");

    History.addValueChangeHandler(new ValueChangeHandler<String>()
    {
      public void onValueChange(ValueChangeEvent<String> event)
      {
        if (historyToken == null || historyToken.compareTo(event.getValue()) != 0)
        {
          historyToken = event.getValue().trim();
          transition(historyToken);
        }
      }
    });

    nativeInit();
  }

  /* *** CHARGEMENT DES DONNEES *** */
  public void chargementPlayer(String pseudo)
  {
    DataContainer.getInstance().getPlayer().setPseudo(pseudo);

    bord.add(new Loader());
    // transition("MainPanel");
  }

  /* *** GESTION UI *** */

  public void transition(String newEcran)
  {
    if (DataContainer.getInstance().getPlayer().getPseudo() != null || newEcran.equals("MainPanel"))
    {
      bord.clear();
      actualPage = newEcran;

      if (newEcran.equals("MainPanel"))
      {
        bord.add(clientFactory.GetMainPanel());
      }
      else if (newEcran.equals("GamePanel") || newEcran.equals("Game"))
      {
        bord.add(new GamePanel());
      }
      else if (newEcran.equals("CreationPersoPanel"))
        bord.add(new CreationPersoPanel());
    }
    // History.newItem(newEcran);
  }

  public void annulerChargement()
  {
    bord.clear();
    ConnexionPanel connexionPanel = new ConnexionPanel();
    mainContainer.add(connexionPanel);
  }

  public void finchargement()
  {
    transition("MainPanel");
    if (getDeviceType() == "desktop")
      Main.runHideEffect("drop", "loaderPanel");
    RootPanel.get("topbarMenu").getElement().setAttribute("style", "display:block");
    RootPanel.get("topbarDropdown").getElement().setAttribute("style", "display:block");
  }

  /* *** ALERT *** */

  public static void alert(String message, String sujet)
  {
    if (!isMobile())
    {
      AlertBox alert = new AlertBox();
      alert.show(message, sujet);
    }
    else
    {
      message = Converter.htmlToText(message);
      if (!isNative())
        Main.alertBox(message);
      else
        mobileNativeCall("alert", message);
    }
  }

  public static void alert(String message)
  {
    alert(message, "Message");
  }

  public static void alertWebError()
  {
    alert(
        "Une erreur est survenue lors de la communication avec le serveur. Veuillez v&eacute;rifier votre connexion internet !",
        "Erreur");
  }

  public static ConfirmBox confirm(String message)
  {
    ConfirmBox confirm = new ConfirmBox();
    confirm.show(message, "Message");
    return confirm;
  }

  public static boolean mobileConfirm(String message)
  {
    message = Converter.htmlToText(message);
    if (!isNative())
    {
      nativeConfirm(message);
    }
    else
      return Converter.convertStringToBoolean(mobileNativeCall("confirm", message));
    return true;
  }

  /* *** METHODES NATIVES *** */
  public static native String nativeRequete(String url)
  /*-{
  	return $wnd.requete(url, null);
  }-*/;

  public static native void log(String message)
  /*-{
  	$wnd.console.log(message);
  }-*/;

  public static native void runHideEffect(String effect, String div)
  /*-{
  	$wnd.runHideEffect(effect, div);
  }-*/;

  public static native void runAddClassEffect(String classe, String div)
  /*-{
  	$wnd.runAddClassEffect(classe, div);
  }-*/;

  public static native void runRemoveClassEffect(String classe, String div)
  /*-{
  	$wnd.runRemoveClassEffect(classe, div);
  }-*/;

  public static native void runUIEffect(String effect, String div, int duration)
  /*-{
    $wnd.runUIEffect(effect, div,duration);
  }-*/;

  public static native void runShowEffect(String effect, String div)
  /*-{
    $wnd.runShowEffect(effect, div);
  }-*/;

  public static native void nativeInit()
  /*-{
  	$wnd.nativeInit();
  }-*/;

  public static native void alertBox(String message)
  /*-{
    $wnd.alert(message);
  }-*/;

  public static native boolean nativeConfirm(String message)
  /*-{
    return $wnd.confirm(message);
  }-*/;

  public static native String getUA()
  /*-{
    return $wnd.navigator.userAgent;
  }-*/;

  public static native String getDeviceType()
  /*-{
    return $wnd.getDeviceType();
  }-*/;

  public static native String mobileNativeCall(String func, String value)
  /*-{
    return $wnd.mobileNativeCall(func,value);
  }-*/;

  /* *** DIVERS *** */
  public static void storeSet(String key, String value)
  {
    try
    {
      if (Storage.isLocalStorageSupported())
      {
        Storage storage = Storage.getLocalStorageIfSupported();
        if (storage != null)
          storage.setItem(key, value);
      }
    }
    catch (Exception e)
    {
      Cookies.setCookie(key, value);
    }
  }

  public static String storeGet(String key)
  {
    String result = "";
    try
    {
      if (Storage.isLocalStorageSupported())
      {
        Storage storage = Storage.getLocalStorageIfSupported();
        if (storage != null)
          result = storage.getItem(key);
      }
    }
    catch (Exception e)
    {
      if (Cookies.getCookie("pseudo") != null)
        result = Cookies.getCookie("pseudo");
    }

    return result;
  }

  public static boolean calculateOrientationPortrait()
  {
    return Window.getClientHeight() > Window.getClientWidth();
  }

  public static boolean isMobile()
  {
    return (getDeviceType().equals("mobile")) ? true : false;
  }

  public static boolean isNative()
  {
    if (Window.Location.getParameter("n") != null)
      return true;
    else
      return false;
  }

  public static void addLoader()
  {
    Main._instance.bord.add(new GenericLoader());
  }

  public static void endLoader(GenericLoader loader)
  {
    Main._instance.bord.remove(loader);
  }

  public static boolean isLocalhost()
  {
    if (Window.Location.getHost().startsWith("127.0.0.1"))
      return true;
    else
      return false;
  }

  /*
   * public void requete(String url) { RequestBuilder builder = new
   * RequestBuilder(RequestBuilder.GET, url);
   * 
   * try { Request request = builder.sendRequest(null, new RequestCallback() {
   * 
   * @Override public void onResponseReceived(Request request, Response
   * response) { // TODO Auto-generated method stub
   * 
   * }
   * 
   * @Override public void onError(Request request, Throwable exception) {
   * alertWebError(); } }); } catch (RequestException e) { alertWebError(); } }
   */
}