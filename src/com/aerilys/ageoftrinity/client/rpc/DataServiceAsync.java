package com.aerilys.ageoftrinity.client.rpc;

import java.util.List;

import com.aerilys.ageoftrinity.client.models.Perso;
import com.aerilys.ageoftrinity.shared.PersoEntity;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DataServiceAsync
{

  void createPerso(PersoEntity persoEntity, AsyncCallback<Boolean> callback);

  void deletePerso(AsyncCallback<Boolean> callback);

  void getPersos(AsyncCallback<List<PersoEntity>> callback);

}
