/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package szoftechtutor;

import java.awt.Point;

import javax.swing.Timer;

/**
 *
 * @author Predi
 */



class Control {

	private GUI gui;
	private Network net = null;
	
	
    
	Control() {
		
	}

	public static void palyafelepites(int palya){
// Téglák állapotát jelzõ 2D tömb feltöltése 
        
        for(int i = 0; i<5; ++i){ // Elõször 0-ba állítjuk az összes elemet
        	for(int j = 0; j<6; ++j) {	
        		faltoro.destroyed[i][j] = 0;	
        	}
        }       
        
        switch (palya){
        	case 1:
        		for(int i = 0; i<5; ++i){
                	for(int j = 0; j<6; ++j) {
                			faltoro.destroyed[i][j] = 1;
                	}
                }
        		break;
        	case 2:
        		for(int i = 0; i<5; ++i){
                	for(int j = 0; j<6; ++j) {
                			if(i==j || (i==3 && j==5)) {
                				faltoro.destroyed[i][j] = 2;
                			}
                			else{
                				faltoro.destroyed[i][j] = 1;
                			}
                	}
                }
        		break;
        	case 3:
        		for(int i = 0; i<5; ++i){
                	for(int j = 0; j<6; ++j) {
                			if((i==2) && (j==2 || j==3)) {
                				faltoro.destroyed[i][j] = 2;
                			}
                			else if(i==0 || j==0 || i==4 || j==5){
                				faltoro.destroyed[i][j] = 1;
                			}
                	}
                }        		
        		break;
        	case 4:
        		for(int i = 0; i<5; ++i){
                	for(int j = 0; j<6; ++j) {
                			if(j==5) {
                				faltoro.destroyed[i][j] = 2;
                			}
                			else if(j==0 || i==0 || i==2 || i==4){
                				faltoro.destroyed[i][j] = 1;
                			}
                	}
                }  
        		break;
        	case 5:
        		for(int i = 0; i<5; ++i){
                	for(int j = 0; j<6; ++j) {
                			if(j>=3) {
                				faltoro.destroyed[i][j] = 2;
                			}
                			else if(j==0 && (i<1 || i>3)){
                				faltoro.destroyed[i][j] = 1;
                			}
                			else if(j==1 && i!=2){
                				faltoro.destroyed[i][j] = 1;
                			}
                			else if(j==2 || j==3){
                				faltoro.destroyed[i][j] = 1;
                			}
                			
                	}
                }  
        		break;
        	default:
        		// nemjóóóó
        		break;
        }
	}
	
	
	void setGUI(GUI g) {
		gui = g;
	}

	void startServer() {
		if (net != null)
			net.disconnect();
		//net = new SerialServer(this);
		net.connect("localhost");
	}

	void startClient() {
		if (net != null)
			net.disconnect();
		//net = new SerialClient(this);
		net.connect("localhost");
	}

	void sendClick(Point p) {
		gui.addPoint(p); //for drawing locally
		/*
		if (net == null)
			return;
		net.send(p);
		*/
	}

	void clickReceived(Point p) {
		/*
		if (gui == null)
			return;
		gui.addPoint(p);
		*/
	}
}
