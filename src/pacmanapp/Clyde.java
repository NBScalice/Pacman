/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

import java.util.Random;

/**
 *
 * @author csc190
 */
public class Clyde implements Sprite {

    protected int x, y, sx, sy;
    protected String pic = "Clyde.png";
    
    public Clyde(int x, int y, int sx, int sy) {
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
        //1. get the dx,dy of pacman
        Pacman p1 = GameEngine.getInst().getPacman();
        int dx = p1.getX();
        int dy = p1.getY();

        //2. pick a direction: to bottom left, or to pacman
        Random random = new Random();
        int choice = random.nextInt(100);
        
        int dir;
        
        if (choice < 50) {
            dir = decideDirection(dx, dy);
        } else {
            if (choice < 75) { dir = 1; }
            else { dir = 2; }
        }
        
        
        //3. update the x,y coordinates
        int[] arrSx = new int[]{1, 0, -1, 0};
        int[] arrSy = new int[]{0, -1, 0, 1};
        sx = arrSx[dir];
        sy = arrSy[dir];

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
        boolean flag = true;
        
        if (distanceX == 0 && distanceY == 0) {
            return 0;
        }
        else {
            
            Random random = new Random();
            int num = random.nextInt(2)+1;
            if(num == 1) flag = false;
            if (Math.abs(distanceX) > Math.abs(distanceY)) {
                if (distanceX > 0) {
                    //setDirection(1, 0);
                    if(flag) return 0;   //East
                    else return 2;
                } else {
                    //setDirection(-1, 0);
                    if(flag) return 2;   //West
                    else return 0;
                }
            } else {
                if (distanceY > 0) {
                    //setDirection(0, -1);
                    if(flag) return 1;   //North
                    else return 3;
                } else {
                    //setDirection(0, 1);
                    if(flag) return 3;   //South
                    else return 1;
                }
            }
        }

    }

}
