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
		
		/*
		 * az eredeti Main felépítése
		 * 
		JFrame window = new JFrame(); // Ablak létrehozása
		window.setTitle("Faltoro"); // Ablak neve
		window.setSize(d); // Ablak mérete
		window.setMaximumSize(d); // Ablak ne legyen átméretezhetõ
		window.setMinimumSize(d);
        window.setVisible(true); // Ablak láthatóvá tétele
        window.setLocationRelativeTo(null); // Ablak elhelyezése a képeryõ közepén
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // memória takarítás bezáráskor
        

        
        faltoro faltoro = new faltoro(); // Új faltoro létrehozása
        window.add(faltoro); // hozzáadása az ablakhoz
        
        window.addMouseMotionListener(faltoro); // egér mûködtetése
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
		
        Timer timer = new Timer(faltoro.jatek_sebessege, faltoro); // ciklikus lefutás
        timer.start();
        
        Control.palyafelepites(5);	
        */
	
	}
    
        
        
        
	}
