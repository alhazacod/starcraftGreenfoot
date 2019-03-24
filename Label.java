import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Label here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Label extends Actor
{
    public Label(String Text){
        setImage(new GreenfootImage(Text, 10, Color.WHITE, null));
    }  
}
