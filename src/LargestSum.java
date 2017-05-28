
import java.util.Arrays;
import java.util.Scanner;

/**
 * Find the largest sum of a increasing sub-sequence
 * Dynamic programming approach
 * @author Lakshmi Ravi
 * @author Aarti Gorade
 *
 */
public class LargestSum {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		// number of elements
		int numInputs = sc.nextInt();
		int[] inputArray = new int[numInputs];

		// input array elements from user
		for (int index = 0; index < numInputs; index++) {
			inputArray[index] = sc.nextInt();
		}

		int[] result = new int[numInputs];
		result = Arrays.copyOfRange(inputArray, 0, numInputs);
		int max = 0;

		for (int outer = 1; outer < numInputs; outer++) {
			for (int inner = 0; inner < outer; inner++) {
				// To check if it is increasing subsequence
				if (inputArray[outer] > inputArray[inner]) {
					// To check if new sum is greater than previous calculated
					// sum
					if (result[outer] < result[inner] + inputArray[outer]) {
						result[outer] = result[inner] + inputArray[outer];
						// Keep track of maximum sum value found
						if (max < result[outer]) {
							max = result[outer];
						}
					}
				}
			}
		}
		//print the max-obtained sum
		System.out.println(max);

	}
}