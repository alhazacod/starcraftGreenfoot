import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;
import javax.swing.JFrame;
/**
 * Write a description of class Protoss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Protoss extends Actor
{
    Actor warriorCollision;
    Actor medicCollision;
    Actor builderCollision;
    
    public String sprite;
    public String wrongSprite;
    
    public int rol;
    
    public int energy;
    
    private int daño;
    
    public boolean move;
    
    //There are 3 rols 
    //1: warrior
    //2: medic
    //3: builder
    
    public Protoss(int rol, String sprite){
        
        setRol(rol);
        
        this.sprite = sprite;
        setImage(this.sprite);
        
        energy = 100;
        
        move = true;
        
        wrongSprite = "skull.png";
    }
    
    public void act() 
    {
        setImage(this.sprite);
        if(energy>160) energy = 160;//max energy 160
        
        
    }    
    
    public void wrong(){
        this.sprite = this.wrongSprite;
    }
    
    public void setRol(int rol){
        this.rol = rol;
        
        //setImage(sprite);
    }
    
    public void addEnergy(int e){
        energy += e;
    }
    
    public void checkMedic(){
        Actor closest = null; // to hold closest ball, if any
        int closeness = 50; // how close closest ball found so far is
        for (Object obj : getWorld().getObjects(MedicP.class)) // for each ball in world (as Object object)
        {
            Actor medic = (Actor) obj; // cast as Actor object
            int distance = (int)Math.hypot(medic.getX()-getX(), medic.getY()-getY()); // get distance
            if (closest == null || distance < closeness) // if first ball or closer ball
            {
                closest = medic;
                closeness = distance;
                this.energy += 20;
            }
        }
    }
}
