import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WarriorT here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WarriorT extends Terran
{
    
    public WarriorT(){
        super(1, "terran_warrior.png");
    }
    
    public void act() 
    {
        // Add your action code here.
    }  
    public void checkCollision(){
        warriorCollision = this.getOneIntersectingObject(WarriorP.class);
        if(warriorCollision != null){
            collision();
            this.energy -= 45;
        }else{
            noCollision();
        }
        medicCollision = this.getOneIntersectingObject(MedicP.class);
        if(medicCollision != null){
            collision();
            this.energy -= 70;
        }else{
            noCollision();
        }
        builderCollision = this.getOneIntersectingObject(BuilderP.class);
        if(builderCollision != null){
            collision();
            this.energy -= 70;
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
    
    public void addEnergy(int e){
        energy += e;
    }
}
