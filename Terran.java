import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Terran here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Terran extends Actor
{
    public String sprite;
    public String wrongSprite;
    
    public int rol;
    
    public int energy;
    
    //There are 3 rols 
    //1: warrior
    //2: medic
    //3: builder
    
    public Terran(int rol, String sprite){
        
        setRol(rol);
        
        this.sprite = sprite;
        setImage(this.sprite);
        
        energy = 100;
        
        wrongSprite = "skull.png";
    }
    
    public void act() 
    {
        setImage(this.sprite);
    }    
    
    public void wrong(){
        this.sprite = this.wrongSprite;
    }
    
    public void setRol(int rol){
        this.rol = rol;
        
        //setImage(sprite);
    }
}
