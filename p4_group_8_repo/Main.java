package p4_group_8_repo;

import java.io.File;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	AnimationTimer timer;
	private MyStage background;
	Animal animal;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	    setBackground(new MyStage());
	    Scene scene  = new Scene(getBackground(),600,850);
	    
		BackgroundImage froggerback = new BackgroundImage("file:///D:/eclipse-workspace/Frogger/src/p4_group_8_repo/background.png");
	    
		getBackground().add(froggerback);
		
		getBackground().add(new Log("file:src/p4_group_8_repo/log3.png", 150, 0, 166, 0.75));
		getBackground().add(new Log("file:src/p4_group_8_repo/log3.png", 150, 220, 166, 0.75));
		getBackground().add(new Log("file:src/p4_group_8_repo/log3.png", 150, 440, 166, 0.75));
		//background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 0, 166, 0.75));
		getBackground().add(new Log("file:src/p4_group_8_repo/logs.png", 300, 0, 276, -2));
		getBackground().add(new Log("file:src/p4_group_8_repo/logs.png", 300, 400, 276, -2));
		//background.add(new Log("file:src/p4_group_8_repo/logs.png", 300, 800, 276, -2));
		getBackground().add(new Log("file:src/p4_group_8_repo/log3.png", 150, 50, 329, 0.75));
		getBackground().add(new Log("file:src/p4_group_8_repo/log3.png", 150, 270, 329, 0.75));
		getBackground().add(new Log("file:src/p4_group_8_repo/log3.png", 150, 490, 329, 0.75));
		//background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 570, 329, 0.75));
		
		getBackground().add(new Turtle(500, 376, -1, 130, 130));
		getBackground().add(new WetTurtle(300, 376, -1, 130, 130));
		getBackground().add(new Turtle(700, 376, -1, 130, 130));
		getBackground().add(new WetTurtle(600, 217, -1, 130, 130));
		getBackground().add(new Turtle(400, 217, -1, 130, 130));
		getBackground().add(new Turtle(200, 217, -1, 130, 130));

		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 0, 649, 1, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 350, 649, 1, 120, 120));
		//background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 600, 649, 1, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 720, 649, 1, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 100, 597, -1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 250, 597, -1, 50, 50));
		//background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 400, 597, -1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 550, 597, -1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck2Right.png", 0, 540, 1, 200, 200));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck2Right.png", 500, 540, 1, 200, 200));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 250, 490, -5, 50, 50));
		background.add(new Digit(0, 30, 360, 25));

		getBackground().add(new End(13,96));
		getBackground().add(new End(141,96));
		getBackground().add(new End(269,96));
		getBackground().add(new End(398,96));
		getBackground().add(new End(528,96));
		getBackground().add(new Secret(269,750));
		animal = new Animal("file:src/p4_group_8_repo/froggerUp.png");
		getBackground().add(animal);
		

		getBackground().start();
		primaryStage.setScene(scene);
		primaryStage.show();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Instructions");
		alert.setHeaderText("'WASD' TO MOVE");
		alert.show(); 
		start();  
	}
	public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	if (animal.changeScore()) {
            		setNumber(animal.getPoints());
            	}
            	if (animal.getRoundStop()) {
            		stop();	
            		getBackground().stop();
            		Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("Find my secret win.");
            		alert.setHeaderText("Possible score: 50000");
            		alert.setContentText("Press S.");
            		alert.show();            		
            	}
            	if (animal.getStop()) {
            		System.out.print("STOPP:");
            		getBackground().stopMusic();
            		stop();
            		getBackground().stop();
            		Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("You Found The Secret!");
            		alert.setHeaderText("Sub to me: https://youtube.com/user/EmoFreakPro");
            		alert.setContentText("Your High Score: "+animal.getPoints()+"!");
            		alert.show();
            	}
            }
        };
    }
	public void start() {
		background.playMusic();
    	createTimer();
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
    
    public void setNumber(int n) {
    	int shift = 0;
    	while (n > 0) {
    		  int d = n / 10;
    		  int k = n - d * 10;
    		  n = d;
    		  getBackground().add(new Digit(k, 30, 360 - shift, 25));
    		  shift+=30;
    		}
    }

	MyStage getBackground() {
		return background;
	}

	void setBackground(MyStage background) {
		this.background = background;
	}
}
