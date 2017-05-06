package szoftechtutor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Top10{
	
	ArrayList <Player> bestplayers = new ArrayList<Player>(10);
	
	public Top10(){
		super();
		
		Player jatekos = new Player();

		try{
			File x = new File("top10.txt");
			System.out.println("Read");
			Scanner sc = new Scanner(x);
			while(sc.hasNext()){
				jatekos.place = sc.nextInt();
				jatekos.score = sc.nextInt();
				jatekos.name = sc.next();
				bestplayers.add(jatekos.place, jatekos);
			}
			//f.format("%d %d %s", jatekos.score, jatekos.lives, wall);
			sc.close();
		}
		catch (FileNotFoundException e){
			System.out.println("Error");
		}
	}
	

	void insertPlayer(Player jatekos) {
		
		for (int i = 0; i < bestplayers.size();i++){
			if(bestplayers.get(i).score < jatekos.score){
				jatekos.intop10 = true;
				jatekos.place = i+1;
				if (bestplayers.isEmpty()){
					bestplayers.remove(bestplayers.size());
				}
				bestplayers.add(jatekos.place, jatekos);
			}
		}
		
	}



	void saveTop10(Player jatekos){
		insertPlayer(jatekos);
		Iterator<Player> it = bestplayers.iterator();
		if (jatekos.intop10){
			Player other;
			try{
				Formatter f = new Formatter("top10.txt");
				System.out.println("Open");
				while(it.hasNext()){
					other = it.next();
					f.format("%d %d %s \n", other.place , other.score, other.name);
				}
				f.close();
			}
			catch (Exception e){
				System.out.println("Error");
			}
		}
	}
	

	
	
}
