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
    //protected int picIdxdot = 0;
    protected String dotpic = "pacdot.png";
    //protected API dotapi;
    protected boolean visible = true;
    //protected int score;

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

    /**
     *
     * @return
     */
    @Override
    public int getH() {
        return 50;
    }

    public pacdot(int x, int y) {
        this.x = x;
        this.y = y;

    }

    @Override
    public void draw(API api) {
        String pic = this.dotpic;

        api.drawImg(pic, x, y, 50, 50);

    }

    @Override
    public void update() {
        return;

    }
}
