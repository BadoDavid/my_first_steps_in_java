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
        JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Start");
		JMenuItem menuItem = new JMenuItem("Single Player");
		
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.startGameEn();
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
		window.setJMenuBar(menuBar);
		
		/*
		Timer timer = new Timer(ctrl.jatek_sebessege, ctrl); // ciklikus lefutás
        timer.start();
        
        // eltelt idõ mérése
        Timer timer1 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	ctrl.eltelt_ido = ctrl.eltelt_ido+1;
            	if(ctrl.jatek_sebessege>1){
            		if((ctrl.eltelt_ido%30)==0){
            			ctrl.jatek_sebessege = ctrl.jatek_sebessege-1;
            			timer.setDelay(ctrl.jatek_sebessege); 
            		}
            	}
            }
        });
        timer1.start();
        */
		
        ctrl.palya.BuildWall();
        
        drawComponent.repaint();
	}
	/*
	public static void palyafelepites(Wall fal){
		// Téglák állapotát jelzõ 2D tömb feltöltése 
		        int palya = fal.palya;
		        for(int i = 0; i<5; ++i){ // Elõször 0-ba állítjuk az összes elemet
		        	for(int j = 0; j<6; ++j) {	
		        		Wall.destroyed[i][j] = 0;	
		        	}
		        }       
		        
		        switch (palya){
		        	case 1:
		        		for(int i = 0; i<5; ++i){
		                	for(int j = 0; j<6; ++j) {
		                		Wall.destroyed[i][j] = 1;
		                	}
		                }
		        		break;
		        	case 2:
		        		for(int i = 0; i<5; ++i){
		                	for(int j = 0; j<6; ++j) {
		                			if(i==j || (i==3 && j==5)) {
		                				Wall.destroyed[i][j] = 2;
		                			}
		                			else{
		                				Wall.destroyed[i][j] = 1;
		                			}
		                	}
		                }
		        		break;
		        	case 3:
		        		for(int i = 0; i<5; ++i){
		                	for(int j = 0; j<6; ++j) {
		                			if((i==2) && (j==2 || j==3)) {
		                				Wall.destroyed[i][j] = 2;
		                			}
		                			else if(i==0 || j==0 || i==4 || j==5){
		                				Wall.destroyed[i][j] = 1;
		                			}
		                	}
		                }        		
		        		break;
		        	case 4:
		        		for(int i = 0; i<5; ++i){
		                	for(int j = 0; j<6; ++j) {
		                			if(j==5) {
		                				Wall.destroyed[i][j] = 2;
		                			}
		                			else if(j==0 || i==0 || i==2 || i==4){
		                				Wall.destroyed[i][j] = 1;
		                			}
		                	}
		                }  
		        		break;
		        	case 5:
		        		for(int i = 0; i<5; ++i){
		                	for(int j = 0; j<6; ++j) {
		                			if(j>=3) {
		                				Wall.destroyed[i][j] = 2;
		                			}
		                			else if(j==0 && (i<1 || i>3)){
		                				Wall.destroyed[i][j] = 1;
		                			}
		                			else if(j==1 && i!=2){
		                				Wall.destroyed[i][j] = 1;
		                			}
		                			else if(j==2 || j==3){
		                				Wall.destroyed[i][j] = 1;
		                			}
		                			
		                	}
		                }  
		        		break;
		        	default:
		        		// nemjóóóó
		        		break;
		        }
			}
	*/
	
	private void drawString(String string, int i, int j) {
		// TODO Auto-generated method stub
		
	}

	private void setFont(Font font) {
		// TODO Auto-generated method stub
		
	}

	private void setColor(Color blue) {
		// TODO Auto-generated method stub
		
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
			
			if (ctrl.isGameOver())
				{
				graphics.setColor(Color.red);
				graphics.setFont(new Font("serif", Font.BOLD, 50));
				graphics.drawString("GAME OVER", 350, 300);
				graphics.setFont(new Font("serif", Font.BOLD, 40));
				graphics.drawString("Your score:  " +ctrl.jatekos.getScore(), 380, 500);
				}
			else if(ctrl.isGameFinished()){
				graphics.setColor(Color.green);
				graphics.setFont(new Font("serif", Font.BOLD, 50));
				graphics.drawString("YOU WIN", 390, 300);
				graphics.setFont(new Font("serif", Font.BOLD, 40));
				graphics.drawString("Your score:  " +ctrl.jatekos.getScore(), 380, 500);
			}
			else {
				// labda
				graphics.setColor(Color.cyan); // labda színének bequállítása
				graphics.fillOval(ctrl.labda.poz_x, ctrl.labda.poz_y, ctrl.labda.size, ctrl.labda.size);
	        
				// ütõ
				graphics.setColor(Color.blue); // ütõ színének beállítása
				graphics.fillRect(ctrl.uto.poz, 668, ctrl.uto.size_x, ctrl.uto.size_y);
				
				// vonal
				graphics.setColor(Color.black); 
				graphics.fillRect(0, 43, 1024, 2);
				graphics.fillRect(0, 0, 1024, 43);
				
				//pálya száma
		        graphics.setColor(Color.cyan);
		        graphics.setFont(new Font("serif", Font.BOLD, 40));
		        graphics.drawString("Level:", 80, 37);
		        graphics.drawString(""+ctrl.palya.palya, 200, 37);
		        
		        //élet
		        graphics.setColor(Color.red);
		        graphics.setFont(new Font("serif", Font.BOLD, 40));
				graphics.drawString("Lives:", 320, 37);
		        graphics.drawString(""+ctrl.jatekos.lives, 440, 37);
		        
		        //pontozás
				graphics.setColor(Color.orange);
				graphics.setFont(new Font("serif", Font.BOLD, 40));
				graphics.drawString("Score:", 550, 37);
		        graphics.drawString(""+ctrl.jatekos.score, 670, 37);
	        
		        //eltelt ido
		        graphics.setColor(Color.green);
		        graphics.setFont(new Font("serif", Font.BOLD, 40));
		        graphics.drawString("Time:", 795, 37);
		        graphics.drawString(""+ctrl.eltelt_ido, 915, 37);
	        
	        
	        
	        // téglák
	        // j-> sorok száma    i-> oszlopok száma
	        for(int i = 0; i<5; ++i){
	        	for(int j = 0; j<6; ++j) {
	        		if(Wall.destroyed[i][j] > 0){
	        			
	        			// Az egy életû téglák feketék
	        			if(Wall.destroyed[i][j] == 1){
	        				graphics.setColor(Color.black);
	        				graphics.fillRect((ctrl.palya.tegla_szelesseg+ctrl.palya.tegla_tavolsag_x)*i+ctrl.palya.tegla_eltolas_x,(ctrl.palya.tegla_magassag+ctrl.palya.tegla_tavolsag_y)*j+ctrl.palya.tegla_eltolas_y,ctrl.palya.tegla_szelesseg,ctrl.palya.tegla_magassag);
	        			}
	        			
	        			// A két életû téglák pirosak
	        			if(Wall.destroyed[i][j] == 2){
	        				graphics.setColor(Color.red);
	        				graphics.fillRect((ctrl.palya.tegla_szelesseg+ctrl.palya.tegla_tavolsag_x)*i+ctrl.palya.tegla_eltolas_x,(ctrl.palya.tegla_magassag+ctrl.palya.tegla_tavolsag_y)*j+ctrl.palya.tegla_eltolas_y,ctrl.palya.tegla_szelesseg,ctrl.palya.tegla_magassag);
	        			}
	        		}
	        	}
	        }
			}
			}
			
	}
	
}
