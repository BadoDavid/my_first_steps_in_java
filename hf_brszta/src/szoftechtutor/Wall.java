package szoftechtutor;

public class Wall {
	
	public int tegla_eltolas_x = 50;
	public int tegla_eltolas_y = 20;
	public int tegla_tavolsag_x = 130; //120
	public int tegla_tavolsag_y = 30; // 20
	public int tegla_szelesseg = 80;
	public int tegla_magassag = 40;
	public int palya = 1; // 1...5
	
	public static int[][] destroyed = new int[5][6];
	
	void HandleBricks()
	{
		
	}
	
	boolean IsEverythingDestroyed(){
		boolean clear=true;
		for(int i = 0; i<5; ++i){
        	for(int j = 0; j<6; ++j) {
        		if(Wall.destroyed[i][j]>0){	
        			clear=false;
          		}
        	}	
        }
		return clear;
	}
	
}
