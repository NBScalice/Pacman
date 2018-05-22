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
public class Blinky implements movingSprites {

    protected int x, y, sx, sy, dir, c;
    //protected int picIdx = 0;
    protected String pic = "blinky.png";

    public Blinky(int x, int y, int sx, int sy) {
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
        //1. get the dx,dy of pacman
        Pacman p1 = GameEngine.getInst().getPacman();
        int dx = p1.getX();
        int dy = p1.getY();

        //2. decide what is the right direction to go to pacman
        if(c%100 == 0){
            Map map = Map.getInstance(); 
            dir = map.decideDirection(this.x,this.y, dx, dy);
            if(dir==-1){return;} //don't do anything
        }
        c++;

        //3. update the x,y coordinates
        int[] arrSx = new int[]{1, 0, -1, 0};
        int[] arrSy = new int[]{0, -1, 0, 1};
        //int dir = 0; //actually above
        sx = arrSx[dir];
        sy = arrSy[dir];

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
        int distanceX = this.x - dx;
        int distanceY = this.y - dy;
        if (distanceX == 0 && distanceY == 0) {
            return 0;
        } else {
            if (Math.abs(distanceX) > Math.abs(distanceY)) {
                if (distanceX > 0) {
                    //setDirection(1, 0);
                    return 0;   //East
                } else {
                    //setDirection(-1, 0);
                    return 2;   //West
                }
            } else {
                if (distanceY > 0) {
                    //setDirection(0, -1);
                    return 1;   //North
                } else {
                    //setDirection(0, 1);
                    return 3;   //South
                }
            }
        }

    }

}
