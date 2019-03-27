import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;

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
    
    public ProtossWarrior[] protoss;
    public TerranWarrior[] terran;

    int i;
    Notification notificator;
    
    int squareSize;
    String antSprite;
    String wrongSprite;
    
    public int troopSize;
    
    boolean protossReady;
    boolean terranReady;
    
    boolean troopsInitialized;
    
    public MyWorld(int troopSize)
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 420, 1);
        i=0;
        
        this.troopSize = troopSize;
        
        //Notification
        notificator = new Notification("Acomoda tus tropas", 30, Color.BLACK);
        addObject(notificator, 200, getHeight()-30);
        
        //Create the protoss's troops
        protoss = new ProtossWarrior[500];
        protoss[i] = new ProtossWarrior();//First soldier
        addObject(protoss[i], 0, 0);//Add the soldier to scene
        
        //The window will be sliced in squares where the soldiers will be 
        squareSize = getWidth()/15;
        
        troopsInitialized = false;
        protossReady = false;
        terranReady = false;
    }
    
    public void act(){
        if(!troopsInitialized){
            initializeTroops();
        }
    }
    
    private void initializeTroops(){
        if(!protossReady){ 
            protossFormation();
            if(i >= troopSize){
                i = 0;
                protossReady=true;
                terran = new TerranWarrior[500];
                terran[i] = new TerranWarrior();//First soldier
                addObject(terran[i], 0, 0);//Add the soldier to scene
            }
        }
        if(!terranReady && protossReady){
            terranFormation();
            if(i>=troopSize){
                i = 0;
                terranReady=true;
                notificator.setText("ready");
                troopsInitialized = true;
            }
        }
        notificator.setText(String.valueOf(i));
    }
    
    private void protossFormation(){
        MouseInfo mouse =  Greenfoot.getMouseInfo();//Get the mouse info
        if(mouse!=null){ //move mouse action
            //You can see a previsulization of the soldier that you will
            //put in scene
            protoss[i].setLocation( ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                 ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
            if(mouse.getX() < getWidth()/2){
                protoss[i].defaultSprite();
                if(Greenfoot.mouseClicked(null)&&mouse.getButton()==1) //click action
                {
                    //Create the next soldier
                    i++;
                    protoss[i] = new ProtossWarrior();
                    addObject(protoss[i], ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                       ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
                }
            } else{
                protoss[i].wrong();
            }
        }
    }
    
    private void terranFormation(){
        MouseInfo mouse =  Greenfoot.getMouseInfo();//Get the mouse info
        if(mouse!=null){ //move mouse action
            //You can see a previsulization of the soldier that you will
            //put in scene
            terran[i].setLocation( ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                   ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
            if(mouse.getX() > getWidth()/2){
                terran[i].defaultSprite();
                if(Greenfoot.mouseClicked(null)&&mouse.getButton()==1) //click action
                {
                    //Create the next soldier
                    i++;
                    terran[i] = new TerranWarrior();
                    addObject(terran[i], ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                         ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
                }
            } else{
                terran[i].wrong();
            }
        }
    }
}
