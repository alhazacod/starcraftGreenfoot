import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ProtossWarrior here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ProtossWarrior extends Protoss
{
    /**
     * Act - do whatever the ProtossWarrior wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public String sprite;
    public String wrongSprite;
    public String defaultSprite;
    
    public ProtossWarrior(){
        defaultSprite = "protoss_warrior.png";
        wrongSprite = "skull.png";
        sprite = "protoss_warrior.png";
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
    }
}
