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
public class Timer {
    
    protected long StartTime;
    
    public void start()
    {
        StartTime = System.currentTimeMillis();
    }
    
    public double stop()
    {
        long EndTime = System.currentTimeMillis();
        return EndTime - StartTime;
    }
}
