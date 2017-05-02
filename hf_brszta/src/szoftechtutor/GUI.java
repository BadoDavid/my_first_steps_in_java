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
	public static Dimension d = new Dimension(1024,768); // Ablak fix méretének beállítása


	GUI(Control c) {
		//super("SzoftechTutor");
		ctrl = c;
		//setSize(500, 350);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLayout(null);

		JFrame window = new JFrame(); // Ablak létrehozása
		window.setTitle("Faltoro"); // Ablak neve
		window.setSize(d); // Ablak mérete
		window.setMaximumSize(d); // Ablak ne legyen átméretezhetõ
		window.setMinimumSize(d);
        window.setVisible(true); // Ablak láthatóvá tétele
        window.setLocationRelativeTo(null); // Ablak elhelyezése a képeryõ közepén
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // memória takarítás bezáráskor
        
        drawComponent = new DrawComponent();
        window.add(drawComponent); // hozzáadása az ablakhoz
        
        window.addMouseMotionListener(ctrl); // egér mûködtetése
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
		
        Timer timer = new Timer(ctrl.jatek_sebessege, ctrl); // ciklikus lefutás
        timer.start();
        
        palyafelepites(ctrl.palya);
        
        drawComponent.repaint();
	}
	
	public static void palyafelepites(int palya){
		// Téglák állapotát jelzõ 2D tömb feltöltése 
		        
		        for(int i = 0; i<5; ++i){ // Elõször 0-ba állítjuk az összes elemet
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
		        		// nemjóóóó
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
				graphics.setColor(Color.cyan); // labda színének bequállítása
				graphics.fillOval(ctrl.labda_poz_x, ctrl.labda_poz_y, ctrl.labda_size, ctrl.labda_size);
	        
	        // ütõ beállításai
				graphics.setColor(Color.blue); // ütõ színének beállítása
	        
	        if(ctrl.uto_poz>852){ // ütõ mozgásának korlátozása
	        	ctrl.uto_poz=852;
	        }
	        
	        if(ctrl.uto_poz<10){ // ütõ mozgásának korlátozása
	        	ctrl.uto_poz=10;
	        }
	        
	        
	        graphics.fillRect(ctrl.uto_poz, 668, ctrl.uto_size_x, ctrl.uto_size_y);
	        
	        //pontozás
	        graphics.setColor(Color.black);
	        graphics.setFont(new Font("serif", Font.BOLD, 50));
	        graphics.drawString(""+ctrl.score, 600, 40);
	        
	        //élet
	        graphics.setColor(Color.black);
	        graphics.setFont(new Font("serif", Font.BOLD, 50));
	        graphics.drawString(""+ctrl.lives, 400, 40);
	        
	        // téglák
	        // j-> sorok száma    i-> oszlopok száma
	        for(int i = 0; i<5; ++i){
	        	for(int j = 0; j<6; ++j) {
	        		if(Control.destroyed[i][j] > 0){
	        			
	        			// Az egy életû téglák feketék
	        			if(Control.destroyed[i][j] == 1){
	        				graphics.setColor(Color.black);
	        				graphics.fillRect((ctrl.tegla_szelesseg+ctrl.tegla_tavolsag_x)*i+ctrl.tegla_eltolas_x,(ctrl.tegla_magassag+ctrl.tegla_tavolsag_y)*j+ctrl.tegla_eltolas_y,ctrl.tegla_szelesseg,ctrl.tegla_magassag);
	        			}
	        			
	        			// A két életû téglák pirosak
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
