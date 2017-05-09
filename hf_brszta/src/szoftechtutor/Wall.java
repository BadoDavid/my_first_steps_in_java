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
	
	boolean IsEverythingDestroyed(){
		boolean clear=true;
		for(int i = 0; i<5; ++i){
        	for(int j = 0; j<6; ++j) {
        		if(destroyed[i][j]>0){	
        			clear=false;
          		}
        	}	
        }
		return clear;
	}
	
	// IDE KÉNE A PÁLYAFELÉPÍTÕ FÜGGVÉNY IS NEM?
	
	void HandleBricks(){
			
		/*
		for(int i = 0; i<5; ++i){
        	for(int j = 0; j<6; ++j) {
        		if(Wall.destroyed[i][j]>0){	
   				
        			// tégla bal oldalát érte
        			if((labda.x==(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x-labda.r) && (labda.y>(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y-labda.r-1) && (labda.y<(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y+palya.tegla_magassag+labda.r+1) && (labda.sebesseg_x>0)){
        				Wall.destroyed[i][j]--;
        				ScoreIncrease(5);
        				RightBorderReached();
        			}
        		
        			// tégla jobb oldalát érte
        			if((labda.x==(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x+palya.tegla_szelesseg+labda.r) && (labda.y>(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y-labda.r-1) && (labda.y<(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y+palya.tegla_magassag+labda.r+1) && (labda.sebesseg_x<0)){
        				Wall.destroyed[i][j]--;
        				ScoreIncrease(5);
        				LeftBorderReached();
        			}
        		
        			// tégla felsõ oldalát érte
        			if((labda.y==(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y-labda.r) && (labda.x>(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x-labda.r) && (labda.x<(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x+labda.r+palya.tegla_szelesseg) && (labda.sebesseg_y>0)){
        				Wall.destroyed[i][j]--;
        				ScoreIncrease(5);
        				BottomBorderReached();
        			}
        		
        			// tégla alsó oldalát érte
        			if((labda.y==(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y+palya.tegla_magassag+labda.r) && (labda.x>(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x-labda.r) && (labda.x<(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x+palya.tegla_szelesseg+labda.r) && (labda.sebesseg_y<0)){
        				Wall.destroyed[i][j]--;
        				ScoreIncrease(5);
        				TopBorderReached();
        			} 		
        		}
        	}
		}*/
	}
	
}
