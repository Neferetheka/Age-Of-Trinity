package com.aerilys.ageoftrinity.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AuthServiceAsync
{

  void authenticate(String pseudo, String mdp, AsyncCallback<Boolean> callback);

}
