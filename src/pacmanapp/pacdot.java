/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

/**
 *
 * @author csc190
 */
public class pacdot implements Sprite {

    protected int x, y;
    protected int picIdxdot = 0;
    protected String[] arrPics = {"tmp1.png"};
    protected boolean visible = true;
    protected int score;

    @Override
    public int getX() {
        return x;
    }

    public int getY() {
        return 100;
    }

    public int getW() {
        return 100;
    }

    public int getH() {
        return 100;
    }

    public pacdot(int x, int y) {
        this.x = x;
        this.y = y;

    }

    @Override
    public void draw(API api) {
        if (visible) {
            String pic = this.arrPics[this.picIdxdot];
            api.drawImg(pic, x, y, 100, 100);
        }

    }
    //public boolean collide_with_pacman(Sprite Pacman){ //Test if collided with pacman
    //if (visible){
    // boolean collided =  getX() < Pacman.getX() && getX() > Pacman.getX() && getY() < Pacman.getY()  && getY() > Pacman.getY();
    //if(collided){ //Is 'eaten' if collided, so set visible to false
    // visible = false;
    //}
    //return collided; //Regardless, return the result
    //} //If it's not visible, it can't collide
    //return false;
    //}

    //private int counter =0;
    private int counter = 0;
    @Override
    public void update() 
    {
        counter++;
        for (int i=0; i<100;i++){
            this.x++;
            this.y++;
        }
    
       if(counter%25 == 0)
        {
          this.picIdxdot = (picIdxdot+1)%this.arrPics.length;
    }

    //this.picIdx = (picIdx+1)%this.arrPics.length;
}}
