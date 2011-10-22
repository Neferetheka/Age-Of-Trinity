package com.aerilys.ageoftrinity.client.tools.game;

public class Animation {
  
  String srcBase;
  int timeSinceLastUpdate = 0;
  int indexAnimation = 1;
  int indexMaxAnimation = 0;
  boolean animationDirectionUp = true;
  int updateFrameRate = 200;
  boolean oneTime = false;
  boolean inPlay = true;
  
  
  
  
  public Animation(String srcBase, int indexMaxAnimation, int updateFrameRate, boolean oneTime) {
    super();
    this.srcBase = srcBase;
    this.indexMaxAnimation = indexMaxAnimation;
    this.updateFrameRate = updateFrameRate;
    this.oneTime = oneTime;
  }

  public void play()
  {
    this.inPlay = true;
    this.indexAnimation = 1;
    animationDirectionUp = true;
  }

  public void update(Combattant combattant)
  {
    timeSinceLastUpdate += GameTime.FRAMERATE;
    
    if(inPlay && timeSinceLastUpdate % updateFrameRate == 0 && indexMaxAnimation > 0)
    {
      if(animationDirectionUp)
        indexAnimation++;
      else
        indexAnimation --;
      if(indexAnimation > indexMaxAnimation)
      {
        animationDirectionUp = false;
        indexAnimation = indexMaxAnimation;
      }
      else if(indexAnimation < 1)
      {
        if(!oneTime)
        {
          indexAnimation = 1;
          animationDirectionUp = true;
        }
        else
          inPlay = false;
      }
      if(indexAnimation > 0)
        combattant.changerImage(combattant.srcBase+"-"+srcBase+"-"+indexAnimation+".png");
    }
  }

  /**
   * @return the srcBase
   */
  public String getSrcBase() {
    return srcBase;
  }

  /**
   * @param srcBase the srcBase to set
   */
  public void setSrcBase(String srcBase) {
    this.srcBase = srcBase;
  }

  /**
   * @return the indexMaxAnimation
   */
  public int getIndexMaxAnimation() {
    return indexMaxAnimation;
  }

  /**
   * @param indexMaxAnimation the indexMaxAnimation to set
   */
  public void setIndexMaxAnimation(int indexMaxAnimation) {
    this.indexMaxAnimation = indexMaxAnimation;
  }

  /**
   * @return the updateFrameRate
   */
  public int getUpdateFrameRate() {
    return updateFrameRate;
  }

  /**
   * @param updateFrameRate the updateFrameRate to set
   */
  public void setUpdateFrameRate(int updateFrameRate) {
    this.updateFrameRate = updateFrameRate;
  }
}
