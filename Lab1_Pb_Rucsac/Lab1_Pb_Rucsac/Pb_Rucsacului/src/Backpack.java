
public class Backpack {
	private int value;
	private int weigth;

	public Backpack(){ 
		this.value=0;
		this.weigth=0;
	}

	public Backpack(int v,int w){
		this.value=v;
		this.weigth=w;
	}
	
	int getValue(){
		return this.value;
	}
	
	int getWeigth(){
		return this.weigth;
	}
}
