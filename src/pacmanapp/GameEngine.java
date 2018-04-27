/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

import java.util.ArrayList;

/**
 *
 * @author csc190
 */
public class GameEngine {
    protected Pacman playerPacman;
    protected ArrayList<Sprite> arrSprites = new ArrayList<Sprite>();
    protected API api;
    
    public GameEngine(API api) {this.api = api;}
    
    public void register(Sprite s)
    {
        this.arrSprites.add(s);
    }
    
    public void delete(Sprite s)
    {
        this.arrSprites.remove(s);
    }
    
    public enum KEY{UP, DOWN, LEFT, RIGHT};
    
    public void handleKey(KEY key)
    {
        switch(key){
            case UP:
                this.playerPacman.setDirection(0,-1);
                break;
            case DOWN:
                this.playerPacman.setDirection(0,11);
                break;
            case LEFT:
                this.playerPacman.setDirection(-1,0);
                break;
            case RIGHT:
                this.playerPacman.setDirection(0,-1);
                break;
            }
    }
    
    public void oneRound()
    {
        Timer timer = new Timer();
        timer.start();
        api.clear();
        for(Sprite s: this.arrSprites)
        {
            s.update();
            s.draw(this.api);
        }
        double du = timer.stop();
        System.out.println("Time taken: "+du+" milliseconds.");
    }
    
    public void loadMap()
    {
        Pacman man1 = new Pacman(100,100,1,0);
        Pacman man2 = new Pacman(300,300,0,1);
        
        this.register(man1);
        this.register(man2);
        this.playerPacman = man1;
    }
    
    protected void collisionDetection(){ return;}
    
    protected void updateAll(){ return;}
    
    protected void drawAll(){ return;}
    
    protected boolean isPointInRectangle(){ return true;}
    
    protected void handleCollision(Sprite sprite1, Sprite sprite2){ return;}
}
