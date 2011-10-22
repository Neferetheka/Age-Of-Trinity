package com.aerilys.ageoftrinity.client.tools;


import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class Sprite {

  Image img;
  ImageElement imgElement;
  Vector position = new Vector(0, 0);
  double rotation = 0;
  Vector scale = new Vector(1,1);
  
  public Sprite(String src, RootPanel rootPanel) {
    super();
    img = new Image(src);
    img.addLoadHandler(new LoadHandler() {
      public void onLoad(LoadEvent event) {
        imgElement = (ImageElement) img.getElement().cast(); 
      }
    });
    
    rootPanel.add(img);
    img.setVisible(false);
  }
  
  public void changerImage(String src)
  {
    img.setUrl(src); 
  }
  

  /*
   * Permet de mettre à jour la logique du sprite
   */
  public void update()
  {
    ;
  }
  
  /*
   * Permet d'afficher le sprite
   */
  public void draw(Context2d context)
  {
    if(imgElement != null)
    {
      try
      {
      context.save();
      }
      finally
      {
        double X = this.position.x;
        if(scale.x < 0)
          X += 72;
        context.translate(X, this.position.y);
        context.rotate(rotation);
        context.scale(scale.x, scale.y);
        try {
          context.drawImage(imgElement, 0, 0);
        }
        finally {
          context.restore();
        }
      }
    }
  }
  
  
  /**
   * @return the img
   */
  public Image getImg() {
    return img;
  }

  /**
   * @param img the img to set
   */
  public void setImg(Image img) {
    this.img = img;
  }

  /**
   * @return the imgElement
   */
  public ImageElement getImgElement() {
    return imgElement;
  }

  /**
   * @param imgElement the imgElement to set
   */
  public void setImgElement(ImageElement imgElement) {
    this.imgElement = imgElement;
  }

  /**
   * @return the position
   */
  public Vector getPosition() {
    return position;
  }

  /**
   * @param position the position to set
   */
  public void setPosition(Vector position) {
    this.position = position;
  }

  /**
   * @return the rotation
   */
  public double getRotation() {
    return rotation;
  }

  /**
   * @param rotation the rotation to set
   */
  public void setRotation(double rotation) {
    this.rotation = rotation;
  }

  /**
   * @return the scale
   */
  public Vector getScale() {
    return scale;
  }

  /**
   * @param scale the scale to set
   */
  public void setScale(Vector scale) {
    this.scale = scale;
  }

}
