package szoftechtutor;

public class Ball {
	
	public int sebesseg_x = 0;
	public int sebesseg_y = 0;
	public int poz_x = 480;
	public int poz_y = 640;
	public int size = 24;
	public int x; 
	public int y;
	public int r;
	
	public Ball() {
		super();
		x = poz_x+(size/2); // labda k�z�ppontj�nak x poz�ci�ja
		y = poz_y+(size/2); // labda k�z�ppontj�nak y poz�ci�ja
		r = size/2; // labda sugara
	}
		
}
