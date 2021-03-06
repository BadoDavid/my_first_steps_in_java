package szoftechtutor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class faltoro implements ActionListener, MouseListener, MouseMotionListener, KeyListener   {
	
	//public static Dimension dimension = new Dimension(1024,768); // Ablak fix m�ret�nek be�ll�t�sa
	
	// V�ltoz�k inicializ�l�sa 
	public int state = 0;
	//public int jatek_sebessege = 5; // 0...5
	public int labda_sebesseg_x = 0;
	public int labda_sebesseg_y = 0;
	public int labda_poz_x = 480;
	public int labda_poz_y = 640;
	public int labda_size = 24;
	public int labda_x = labda_poz_x+(labda_size/2); // labda k�z�ppontj�nak x poz�ci�ja
	public int labda_y = labda_poz_y+(labda_size/2); // labda k�z�ppontj�nak y poz�ci�ja
	public int labda_r = labda_size/2; // labda sugara
	public int uto_size_x = 150;
	public int uto_size_y = 20;
	public int uto_poz = 426;
	public int tegla_eltolas_x = 50;
	public int tegla_eltolas_y = 20;
	public int tegla_tavolsag_x = 130; //120
	public int tegla_tavolsag_y = 30; // 20
	public int tegla_szelesseg = 80;
	public int tegla_magassag = 40;
	public int palya = 5; // 1...5
	public int score = 0;
	public int lives = 3;
	public boolean gameover = true;
	public static int[][] destroyed = new int[5][6];
	public boolean click = false; //true;
	
	private GUI gui;
	private Network net = null;
	
	void setGUI(GUI g) {
		gui = g;
	}
	/*
	void startServer() {
	if (net != null)
		net.disconnect();
	net = new SerialServer(this);
	net.connect("192.168.0.102");
	}
	
	void startClient() {
	if (net != null)
		net.disconnect();
	net = new SerialClient(this);
	net.connect("192.168.0.102");
	}	
	*/
	void start_game(){
		if (gameover==true)
		{
			lives=3;
			click=true;
			
		}
		if (click==true)
		{	
			if(gameover==false)
			{
				labda_sebesseg_x = 0;
				labda_sebesseg_y = 1;
				gui.repaint();
				click=false;
			}
			else
				gameover=false;
		}
	}

	
	//sta
	/*
	@Override
    protected void paintComponent(Graphics faltoro) {
		
		if (lives == 0)
			{
			gameover=true;
			faltoro.setColor(Color.red);
        	faltoro.setFont(new Font("serif", Font.BOLD, 50));
        	faltoro.drawString("GAME OVER", 350, 300);
        	faltoro.setFont(new Font("serif", Font.BOLD, 40));
        	faltoro.drawString("Your score:  " +score, 380, 500);
			}
		
		else {
		// labda
		faltoro.setColor(Color.cyan); // labda sz�n�nek bequ�ll�t�sa
        faltoro.fillOval(labda_poz_x, labda_poz_y, labda_size, labda_size);
        
        // �t� be�ll�t�sai
        faltoro.setColor(Color.blue); // �t� sz�n�nek be�ll�t�sa
        
        if(uto_poz>852){ // �t� mozg�s�nak korl�toz�sa
        	uto_poz=852;
        }
        
        if(uto_poz<10){ // �t� mozg�s�nak korl�toz�sa
        	uto_poz=10;
        }
        
        
        faltoro.fillRect(uto_poz, 668, uto_size_x, uto_size_y);
        
        //pontoz�s
        faltoro.setColor(Color.black);
        faltoro.setFont(new Font("serif", Font.BOLD, 50));
        faltoro.drawString(""+score, 600, 40);
        
        //�let
        faltoro.setColor(Color.black);
        faltoro.setFont(new Font("serif", Font.BOLD, 50));
        faltoro.drawString(""+lives, 400, 40);
        
        // t�gl�k
        // j-> sorok sz�ma    i-> oszlopok sz�ma
        for(int i = 0; i<5; ++i){
        	for(int j = 0; j<6; ++j) {
        		if(destroyed[i][j] > 0){
        			
        			// Az egy �let� t�gl�k feket�k
        			if(destroyed[i][j] == 1){
        				faltoro.setColor(Color.black);
        				faltoro.fillRect((tegla_szelesseg+tegla_tavolsag_x)*i+tegla_eltolas_x,(tegla_magassag+tegla_tavolsag_y)*j+tegla_eltolas_y,tegla_szelesseg,tegla_magassag);
        			}
        			
        			// A k�t �let� t�gl�k pirosak
        			if(destroyed[i][j] == 2){
        				faltoro.setColor(Color.red);
        				faltoro.fillRect((tegla_szelesseg+tegla_tavolsag_x)*i+tegla_eltolas_x,(tegla_magassag+tegla_tavolsag_y)*j+tegla_eltolas_y,tegla_szelesseg,tegla_magassag);
        			}
        		}
        	}
        }
		}
		}
	*/
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		labda_poz_x = labda_poz_x + labda_sebesseg_x;
		labda_poz_y = labda_poz_y + labda_sebesseg_y;
		labda_x = labda_poz_x+(labda_size/2); // labda k�z�ppontj�nak x poz�ci�ja
		labda_y = labda_poz_y+(labda_size/2); // labda k�z�ppontj�nak y poz�ci�ja
		labda_r = labda_size/2; // labda sugara
		
		// leesett a labda
		if(labda_poz_y > 680){
			uto_poz = 426;
			labda_poz_x = 480;
			labda_poz_y = 640;
			labda_sebesseg_y = 0;
			labda_sebesseg_x = 0;
			if (lives>0){
				lives--;  //-1 �let
			
			}
			click=true;
			
		}
		
		// �t�t �rt a labda
		if((labda_poz_y == 645) && (labda_poz_x >= (uto_poz-14)) && (labda_poz_x <= (uto_poz+uto_size_x+14))){

            if (labda_poz_x < (uto_poz + 30)) {
            	labda_sebesseg_x = -1;
            	labda_sebesseg_y = -1;
            }

            if (labda_poz_x >= (uto_poz + 30) && labda_poz_x < (uto_poz + 60)) {
            	labda_sebesseg_x = -1;
            	labda_sebesseg_y = -1 * labda_sebesseg_y;
            }

            if (labda_poz_x >= (uto_poz + 60) && labda_poz_x < (uto_poz + 90)) {
            	labda_sebesseg_x = 0;
            	labda_sebesseg_y = -1;
            }

            if (labda_poz_x >= (uto_poz + 90) && labda_poz_x < (uto_poz + 120)) {
            	labda_sebesseg_x = 1;
            	labda_sebesseg_y = -1*labda_sebesseg_y;
            }

            if (labda_poz_x > (uto_poz + 120)) {
            	labda_sebesseg_x = 1;
            	labda_sebesseg_y = -1;
            }

			
			//labda_poz_y = labda_poz_y - 2;  // hogy azonnal elt�volodjon az �t�t�l
		}
		
		// bal fal
		if(labda_poz_x < 10){
			labda_sebesseg_x = -1*labda_sebesseg_x;
		}
		
		// jobb fal
		if(labda_poz_x > 970){
			labda_sebesseg_x = -1*labda_sebesseg_x;
		}
		
		// tet�
		if(labda_poz_y < 10){
			labda_sebesseg_y = -1*labda_sebesseg_y;
		}
		
		//T�gl�k kezel�se
		for(int i = 0; i<5; ++i){
        	for(int j = 0; j<6; ++j) {
        		if(destroyed[i][j]>0){	
   				
        			// t�gla bal oldal�t �rte
        			if((labda_x==(tegla_szelesseg+tegla_tavolsag_x)*i+tegla_eltolas_x-labda_r) && (labda_y>(tegla_magassag+tegla_tavolsag_y)*j+tegla_eltolas_y-labda_r-1) && (labda_y<(tegla_magassag+tegla_tavolsag_y)*j+tegla_eltolas_y+tegla_magassag+labda_r+1) && (labda_sebesseg_x>0)){
        				destroyed[i][j]--;
        				score += 5;
        				labda_sebesseg_x = -1*labda_sebesseg_x;
        			}
        		
        			// t�gla jobb oldal�t �rte
        			if((labda_x==(tegla_szelesseg+tegla_tavolsag_x)*i+tegla_eltolas_x+tegla_szelesseg+labda_r) && (labda_y>(tegla_magassag+tegla_tavolsag_y)*j+tegla_eltolas_y-labda_r-1) && (labda_y<(tegla_magassag+tegla_tavolsag_y)*j+tegla_eltolas_y+tegla_magassag+labda_r+1) && (labda_sebesseg_x<0)){
        				destroyed[i][j]--;
        				score += 5;
        				labda_sebesseg_x = -1*labda_sebesseg_x;
        			}
        		
        			// t�gla fels� oldal�t �rte
        			if((labda_y==(tegla_magassag+tegla_tavolsag_y)*j+tegla_eltolas_y-labda_r) && (labda_x>(tegla_szelesseg+tegla_tavolsag_x)*i+tegla_eltolas_x-labda_r) && (labda_x<(tegla_szelesseg+tegla_tavolsag_x)*i+tegla_eltolas_x+labda_r+tegla_szelesseg) && (labda_sebesseg_y>0)){
        				destroyed[i][j]--;
        				score += 5;
        				labda_sebesseg_y = -1*labda_sebesseg_y;
        			}
        		
        			// t�gla als� oldal�t �rte
        			if((labda_y==(tegla_magassag+tegla_tavolsag_y)*j+tegla_eltolas_y+tegla_magassag+labda_r) && (labda_x>(tegla_szelesseg+tegla_tavolsag_x)*i+tegla_eltolas_x-labda_r) && (labda_x<(tegla_szelesseg+tegla_tavolsag_x)*i+tegla_eltolas_x+tegla_szelesseg+labda_r) && (labda_sebesseg_y<0)){
        				destroyed[i][j]--;
        				score += 5;
        				labda_sebesseg_y = -1*labda_sebesseg_y;
        			} 		
        		}
        	}
		}
		
		gui.repaint(); // k�perny� �jrarajzol�sa
	}
	
	private void setFont(Font font) {
		// TODO Auto-generated method stub
		
	}
	private static void setColor(Color red) {
		// TODO Auto-generated method stub
		
	}



	private static void drawString(String string, int i, int j) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
			uto_poz+=10;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
			uto_poz-=10;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (gameover==true)
		{
			lives=3;
			score=0;
			click=true;
		}
		if (click==true)
		{	
			if(gameover==false)
			{
				labda_sebesseg_x = 0;
				labda_sebesseg_y = 1;
				gui.repaint();
				click=false;
			}
			else
				gameover=false;
		}

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		uto_poz = arg0.getX(); // eg�r x poz�ci�j�nak beolvas�sa
	
		gui.repaint(); // k�perny� �jrarajzol�sa
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	
	/*if(gameover==true)
    {
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
		break;
	case 5:
		break;
	default:
		// nemj����
		break;*/
    	}

}
