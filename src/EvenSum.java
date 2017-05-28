import java.util.Scanner;

/**
 * 
 * @author Lakshmi Ravi
 *
 */
public class EvenSum {

	/**
	 * Reads the input from standard input and computes the sum of even numbers
	 * 
	 */
	public int calculateSum(){
		Scanner scan = new Scanner(System.in);
		int count=0, num=0, evensum=0;
		if(scan.hasNext()){
			count=Integer.parseInt(scan.nextLine());
		}
		//ignores the extra digits and calculates only for existing digits
		while(count >0){
			num=Integer.parseInt(scan.nextLine());
			if(num%2==0){
				evensum+=num;
			}
			count--;
		}
		return evensum;
	}
	
	public static void main(String[] args){
		EvenSum es = new EvenSum();
		System.out.println(es.calculateSum());
	}
}
