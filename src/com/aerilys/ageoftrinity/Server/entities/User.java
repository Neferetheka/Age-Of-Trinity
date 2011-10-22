package com.aerilys.ageoftrinity.Server.entities;

import java.util.Date;

import com.google.appengine.api.datastore.Entity;

public class User
{
  public static String NameEntity;
  private String pseudo;
  private String mdp;
  private String mail;
  private String IP;
  private Date dateInscription;
  
  
  
  public User()
  {
    super();
    NameEntity = "User";
  }

  public User(String pseudo, String mdp, String mail, String iP, Date dateInscription)
  {
    this();
    this.pseudo = pseudo;
    this.mdp = mdp;
    this.mail = mail;
    IP = iP;
    this.dateInscription = dateInscription;
  }

  public User(Entity entity)
  {
    this();
    GetProperties(entity);
  }

  public void GetProperties(Entity entity)
  {
    this.pseudo = (String) entity.getProperty("pseudo");
    this.mdp = (String) entity.getProperty("mdp");
    this.mail = (String) entity.getProperty("mail");
  }
  
  public Entity SaveAsEntity()
  {
    Entity entity = new Entity(NameEntity);
    entity.setProperty("pseudo", this.pseudo);
    entity.setProperty("mdp", this.mdp);
    entity.setProperty("mail", this.mail);
    
    return entity;
  }
  
  /**
   * @return the pseudo
   */
  public String getPseudo()
  {
    return pseudo;
  }
  /**
   * @param pseudo the pseudo to set
   */
  public void setPseudo(String pseudo)
  {
    this.pseudo = pseudo;
  }
  /**
   * @return the mdp
   */
  public String getMdp()
  {
    return mdp;
  }
  /**
   * @param mdp the mdp to set
   */
  public void setMdp(String mdp)
  {
    this.mdp = mdp;
  }
  /**
   * @return the mail
   */
  public String getMail()
  {
    return mail;
  }
  /**
   * @param mail the mail to set
   */
  public void setMail(String mail)
  {
    this.mail = mail;
  }
  /**
   * @return the iP
   */
  public String getIP()
  {
    return IP;
  }
  /**
   * @param iP the iP to set
   */
  public void setIP(String iP)
  {
    IP = iP;
  }
  /**
   * @return the dateInscription
   */
  public Date getDateInscription()
  {
    return dateInscription;
  }
  /**
   * @param dateInscription the dateInscription to set
   */
  public void setDateInscription(Date dateInscription)
  {
    this.dateInscription = dateInscription;
  }
}
