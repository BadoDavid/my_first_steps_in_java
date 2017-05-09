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
		x = poz_x+(size/2); // labda középpontjának x pozíciója
		y = poz_y+(size/2); // labda középpontjának y pozíciója
		r = size/2; // labda sugara
	}
	
	void setBall(){
		x = poz_x+(size/2); // labda középpontjának x pozíciója
		y = poz_y+(size/2); // labda középpontjának y pozíciója
		r = size/2; // labda sugara
	}
	/*
	void setDefaults(){
		sebesseg_x = 0;
		sebesseg_y = 0;
		poz_x = 480;
		poz_y = 640;
		public int size = 24;
		public int x; 
		public int y;
		public int r;
	}
	*/
	void startBall(int x, int y){
		sebesseg_x = x;
		sebesseg_y = y;
	}
	
	void BallMove(int poz, boolean GameStopped)
	{
		if (GameStopped == true)
			{
			poz_x = poz+60;
			poz_y = 640;
			}
		else
			{
			poz_x = poz_x + sebesseg_x;
			poz_y = poz_y + sebesseg_y;
			}
		
	}
	
	boolean BallFallen(){
		if(poz_y > 680){
			return true;
		}
		else return false;
	}
	
	
	void checkBorder(){
		// bal fal
		if(poz_x < 10){
			sebesseg_x = -1*sebesseg_x;
		}
		// jobb fal
		if(poz_x > 970){
			sebesseg_x = -1*sebesseg_x;
		}
		// tetõ
		if(poz_y < 10){
			sebesseg_y = -1*sebesseg_y;
		}
	}
	
	void BallCatched(Bat uto){
		if((poz_y == 645) && (poz_x >= (uto.poz-14)) && (poz_x <= (uto.poz+uto.size_x+14))){
	        if (poz_x < (uto.poz + 30)) {
	        	sebesseg_x = -1;
	        	sebesseg_y = -1;
	        }
	
	        if (poz_x >= (uto.poz + 30) && poz_x < (uto.poz + 60)) {
	        	sebesseg_x = -1;
	        	sebesseg_y = -1 * sebesseg_y;
	        }
	
	        if (poz_x >= (uto.poz + 60) && poz_x < (uto.poz + 90)) {
	        	sebesseg_x = 0;
	        	sebesseg_y = -1;
	        }
	
	        if (poz_x >= (uto.poz + 90) && poz_x < (uto.poz + 120)) {
	        	sebesseg_x = 1;
	        	sebesseg_y = -1*sebesseg_y;
	        }
	
	        if (poz_x > (uto.poz + 120)) {
	        	sebesseg_x = 1;
	        	sebesseg_y = -1;
	        }
		}
	}
		
}
