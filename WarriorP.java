import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;
/**
 * Write a description of class WarriorP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WarriorP extends Protoss
{
    
    public WarriorP(){
        super(1, "protoss_warrior.png");
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
    public void checkCollision(){
        warriorCollision = this.getOneIntersectingObject(WarriorT.class);
        if(warriorCollision != null){
            collision();
            this.energy -= 45;
        }else{
            noCollision();
        }
        medicCollision = this.getOneIntersectingObject(MedicT.class);
        if(medicCollision != null){
            collision();
            this.energy -= 30;
        }else{
            noCollision();
        }
        builderCollision = this.getOneIntersectingObject(BuilderT.class);
        if(builderCollision != null){
            collision();
            this.energy -= 30;
        }else{
            noCollision();
        }
    }
    public void collision(){
        move = false;
    }
    public void noCollision(){
        move = true;
    }
}
