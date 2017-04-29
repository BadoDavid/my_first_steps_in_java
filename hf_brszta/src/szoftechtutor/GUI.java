/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package szoftechtutor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 *
 * @author Predi
 */
public class GUI {

	private static final long serialVersionUID = 1L;
	private faltoro ctrl;
	private DrawPanel drawPanel;
	public static Dimension d = new Dimension(1024,768); // Ablak fix m�ret�nek be�ll�t�sa


	GUI(faltoro c) {
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

        window.add(ctrl); // hozz�ad�sa az ablakhoz
        
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
        
        palyafelepites(5);
	}
	
	public static void palyafelepites(int palya){
		// T�gl�k �llapot�t jelz� 2D t�mb felt�lt�se 
		        
		        for(int i = 0; i<5; ++i){ // El�sz�r 0-ba �ll�tjuk az �sszes elemet
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
		        		// nemj����
		        		break;
		        }
			}

	void addPoint(Point p) {
		drawPanel.points.add(p);
		drawPanel.repaint();
	}

	private class DrawPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		private ArrayList<Point> points = new ArrayList<Point>();

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			for (Point p : points) {
				g.drawOval(p.x, p.y, 10, 10);
			}
		}
	}
}
