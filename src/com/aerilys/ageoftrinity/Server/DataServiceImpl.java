package com.aerilys.ageoftrinity.Server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.aerilys.ageoftrinity.Server.entities.PersoEntityHelper;
import com.aerilys.ageoftrinity.client.models.Perso;
import com.aerilys.ageoftrinity.client.rpc.DataService;
import com.aerilys.ageoftrinity.shared.PersoEntity;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DataServiceImpl extends RemoteServiceServlet implements DataService
{

  @Override
  public List<PersoEntity> getPersos()
  {
    HttpSession session = this.getThreadLocalRequest().getSession();

    if (session.getAttribute("auth") == null)
      return null;
    else
    {
      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      
     /* Perso tmp2 = new Perso("Galaad", "Biographie", 1);
      datastore.put(PersoEntityHelper.SaveAsEntity(tmp2,2, 5, 1));*/
      
      List<PersoEntity> listePersos = new ArrayList<PersoEntity>();

      Query query = new Query("Perso").addFilter("user", FilterOperator.EQUAL,
          session.getAttribute("pseudo"));
      List<Entity> listeEntities = datastore.prepare(query).asList(
          FetchOptions.Builder.withDefaults());

      for (Entity entity : listeEntities)
      {
        listePersos.add(PersoEntityHelper.GetProperties(entity));
      }

      return listePersos;
    }
  }

  @Override
  public boolean deletePerso()
  {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean createPerso(PersoEntity persoEntity)
  {
    HttpSession session = this.getThreadLocalRequest().getSession();

    if (session.getAttribute("auth") == null)
      return false;
    else
    {
      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      
      persoEntity.user = session.getAttribute("pseudo").toString();
      persoEntity.setNiveau(1);
      
      Entity entity = PersoEntityHelper.SaveAsEntity(persoEntity);
      datastore.put(entity);
      
      return true;
    }
  }

}
