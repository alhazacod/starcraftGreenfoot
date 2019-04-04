import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BuilderT here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BuilderT extends Terran
{
    
    public BuilderT(){
        super(3, "terran_builder.png");
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
