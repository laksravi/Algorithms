import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * Class describing attributes of People
 * @author Lakshmi Ravi
 * @author Aarti Gorade
 *
 */
class People{
	int age;
	double height;
	
	People(int age, double h){
		this.age=age;
		this.height=h;
	}
	//Condition of which person comes before whom
	public boolean isBefore(People p){
		//If P is higher age
		if( this.age==7 && p.age!=7)
			return true;
		//If current is professor
		if(this.age!=7 && this.age!=8){
			return p.age ==8;
		}
		//If p is professor
		if(p.age!=7 && p.age!=8){
			return this.age ==7;
		}
		//If current is 8
		if(this.age==8 && p.age==7){
			return false;
		}
		//Same age - age 7
		if(this.age== p.age && this.age==7){
			return this.height <= p.height;
		}
		if(this.age== p.age && this.age==8){
			return this.height >= p.height;
		}
		System.out.println("Some Error!!"+this.age+" "+this.height+" "+p.age+" "+p.height);
		return false;
	}
}

/**
 * Class containing functions and attributes to compute inverse-Count
 * @author Lakshmi Ravi
 * @author Aarti Gorade
 *
 */
public class Picture {
	int count;
	People[] people;
	int InversionCount;
	/**
	 * Function to sort the elements in the preferred order
	 * @param people
	 * @param start
	 * @param end
	 * @return
	 */
	public People[] countAndSort(People[] people, int start, int end){
		if(start > end){
			return null;
		}
		if(start==end){
			People[] p= new People[1];
			p[0]=people[start];
			return p;
		}
		int mid = start+(end-start)/2;
		People[] p1= countAndSort(people, start, mid);
		People[] p2= countAndSort(people, mid+1, end);
		return Merge(p1,p2);
	}
	
	/**
	 * Merge two sorted arrays and compute the count
	 * @param p1 - first sorted array
	 * @param p2 - second sorted array
	 * @return sorted array
	 */
	public People[] Merge(People[] p1, People[] p2){
		if(p1==null){
			return p2;
		}
		if(p2==null){
			return p1;
		}
		People[] all = new People[p1.length+p2.length];
		int i=0, j=0,k=0;
		
		while(i<p1.length && j<p2.length){
			//Check what comes before and insert in the major array
			if(p1[i].isBefore(p2[j])){
				all[k++]=p1[i++];
			}else{
				//Count the number of elements that are left in first array 
				//As many swaps has to be made.
				all[k++]=p2[j++];
				InversionCount+=(p1.length-i);
			}
		}
		while(i < p1.length){
			all[k++]=p1[i++];
		}
		while(j<p2.length){
			all[k++]=p2[j++];
		}
		return all;
	}
	
	/**
	 * Helper utility to print the array of people
	 * @param people
	 */
	public void PrintArray(People[] people){
		if(people == null){
			return;
		}
		for(int i=0;i<people.length;i++){
			System.out.println(people[i].age+" "+people[i].height);
		}
	}
	/**
	 * Gets the array from system input   
	 */
	public void getInput(){
		Scanner scan= new Scanner(System.in);
		//Get the range
		if(scan.hasNext()){
			count=Integer.parseInt(scan.nextLine()); 
		}
		//Initialize the array
		people = new People[count];
		//Get the array elements
		int i=0;
		while(i<count){
			String lin[] = scan.nextLine().split(" ");
			people[i] = new People(Integer.parseInt(lin[0]), Double.parseDouble(lin[1]));
			i++;
		}
		
		
	}
	/**
	 * Function to call element-sort
	 */
	public void sortElements(){
		getInput();
		People[] all = countAndSort(people, 0, people.length-1);
		//PrintArray(all);
		System.out.println(InversionCount);
	}
	
	public static void main(String[] args){
		Picture pic = new Picture();
		pic.sortElements();
	}

}
