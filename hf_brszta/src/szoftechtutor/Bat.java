package szoftechtutor;

public class Bat {
	
	public int size_x = 150;
	public int size_y = 20;
	public int poz = 426;
	
	
	public int getPoz() {
		return poz;
	}
	
	public void setPoz(int poz) {
		//poz-=size_x/2;
        if(poz>852){ // �t� mozg�s�nak korl�toz�sa
        	poz=852;
        }
        
        if(poz<10){ // �t� mozg�s�nak korl�toz�sa
        	poz=10;
        }
		this.poz = poz;
	}
	
	
	

}
