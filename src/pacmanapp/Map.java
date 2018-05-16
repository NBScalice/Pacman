/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

import java.util.HashSet;
import java.util.Set;

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

    public int decideDirection(int x, int y, int dx, int dy) {
        return 0;
    }

    public Set<Integer> getAvailablePath(int x, int y) {
        Set<Integer> path = new HashSet<Integer>();
        int nx = x / 50;
        int ny = y / 50;
        for (int dir = 0; dir < 3; dir++) {
            int dx = arrSx[dir];
            int dy = arrSy[dir];
            int fx = nx + dx;
            int fy = ny + dy;
            if (fx >= 0 && fx < 100 && fy >= 0 && fy < 100 && map[fy][fx] != '#') {
                path.add(dir);
            }
        }
        return path;
    }

}
