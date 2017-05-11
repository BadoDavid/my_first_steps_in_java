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
		
		//Player jatekos = new Player();

		try{
			File x = new File("top10.txt");
			System.out.println("Read");
			Scanner sc = new Scanner(x);
			while(sc.hasNext()){
				Player jatekos = new Player();
				
				jatekos.place = sc.nextInt();
				jatekos.score = sc.nextInt();
				jatekos.name = sc.next();
				//bestplayers.add((jatekos.place-1), jatekos);
				bestplayers.add(jatekos);
			}
			System.out.println(bestplayers);
			//f.format("%d %d %s", jatekos.score, jatekos.lives, wall);
			sc.close();
		}
		catch (FileNotFoundException e){
			System.out.println("Error");
		}
	}
	

	boolean insertPlayer(Player jatekos) {
		boolean state = false;
		/*
		if (bestplayers.size() == 0){
			jatekos.intop10 = true;
			state = true;
			jatekos.place = 1;
		}
		*/
		if (bestplayers.size() != 10){
			jatekos.intop10 = true;
			state = true;
			jatekos.place = bestplayers.size()+1;
		}
		System.out.println(bestplayers.size());
		for (int i = bestplayers.size(); i > 0; i--){

			System.out.println("Hello");
			//int max_score = bestplayers.get(i).score ;
			if(bestplayers.get(i-1).score < jatekos.score){
				System.out.println("csao");
				jatekos.intop10 = true;
				state = true;
				jatekos.place = bestplayers.get(i-1).place;
				bestplayers.get(i-1).place++;
				/*
				if (bestplayers.size()==10){
					bestplayers.remove(bestplayers.size());
				}
				bestplayers.add(jatekos.place, jatekos);
				*/
			}
		}

		return state;
		
	}



	void saveTop10(Player gamer){
		//insertPlayer(jatekos);
		Player jatekos = new Player(gamer);
		if (bestplayers.size()==10){
			bestplayers.remove(bestplayers.size()-1);
		}
				
		bestplayers.add(jatekos.place-1, jatekos);
		//bestplayers.add(jatekos);
		
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
