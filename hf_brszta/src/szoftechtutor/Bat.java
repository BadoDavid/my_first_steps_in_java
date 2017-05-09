package szoftechtutor;

public class Bat {
	
	public int size_x = 150;
	public int size_y = 20;
	public int poz = 426;
	
	
	public int getPoz() {
		return poz;
	}
	
	public void setPoz(int poz) {
        if(poz>852){ // ütõ mozgásának korlátozása
        	poz=852;
        }
        
        if(poz<10){ // ütõ mozgásának korlátozása
        	poz=10;
        }
		this.poz = poz;
	}
	
	
	

}
