/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author csc190
 */
public class ScoreBoard implements Sprite {
    protected Text text;
    protected GameEngine ge;
    
    public ScoreBoard(GameEngine G){
        ge = G;
        text = new Text(900, 900, "Score: " + ge.getScore());
        text.setFont(new Font(20));
        //text.setText("Score: "+ge.getScore());
    }

    @Override
    public void draw(API api) {
        return;
    }

    @Override
    public void update() {
        text.setText("Score: "+ge.getScore());
    }

    @Override
    public int getX() {
        return 900;
    }

    @Override
    public int getY() {
        return 900;
    }

    @Override
    public int getW() {
        return 0;
    }

    @Override
    public int getH() {
        return 0;
    }
    
    
}
