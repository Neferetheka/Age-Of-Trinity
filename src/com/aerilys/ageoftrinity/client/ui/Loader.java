package com.aerilys.ageoftrinity.client.ui;

import com.aerilys.ageoftrinity.client.Main;
import com.aerilys.ageoftrinity.client.models.Arme;
import com.aerilys.ageoftrinity.client.models.Armure;
import com.aerilys.ageoftrinity.client.models.Classe;
import com.aerilys.ageoftrinity.client.models.Destinee;
import com.aerilys.ageoftrinity.client.models.ObjectMagique;
import com.aerilys.ageoftrinity.client.models.Race;
import com.aerilys.ageoftrinity.client.tools.Converter;
import com.aerilys.ageoftrinity.client.tools.DataContainer;
import com.aerilys.ageoftrinity.client.tools.XMLHelper;
import com.aerilys.ageoftrinity.client.tools.game.AttaquePerso;
import com.aerilys.ageoftrinity.client.tools.game.Combattant;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.xml.client.*;

public class Loader extends HTMLPanel
{

  private InlineHTML loaderLabel;

  public Loader()
  {
    super("");
    setStyleName("loaderPanel");
    this.getElement().setId("loaderPanel");

    if (Main.getDeviceType() != "mobile")
    {
      Image image = new Image("gfx/GUI/circleAOT.png");
      image.setAltText("Chargement...");
      add(image);
    }

    Image image_1 = null;
    
    if(Main.getDeviceType() != "mobile")
      image_1 = new Image("gfx/GUI/sphere_humain.png");
    else
      image_1 = new Image("gfx/GUI/sphere_humain_mobile.png"); 
    
    image_1.setStyleName("loaderBlueSphere");
    image_1.setAltText("Chargement...");
    add(image_1);

    loaderLabel = new InlineHTML("Debut du chargement...");
    loaderLabel.setStyleName("loaderLabel");
    add(loaderLabel);

    if (!Main.isLocalhost())
      setStep(0);
    else
      finChargement();
  }

  protected void finChargement()
  {
    Main._instance.finchargement();
  }

  public void setStep(int step)
  {
    switch (step)
    {
      case 0:
        loaderLabel.setText("Debut du chargement...");
        setStep(1);
        break;
      case 1:
        loaderLabel.setText("Lecture de la Genese..."); // Chargement des races
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, Main.HOSTGAE
            + "/API/XML/Races.xml");

        try
        {
          builder.sendRequest(null, new RequestCallback()
          {
            @Override
            public void onResponseReceived(Request request, Response response)
            {
              if (response.getStatusCode() == 200)
              {

                try
                {
                  Document messageDom = XMLParser.parse(response.getText());
                  int count = messageDom.getElementsByTagName("Race").getLength();
                  Node node;
                  // Main.alertBox(count+"");
                  DataContainer.getInstance().getListeRaces().clear();
                  for (int i = 0; i < count; i++)
                  {
                    node = messageDom.getElementsByTagName("Race").item(i);
                    DataContainer.getInstance().getListeRaces().add(
                        new Race(Integer.parseInt(XMLHelper.getChild(node, 1)), XMLHelper.getChild(
                            node, 3), XMLHelper.getChild(node, 5), XMLHelper.getChild(node, 7)));
                    /*
                     * Main.alertBox(node.getChildNodes().item(3).getFirstChild()
                     * .getNodeValue()+
                     * "/"+node.getChildNodes().item(3).getFirstChild
                     * ().getNodeValue());
                     */
                  }
                  // Main.alertBox(DataContainer.getInstance().getListeRaces().size()+"");
                  setStep(2);
                }
                catch (DOMException e)
                {
                  loaderError();;// Main.alertBox(e.getMessage());
                }

              }
              else
                loaderError();
            }

            @Override
            public void onError(Request request, Throwable exception)
            {
              loaderError();
            }
          });
        }
        catch (RequestException e)
        {
          loaderError();
        }
        break;
      case 2:
        loaderLabel.setText("Recrutement de la Damathair..."); // Chargement des
                                                               // classes
        RequestBuilder builder2 = new RequestBuilder(RequestBuilder.GET, Main.HOSTGAE
            + "/API/XML/Classes.xml");

        try
        {
          builder2.sendRequest(null, new RequestCallback()
          {
            @Override
            public void onResponseReceived(Request request, Response response)
            {
              if (response.getStatusCode() == 200)
              {
                try
                {
                  Document messageDom = XMLParser.parse(response.getText());
                  int count = messageDom.getElementsByTagName("Classe").getLength();
                  Node node;
                  for (int i = 0; i < count; i++)
                  {
                    node = messageDom.getElementsByTagName("Classe").item(i);
                    DataContainer.getInstance().getListeClasses().add(
                        new Classe(Integer.parseInt(XMLHelper.getChild(node, 1)),
                            XMLHelper.getChild(node, 3), XMLHelper.getChild(node, 5),
                            XMLHelper.getChild(node, 7)));
                  }

                  setStep(3);
                }
                catch (DOMException e)
                {
                  loaderError();;// Main.alertBox(e.getMessage());
                }
              }
              else
                loaderError();
            }

            @Override
            public void onError(Request request, Throwable exception)
            {
              loaderError();
            }
          });
        }
        catch (RequestException e)
        {
          loaderError();
        }
        break;
      case 3:
        loaderLabel.setText("Naissance de l'Arbre de Vie..."); // Chargement des
                                                               // competences
        RequestBuilder builder3 = new RequestBuilder(RequestBuilder.GET, Main.HOSTGAE
            + "/API/XML/Competences.xml");

        try
        {
          builder3.sendRequest(null, new RequestCallback()
          {
            @Override
            public void onResponseReceived(Request request, Response response)
            {
              if (response.getStatusCode() == 200)
              {
                // TODO : charger les competences
                setStep(4);
              }
              else
                loaderError();
            }

            @Override
            public void onError(Request request, Throwable exception)
            {
              loaderError();
            }
          });
        }
        catch (RequestException e)
        {
          loaderError();
        }
        break;
      case 4:
        loaderLabel.setText("Invocation de Roger le Tavernier..."); // Chargement
                                                                    // des
                                                                    // équipements

        RequestBuilder builder4 = new RequestBuilder(RequestBuilder.GET, Main.HOSTGAE
            + "/API/XML/Items.xml");

        try
        {
          builder4.sendRequest(null, new RequestCallback()
          {
            @Override
            public void onResponseReceived(Request request, Response response)
            {
              if (response.getStatusCode() == 200)
              {

                try
                {
                  Document messageDom = XMLParser.parse(response.getText());
                  int count = messageDom.getElementsByTagName("Arme").getLength();
                  Node node;
                  for (int i = 0; i < count; i++)
                  {
                    node = messageDom.getElementsByTagName("Arme").item(i);
                    DataContainer.getInstance().getListeArmes().add(
                        new Arme(Integer.parseInt(XMLHelper.getChild(node, 1)), XMLHelper.getChild(
                            node, 3), XMLHelper.getChild(node, 7), XMLHelper.getChild(node, 5),
                            Integer.parseInt(XMLHelper.getChild(node, 9)),
                            Converter.convertIntToBoolean(XMLHelper.getChild(node, 11)),
                            Converter.convertIntToAttaquePersoType(XMLHelper.getChild(node, 17)),
                            Converter.cti(XMLHelper.getChild(node, 19)),
                            Converter.cti(XMLHelper.getChild(node, 21)),
                            Converter.cti(XMLHelper.getChild(node, 23))));
                  }

                  count = messageDom.getElementsByTagName("Armure").getLength();
                  for (int i = 0; i < count; i++)
                  {
                    node = messageDom.getElementsByTagName("Armure").item(i);
                    DataContainer.getInstance().getListeArmures().add(
                        new Armure(Integer.parseInt(XMLHelper.getChild(node, 1)),
                            XMLHelper.getChild(node, 3), XMLHelper.getChild(node, 7),
                            XMLHelper.getChild(node, 5),
                            Converter.cti(XMLHelper.getChild(node, 9)),
                            Converter.convertIntToBoolean(XMLHelper.getChild(node, 11)),
                            Converter.cti(XMLHelper.getChild(node, 17))));
                  }

                  count = messageDom.getElementsByTagName("Objet").getLength();
                  for (int i = 0; i < count; i++)
                  {
                    node = messageDom.getElementsByTagName("Objet").item(i);
                    DataContainer.getInstance().getListeObjetsMagiques().add(
                        new ObjectMagique(Integer.parseInt(XMLHelper.getChild(node, 1)),
                            XMLHelper.getChild(node, 3), XMLHelper.getChild(node, 7),
                            XMLHelper.getChild(node, 5),
                            Converter.cti(XMLHelper.getChild(node, 9)),
                            Converter.convertIntToBoolean(XMLHelper.getChild(node, 11))));
                  }

                  setStep(5);
                }
                catch (DOMException e)
                {
                  loaderError();;// Main.alertBox(e.getMessage());
                }
              }
              else
                loaderError();
            }

            @Override
            public void onError(Request request, Throwable exception)
            {
              loaderError();
            }
          });
        }
        catch (RequestException e)
        {
          loaderError();
        }
        break;
      case 5:
        loaderLabel.setText("Observervation des Etoiles..."); // Chargement des
                                                              // destinées
        RequestBuilder builder5 = new RequestBuilder(RequestBuilder.GET, Main.HOSTGAE
            + "/API/XML/Destinees.xml");

        try
        {
          builder5.sendRequest(null, new RequestCallback()
          {
            @Override
            public void onResponseReceived(Request request, Response response)
            {
              if (response.getStatusCode() == 200)
              {
                try
                {
                  Document messageDom = XMLParser.parse(response.getText());
                  int count = messageDom.getElementsByTagName("Destinee").getLength();
                  Node node;
                  for (int i = 0; i < count; i++)
                  {
                    node = messageDom.getElementsByTagName("Destinee").item(i);
                    DataContainer.getInstance().getListeDestinee().add(
                        new Destinee(Integer.parseInt(XMLHelper.getChild(node, 1)),
                            XMLHelper.getChild(node, 3), XMLHelper.getChild(node, 5),
                            XMLHelper.getChild(node, 7), XMLHelper.getChild(node, 9),
                            Converter.cti(XMLHelper.getChild(node, 11))));
                  }

                  setStep(6);
                }
                catch (DOMException e)
                {
                  loaderError();;// Main.alertBox(e.getMessage());
                }
              }
              else
                loaderError();
            }

            @Override
            public void onError(Request request, Throwable exception)
            {
              loaderError();
            }
          });
        }
        catch (RequestException e)
        {
          loaderError();
        }
        break;
      case 6:
        loaderLabel.setText("Consultation du Grand Registre..."); // Chargement
                                                                  // des samples
                                                                  // de nom
        setStep(7);
        break;
      case 7:
        loaderLabel.setText("Recherche de l'Avatar..."); // Chargement des
                                                         // persos du joueur
        DataContainer.getInstance().chargementPersos(this);
        break;
        
      case 8:
        //Chargement des items des persos
        setStep(9);
        break;
      case 9:
        loaderLabel.setText("Resolution de l'Equation de Zoran...");
        finChargement();
        break;
    }
  }

  public static void loaderError()
  {
    Main.alertWebError();
    Main._instance.annulerChargement();
  }

}
