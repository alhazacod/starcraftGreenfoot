import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Label here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Label extends Actor
{
    public Label(String Text, int size){
        setImage(new GreenfootImage(Text, size, Color.BLACK, null));
    }  
}
