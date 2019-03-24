import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    
    public ant[] ants;
<<<<<<< HEAD
    int i;
    Notification notificatorAnts;
=======
    public int antTroppsSize;
    
    int i;
    
    Notification notificatorAnts;
    
>>>>>>> You can create the troop's formation.
    int squareSize;
    String antSprite;
    String wrongSprite;
    
<<<<<<< HEAD
=======
    
>>>>>>> You can create the troop's formation.
    public MyWorld()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 420, 1);
        i=0;
        
        antSprite = "ant3.png";
        wrongSprite = "skull.png";
        
        //Notification
        notificatorAnts = new Notification("Acomoda tus tropas", 30, Color.BLACK);
        addObject(notificatorAnts, 200, getHeight()-30);
        
        //Create the troops
        ants = new ant[500];
        ants[i] = new ant();//First soldier
        addObject(ants[i], 0, 0);//Add the soldier to scene
        
        //The window will be sliced in squares where the soldiers will be 
        squareSize = getWidth()/15;
    }
    
    public void act(){
        troops();
    }
    
    private void troops(){
        MouseInfo mouse =  Greenfoot.getMouseInfo();//Get the mouse info
        if(mouse!=null){ //move mouse action
            //You can see a previsulization of the soldier that you will
            //put in scene
            ants[i].setLocation( ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                 ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
            if(mouse.getX() < getWidth()/2){
                ants[i].setImage(antSprite);
                if(Greenfoot.mouseClicked(null)) //click action
                {
                    //Create the next soldier
                    i++;
                    ants[i] = new ant();
                    addObject(ants[i], ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                       ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
                }
            } else{
                ants[i].setImage(wrongSprite);
            }
        }
    }
}
