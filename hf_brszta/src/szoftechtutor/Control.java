package szoftechtutor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class Control implements ActionListener, MouseListener, MouseMotionListener, KeyListener   {
	
	//public static Dimension dimension = new Dimension(1024,768); // Ablak fix m�ret�nek be�ll�t�sa
	
	// V�ltoz�k inicializ�l�sa 
	public int state = 0;
	public int jatek_sebessege = 5; // 1...5
	/*
	private int labda.sebesseg_x = 0;
	private int labda.sebesseg_y = 0;
	private int labda.poz_x = 480;
	private int labda.poz_y = 640;
	private int labda.size = 24;
	private int labda.x = labda.poz_x+(labda.size/2); // labda k�z�ppontj�nak x poz�ci�ja
	private int labda.y = labda.poz_y+(labda.size/2); // labda k�z�ppontj�nak y poz�ci�ja
	private int labda.r = labda.size/2; // labda sugara
	*/
	public Ball labda;
	/*
	private int uto.size_x = 150;
	private int uto.size_y = 20;
	private int uto.poz = 426;
	*/
	public Bat uto;
	/*
	public int palya.tegla_eltolas_x = 50;
	public int palya.tegla_eltolas_y = 20;
	public int palya.tegla_tavolsag_x = 130; //120
	public int palya.tegla_tavolsag_y = 30; // 20
	public int palya.tegla_szelesseg = 80;
	public int palya.tegla_magassag = 40;
	public int palya = 1; // 1...5
	*/
	public Wall palya;
	/*
	public int jatekos.score = 0;
	public int jatekos.lives = 3;
	*/
	
	public Player jatekos;
	public boolean clear = true;
	public boolean gameover = false;
	public boolean click = false; //true;
	public boolean GameStopped = true;
	public int eltelt_ido = 0;
	private GUI gui;
	private Network net = null;
	
	public Control() {
		super();
		labda = new Ball();
		uto = new Bat();
		jatekos = new Player();
		palya = new Wall();
	}

	void setGUI(GUI g) {
		gui = g;
	}
	
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
	
	void start_network_game(){
		if (gameover==true)
		{
			jatekos.lives=3;
			click=true;
			
		}
		if (click==true)
		{	
			if(gameover==false)
			{
				labda.sebesseg_x = 0;
				labda.sebesseg_y = 1;
				gui.repaint();
				click=false;
			}
			else
				gameover=false;
		}
	}
	
	void startGame(){
		labda.sebesseg_x = 0;
		labda.sebesseg_y = 1;
		gui.repaint();
		click=false;
		GameStopped=false;
	}
	
	void startGameEn(){
		click=true;
	}
	
	void saveGame(){
		String wall = "";
		
		for(int i = 0; i<5; ++i){
        	for(int j = 0; j<6; ++j) {
        		wall += " " + Wall.destroyed[i][j];
        	}
        }   
		try{
			Formatter f = new Formatter("save.txt");
			System.out.println("Open");
			f.format("%d %d %s", jatekos.score, jatekos.lives, wall);
			f.close();
		}
		catch (Exception e){
			System.out.println("Error");
		}
	}
	
	void loadGame(){
		int wall;
		int i = 0;
		int j = 0;
		/*
		for(int i = 0; i<5; ++i){
        	for(int j = 0; j<6; ++j) {
        		wall += Control.Wall.destroyed[i][j];
        	}
        }   
        */
		try{
			File x = new File("save.txt");
			System.out.println("Read");
			Scanner sc = new Scanner(x);
			jatekos.score = sc.nextInt();
			jatekos.lives = sc.nextInt();
			while(sc.hasNext()){
				wall = sc.nextInt();
				Wall.destroyed[i][j] = wall;
				j++;
				if(j == 6){
					i++;
					j=0;
				}
				//System.out.print(wall);
			}
			//f.format("%d %d %s", jatekos.score, jatekos.lives, wall);
			sc.close();
		}
		catch (FileNotFoundException e){
			System.out.println("Error");
		}
	}

	
	//sta
	/*
	@Override
    protected void paintComponent(Graphics faltoro) {
		
		if (jatekos.lives == 0)
			{
			gameover=true;
			faltoro.setColor(Color.red);
        	faltoro.setFont(new Font("serif", Font.BOLD, 50));
        	faltoro.drawString("GAME OVER", 350, 300);
        	faltoro.setFont(new Font("serif", Font.BOLD, 40));
        	faltoro.drawString("Your jatekos.score:  " +jatekos.score, 380, 500);
			}
		
		else {
		// labda
		faltoro.setColor(Color.cyan); // labda sz�n�nek bequ�ll�t�sa
        faltoro.fillOval(labda.poz_x, labda.poz_y, labda.size, labda.size);
        
        // �t� be�ll�t�sai
        faltoro.setColor(Color.blue); // �t� sz�n�nek be�ll�t�sa
        
        if(uto.poz>852){ // �t� mozg�s�nak korl�toz�sa
        	uto.poz=852;
        }
        
        if(uto.poz<10){ // �t� mozg�s�nak korl�toz�sa
        	uto.poz=10;
        }
        
        
        faltoro.fillRect(uto.poz, 668, uto.size_x, uto.size_y);
        
        //pontoz�s
        faltoro.setColor(Color.black);
        faltoro.setFont(new Font("serif", Font.BOLD, 50));
        faltoro.drawString(""+jatekos.score, 600, 40);
        
        //�let
        faltoro.setColor(Color.black);
        faltoro.setFont(new Font("serif", Font.BOLD, 50));
        faltoro.drawString(""+jatekos.lives, 400, 40);
        
        // t�gl�k
        // j-> sorok sz�ma    i-> oszlopok sz�ma
        for(int i = 0; i<5; ++i){
        	for(int j = 0; j<6; ++j) {
        		if(Wall.destroyed[i][j] > 0){
        			
        			// Az egy �let� t�gl�k feket�k
        			if(Wall.destroyed[i][j] == 1){
        				faltoro.setColor(Color.black);
        				faltoro.fillRect((palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x,(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y,palya.tegla_szelesseg,palya.tegla_magassag);
        			}
        			
        			// A k�t �let� t�gl�k pirosak
        			if(Wall.destroyed[i][j] == 2){
        				faltoro.setColor(Color.red);
        				faltoro.fillRect((palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x,(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y,palya.tegla_szelesseg,palya.tegla_magassag);
        			}
        		}
        	}
        }
		}
		}
	*/
	
	void BallMove()
	{
		if (GameStopped == true){
			if(uto.poz>852){ // labda mozg�s�nak korl�toz�sa
				labda.poz_x=912;
	        }
			else{
			labda.poz_x = uto.poz+60;
			labda.poz_y = 640;
			
			}
		}
		else{
				labda.poz_x = labda.poz_x + labda.sebesseg_x;
				labda.poz_y = labda.poz_y + labda.sebesseg_y;
		}
		
	}
	
	void BallFallen()
	{
			uto.poz = 426;
			labda.poz_x = 480;
			labda.poz_y = 640;
			labda.sebesseg_y = 0;
			labda.sebesseg_x = 0;
			if (jatekos.lives>0){
				jatekos.lives--;  //-1 �let
			jatek_sebessege = 5;
				}
			click=true;
			GameStopped=true;
	}
	
	// �t�t �rt a labda
	void BallCatched(){
        if (labda.poz_x < (uto.poz + 30)) {
        	labda.sebesseg_x = -1;
        	labda.sebesseg_y = -1;
        }

        if (labda.poz_x >= (uto.poz + 30) && labda.poz_x < (uto.poz + 60)) {
        	labda.sebesseg_x = -1;
        	labda.sebesseg_y = -1 * labda.sebesseg_y;
        }

        if (labda.poz_x >= (uto.poz + 60) && labda.poz_x < (uto.poz + 90)) {
        	labda.sebesseg_x = 0;
        	labda.sebesseg_y = -1;
        }

        if (labda.poz_x >= (uto.poz + 90) && labda.poz_x < (uto.poz + 120)) {
        	labda.sebesseg_x = 1;
        	labda.sebesseg_y = -1*labda.sebesseg_y;
        }

        if (labda.poz_x > (uto.poz + 120)) {
        	labda.sebesseg_x = 1;
        	labda.sebesseg_y = -1;
        }
	}
	
	void LeftBorderReached(){
		labda.sebesseg_x = -1*labda.sebesseg_x;
	}
				
	void RightBorderReached(){
		labda.sebesseg_x = -1*labda.sebesseg_x;
	}
	
	void TopBorderReached(){
		labda.sebesseg_y = -1*labda.sebesseg_y;
	}
	
	
	void BottomBorderReached(){
		labda.sebesseg_y = -1*labda.sebesseg_y;
	}
	
	void ScoreIncrease()
	{
		jatekos.score+=(6-jatek_sebessege);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		BallMove();
		labda.x = labda.poz_x+(labda.size/2); // labda k�z�ppontj�nak x poz�ci�ja
		labda.y = labda.poz_y+(labda.size/2); // labda k�z�ppontj�nak y poz�ci�ja
		labda.r = labda.size/2; // labda sugara
		
		if(labda.poz_y > 680){
			BallFallen(); // leesett a labda
		}
		
		// �t�t �rt a labda
		if((labda.poz_y == 645) && (labda.poz_x >= (uto.poz-14)) && (labda.poz_x <= (uto.poz+uto.size_x+14))){
			BallCatched();
		}
		
		// bal falat �rt a labda
		if(labda.poz_x < 10){
			LeftBorderReached();
		}
		
		// jobb falat �rt a labda
		if(labda.poz_x > 970){
			RightBorderReached();
		}
		
		// tet�t �rt a labda
		if(labda.poz_y < 10){
			TopBorderReached();
		}
		
		
		// wall -> palya
		//T�gl�k kezel�se
		
		//palya.HandleBricks(); EZT K�NE HASZN�LNI AZ EZALATT L�V� K�D HELYETT
		
		for(int i = 0; i<5; ++i){
        	for(int j = 0; j<6; ++j) {
        		if(Wall.destroyed[i][j]>0){	
   				
        			// t�gla bal oldal�t �rte
        			if((labda.x==(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x-labda.r) && (labda.y>(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y-labda.r-1) && (labda.y<(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y+palya.tegla_magassag+labda.r+1) && (labda.sebesseg_x>0)){
        				Wall.destroyed[i][j]--;
        				ScoreIncrease();
        				RightBorderReached();
        			}
        		
        			// t�gla jobb oldal�t �rte
        			if((labda.x==(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x+palya.tegla_szelesseg+labda.r) && (labda.y>(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y-labda.r-1) && (labda.y<(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y+palya.tegla_magassag+labda.r+1) && (labda.sebesseg_x<0)){
        				Wall.destroyed[i][j]--;
        				ScoreIncrease();
        				LeftBorderReached();
        			}
        		
        			// t�gla fels� oldal�t �rte
        			if((labda.y==(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y-labda.r) && (labda.x>(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x-labda.r) && (labda.x<(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x+labda.r+palya.tegla_szelesseg) && (labda.sebesseg_y>0)){
        				Wall.destroyed[i][j]--;
        				ScoreIncrease();
        				BottomBorderReached();
        			}
        		
        			// t�gla als� oldal�t �rte
        			if((labda.y==(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y+palya.tegla_magassag+labda.r) && (labda.x>(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x-labda.r) && (labda.x<(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x+palya.tegla_szelesseg+labda.r) && (labda.sebesseg_y<0)){
        				Wall.destroyed[i][j]--;
        				ScoreIncrease();
        				TopBorderReached();
        			} 		
        		}
        	}
		}
		
		// Annak ellen�rz�se, hogy minden t�gla lebont�sra ker�lt-e
		clear=palya.IsEverythingDestroyed();
		
	if (clear==true)  //szintet l�pt�nk
	{
		
		palya.palya++;
		//jatek_sebessege = 5;
		//timer.setDelay(jatek_sebessege); itt k�ne �ll�tani a gui-ban l�v� timer k�sleltet�s�t
		GUI.palyafelepites(palya);
		uto.poz = 426;
		labda.poz_x = 480;
		labda.poz_y = 640;
		labda.sebesseg_y = 0;
		labda.sebesseg_x = 0;
		click=true;
		GameStopped=true;
	}
	
		gui.repaint(); // k�perny� �jrarajzol�sa
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
			uto.poz+=10;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
			uto.poz-=10;
		}
		
		if (arg0.getKeyCode() == KeyEvent.VK_S){
			saveGame();
		}
		if (arg0.getKeyCode() == KeyEvent.VK_L){
			loadGame();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		/*
		if (arg0.getKeyCode() == KeyEvent.VK_S){
			saveGame();
		}
		*/
	}
	
	void setDefaults(){
		jatekos.lives=3;
		jatekos.score=0;
		palya.palya=1;
		GameStopped=true;
		GUI.palyafelepites(palya);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (gameover==true)
		{	
			setDefaults();
			/*
			jatekos.lives=3;
			jatekos.score=0;
			palya.palya=1;
			GUI.palyafelepites(palya);
			click=true;
			*/
		}
		if (click==true)
		{	
			if(gameover==false)
			{
				startGame();
			}
			else
				gameover=false;
		}

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		uto.setPoz(arg0.getX()); // eg�r x poz�ci�j�nak beolvas�sa
	
		//gui.repaint(); // k�perny� �jrarajzol�sa
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
        			faltoro.Wall.destroyed[i][j] = 1;
        	}
        }
		break;
	case 2:
		for(int i = 0; i<5; ++i){
        	for(int j = 0; j<6; ++j) {
        			if(i==j || (i==3 && j==5)) {
        				faltoro.Wall.destroyed[i][j] = 2;
        			}
        			else{
        				faltoro.Wall.destroyed[i][j] = 1;
        			}
        	}
		}
		break;
	case 3:
		for(int i = 0; i<5; ++i){
        	for(int j = 0; j<6; ++j) {
        			if((i==2) && (j==2 || j==3)) {
        				faltoro.Wall.destroyed[i][j] = 2;
        			}
        			else if(i==0 || j==0 || i==4 || j==5){
        				faltoro.Wall.destroyed[i][j] = 1;
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
