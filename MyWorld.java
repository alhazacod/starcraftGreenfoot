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
    int iterations;
    Notification notificator;
    
    int turno;
    
    int squareSize;
    String antSprite;
    String wrongSprite;
    
    public int troopSize;
    
    boolean protossReady;
    boolean terranReady;
    
    boolean troopsInitialized;
    
    int rol;
    
    //gamepad buttons
    Button up;
    Button down;
    Button right;
    Button left;
    
    int protossE;
    int terranE;
    Label protossEnergy;
    Label terranEnergy;
    
    public MyWorld(int troopSize, int iterations)
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 420, 1);
        i=0;
        
        this.troopSize = troopSize;
        
        //Notification
        notificator = new Notification("Acomoda tus tropas", 30, Color.BLACK);
        addObject(notificator, getWidth()/2, getHeight()-30);
        
        rol = 1;
        
        this.iterations = iterations;
        
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
        
        turno = 1;
        
        protossE = 0;
        terranE = 0;
        protossEnergy = new Label("Energia: ", 20, Color.BLACK);
        terranEnergy = new Label("Energia: ", 20, Color.BLACK);
    }
    
    public void act(){
       if(!troopsInitialized){
           initializeTroops();
           notificator.setText("rol "+ 
                             ((rol==1) ? "guerrero" : 
                              (rol==2) ? "medico" :
                                         "constructor"));
       }else{
           if(i<iterations){
               if(turno == 1){//Protoss team iteration
                   moveProtoss();
               }else{//Terran team iteration
                   moveTerran();
               }
               //calculate the energy
               getTotalEnergy();
               protossEnergy.setText("Energy: "+protossE);//print the total energy
               terranEnergy.setText("Energy: "+terranE);//print te total energy
           }else{
               if(protossE>terranE) Greenfoot.setWorld(new Win("LOS PROTOSS HAN VENCIDO!"));
               else Greenfoot.setWorld(new Win("LOS TERRAN HAN VENCIDO!"));
           }
            
       }
    }
    
    private void getTotalEnergy(){
        protossE = 0;
        for(int j = 0; j<499; j++){//move all the troops
            if(warriorP[j].getWorld()!=null)//check if the element exist on the world
                protossE += warriorP[j].energy;//add the energy to the total
            if(medicP[j].getWorld()!=null)//check if the element exist on the world
                protossE += medicP[j].energy;//add the energy to the total
            if(builderP[j].getWorld()!=null)//check if the element exist on the world
                protossE += builderP[j].energy;//add the energy to the total
        }
        terranE = 0;
        for(int j = 0; j<499; j++){//move all the troops
            if(warriorT[j].getWorld()!=null)//check if the element exist on the world
                terranE += warriorT[j].energy;//add the energy to the total
            if(medicT[j].getWorld()!=null)//check if the element exist on the world
                terranE += medicT[j].energy;//add the energy to the total
            if(builderT[j].getWorld()!=null)//check if the element exist on the world
                terranE += builderT[j].energy;//add the energy to the total
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
                
                for(int j = 0; j<499; j++){//move all the troops
                    if(warriorT[j].getWorld()!=null)//check if the element exist on the world
                        terranE += warriorT[j].energy;//add the energy to the total
                    if(medicT[j].getWorld()!=null)//check if the element exist on the world
                        terranE += medicT[j].energy;//add the energy to the total
                    if(builderT[j].getWorld()!=null)//check if the element exist on the world
                        terranE += builderT[j].energy;//add the energy to the total
                }
                
                //let's create the gamepad
                up = new Button("up.png");
                addObject(up, 90, 40);
                down = new Button("down.png");
                addObject(down, 90, 140);
                right = new Button("right.png");
                addObject(right, 140, 90);
                left = new Button("left.png");
                addObject(left, 40, 90);
                
                //add the energy indicator
                addObject(protossEnergy, 100,getHeight()-20);
                addObject(terranEnergy, getWidth()-100,getHeight()-20);
                
                notificator.setText("");
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
                    if(rol==1 && i!=troopSize){
                    warriorP[i] = new WarriorP();
                    addObject(warriorP[i], ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                          ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
                    }
                    if(rol==2 && i!=troopSize){
                    medicP[i] = new MedicP();
                    addObject(medicP[i], ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                          ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
                    }
                    if(rol==3 && i!=troopSize){
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
                    if(rol==1 && i!=troopSize){
                    warriorT[i] = new WarriorT();
                    addObject(warriorT[i], ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                          ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
                    }
                    if(rol==2 && i!=troopSize){
                    medicT[i] = new MedicT();
                    addObject(medicT[i], ((mouse.getX()/squareSize)*squareSize)+(squareSize/2),
                                          ((mouse.getY()/squareSize)*squareSize)+(squareSize/2));
                    }
                    if(rol==3 && i!=troopSize){
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
    private void moveProtoss(){
        //movement w/ gamepad
        if(Greenfoot.mouseClicked(up)){
            turno=2;
            i++;
            for(int j = 0; j<499; j++){//check all the troops collisions
                if(warriorP[j].getWorld()!=null){//check if the element exist on the world
                    warriorP[j].checkCollision();
                    warriorP[j].checkMedic();//check if a medic is near
                }
                if(medicP[j].getWorld()!=null)//check if the element exist on the world
                    medicP[j].checkCollision();
                if(builderP[j].getWorld()!=null){//check if the element exist on the world
                    builderP[j].checkCollision();
                    builderP[j].checkMedic();//check if a medic is near
                }
            }
            for(int j = 0; j<499; j++){//move all the troops
                if(warriorP[j].getWorld()!=null && warriorP[j].move == true)//check if the element exist on the world
                    warriorP[j].setLocation(warriorP[j].getX(),warriorP[j].getY()-squareSize);
                if(medicP[j].getWorld()!=null && medicP[j].move == true)//check if the element exist on the world
                    medicP[j].setLocation(medicP[j].getX(),medicP[j].getY()-squareSize);
                if(builderP[j].getWorld()!=null && builderP[j].move == true)//check if the element exist on the world
                    builderP[j].setLocation(builderP[j].getX(),builderP[j].getY()-squareSize);
            }
        }
            
        if(Greenfoot.mouseClicked(down)){
            turno=2;
            i++;
            for(int j = 0; j<499; j++){//check all the troops collisions
                if(warriorP[j].getWorld()!=null){//check if the element exist on the world
                    warriorP[j].checkCollision();
                    warriorP[j].checkMedic();//check if a medic is near
                }
                if(medicP[j].getWorld()!=null)//check if the element exist on the world
                    medicP[j].checkCollision();
                if(builderP[j].getWorld()!=null){//check if the element exist on the world
                    builderP[j].checkCollision();
                    builderP[j].checkMedic();//check if a medic is near
                }
           }
            for(int j = 0; j<499; j++){
                if(warriorP[j].getWorld()!=null && warriorP[j].move == true)
                    warriorP[j].setLocation(warriorP[j].getX(),warriorP[j].getY()+squareSize);
                if(medicP[j].getWorld()!=null && medicP[j].move == true)
                    medicP[j].setLocation(medicP[j].getX(),medicP[j].getY()+squareSize);
                if(builderP[j].getWorld()!=null && builderP[j].move == true)
                    builderP[j].setLocation(builderP[j].getX(),builderP[j].getY()+squareSize);
            }
        }
            
        if(Greenfoot.mouseClicked(right)){
            turno=2;
            i++;
            for(int j = 0; j<499; j++){//check all the troops collisions
                    if(warriorP[j].getWorld()!=null){//check if the element exist on the world
                        warriorP[j].checkCollision();
                        warriorP[j].checkMedic();//check if a medic is near
                    }
                    if(medicP[j].getWorld()!=null)//check if the element exist on the world
                        medicP[j].checkCollision();
                    if(builderP[j].getWorld()!=null){//check if the element exist on the world
                        builderP[j].checkCollision();
                        builderP[j].checkMedic();//check if a medic is near
                    }
               }
            for(int j = 0; j<499; j++){
                if(warriorP[j].getWorld()!=null && warriorP[j].move == true)
                    warriorP[j].setLocation(warriorP[j].getX()+squareSize,warriorP[j].getY());
                if(medicP[j].getWorld()!=null && medicP[j].move == true)
                    medicP[j].setLocation(medicP[j].getX()+squareSize,medicP[j].getY());
                if(builderP[j].getWorld()!=null && builderP[j].move == true)
                    builderP[j].setLocation(builderP[j].getX()+squareSize,builderP[j].getY());
            }
        }
            
        if(Greenfoot.mouseClicked(left)){
            turno=2;
            i++;
            for(int j = 0; j<499; j++){//check all the troops collisions
                    if(warriorP[j].getWorld()!=null){//check if the element exist on the world
                        warriorP[j].checkCollision();
                        warriorP[j].checkMedic();//check if a medic is near
                    }
                    if(medicP[j].getWorld()!=null)//check if the element exist on the world
                        medicP[j].checkCollision();
                    if(builderP[j].getWorld()!=null){//check if the element exist on the world
                        builderP[j].checkCollision();
                        builderP[j].checkMedic();//check if a medic is near
                    }
               }
            for(int j = 0; j<499; j++){
                if(warriorP[j].getWorld()!=null && warriorP[j].move == true)
                    warriorP[j].setLocation(warriorP[j].getX()-squareSize,warriorP[j].getY());
                if(medicP[j].getWorld()!=null && medicP[j].move == true)
                    medicP[j].setLocation(medicP[j].getX()-squareSize,medicP[j].getY());
                if(builderP[j].getWorld()!=null && builderP[j].move == true)
                    builderP[j].setLocation(builderP[j].getX()-squareSize,builderP[j].getY());
            }
        }
    }
    private void moveTerran(){
        //movement w/ gamepad
        if(Greenfoot.mouseClicked(up)){
            turno=1;
            i++;
            for(int j = 0; j<499; j++){//check all the troops collisions
                if(warriorT[j].getWorld()!=null){//check if the element exist on the world
                    warriorT[j].checkCollision();
                    warriorT[j].checkMedic();//check if a medic is near
                }
                if(medicT[j].getWorld()!=null)//check if the element exist on the world
                    medicT[j].checkCollision();
                if(builderT[j].getWorld()!=null){//check if the element exist on the world
                    builderT[j].checkCollision();
                    builderT[j].checkMedic();//check if a medic is near
                }
           }
            for(int j = 0; j<499; j++){//move all the troops
                if(warriorT[j].getWorld()!=null && warriorT[j].move == true)//check if the element exist on the world
                    warriorT[j].setLocation(warriorT[j].getX(),warriorT[j].getY()-squareSize);
                if(medicT[j].getWorld()!=null && warriorT[j].move == true)//check if the element exist on the world
                    medicT[j].setLocation(medicT[j].getX(),medicT[j].getY()-squareSize);
                if(builderT[j].getWorld()!=null && warriorT[j].move == true)//check if the element exist on the world
                    builderT[j].setLocation(builderT[j].getX(),builderT[j].getY()-squareSize);
            }
        }
            
        if(Greenfoot.mouseClicked(down)){
            turno=1;
            i++;
            for(int j = 0; j<499; j++){//check all the troops collisions
                    if(warriorT[j].getWorld()!=null){//check if the element exist on the world
                        warriorT[j].checkCollision();
                        warriorT[j].checkMedic();//check if a medic is near
                    }
                    if(medicT[j].getWorld()!=null)//check if the element exist on the world
                        medicT[j].checkCollision();
                    if(builderT[j].getWorld()!=null){//check if the element exist on the world
                        builderT[j].checkCollision();
                        builderT[j].checkMedic();//check if a medic is near
                    }
               }
            for(int j = 0; j<499; j++){
                if(warriorT[j].getWorld()!=null && warriorT[j].move == true)
                    warriorT[j].setLocation(warriorT[j].getX(),warriorT[j].getY()+squareSize);
                if(medicT[j].getWorld()!=null && warriorT[j].move == true)
                    medicT[j].setLocation(medicT[j].getX(),medicT[j].getY()+squareSize);
                if(builderT[j].getWorld()!=null && warriorT[j].move == true)
                    builderT[j].setLocation(builderT[j].getX(),builderT[j].getY()+squareSize);
            }
        }
            
        if(Greenfoot.mouseClicked(right)){
            turno=1;
            i++;
            for(int j = 0; j<499; j++){//check all the troops collisions
                    if(warriorT[j].getWorld()!=null){//check if the element exist on the world
                        warriorT[j].checkCollision();
                        warriorT[j].checkMedic();//check if a medic is near
                    }
                    if(medicT[j].getWorld()!=null)//check if the element exist on the world
                        medicT[j].checkCollision();
                    if(builderT[j].getWorld()!=null){//check if the element exist on the world
                        builderT[j].checkCollision();
                        builderT[j].checkMedic();//check if a medic is near
                    }
               }
            for(int j = 0; j<499; j++){
                if(warriorT[j].getWorld()!=null && warriorT[j].move == true)
                    warriorT[j].setLocation(warriorT[j].getX()+squareSize,warriorT[j].getY());
                if(medicT[j].getWorld()!=null && warriorT[j].move == true)
                    medicT[j].setLocation(medicT[j].getX()+squareSize,medicT[j].getY());
                if(builderT[j].getWorld()!=null && warriorT[j].move == true)
                    builderT[j].setLocation(builderT[j].getX()+squareSize,builderT[j].getY());
            }
        }
            
        if(Greenfoot.mouseClicked(left)){
            turno=1;
            
            for(int j = 0; j<499; j++){//check all the troops collisions
                    if(warriorT[j].getWorld()!=null){//check if the element exist on the world
                        warriorT[j].checkCollision();
                        warriorT[j].checkMedic();//check if a medic is near
                    }
                    if(medicT[j].getWorld()!=null)//check if the element exist on the world
                        medicT[j].checkCollision();
                    if(builderT[j].getWorld()!=null){//check if the element exist on the world
                        builderT[j].checkCollision();
                        builderT[j].checkMedic();//check if a medic is near
                    }
               }
            for(int j = 0; j<499; j++){
                if(warriorT[j].getWorld()!=null && warriorT[j].move == true)
                    warriorT[j].setLocation(warriorT[j].getX()-squareSize,warriorT[j].getY());
                if(medicT[j].getWorld()!=null && warriorT[j].move == true)
                    medicT[j].setLocation(medicT[j].getX()-squareSize,medicT[j].getY());
                if(builderT[j].getWorld()!=null && warriorT[j].move == true)
                    builderT[j].setLocation(builderT[j].getX()-squareSize,builderT[j].getY());
            }
        }
    }
}
