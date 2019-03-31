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
    
    public Protoss[] protoss;
    
    public WarriorP[] warriorP;
    public MedicP[] medicP;
    public BuilderP[] builderP;
    
    public WarriorT[] warriorT;
    public MedicT[] medicT;
    public BuilderT[] builderT;
    
    public Terran[] terran;

    int i;
    Notification notificator;
    
    int squareSize;
    String antSprite;
    String wrongSprite;
    
    public int troopSize;
    
    boolean protossReady;
    boolean terranReady;
    
    boolean troopsInitialized;
    
    int rol;
    
    public MyWorld(int troopSize)
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 420, 1);
        i=0;
        
        this.troopSize = troopSize;
        
        //Notification
        notificator = new Notification("Acomoda tus tropas", 30, Color.BLACK);
        addObject(notificator, 200, getHeight()-30);
        
        rol = 1;
        
        //Create the protoss's troops
        protoss = new Protoss[500];
        warriorP = new WarriorP[500];
        medicP = new MedicP[500];
        builderP = new BuilderP[500];
        
        for(int i=0;i<499;i++){
            warriorP[i] = new WarriorP();
            medicP[i] = new MedicP();
            builderP[i] = new BuilderP();
        }
        addObject(warriorP[i], 0, 0);//Add the soldier to scene
        
        //The window will be sliced in squares where the soldiers will be 
        squareSize = getWidth()/15;
        
        troopsInitialized = false;
        protossReady = false;
        terranReady = false;
    }
    
    public void act(){
        if(!troopsInitialized){
            initializeTroops();
            notificator.setText("rol "+ 
                              ((rol==1) ? "guerrero" : 
                               (rol==2) ? "medico" : "constructor"));
        }
    }
    
    private void initializeTroops(){
        if(!protossReady){ 
            protossFormation();
            if(i >= troopSize){
                i = 0;
                protossReady=true;
                terran = new Terran[500];
                warriorT = new WarriorT[500];
                medicT = new MedicT[500];
                builderT = new BuilderT[500];
                for(int i=0;i<499;i++){
                    warriorT[i] = new WarriorT();
                    medicT[i] = new MedicT();
                    builderT[i] = new BuilderT();
                }
                rol=1;
                addObject(warriorT[i], 0, 0);//Add the soldier to scene
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
        //notificator.setText(String.valueOf(i));
    }
    
    private void protossFormation(){
        MouseInfo mouse =  Greenfoot.getMouseInfo();//Get the mouse info
        if(mouse!=null){ //move mouse action
            //You can see a previsulization of the soldier that you will
            //put in scene
            if(rol==1)
                warriorP[i].setLocation( ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                         ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
            if(rol==2)
                medicP[i].setLocation( ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                       ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
            if(rol==3)
                builderP[i].setLocation( ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                         ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
            if(mouse.getX() < getWidth()/2){
                if(Greenfoot.mouseClicked(null)&&mouse.getButton()==1) //click action
                {
                    //Create the next soldier
                    i++;
                    if(rol==1){
                    warriorP[i] = new WarriorP();
                    addObject(warriorP[i], ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                          ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
                    }
                    if(rol==2){
                    medicP[i] = new MedicP();
                    addObject(medicP[i], ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                          ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
                    }
                    if(rol==3){
                    builderP[i] = new BuilderP();
                    addObject(builderP[i], ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                          ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
                    }
                }
                if(Greenfoot.mouseClicked(null)&&mouse.getButton()==3){
                    if(rol>=3){
                        rol = 1;
                    }
                    else {
                        rol++;
                    }
                    removeObject(warriorP[i]);
                    removeObject(medicP[i]);
                    removeObject(builderP[i]);
                    if(rol==1) addObject(warriorP[i],0,0);
                    if(rol==2) addObject(medicP[i],0,0);
                    if(rol==3) addObject(builderP[i],0,0);
                }
            } else{
                if(rol==1) warriorP[i].wrong();
                if(rol==2) medicP[i].wrong();
                if(rol==3) builderP[i].wrong();
            }
        }
    }
    
    private void terranFormation(){
        MouseInfo mouse =  Greenfoot.getMouseInfo();//Get the mouse info
        if(mouse!=null){ //move mouse action
            //You can see a previsulization of the soldier that you will
            //put in scene
            if(rol==1)
                warriorT[i].setLocation( ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                         ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
            if(rol==2)
                medicT[i].setLocation( ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                       ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
            if(rol==3)
                builderT[i].setLocation( ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                         ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
            if(mouse.getX() > getWidth()/2){
                if(Greenfoot.mouseClicked(null)&&mouse.getButton()==1) //click action
                {
                    //Create the next soldier
                    i++;
                    if(rol==1){
                    warriorT[i] = new WarriorT();
                    addObject(warriorT[i], ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                          ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
                    }
                    if(rol==2){
                    medicT[i] = new MedicT();
                    addObject(medicT[i], ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                          ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
                    }
                    if(rol==3){
                    builderT[i] = new BuilderT();
                    addObject(builderT[i], ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                          ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
                    }
                }
                if(Greenfoot.mouseClicked(null)&&mouse.getButton()==3){
                    if(rol>=3){
                        rol = 1;
                    }
                    else {
                        rol++;
                    }
                    removeObject(warriorT[i]);
                    removeObject(medicT[i]);
                    removeObject(builderT[i]);
                    if(rol==1) addObject(warriorT[i],0,0);
                    if(rol==2) addObject(medicT[i],0,0);
                    if(rol==3) addObject(builderT[i],0,0);
                }
            } else{
                if(rol==1) warriorT[i].wrong();
                if(rol==2) medicT[i].wrong();
                if(rol==3) builderT[i].wrong();
            }
        }
    }
}
