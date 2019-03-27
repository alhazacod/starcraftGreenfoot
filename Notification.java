import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Notification here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Notification extends Actor
{
    String text;
    int size;
    Color color;
    
    public Notification(String text, int size, Color color){
        this.text = text;
        this.size = size;
        this.color = color;
        setImage(new GreenfootImage(this.text, this.size, this.color, null));
    }  
    
    public void setText(String text){
        this.text = text;
        setImage(new GreenfootImage(this.text, this.size, this.color, null));
    }
}
