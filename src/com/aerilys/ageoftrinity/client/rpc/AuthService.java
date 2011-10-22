package com.aerilys.ageoftrinity.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("AuthService")
public interface AuthService extends RemoteService
{
  boolean authenticate(String pseudo, String mdp);
}
