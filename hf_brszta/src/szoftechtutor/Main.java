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
	
	public static Dimension d = new Dimension(1024,768); // Ablak fix m�ret�nek be�ll�t�sa

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		/*
		 * Java gyakorlaton bemutatott mintaprogram main-j�nek belseje
		 * ehhez tartozik m�g a GUI.java, a Control.java 
		 * �s a Network.java, de ez m�g nincs implement�lva
		 * 
		 * 
		*/
		/*
		Control c = new Control();
		GUI g = new GUI(c);
		c.setGUI(g);
		*/
		
		JFrame window = new JFrame(); // Ablak l�trehoz�sa
		window.setTitle("Faltoro"); // Ablak neve
		window.setSize(d); // Ablak m�rete
		window.setMaximumSize(d); // Ablak ne legyen �tm�retezhet�
		window.setMinimumSize(d);
        window.setVisible(true); // Ablak l�that�v� t�tele
        window.setLocationRelativeTo(null); // Ablak elhelyez�se a k�pery� k�zep�n
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // mem�ria takar�t�s bez�r�skor
        

        
        faltoro faltoro = new faltoro(); // �j faltoro l�trehoz�sa
        window.add(faltoro); // hozz�ad�sa az ablakhoz
        
        window.addMouseMotionListener(faltoro); // eg�r m�k�dtet�se
        window.addMouseListener(faltoro);
        window.addKeyListener(faltoro);
        
		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("Start");
		
		JMenuItem menuItem = new JMenuItem("Single Player");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				faltoro.click=true;
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Client");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				faltoro.startClient();
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Server");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				faltoro.startServer();
			}
		});
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		window.setJMenuBar(menuBar);
		
        Timer timer = new Timer(faltoro.jatek_sebessege, faltoro); // ciklikus lefut�s
        timer.start();
        
        // T�gl�k �llapot�t jelz� 2D t�mb felt�lt�se 
        switch (faltoro.palya){
        	case 1:
        		for(int i = 0; i<5; ++i){
                	for(int j = 0; j<6; ++j) {
                			faltoro.destroyed[i][j] = 1;
                	}
                }
        		break;
        	case 2:
        		break;
        	case 3:
        		break;
        	case 4:
        		break;
        	case 5:
        		break;
        	default:
        		// nemj����
        		break;
        }
        
     }
    
        
        
        
	}
