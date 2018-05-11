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
public class Blinky implements Sprite{
    protected int x, y, sx, sy;
    //protected int picIdx = 0;
    protected String pic = "Blinky.png";
    
    public Blinky(int x, int y, int sx, int sy)
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
