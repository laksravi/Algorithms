import java.util.Scanner;

/**
 * @author Lakshmi Ravi
 * @author Aarti Gorade
 * 
 * Class to find the duplicated element in sorted array
 * Main class : oneDup
 *  
 */
public class OneDup {
	private int[] array;
	private int size;
	
	void printArray(){
		for(int i=0;i<array.length;i++){
			System.out.println(array[i]);
		}
	}
	/**
	 * Gets the array from system input   
	 */
	public void getInput(){
		Scanner scan= new Scanner(System.in);
		size=-1;
		//Get the range
		if(scan.hasNext()){
			size=Integer.parseInt(scan.nextLine()); 
		}
		//Initialize the array
		array = new int[size+2];
		//Get the array elements
		int i=0;
		while(i<=size+1){
			array[i++]=Integer.parseInt(scan.nextLine());
		}
	}

	/**
	 * Modified binary search which searches for a particular element
	 * @return duplicate entry
	 */
	public int getDuplicate(){
		int start=0, end=array.length-1;
		
		while(start <= end){
			int mid = start+((end-start)/2);
			//Found the first duplicate
			if( mid!=array[mid] && ( mid==0 || array[mid-1]==mid-1)){
				return array[mid];
			}
			//If all elements are in-place before mid then start with right side
			else if(array[mid]==mid){
				start=mid+1;
			}
			else{
				end=mid-1;
			}
			
		}
		return size+1;
	}
	
	public static void main(String[] args){
		OneDup onedup = new OneDup();
		onedup.getInput();
		//onedup.printArray();
		System.out.println(onedup.getDuplicate());
	}
}
