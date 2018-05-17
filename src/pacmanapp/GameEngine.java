/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;

/**
 *
 * @author csc190
 */
public class GameEngine {

    protected Pacman playerPacman;
    //protected pacdot dot; 
    protected Clyde clyde;
    protected Inky inky;
    protected Blinky blinky;
    protected Pinky pinky;
    protected static GameEngine inst;
    protected ArrayList<Sprite> arrSprites = new ArrayList<Sprite>();
    protected API api;
    protected ArrayList<Sprite> toDel = new ArrayList<Sprite>();
    protected char[][] map = null;
    protected int rows = 0;
    protected int cols = 0;
    protected int xStart = 0;
    protected int yStart = 0;
    protected int score = 0;
    //private Object chooser;
    protected ScoreBoard scoreboard;

    public GameEngine(API api) {
        this.api = api;
        inst = this;
    }

    public static GameEngine getInst() {
        return inst;
    }

    public void register(Sprite s) {
        this.arrSprites.add(s);
    }

    public void delete(Sprite s) {
        this.arrSprites.remove(s);
    }

    public Pacman getPacman() {
        return this.playerPacman;
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

    public void loadMap() throws IOException {

        //1. choose a file from a dialog, currently it's hard coded
        String filepath = "map.txt";
        //2. open and read the file
        BufferedReader read = new BufferedReader(new FileReader(filepath));
        String rea = read.readLine();
        String[] split = rea.split(" ");
        Integer width = Integer.valueOf(split[0]);
        Integer height = Integer.valueOf(split[1]);

        String readline;

        char[][] map1 = new char[height][width];
        int row = 0;
        try {
            Sprite sp = null;
            while ((readline = read.readLine()) != null) {
                char[] arrCh = readline.toCharArray();
                for (int i = 0; i < arrCh.length; i++) {
                    map1[row][i] = arrCh[i];
                    int x = 50 * i;
                    int y = 50 * row;
                    switch (arrCh[i]) {
                        case '#':
                            sp = new wall(x, y);
                            this.register(sp);
                            break;
                        case 'P':
                            sp = new Pacman(x, y, 1, 0);
                            this.register(sp);
                            break;
                        case '.':
                            sp = new pacdot(x, y);
                            this.register(sp);
                            break;

                    }
                }

                row++;
            }
        } catch (IOException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }

        //pacdot dot1 = new pacdot(80, 80);
        Pacman man1 = new Pacman(100, 100, 1, 0);
        Pacman man2 = new Pacman(300, 300, 0, 1);

        this.register(man1);
        this.register(man2);
        this.playerPacman = man1;

        Blinky b = new Blinky(200, 200, 1, 0);
        this.register(b);
        this.blinky = b;
        
        Clyde c = new Clyde(300, 300, 1, 0);
        this.register(c);
        this.clyde = c;

        Pinky p = new Pinky(200, 300, 1, 0);
        this.register(p);
        this.pinky = p;

        Inky I = new Inky(200, 100, 1, 0);
        this.register(I);
        this.inky = I;

        ScoreBoard sb = new ScoreBoard(this);
        this.register(sb);
        this.scoreboard = sb;

        Map map = new Map(map1);
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
            if (sprite2 instanceof pacdot) {
                toDel.add(sprite2);
            } else if (sprite2 instanceof wall) {
                ((Pacman) sprite1).setDirection(0, 0);
            } else {
                toDel.add(sprite1);
            }
        }//checking pacman packdot
        else if (!(sprite1 instanceof Pacman) && sprite2 instanceof Pacman) {
            if (sprite1 instanceof pacdot) {
                toDel.add(sprite1);
                score++;
            } else if (sprite1 instanceof wall) {
                ((Pacman) sprite2).setDirection(0, 0);
            } else {
                toDel.add(sprite2);
            }
        }//checking pacdot pacman
        else {
            return;//coz two pacdots
        }
    }

    public int getScore() {
        return score;
    }

}
