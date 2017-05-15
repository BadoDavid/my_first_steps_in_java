/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;
import javax.swing.WindowConstants;




/**
 *
 * @author Predi
 */
public class Main {
	//public static Dimension d = new Dimension(1024,768); // Ablak fix méretének beállítása

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		Control c = new Control();
		GUI g = new GUI(c);
		c.setGUI(g);
		
		
		Timer timer = new Timer(c.jatek_sebessege, c); // ciklikus lefutás
        timer.start();
        
        // eltelt idõ mérése
        Timer timer1 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	c.eltelt_ido = c.eltelt_ido+1;
            	if(c.jatek_sebessege>1){
            		if((c.eltelt_ido%30)==0){
            			c.jatek_sebessege = c.jatek_sebessege-1;
            			timer.setDelay(c.jatek_sebessege); 
            		}
            	}
            }
        });
        
        while(true){
        	if(c.isGameStopped()==true){
        		if(timer1.isRunning()){
        			timer1.stop();
        		}	
        	}
        	if(c.isGameStopped()==false){
        		if(timer1.isRunning()==false){
        			timer1.start();
        		}
        	}
        	
        	if(c.isGamePaused()==true){
        		if(timer1.isRunning()){
        			timer1.stop();
        		}
        	}
        	
        	if(c.isGamePaused()==false){
        		if(timer1.isRunning()==false){
        			timer1.start();
        		}	
        	}
        	
        	if(c.TimerChanged){
        		if(timer.isRunning()){
        			timer.setDelay(c.jatek_sebessege); 
        		}	
        		c.TimerChanged=false;
        	}
        	if(c.clear==true){
        		if(timer1.isRunning()){
        			timer1.stop();
        			timer1.restart();
        			c.eltelt_ido=0;
        			c.jatek_sebessege=5;
        			timer.setDelay(c.jatek_sebessege);
        		}	
        		
        	}
        }
        
	
	}
    
        
        
        
	}
