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
	public boolean isIntop10() {
		return intop10;
	}
	public void setIntop10(boolean intop10) {
		this.intop10 = intop10;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
	
	

}
