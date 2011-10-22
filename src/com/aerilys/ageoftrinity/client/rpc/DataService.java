package com.aerilys.ageoftrinity.client.rpc;

import java.util.List;

import com.aerilys.ageoftrinity.client.models.Perso;
import com.aerilys.ageoftrinity.shared.PersoEntity;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("DataService")
public interface DataService extends RemoteService
{
  List<PersoEntity> getPersos();
  boolean createPerso(PersoEntity persoEntity);
  boolean deletePerso();
}
