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

    protected static Map Instance;
    protected GameEngine ge;

    public Map(GameEngine GE) {
        this.ge = GE;
        Instance = this;
    }

    public static Map getInstance() {
        return Instance;
    }

    public int decideDirection(int sx, int sy, int dx, int dy) {
        return 0;
    }

}
