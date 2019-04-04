import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;
import javax.swing.JFrame;

/**
 * Write a description of class Start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Start extends World
{
    
    public Label startButton;
    
    /**
     * Constructor for objects of class Start.
     * 
     */
    public Start()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 420, 1);
        
        startButton = new Label("Iniciar Juego", 40, Color.BLACK);
        addObject(startButton, 450, 210);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(startButton)){ //click action
            String troopSize = JOptionPane.showInputDialog("Ingrese el tamaño de las tropas");
            String iterations = JOptionPane.showInputDialog("Ingrese la cantidad de iteraciones");
            Greenfoot.setWorld(new MyWorld(Integer.valueOf(troopSize), Integer.valueOf(iterations)));
        }
    }
}
