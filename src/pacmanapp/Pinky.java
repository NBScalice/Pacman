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
public class Pinky implements movingSprites {

    protected int x, y, sx, sy, c, dir;
    //protected int picIdx = 0;
    protected String pic = "pinky.png";

    public Pinky(int x, int y, int sx, int sy) {
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
        this.c = 0;
    }

    public void setDirection(int sx, int sy) {
        this.sx = sx;
        this.sy = sy;
    }

    @Override
    public void draw(API api) {
        api.drawImg(pic, x, y, 40, 40);
    }

    private int counter = 0;

    @Override
    public void update() {
        
        counter++;
        this.x += sx;
        this.y += sy;
          this.x %= 600;
        this.y %= 600;
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
        return 40;
    }

    @Override
    public int getH() {
        return 40;
    }

    public int decideDirection(int dx, int dy) {
        Map map = Map.getInstance(); 
        if (dx < 300 && dy < 300) {  //NORTH-WEST
            if (dx < dy) {
                int dir = map.decideDirection(x, y, dx, 0);
                //setDirection(-1, 0);
                return dir;   //West
            } else {
                int dir = map.decideDirection(x, y, 0, dy);
                //setDirection(0, -1);
                return dir;   //North
            }
        } else if (dx > 300 && dy < 300) {    //NORTH-EAST
            if (dy < (600 - dx)) {
                int dir = map.decideDirection(x, y, 0, dy);
                //setDirection(0, -1);
                return dir;   //North
            } else {
                int dir = map.decideDirection(x, y, 600, dy);
                //setDirection(1, 0);
                return dir;   //East
            }
        } else if (dx < 300 && dy > 300) {    //SOUTH-WEST
            if (dx < (600 - dy)) {
                int dir = map.decideDirection(x, y, 0, dy);
                //setDirection(-1, 0);
                return dir;   //West
            } else {
                int dir = map.decideDirection(x, y, dx, 600);
                //setDirection(0, 1);
                return dir;   //South
            }
        } else if (dx > 300 && dy > 300) {    //SOUTH-EAST
            if (dx < dy) {
                int dir = map.decideDirection(x, y, 600, dy);
                //setDirection(1, 0);
                return dir;   //East
            } else {
                int dir = map.decideDirection(x, y, dx, 600);
                //setDirection(0, 1);
                return dir;   //South
            }
        } else {
            return 1;
        }
    }

}
