import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TerranWarrior here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TerranWarrior extends Terran
{
    /**
     * Act - do whatever the TerranWarrior wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public String sprite;
    public String wrongSprite;
    public String defaultSprite;
    
    public TerranWarrior(){
        defaultSprite = "terran_warrior.png";
        wrongSprite = "skull.png";
        sprite = defaultSprite;
        setImage(sprite);
    }
    
    public void act() 
    {
        setImage(sprite);
    }    
    
    public void wrong(){
        this.sprite = this.wrongSprite;
    }
    
    public void defaultSprite(){
        this.sprite = this.defaultSprite;
        setImage(sprite);
    }   
}
