import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MedicP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MedicP extends Protoss
{
    
    public MedicP(){
        super(2, "protoss_medic.png");
        energy = 120;
    }
    
    public void act() 
    {
        // Add your action code here.
    }  
    public void checkCollision(){
        warriorCollision = this.getOneIntersectingObject(WarriorT.class);
        if(warriorCollision != null){
            collision();
            this.energy -= 70;
        }else{
            noCollision();
        }
        medicCollision = this.getOneIntersectingObject(MedicT.class);
        if(medicCollision != null){
            collision();
            this.energy -= 50;
        }else{
            noCollision();
        }
        builderCollision = this.getOneIntersectingObject(BuilderT.class);
        if(builderCollision != null){
            collision();
            this.energy -= 50;
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
