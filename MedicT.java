import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MedicT here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MedicT extends Terran
{
    
    public MedicT(){
        super(2, "terran_medic.png");
        energy = 120;
    }
    
    public void act() 
    {
        // Add your action code here.
    }  
    public void checkCollision(){
        warriorCollision = this.getOneIntersectingObject(WarriorP.class);
        if(warriorCollision != null){
            collision();
            this.energy -= 30;
        }else{
            noCollision();
        }
        medicCollision = this.getOneIntersectingObject(MedicP.class);
        if(medicCollision != null){
            collision();
            this.energy -= 50;
        }else{
            noCollision();
        }
        builderCollision = this.getOneIntersectingObject(BuilderP.class);
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
