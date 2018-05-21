/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

import java.util.HashSet;
import java.util.PriorityQueue;
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
    boolean[][] wasHere = new boolean[1000][1000];
    //boolean[][] correctPath = new boolean[100][100];
    protected GameEngine ge;
    int startX, startY;
    int endX, endY;

    public Map(char[][] Map) {
        this.map = Map;
        Instance = this;
    }

    public static Map getInstance() {
        return Instance;
    }

    public int decideDirection(int x, int y, int dx, int dy) {
        PriorityQueue<Integer> path = new PriorityQueue<Integer>();
        getAvailablePath(x, y, dx, dy, path);
        int firstDir = path.peek();
        return firstDir;
    }

    public Set<Integer> getAvailableDirection(int x, int y) {   //return a possible direction given a coordinate
        Set<Integer> Dir = new HashSet<Integer>();
        int nx = x / 50;
        int ny = y / 50;
        for (int dir = 0; dir < 3; dir++) {
            int dx = arrSx[dir];
            int dy = arrSy[dir];
            int fx = nx + dx;
            int fy = ny + dy;
            if (fx >= 0 && fx < 100 && fy >= 0 && fy < 100 && map[fy][fx] != '#') {
                Dir.add(dir);
            }
        }
        return Dir;
    }

    public void getAvailablePath(int x, int y, int dx, int dy, PriorityQueue<Integer> path) { // get a set of direction from map[y][x] to map[dy][dx]
        wasHere[y][x] = true;
        Set<Integer> Dir = getAvailableDirection(x, y);
        for (int dir : Dir) {
            int nx = (x / 50) + arrSx[dir];
            int ny = (y / 50) + arrSy[dir];
            int ax = nx * 50;
            int ay = ny * 50;
            path.add(dir);
            if (ax != dx && ay != dy && wasHere[ay][ax]!=true) {
                getAvailablePath(ax, ay, dx, dy, path);
            } else {
                return;
            }
        }
    }

    public PriorityQueue<Integer> getPath(int x, int y, int dx, int dy){
        PriorityQueue<Integer> path = new PriorityQueue<Integer>();
        getAvailablePath(x, y, dx, dy, path);
        return path;
    }
    /*
    public void solveMap(){
        for (int row = 0; row < 99; row++){
            for (int col = 0; col < 99; col++){
                wasHere[col][row] = false;
                correctPath[col][row] = false;
            }
        }
        boolean b = recursiveSolve(startX,startY);
    }
    
    public boolean recursiveSolve(int x, int y) {
        if (x == endX && y == endY) return true; // If you reached the end
    if (map[y][x] == '#' || wasHere[x][y]) return false;  
    // If you are on a wall or already were here
    wasHere[x][y] = true;
    if (x != 0) // Checks if not on left edge
        if (recursiveSolve(x-1, y)) { // Recalls method one to the left
            correctPath[y][x] = true; // Sets that path value to true;
            return true;
        }
    if (x != 99) // Checks if not on right edge
        if (recursiveSolve(x+1, y)) { // Recalls method one to the right
            correctPath[y][x] = true;
            return true;
        }
    if (y != 0)  // Checks if not on top edge
        if (recursiveSolve(x, y-1)) { // Recalls method one up
            correctPath[y][x] = true;
            return true;
        }
    if (y != 99) // Checks if not on bottom edge
        if (recursiveSolve(x, y+1)) { // Recalls method one down
            correctPath[y][x] = true;
            return true;
        }
    return false;
    }
     */
}
