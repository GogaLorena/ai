//RHC

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.*;
import java.util.Random;
public class Main {

	private static int n;
	private static int W;
	private static List<Backpack> backpack=new ArrayList<>();
	
	//reads data from file
	private static  void readFile(String filename) throws FileNotFoundException {
        Scanner s = new Scanner(new File(filename));
        String[] line;
        n=Integer.parseInt(s.nextLine());
        
        for (int i = 0; i < n; i++) {
            line = s.nextLine().split(" ");
            backpack.add(new Backpack(Integer.parseInt(line[0]),Integer.parseInt(line[1])));
           }
        W=Integer.parseInt(s.nextLine());
    }
	
	//generates a random number in range [0,1]
	private static int genRandom(){
		Random rand = new Random(); 
		return rand.nextInt(2);
	}

	/*
	 * validates the solution
	 * if weigth of solution < weigth of backpack return true else false
	 */
	private static boolean verifSol(List<Integer>sol) {
		int sum=0;
		for(int i=0;i<n;i++)
		{
			
			if(sol.get(i)==1)
			{	
				sum=backpack.get(i).getWeigth()+sum;
			}
		}
		if(sum<W)
			return true;
		return false;
	}
	
	//generates a valid solution
	private static  List<Integer> genSolAleat(){
		List<Integer> sol=new ArrayList<>();
		boolean find=false;
		while(find==false) {
			sol.clear();
			for(int i=1;i<=n;i++){
				sol.add(genRandom());
			}
		find=verifSol(sol);
		}
		return sol;
	}
	
	/*
	 * evaluates quality of solution 
	 * calculates sum of solution values  and returns it
	 */
	private static int eval(List<Integer>sol) {
		int value=0;
		for(int i=0;i<n;i++){
			if(sol.get(i)==1)
				value+=backpack.get(i).getValue();
		}
		return value;
	}
	
	

	
	//sets value of k (number of steps)
	private static int setK(){
		if(n<=5)
			return 10;
		if(n<=20)
			return 100;
		if(n<=200)
			return 1000;
		return 1000000;
	}
	
	//returns best solution 
	private static List<Integer> RHC(int k)
	{
		List<Integer> c=new ArrayList<>();
		List<Integer> x=new ArrayList<>();
		c=genSolAleat();
		for(int i=0;i<n;i++) 
			x.add(c.get(i));
		int i=0;
		while(k>0 && i<n) {
			k--;
				if(x.get(i)==0) {
					x.set(i, 1);
					if(eval(c)<eval(x) && verifSol(x)==true)
					{
						for(int j=0;j<n;j++)
							c.set(j, x.get(j));
						i=-1;
					}
			
				}
				i++;
				for(int j=0;j<n;j++) 
					x.set(j,c.get(j));
			}
			return c;
		}
		
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		readFile("Rucsac20");
		int k=setK();
		int sum=0;
		List<Integer>s=new ArrayList<>();
		s=RHC(k);
		System.out.print("Solutia este:\n");
		for(Integer i:s){
			System.out.print(i);
			System.out.print(" ");
		}
		System.out.print("Valoarea finala este: ");
		int evalFin=eval(s);
		System.out.print(evalFin);
		System.out.print("\n");
		for(int i=0;i<n;i++)
		{
			if(s.get(i)==1)
			{	
				sum=backpack.get(i).getWeigth()+sum;
			}
		}
		System.out.print("Greutatea finala este: ");
		System.out.print(sum);
		System.out.print("\n");
		
		readFile("Rucsac200");
		System.out.print("Solutia este:\n");
		k=setK();
		sum=0;
		s=RHC(k);
		
		for(Integer i:s){
			System.out.print(i);
			System.out.print(" ");
		}
		System.out.print("Valoarea finala este: ");
		evalFin=eval(s);
		System.out.print(evalFin);
		System.out.print("\n");
		for(int i=0;i<n;i++)
		{
			if(s.get(i)==1)
			{	
				sum=backpack.get(i).getWeigth()+sum;
			}
		}
		System.out.print("Greutatea finala este: ");
		System.out.print(sum);
		System.out.print("\n");
	}

}
