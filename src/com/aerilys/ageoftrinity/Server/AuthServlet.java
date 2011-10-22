package com.aerilys.ageoftrinity.Server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthServlet extends HttpServlet
{
  
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
  {
    try
    {
      if(req.getParameter("p") != null && req.getParameter("md") != null)
      {
        AuthServiceImpl authSvc = new AuthServiceImpl();
        boolean result = authSvc.authenticate(req.getParameter("p"), req.getParameter("md"));
        
        if(result)
          resp.getWriter().append("*");
        else
          resp.getWriter().append("@");
      }
      else
        resp.getWriter().append("!");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
