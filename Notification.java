import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Notification here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Notification extends Actor
{
    public Notification(String Text, int size, Color color){
        setImage(new GreenfootImage(Text, size, color, null));
    }  
}
