package szoftechtutor;

public class Wall {
	
	public int tegla_eltolas_x = 50;
	public int tegla_eltolas_y = 55;
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
	
	public void BuildWall(){
		// Téglák állapotát jelzõ 2D tömb feltöltése 
		        //int palya = fal.palya;
		        for(int i = 0; i<5; ++i){ // Elõször 0-ba állítjuk az összes elemet
		        	for(int j = 0; j<6; ++j) {	
		        		destroyed[i][j] = 0;	
		        	}
		        }       
		        
		        switch (palya){
		        	case 1:
		        		for(int i = 0; i<5; ++i){
		                	for(int j = 0; j<6; ++j) {
		                		destroyed[i][j] = 1;
		                	}
		                }
		        		break;
		        	case 2:
		        		for(int i = 0; i<5; ++i){
		                	for(int j = 0; j<6; ++j) {
		                			if(i==j || (i==3 && j==5)) {
		                				destroyed[i][j] = 2;
		                			}
		                			else{
		                				destroyed[i][j] = 1;
		                			}
		                	}
		                }
		        		break;
		        	case 3:
		        		for(int i = 0; i<5; ++i){
		                	for(int j = 0; j<6; ++j) {
		                			if((i==2) && (j==2 || j==3)) {
		                				destroyed[i][j] = 2;
		                			}
		                			else if(i==0 || j==0 || i==4 || j==5){
		                				destroyed[i][j] = 1;
		                			}
		                	}
		                }        		
		        		break;
		        	case 4:
		        		for(int i = 0; i<5; ++i){
		                	for(int j = 0; j<6; ++j) {
		                			if(j==5) {
		                				destroyed[i][j] = 2;
		                			}
		                			else if(j==0 || i==0 || i==2 || i==4){
		                				destroyed[i][j] = 1;
		                			}
		                	}
		                }  
		        		break;
		        	case 5:
		        		for(int i = 0; i<5; ++i){
		                	for(int j = 0; j<6; ++j) {
		                			if(j>=3) {
		                				destroyed[i][j] = 2;
		                			}
		                			else if(j==0 && (i<1 || i>3)){
		                				destroyed[i][j] = 1;
		                			}
		                			else if(j==1 && i!=2){
		                				destroyed[i][j] = 1;
		                			}
		                			else if(j==2 || j==3){
		                				destroyed[i][j] = 1;
		                			}
		                	}
		                }  
		        		break;
		        	default:
		        		
		        		break;
		        }
			}
	
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
