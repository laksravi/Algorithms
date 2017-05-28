
import java.util.Scanner;


/**
 * Finding the odd and even sum partitions combinations
 * @author Lakshmi Ravi
 * @author Aarti Gorade
 *
 */
public class Partitions {

	int numInputs;
	int[] inputArray;
	int[] oddPoss;
	
	/**
	 * Get input array
	 */
	public void getInput(){
		Scanner sc = new Scanner(System.in);
		numInputs = sc.nextInt();
		inputArray = new int[numInputs];
		oddPoss = new int[numInputs];
		for (int index = 0; index < numInputs; index++) {
			inputArray[index] = sc.nextInt();
		}
	}
	  
	public void printArr(int[] a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
	}
	
	
	/**
	 * Prints the odd-sum combinations of the array possible
	 * dynamic approach
	 */
	public void printOddSum(){
		int[] shiftInput = new int[inputArray.length+1];
		int[] oddPossiblities= new int[inputArray.length+1];
		oddPossiblities[0]=1;
		//Shift the input array
		for(int i=0;i<inputArray.length;i++){
			shiftInput[i+1]=inputArray[i];
		}
		for(int i=1;i<shiftInput.length;i++){
			int sum=shiftInput[i];
			for(int j=i-1;j>0;j--){
				
				if(sum%2!=0){
					oddPossiblities[i]+=oddPossiblities[j];
				}
				
				sum+=shiftInput[j];
			}
			if(sum %2 !=0){
				oddPossiblities[i]++;
			}
			
		}
		//printArr(oddPossiblities);
		System.out.println(oddPossiblities[oddPossiblities.length-1]);
	}
	
	
	
	/**
	 * Greedy approach to find the even sum- combinations in an array
	 */
	public  void computeEvenSum() {

		int numPossibleSets = 0;
		int oddCount = 0;
		int[] oddEvenArray = new int[numInputs];

		// deciding odd and even array elements
		for (int index = 0; index < numInputs; index++) {
			oddEvenArray[index] = inputArray[index] % 2;
		}

		for (int index = 0; index < numInputs; index++) {
			// Odd number found
			if (oddEvenArray[index] == 1) {
				oddCount += 1;
				oddEvenArray[index] = oddCount;
			} else {
				// Even number found
				if (index != 0) {
					oddEvenArray[index] = oddEvenArray[index - 1];
				} else {
					oddEvenArray[index] = 0;
				}
			}
		}

		boolean startPoint = false;
		for (int index = 0; index < numInputs; index++) {
			// Odd + Odd = Even, Occurrence of odd elements even number of times
			// makes Sum even
			if (oddEvenArray[index] % 2 == 0) {
				if (!startPoint) {
					numPossibleSets = 1;
					startPoint = true;
				} else {
					numPossibleSets = numPossibleSets * 2;
				}
			}

		}

		System.out.println(numPossibleSets);

	}
	
	public static void main(String[] args){
		Partitions ptns = new Partitions();
		ptns.getInput();
		ptns.computeEvenSum();
		ptns.printOddSum();
	}

}
