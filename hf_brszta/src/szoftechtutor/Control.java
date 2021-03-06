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
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import org.omg.CosNaming.IstringHelper;


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
	public boolean GamePaused = false;
	public boolean GameFinished = false;
	public boolean TimerChanged = false;
	public int eltelt_ido = 0;

	public Top10 listTop10;
	
	public int sebx; 
	public int seby;

	
	private GUI gui;
	private Network net = null;
	private int opponentScore = -10;
	
	public Control() {
		super();
		labda = new Ball();
		uto = new Bat();
		jatekos = new Player();
		palya = new Wall();
		listTop10 = new Top10();
	}
	

	void setGUI(GUI g) {
		gui = g;
	}
	
	void startServer() {
	if (net != null)
		net.disconnect();
	net = new SerialServer(this);
	//net.connect("192.168.2.100");
	net.connect("localhost");
	}
	
	void startClient() {
	if (net != null)
		net.disconnect();
	net = new SerialClient(this);
	//net.connect("192.168.2.100");
	net.connect("localhost");
	}	
	
	void startNetworkGame(){
		net.send(-10);
	}
	
	void statsReceived(int p) {
		opponentScore = p;
		System.out.println(p);
		if (isNetworkGame()){
			if ( opponentScore > jatekos.getScore())
				setNetworkMessage("YOU LOST!");
			else if ( opponentScore < jatekos.getScore())
				setNetworkMessage("YOU WON!");
			else setNetworkMessage("DRAW!");
			net.disconnect();
			click=false;
		}
	}
	
	boolean networkGame = false;
	String networkMessage;
	
	public boolean isNetworkGame() {
		return networkGame;
	}


	public void setNetworkGame(boolean networkGame) {
		this.networkGame = networkGame;
	}


	public String getNetworkMessage() {
		return networkMessage;
	}


	public void setNetworkMessage(String networkMessage) {
		this.networkMessage = networkMessage;
	}


	void startGame(){
		if (net != null) {
			startNetworkGame();
			setNetworkGame(false);
		}
		labda.startBall(0, 1);
		//labda.sebesseg_x = 0;
		//labda.sebesseg_y = 1;
		gui.repaint();
		click=false;
		setGameStopped(false);
		setGameFinished(false);
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
			f.format("%d %d %d %d %d %s", jatekos.score, jatekos.lives, eltelt_ido, palya.palya, jatek_sebessege, wall);
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
			eltelt_ido = sc.nextInt();
			palya.palya = sc.nextInt();
			jatek_sebessege = sc.nextInt();
			TimerChanged=true;
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

	
	
	boolean isGameOver(){
		if (jatekos.getLives() == 0){
			gameover=true;
			return true;
		} 
		else {
			return false;
		}	
	}
	boolean isGamePaused() {
		return GamePaused;
	}
	
	void CopySpeeds()
	{
	sebx=labda.sebesseg_x;
	seby=labda.sebesseg_y;
	}
	
	void setGamePaused(boolean a){
		if (labda.sebesseg_x != 0 || labda.sebesseg_y != 0)
			CopySpeeds();
		GamePaused = a;
		if (a == true)
		{
			labda.sebesseg_x=0;
			labda.sebesseg_y=0;
		}
		else
		{	
			labda.sebesseg_x=sebx;
			labda.sebesseg_y=seby;
		}
	}
	
	boolean isGameFinished() {
		return GameFinished;
	}

	void setGameFinished(boolean b) {
		GameFinished = b;
	}
	
	boolean isGameStopped() {
		return GameStopped;
	}

	void setGameStopped(boolean b) {
		GameStopped = b;
	}
	

	void gameRestart(boolean decLives)
	{
			uto.poz = 426;
			labda.poz_x = 480;
			labda.poz_y = 640;
			labda.sebesseg_y = 0;
			labda.sebesseg_x = 0;
			if (jatekos.lives>0 && decLives){
				jatekos.lives--;  //-1 �let
			//jatek_sebessege = 5;
			//timer.setDelay(jatek_sebessege); itt k�ne �ll�tani a gui-ban l�v� timer k�sleltet�s�t
				}
			click=true;
			setGameStopped(true);
	}
	
	//T�gl�k kezel�se
	void HandleBricks(){
		for(int i = 0; i<5; ++i){
        	for(int j = 0; j<6; ++j) {
        		if(Wall.destroyed[i][j]>0){	
   				
        			// t�gla bal oldal�t �rte
        			if((labda.x==(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x-labda.r) && (labda.y>(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y-labda.r-1) && (labda.y<(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y+palya.tegla_magassag+labda.r+1) && (labda.sebesseg_x>0)){
        				Wall.destroyed[i][j]--;
        				jatekos.setScore(jatekos.getScore()+jatek_sebessege);
        				labda.sebesseg_x = -1*labda.sebesseg_x;
        			}
        		
        			// t�gla jobb oldal�t �rte
        			if((labda.x==(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x+palya.tegla_szelesseg+labda.r) && (labda.y>(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y-labda.r-1) && (labda.y<(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y+palya.tegla_magassag+labda.r+1) && (labda.sebesseg_x<0)){
        				Wall.destroyed[i][j]--;
        				jatekos.setScore(jatekos.getScore()+jatek_sebessege);
        				labda.sebesseg_x = -1*labda.sebesseg_x;
        			}
        		
        			// t�gla fels� oldal�t �rte
        			if((labda.y==(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y-labda.r) && (labda.x>(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x-labda.r) && (labda.x<(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x+labda.r+palya.tegla_szelesseg) && (labda.sebesseg_y>0)){
        				Wall.destroyed[i][j]--;
        				jatekos.setScore(jatekos.getScore()+jatek_sebessege);
        				labda.sebesseg_y = -1*labda.sebesseg_y;
        			}
        		
        			// t�gla als� oldal�t �rte
        			if((labda.y==(palya.tegla_magassag+palya.tegla_tavolsag_y)*j+palya.tegla_eltolas_y+palya.tegla_magassag+labda.r) && (labda.x>(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x-labda.r) && (labda.x<(palya.tegla_szelesseg+palya.tegla_tavolsag_x)*i+palya.tegla_eltolas_x+palya.tegla_szelesseg+labda.r) && (labda.sebesseg_y<0)){
        				Wall.destroyed[i][j]--;
        				jatekos.setScore(jatekos.getScore()+jatek_sebessege);
        				labda.sebesseg_y = -1*labda.sebesseg_y;
        			} 		
        		}
        	}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		labda.BallMove(uto.getPoz(), GameStopped);
		
		labda.setBall();
		
		if(labda.BallFallen()){
			gameRestart(true); // leesett a labda
		}
		
		// �t�t �rt a labda
		labda.BallCatched(uto);
		
		// ablak sz�l�t �rte a labda
		labda.checkBorder();
		
		//T�gl�k kezel�se a labda mozg�s�nak f�ggv�ny�ben
		HandleBricks();		
		
		// Minden t�gla el van t�vol�tva?
		clear=palya.IsEverythingDestroyed();
		
		//Network 
		if ((net != null) && !isNetworkGame()){
			if(isGameOver()|| clear==true){
				net.send(jatekos.getScore());
				setNetworkGame(true);
				if (opponentScore == (-10)){
					setNetworkMessage("Waiting for the opponent finish the game.");
					click=false;
				} else {
					if ( opponentScore > jatekos.getScore())
						setNetworkMessage("YOU LOST!");
					else if ( opponentScore < jatekos.getScore())
						setNetworkMessage("YOU WON!");
					else setNetworkMessage("DRAW!");
					net.disconnect();
					click=false;
				}
				clear=false;
			}
		}

		
		if (clear==true)  //szintet l�pt�nk
		{
			if(palya.palya<5){
				palya.palya++;
				palya.BuildWall();
				gameRestart(false);
			}
			else{
				setGameFinished(true);
			}
			
		}
		//TOP10
		if ((isGameOver()|| isGameFinished()) && !isNetworkGame()){
			
			if (!jatekos.intop10){
				if (listTop10.insertPlayer(jatekos)){
					jatekos.setName(gui.getPlayerName());
					listTop10.saveTop10(jatekos);
				}
				jatekos.intop10=true;
			}
			
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
			if(isGameStopped()){
				loadGame();
				startGameEn();
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_P){
			if(isGamePaused())
				setGamePaused(false);
			else
			{
				setGamePaused(true);
			}
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
		jatekos.name="";
		jatekos.intop10=false;
		setGameStopped(true);
		palya.BuildWall();
		eltelt_ido=0;
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
		
		else if(GameFinished==true)
		{
			gameRestart(false);
			setDefaults();
			//startGame();
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
