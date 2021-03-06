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
import javax.swing.JOptionPane;
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
	JFrame window = new JFrame(); // Ablak l�trehoz�sa
	
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
		
		JMenu submenu;
		
		//a submenu
		menu.addSeparator();
		submenu = new JMenu("Multi Player");

		menuItem = new JMenuItem("Client");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.startClient();
			}
		});
		submenu.add(menuItem);

		menuItem = new JMenuItem("Server");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.startServer();
			}
		});
		submenu.add(menuItem);
		menu.add(submenu);
		menuBar.add(menu);

		
		
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
		window.setJMenuBar(menuBar);
		
		/*
		Timer timer = new Timer(ctrl.jatek_sebessege, ctrl); // ciklikus lefut�s
        timer.start();
        
        // eltelt id� m�r�se
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
		// T�gl�k �llapot�t jelz� 2D t�mb felt�lt�se 
		        int palya = fal.palya;
		        for(int i = 0; i<5; ++i){ // El�sz�r 0-ba �ll�tjuk az �sszes elemet
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
		        		// nemj����
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
	
	public String getPlayerName(){
		repaint();
		String name = JOptionPane.showInputDialog(window,
                "You can get into TOP10! \n What is your name?", null);
		System.out.println(name);
		return name;
	}
	
	
	private class DrawComponent extends JComponent {

		private static final long serialVersionUID = 1L;
		//private ArrayList<Point> points = new ArrayList<Point>();
		
		@Override
	    protected void paintComponent(Graphics graphics) {
			super.paintComponent(graphics);
				
			if (ctrl.isGamePaused())
				{
				graphics.setColor(Color.red);
				graphics.setFont(new Font("serif", Font.BOLD, 50));
				graphics.drawString("GAME PAUSED!", 300, 500);
				}

			
			if (ctrl.isGameOver() && !ctrl.isNetworkGame())
				{
				graphics.setColor(Color.red);
				if(!ctrl.jatekos.isIntop10()){
					graphics.setFont(new Font("serif", Font.BOLD, 50));
					graphics.drawString("GAME OVER", 350, 300);
					graphics.setFont(new Font("serif", Font.BOLD, 40));
					graphics.drawString("Your score:  " +ctrl.jatekos.getScore(), 380, 500);
				} else {
					graphics.setFont(new Font("serif", Font.BOLD, 50));
					graphics.drawString("GAME OVER", 350, 100);
					graphics.setFont(new Font("serif", Font.PLAIN, 40));
					graphics.drawString("Place |  Score  |     Name", 300, 150);
					for(int i=0; i<ctrl.listTop10.bestplayers.size();i++){
						graphics.setFont(new Font("serif", Font.ITALIC, 40));
						graphics.drawString("  "+ctrl.listTop10.bestplayers.get(i).getPlace()+".        "+ctrl.listTop10.bestplayers.get(i).getScore()+"       "+ctrl.listTop10.bestplayers.get(i).getName(), 300, (200+i*50));
					}
				}
				}
			else if(ctrl.isGameFinished()){
				graphics.setColor(Color.green);
				graphics.setFont(new Font("serif", Font.BOLD, 50));
				graphics.drawString("YOU WIN", 390, 100);
				graphics.setFont(new Font("serif", Font.PLAIN, 40));
				graphics.drawString("Place |  Score  |     Name", 300, 150);
				for(int i=0; i<ctrl.listTop10.bestplayers.size();i++){
					graphics.setFont(new Font("serif", Font.ITALIC, 40));
					graphics.drawString("  "+ctrl.listTop10.bestplayers.get(i).getPlace()+".        "+ctrl.listTop10.bestplayers.get(i).getScore()+"       "+ctrl.listTop10.bestplayers.get(i).getName(), 300, (200+i*50));
				}
			}
			else if (ctrl.isNetworkGame()){
				String message = ctrl.getNetworkMessage();
		        graphics.setColor(Color.green);
		        graphics.setFont(new Font("serif", Font.BOLD, 50));
				graphics.drawString("NETWORK GAME", 300, 300);
		        graphics.setFont(new Font("serif", Font.BOLD, 40));
				//graphics.drawString("", 500, 37);
		        graphics.drawString(""+message, 500-(8*message.length()), 500);
			}
			else {
				// labda
				graphics.setColor(Color.cyan); // labda sz�n�nek bequ�ll�t�sa
				graphics.fillOval(ctrl.labda.poz_x, ctrl.labda.poz_y, ctrl.labda.size, ctrl.labda.size);
	        
				// �t�
				graphics.setColor(Color.blue); // �t� sz�n�nek be�ll�t�sa
				graphics.fillRect(ctrl.uto.poz, 668, ctrl.uto.size_x, ctrl.uto.size_y);
				
     
	        
	        
		        // t�gl�k
		        // j-> sorok sz�ma    i-> oszlopok sz�ma
		        for(int i = 0; i<5; ++i){
		        	for(int j = 0; j<6; ++j) {
		        		if(Wall.destroyed[i][j] > 0){
		        			
		        			// Az egy �let� t�gl�k feket�k
		        			if(Wall.destroyed[i][j] == 1){
		        				graphics.setColor(Color.black);
		        				graphics.fillRect((ctrl.palya.tegla_szelesseg+ctrl.palya.tegla_tavolsag_x)*i+ctrl.palya.tegla_eltolas_x,(ctrl.palya.tegla_magassag+ctrl.palya.tegla_tavolsag_y)*j+ctrl.palya.tegla_eltolas_y,ctrl.palya.tegla_szelesseg,ctrl.palya.tegla_magassag);
		        			}
		        			
		        			// A k�t �let� t�gl�k pirosak
		        			if(Wall.destroyed[i][j] == 2){
		        				graphics.setColor(Color.red);
		        				graphics.fillRect((ctrl.palya.tegla_szelesseg+ctrl.palya.tegla_tavolsag_x)*i+ctrl.palya.tegla_eltolas_x,(ctrl.palya.tegla_magassag+ctrl.palya.tegla_tavolsag_y)*j+ctrl.palya.tegla_eltolas_y,ctrl.palya.tegla_szelesseg,ctrl.palya.tegla_magassag);
		        			}
		        		}
		        	}
		        }
			}
			// vonal
			graphics.setColor(Color.black); 
			graphics.fillRect(0, 43, 1024, 2);
			graphics.fillRect(0, 0, 1024, 43);
			
			//p�lya sz�ma
	        graphics.setColor(Color.cyan);
	        graphics.setFont(new Font("serif", Font.BOLD, 40));
	        graphics.drawString("Level:", 80, 37);
	        graphics.drawString(""+ctrl.palya.palya, 200, 37);
	        
	        //�let
	        graphics.setColor(Color.red);
	        graphics.setFont(new Font("serif", Font.BOLD, 40));
			graphics.drawString("Lives:", 320, 37);
	        graphics.drawString(""+ctrl.jatekos.lives, 440, 37);
	        
	        //pontoz�s
			graphics.setColor(Color.orange);
			graphics.setFont(new Font("serif", Font.BOLD, 40));
			graphics.drawString("Score:", 550, 37);
	        graphics.drawString(""+ctrl.jatekos.score, 670, 37);
        
	        //eltelt ido
	        graphics.setColor(Color.green);
	        graphics.setFont(new Font("serif", Font.BOLD, 40));
	        graphics.drawString("Time:", 795, 37);
	        graphics.drawString(""+ctrl.eltelt_ido, 915, 37);
			}
			
	}
	
}
