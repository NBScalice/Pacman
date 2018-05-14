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
public class Blinky implements Sprite {

    protected int x, y, sx, sy;
    //protected int picIdx = 0;
    protected String pic = "blinky.png";

    public Blinky(int x, int y, int sx, int sy) {
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
    }

    public void setDirection(int sx, int sy) {
        this.sx = sx;
        this.sy = sy;
    }

    @Override
    public void draw(API api) {
        api.drawImg(pic, x, y, 50, 50);
    }

    private int counter = 0;

    @Override
    public void update() {
        counter++;
        this.x += sx;
        this.y += sy;
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
        return 50;
    }

    @Override
    public int getH() {
        return 50;
    }

    protected int decideDirection(int dx, int dy) {
        int distanceX = this.x - dx;
        int distanceY = this.y - dy;
        if (distanceX == 0 && distanceY == 0) {
            return -1;
        } else {
            if (Math.abs(distanceX) > Math.abs(distanceY)) {
                if (distanceX > 0) {
                    setDirection(1, 0);
                    return 0;   //East
                } else {
                    setDirection(-1, 0);
                    return 2;   //West
                }
            } else {
                if (distanceY > 0) {
                    setDirection(0, -1);
                    return 1;   //North
                } else {
                    setDirection(0, 1);
                    return 3;   //South
                }
            }
        }

    }

}
