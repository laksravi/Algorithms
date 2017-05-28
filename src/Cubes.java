import java.util.Scanner;


public class Cubes {

	/**
	 * Prints the perfect cubes till the given upper limit
	 * @param limit : upper limit when it should stop printing
	 */
	public void printCubes(int limit){
		int cube=0, index=0;
		//Till the end of limit print the cube of the index
		while(cube<=limit){
			System.out.println(cube);
			index++;
			cube=index*index*index;
		}
	}
	/**
	 * Gets the input from system line and 
	 * @return 
	 */
	public int getInput(){
		Scanner scan= new Scanner(System.in);
		System.out.println("Enter the Limit!! ..");
		int upperLimit=-1;
		if(scan.hasNext()){
			upperLimit=Integer.parseInt(scan.nextLine()); 
		}
		return upperLimit;
	}
	
	public static void main(String[] args){
		Cubes mycube= new Cubes();
		int limit=mycube.getInput();
		if(limit>=0){
			mycube.printCubes(limit);
		}
		else{
			System.err.println("Invalid Input");
		}
	}
}
