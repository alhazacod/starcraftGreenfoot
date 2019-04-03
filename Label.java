import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Label here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Label extends Actor
{
    
    String text;
    int size;
    Color color;
    
    public void act(){
        setImage(new GreenfootImage(this.text, this.size, this.color, null));
    }
    
    public Label(String text, int size, Color color){
        this.text = text;
        this.size = size;
        this.color = color;
    }  
    public void setText(String text){
        this.text = text;
    }
}
