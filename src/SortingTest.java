/**
 * @author Aarti Gorade
 * @author Lakshmi Ravi
 * 
 * Compare merge sort , insertion sort and bucket sort on various input size generated with 
 * uniform distribution and Gaussian (normal) distribution
 * 
 * Main class : SortingTest
 *  
 */


import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
class customLinkedList{
	Double value;
	customLinkedList next;
	static customLinkedList head;
	}
public class SortingTest {

	/**
	 * Bucket sort
	 * 
	 * @param aList
	 *            floating point Array to be sorted return None
	 */
	private void bucketSort(double[] input) {
		
		int sizeL = input.length;
		int index;

		// Creation of empty buckets and initializing each to arrayList to hold
		// floating point values
		
		List<Double>buckets[] = new LinkedList[sizeL];
		for (index = 0; index < (sizeL); index++) {
			buckets[index]=(new LinkedList<Double>());
		}
		// Assigning each value from array to particular bucket
		for (index = 0; index < input.length; index++) {
			int position = (int) (input[index] * sizeL);
			(buckets[position]).add(input[index]);
		}
		// Perform insertion sort on individual bucket and update main array
		// with sorted values
		int currentIndex = 0;
		for (index = 0; index < (sizeL); index++) {
			if(buckets[index].size() <= 0){
				continue;
			}
			double[] dArray = new double[buckets[index].size()];
			int capacity=buckets[index].size();
			for (int i = 0; i <capacity ; i++) {
				dArray[i] = buckets[index].get(i);
			}
			dArray = insertionSort(dArray);
			for (int j = 0; j < dArray.length; j++) {
				input[currentIndex] = dArray[j];
				currentIndex++;
			}
		}
	}

	/**
	 * Merge sort - helper function
	 * 
	 * @param aList
	 *            floating point Array to be sorted return None
	 */

	public void helper_mergeSort(double[] myList) {
		if (myList.length > 1) {
			int mid = myList.length / 2;

			// divide input array into left and right half
			double[] leftHalf = new double[mid];
			for (int count = 0; count < mid; count++) {
				leftHalf[count] = myList[count];
			}
			double[] rightHalf = new double[myList.length - mid];
			int index = 0;
			for (int count = mid; count < myList.length; count++) {
				rightHalf[index] = myList[count];
				index++;

			}
			// Perform recursive function calls on left and right array
			helper_mergeSort(leftHalf);
			helper_mergeSort(rightHalf);

			int i = 0;
			int j = 0;
			int k = 0;
			int lSize = leftHalf.length;
			int rSize = rightHalf.length;

			// Perform merge operation on sorted arrays
			while (i < lSize && j < rSize) {
				double a = leftHalf[i];
				double b = rightHalf[j];
				if (a < b) {
					myList[k] = a;
					i++;
				} else {
					myList[k] = b;
					j++;
				}
				k++;
			}

			while (i < lSize) {
				myList[k] = leftHalf[i];
				k++;
				i++;
			}

			while (j < rSize) {
				myList[k] = rightHalf[j];
				k++;
				j++;
			}
		}
	}

	/**
	 * Merge sort - Main function
	 * 
	 * @param aList
	 *            floating point Array to be sorted return Sorted array
	 */

	public double[] mergeSort(double[] myList) {
		helper_mergeSort(myList);
		return myList;
	}

	/**
	 * Insertion sort
	 * 
	 * @param aList
	 *            floating point Array to be sorted return Sorted array
	 */
	public double[] insertionSort(double[] myList) {
		int sizeL = 0;
		double currentValue;
		int position = 0;
		double vToCompare;
		sizeL = myList.length;

		// Compare element with previous element, if smaller perform swapping.
		// Maintains left side of each element as sorted
		for (int index = 1; index < sizeL; index++) {
			currentValue = myList[index];
			position = index;
			while (position > 0
					&& (vToCompare = myList[position - 1]) > currentValue) {
				myList[position] = vToCompare;
				position = position - 1;

			}

			myList[position] = currentValue;
		}

		return myList;
	}

	/**
	 * Print input array
	 * 
	 * @param A
	 *            input Array return None
	 */
	public void printArray(double[] A) {
		System.out.println(A.length);
		for (int index = 0; index < A.length; index++) {
			System.out.print(A[index] + "  ");
		}
		System.out.print("\n\n");
	}

	public boolean sortTest(double[] A){
		for(int i=1;i<A.length;i++){
			if(A[i] < A[i-1]){
				System.out.println(A[i]+" "+A[i-1]+" "+i);
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = 0;
		int mode = 0;
		double mean = 0.5;
		double variance = 1 / (10000.0);

		System.out.println("Please enter size N= ");
		size = sc.nextInt();
		System.out
				.println("uniform distribution(0) OR Gaussian (normal) distribution(1) ?\n Enter your choice :");
		mode = sc.nextInt();

		System.out.println("mode= " + mode);

		Random randomno = new Random();
		double[] aList = new double[size];

		// Generate n random numbers with uniform distribution
		if (mode == 0) {
			for (int index = 0; index < size; index++) {
				aList[index] = randomno.nextDouble();
			}
		}
		// Generate n random numbers with Gaussian (normal) distribution
		else if (mode == 1) {
			for (int index = 0; index < size; index++) {
				aList[index] = mean + randomno.nextGaussian() * variance;
			}
		}
		
		SortingTest S = new SortingTest();
		double[] dList = new double[size];

		// Making copy of original array so that can be passed as input to all
		// sort functions
		for (int index = 0; index < aList.length; index++) {
			dList[index] = aList[index];
		}

		// ==================== Merge Sort =========================

		long startTime = System.currentTimeMillis();
		S.mergeSort(dList);
		long stopTime = System.currentTimeMillis();
		System.out.println("Time required for Merge Sort: "
				+ (stopTime - startTime) + "  ms");

		// ==================== Insertion Sort =========================

		for (int index = 0; index < aList.length; index++) {
			dList[index] = aList[index];
		}

		startTime = System.currentTimeMillis();
		S.insertionSort(dList);
		stopTime = System.currentTimeMillis();
		System.out.println("Time required for Insertion Sort: "
				+ (stopTime - startTime) + "  ms");

		// ==================== Bucket Sort =========================

		for (int index = 0; index < aList.length; index++) {
			dList[index] = aList[index];
		}

		startTime = System.currentTimeMillis();
		double[] dList1={0.1,0.2,0.497,0.03};
		S.bucketSort(dList1);
		stopTime = System.currentTimeMillis();
		System.out.println("Time required for Bucket Sort: "
				+ (stopTime - startTime) + "  ms");

	}

}
