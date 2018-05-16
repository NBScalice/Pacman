/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Hashtable;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author csc190
 */
public class PacmanApp extends Application implements API {

    protected GraphicsContext gc;
    protected GameEngine ge;
    protected Hashtable<String, Image> map = new Hashtable<String, Image>();

    class MyThread extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PacmanApp.class.getName()).log(Level.SEVERE, null, ex);
                }
                ge.oneRound();
            }
        }
    }
    Text text = new Text();

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Pacman");
        Group root = new Group();
        Canvas canvas = new Canvas(1000, 1000);
        this.gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.show();

        this.ge = new GameEngine(this);
        this.ge.loadMap();

        sc.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.UP) {
                    ge.handleKey(GameEngine.KEY.UP);
                } else if (code == KeyCode.DOWN) {
                    ge.handleKey(GameEngine.KEY.DOWN);
                } else if (code == KeyCode.LEFT) {
                    ge.handleKey(GameEngine.KEY.LEFT);
                } else if (code == KeyCode.RIGHT) {
                    ge.handleKey(GameEngine.KEY.RIGHT);
                } else {
                    return;
                }
            }
        });

        MyThread mt = new MyThread();
        mt.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void drawImg(String filename, int x, int y, int w, int h) {
        FileInputStream fis = null;
        try {
            Image ig = map.get(filename);
            if (ig == null) {
                String path = "images/" + filename;
                //String path = filename;
                fis = new FileInputStream(path);
                ig = new Image(fis);
                map.put(filename, ig);
            }
            this.gc.drawImage(ig, x, y, w, h);
            if (fis != null) {
                fis.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PacmanApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PacmanApp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(PacmanApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void clear() {
        this.gc.clearRect(0, 0, 1000, 1000);
    }

}
