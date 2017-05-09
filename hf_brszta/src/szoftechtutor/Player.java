package szoftechtutor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class Player {
	
	public int score = 0;
	public int lives = 3;
	public boolean intop10 = false;
	public String name;
	public int place = 0;
	
		
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	

}
