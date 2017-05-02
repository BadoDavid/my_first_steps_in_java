/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package szoftechtutor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

//import szoftechtutor.GUI.DrawPanel;

/**
 *
 * @author Predi
 */
public class GUI {

	private static final long serialVersionUID = 1L;
	private Control ctrl;
	private DrawComponent drawComponent;
	public static Dimension d = new Dimension(1024,768); // Ablak fix m�ret�nek be�ll�t�sa


	GUI(Control c) {
		//super("SzoftechTutor");
		ctrl = c;
		//setSize(500, 350);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLayout(null);

		JFrame window = new JFrame(); // Ablak l�trehoz�sa
		window.setTitle("Faltoro"); // Ablak neve
		window.setSize(d); // Ablak m�rete
		window.setMaximumSize(d); // Ablak ne legyen �tm�retezhet�
		window.setMinimumSize(d);
        window.setVisible(true); // Ablak l�that�v� t�tele
        window.setLocationRelativeTo(null); // Ablak elhelyez�se a k�pery� k�zep�n
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // mem�ria takar�t�s bez�r�skor
        
        drawComponent = new DrawComponent();
        window.add(drawComponent); // hozz�ad�sa az ablakhoz
        
        window.addMouseMotionListener(ctrl); // eg�r m�k�dtet�se
        window.addMouseListener(ctrl);
        window.addKeyListener(ctrl);
		
        JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("Start");
		
		JMenuItem menuItem = new JMenuItem("Single Player");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.click=true;
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Client");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.startClient();
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Server");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.startServer();
			}
		});
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		window.setJMenuBar(menuBar);
		
        Timer timer = new Timer(ctrl.jatek_sebessege, ctrl); // ciklikus lefut�s
        timer.start();
        
        palyafelepites(ctrl.palya);
        
        drawComponent.repaint();
	}
	
	public static void palyafelepites(int palya){
		// T�gl�k �llapot�t jelz� 2D t�mb felt�lt�se 
		        
		        for(int i = 0; i<5; ++i){ // El�sz�r 0-ba �ll�tjuk az �sszes elemet
		        	for(int j = 0; j<6; ++j) {	
		        		Control.destroyed[i][j] = 0;	
		        	}
		        }       
		        
		        switch (palya){
		        	case 1:
		        		for(int i = 0; i<5; ++i){
		                	for(int j = 0; j<6; ++j) {
		                		Control.destroyed[i][j] = 1;
		                	}
		                }
		        		break;
		        	case 2:
		        		for(int i = 0; i<5; ++i){
		                	for(int j = 0; j<6; ++j) {
		                			if(i==j || (i==3 && j==5)) {
		                				Control.destroyed[i][j] = 2;
		                			}
		                			else{
		                				Control.destroyed[i][j] = 1;
		                			}
		                	}
		                }
		        		break;
		        	case 3:
		        		for(int i = 0; i<5; ++i){
		                	for(int j = 0; j<6; ++j) {
		                			if((i==2) && (j==2 || j==3)) {
		                				Control.destroyed[i][j] = 2;
		                			}
		                			else if(i==0 || j==0 || i==4 || j==5){
		                				Control.destroyed[i][j] = 1;
		                			}
		                	}
		                }        		
		        		break;
		        	case 4:
		        		for(int i = 0; i<5; ++i){
		                	for(int j = 0; j<6; ++j) {
		                			if(j==5) {
		                				Control.destroyed[i][j] = 2;
		                			}
		                			else if(j==0 || i==0 || i==2 || i==4){
		                				Control.destroyed[i][j] = 1;
		                			}
		                	}
		                }  
		        		break;
		        	case 5:
		        		for(int i = 0; i<5; ++i){
		                	for(int j = 0; j<6; ++j) {
		                			if(j>=3) {
		                				Control.destroyed[i][j] = 2;
		                			}
		                			else if(j==0 && (i<1 || i>3)){
		                				Control.destroyed[i][j] = 1;
		                			}
		                			else if(j==1 && i!=2){
		                				Control.destroyed[i][j] = 1;
		                			}
		                			else if(j==2 || j==3){
		                				Control.destroyed[i][j] = 1;
		                			}
		                			
		                	}
		                }  
		        		break;
		        	default:
		        		// nemj����
		        		break;
		        }
			}
	
	void clearcheck(){
	for(int i = 0; i<5; ++i){
    	for(int j = 0; j<6; ++j) {
    		int cle=0;
    		if (Control.destroyed[i][j]==1||Control.destroyed[i][j]==2){
    			cle=1;}
    			if (cle==0)
    				Control.palya++;  	
    				
    		
    	}
	}
	}
	
	void repaint() {
		//drawPanel.points.add(p);
		drawComponent.repaint();
	}

	
	private class DrawComponent extends JComponent {

		private static final long serialVersionUID = 1L;
		//private ArrayList<Point> points = new ArrayList<Point>();
		
		@Override
	    protected void paintComponent(Graphics graphics) {
			super.paintComponent(graphics);
			
			if (ctrl.lives == 0)
				{
				ctrl.gameover=true;
				graphics.setColor(Color.red);
				graphics.setFont(new Font("serif", Font.BOLD, 50));
				graphics.drawString("GAME OVER", 350, 300);
				graphics.setFont(new Font("serif", Font.BOLD, 40));
				graphics.drawString("Your score:  " +ctrl.score, 380, 500);
				}
			
			else {
			// labda
				graphics.setColor(Color.cyan); // labda sz�n�nek bequ�ll�t�sa
				graphics.fillOval(ctrl.labda_poz_x, ctrl.labda_poz_y, ctrl.labda_size, ctrl.labda_size);
	        
	        // �t� be�ll�t�sai
				graphics.setColor(Color.blue); // �t� sz�n�nek be�ll�t�sa
	        
	        if(ctrl.uto_poz>852){ // �t� mozg�s�nak korl�toz�sa
	        	ctrl.uto_poz=852;
	        }
	        
	        if(ctrl.uto_poz<10){ // �t� mozg�s�nak korl�toz�sa
	        	ctrl.uto_poz=10;
	        }
	        
	        
	        graphics.fillRect(ctrl.uto_poz, 668, ctrl.uto_size_x, ctrl.uto_size_y);
	        
	        //pontoz�s
	        graphics.setColor(Color.black);
	        graphics.setFont(new Font("serif", Font.BOLD, 50));
	        graphics.drawString(""+ctrl.score, 600, 40);
	        
	        //�let
	        graphics.setColor(Color.black);
	        graphics.setFont(new Font("serif", Font.BOLD, 50));
	        graphics.drawString(""+ctrl.lives, 400, 40);
	        
	        // t�gl�k
	        // j-> sorok sz�ma    i-> oszlopok sz�ma
	        for(int i = 0; i<5; ++i){
	        	for(int j = 0; j<6; ++j) {
	        		if(Control.destroyed[i][j] > 0){
	        			
	        			// Az egy �let� t�gl�k feket�k
	        			if(Control.destroyed[i][j] == 1){
	        				graphics.setColor(Color.black);
	        				graphics.fillRect((ctrl.tegla_szelesseg+ctrl.tegla_tavolsag_x)*i+ctrl.tegla_eltolas_x,(ctrl.tegla_magassag+ctrl.tegla_tavolsag_y)*j+ctrl.tegla_eltolas_y,ctrl.tegla_szelesseg,ctrl.tegla_magassag);
	        			}
	        			
	        			// A k�t �let� t�gl�k pirosak
	        			if(Control.destroyed[i][j] == 2){
	        				graphics.setColor(Color.red);
	        				graphics.fillRect((ctrl.tegla_szelesseg+ctrl.tegla_tavolsag_x)*i+ctrl.tegla_eltolas_x,(ctrl.tegla_magassag+ctrl.tegla_tavolsag_y)*j+ctrl.tegla_eltolas_y,ctrl.tegla_szelesseg,ctrl.tegla_magassag);
	        			}
	        		}
	        	}
	        }
			}
			}
			
	}
	
}
