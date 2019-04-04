import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BuilderP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BuilderP extends Protoss
{
    
    public BuilderP(){
        super(3, "protoss_builder.png");
    }
    
    public void act() 
    {
        // Add your action code here.
    }
    
    public void collision(){
        move = false;
        
    }
    public void noCollision(){
        move = true;
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
            this.energy -= 50;
            collision();
        }else{
            noCollision();
        }
        builderCollision = this.getOneIntersectingObject(BuilderT.class);
        if(builderCollision != null){
            this.energy -= 50;
            collision();
        }else{
            noCollision();
        }
    }
}
