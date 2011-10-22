package com.aerilys.ageoftrinity.Server;

import javax.servlet.http.HttpSession;

import com.aerilys.ageoftrinity.Server.entities.User;
import com.aerilys.ageoftrinity.client.rpc.AuthService;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AuthServiceImpl extends RemoteServiceServlet implements AuthService
{
  @Override
  public boolean authenticate(String pseudo, String mdp)
  {
    pseudo = pseudo.trim();
    mdp = mdp.trim();
    int count = 0;
    
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    
    /*User user = new User();
    user.setPseudo("Galaad");
    user.setMdp("test");
    user.setMail("neferetheka@gmail.com");
    
    datastore.put(user.SaveAsEntity());*/
    
    //Key userKey = KeyFactory.createKey(User.class.getName(), "User");
    Query query = new Query("User").addFilter("pseudo", FilterOperator.EQUAL, pseudo).
        addFilter("mdp", FilterOperator.EQUAL, mdp);
    
    count = datastore.prepare(query).countEntities(FetchOptions.Builder.withDefaults());
    
    if(count == 1)
    {
      HttpSession session = this.getThreadLocalRequest().getSession();
      session.setAttribute("auth", 1);
      session.setAttribute("pseudo", pseudo);
      return true;
    }
    else
      return false;
  }
  
  

}
