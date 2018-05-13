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
    protected ArrayList<Sprite> toDel = new ArrayList<Sprite>();

    public GameEngine(API api) {
        this.api = api;
    }

    public void register(Sprite s) {
        this.arrSprites.add(s);
    }

    public void delete(Sprite s) {
        this.arrSprites.remove(s);
    }

    public enum KEY {
        UP, DOWN, LEFT, RIGHT
    };

    public void handleKey(KEY key) {
        switch (key) {
            case UP:
                this.playerPacman.setDirection(0, -1);
                break;
            case DOWN:
                this.playerPacman.setDirection(0, 1);
                break;
            case LEFT:
                this.playerPacman.setDirection(-1, 0);
                break;
            case RIGHT:
                this.playerPacman.setDirection(1, 0);
                break;
        }
    }

    public void oneRound() {

        Timer timer = new Timer();
        timer.start();
        collisionDetection();
        api.clear();
        for (Sprite s : this.arrSprites) {
            s.update();
            s.draw(this.api);
        }
        double du = timer.stop();
        System.out.println("Time taken: " + du + " milliseconds.");
    }

    public void loadMap() {
        Pacman man1 = new Pacman(100, 100, 1, 0);
        Pacman man2 = new Pacman(300, 300, 0, 1);

        this.register(man1);
        this.register(man2);
        this.playerPacman = man1;
    }

    protected void collisionDetection() {
        for (int i = 0; i < arrSprites.size() - 1; i++) {
            for (int j = i + 1; j < arrSprites.size() - 1; j++) {
                Sprite sprite1 = arrSprites.get(i);
                Sprite sprite2 = arrSprites.get(j);
                if (sprite1 != sprite2) {
                    int x1, y1, w1, h1, x2, y2, w2, h2;
                    x1 = sprite1.getX();
                    y1 = sprite1.getY();
                    w1 = sprite1.getW();
                    h1 = sprite1.getH();
                    x2 = sprite2.getX();
                    y2 = sprite2.getY();
                    w2 = sprite2.getW();
                    h2 = sprite2.getH();
                    boolean b1, b2, b3, b4;
                    b1 = isPointInRectangle(x1, y1, x2, y2, w2, h2);
                    b2 = isPointInRectangle(x1 + w1, y1, x2, y2, w2, h2);
                    b3 = isPointInRectangle(x1, y1 + h1, x2, y2, w2, h2);
                    b4 = isPointInRectangle(x1 + w1, y1 + h1, x2, y2, w2, h2);
                    if (b1 || b2 || b3 || b4) {
                        handleCollision(sprite1, sprite2, toDel);
                    }
                }
            }
        }

        for (Sprite sp : toDel) {
            this.delete(sp);
        }

    }

    protected void updateAll() {
        for (Sprite s : arrSprites) {
            s.update();
        }
    }

    protected void drawAll() {
        for (Sprite s : arrSprites) {
            s.draw(api);
        }
    }

    protected boolean isPointInRectangle(int x, int y, int tx, int ty, int w, int h) {
        if (x > tx && x < (tx + w) && y > ty && y < (ty + h)) {
            return true;
        } else {
            return false;
        }
    }

    protected void handleCollision(Sprite sprite1, Sprite sprite2, ArrayList<Sprite> toDel) {
        if (sprite1 instanceof Pacman && sprite2 instanceof Pacman) {
            return;
        }//checking pacman pascman
        else if (sprite1 instanceof Pacman && !(sprite2 instanceof Pacman)) {
            //add all the pacdot to to delete 
            toDel.add(sprite2);
        }//checking pacman packdot
        else if (!(sprite1 instanceof Pacman) && sprite2 instanceof Pacman) {

            toDel.add(sprite1);
        }//checking pacdot pacman
        else {
            return;//coz two pacdots
        }
    }

}
