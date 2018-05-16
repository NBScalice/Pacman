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
public class Map {

    protected char[][] map;
    protected int[] arrSx = new int[]{1, 0, -1, 0};
    protected int[] arrSy = new int[]{0, -1, 0, 1}; //East,North,West,South
    protected static Map Instance;
    protected GameEngine ge;

    public Map(char[][] Map) {
        this.map = Map;
        Instance = this;
    }

    public static Map getInstance() {
        return Instance;
    }

    public int decideDirection(int sx, int sy, int dx, int dy) {
        return 0;
    }

    public boolean isAvailableToGo(int x, int y, int dir){
        int nx = x / 50;
        int ny = y / 50;
        int sx = arrSx[dir];
        int sy = arrSy[dir];
        int dx = sx + nx;
        int dy = sy + ny;
        /*if (map[dy][dx].equals("#")){
            
        }*/
        return false;
    }
}
