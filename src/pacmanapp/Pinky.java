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
public class Pinky implements Sprite{
    protected int x, y, sx, sy;
    //protected int picIdx = 0;
    protected String pic = "Pinky.png";
    
    public Pinky(int x, int y, int sx, int sy)
    {
        this.x = x; this.y = y; this.sx = sx; this.sy = sy;
    }
    
    public void setDirection(int sx, int sy){
        this.sx = sx;
        this.sy = sy;
    }

    @Override
    public void draw(API api) {
        api.drawImg(pic,x,y,100,100);
    }

    private int counter = 0;
    
    @Override
    public void update() {
        this.x+=sx;
        this.y+=sy;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getW() {
        return 100;
    }

    @Override
    public int getH() {
        return 100;
    }
    
    protected int decideDirection(int dx, int dy){
        if (dx < 500 && dy < 500){  //NORTH-WEST
            if (dx < dy){
                return 2;   //West
            }else {
                return 1;   //North
            }
        }else if (dx > 500 && dy < 500){    //NORTH-EAST
            if (dy < (1000-dx)){
                return 1;   //North
            } else {
                return 0;   //East
            }
        }else if (dx < 500 && dy > 500){    //SOUTH-WEST
            if (dx < (1000-dy)){
                return 2;   //West
            }
            else {
                return 3;   //South
            }
        }else if (dx > 500 && dy > 500){    //SOUTH-EAST
            if (dx < dy){
                return 0;   //East
            }else {
                return 3;   //South
            }
        }else{
            return 0;
        }
    }
    
}
