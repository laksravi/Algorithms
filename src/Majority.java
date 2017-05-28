
/**
 * Class to find out whether element 'x' is present more than n/k times in given array of 'n' size
 * @author Aarti Gorade
 * @author Lakshmi Ravi
 *
 */

import java.util.Arrays;
import java.util.Scanner;

public class Majority {

	/**
	 * Implemented Select algorithm to find 'm'th smallest element in given
	 * array
	 * 
	 * @param A
	 *            : input array
	 * @param m
	 *            : find m'th smallest element
	 * 
	 *            return: m'th smallest element
	 */
	private static int selectAlgorithm(int[] A, int m) {

		int min = 0;
		int max = A.length;
		int randIndex = min + (int) (Math.random() * (max - min));
		int randomNumber = A[randIndex];

		int[] temp = new int[max];

		int left = 0;
		int right = max - 1;

		for (int index = 0; index < max; index++) {
			if (A[index] < randomNumber) {
				temp[left] = A[index];
				left++;
			} else if (A[index] > randomNumber) {
				temp[right] = A[index];
				right--;
			}
		}

		int j1 = 0;
		boolean firstPosition = false;
		int j2 = 0;

		while (left <= right) {
			temp[left] = randomNumber;
			if (!firstPosition) {
				j1 = left;
				firstPosition = true;
			}
			left++;
		}

		j2 = left - 1;

		if (m < j1) {
			return selectAlgorithm(Arrays.copyOfRange(temp, 0, j1), m);
		}
		if (m >= j1 && m <= j2) {
			return randomNumber;
		}
		if (m > j2) {
			return selectAlgorithm(Arrays.copyOfRange(temp, j2 + 1, max), m
					- j2 - 1);
		}

		return Integer.MIN_VALUE;
	}

	/**
	 * Find element occurring more than (n/k) times using k-Select algorithm and
	 * confirm the same by measuring frequency of that element by linear search
	 * 
	 * @param arrayKA
	 *            : input array
	 * @param maxLength
	 *            : length of input array
	 * @param k
	 *            : part of expected frequency of element in array = (n/k)times
	 * 
	 *            return: true : element exists , false: do not exists
	 */

	private static boolean findKthElement(int[] arrayK, int maxLength, int k) {
		int loop = 1;

		while (loop < k) {
			int majorityElement = selectAlgorithm(arrayK,
					(loop * maxLength / k));
			int countMajority = 0;

			for (int index = 0; index < maxLength; index++) {
				if (arrayK[index] == majorityElement) {
					countMajority++;
				}
			}
			if (countMajority > (maxLength / k)) {

				return true;
			}
			loop++;
		}

		return false;

	}

	/**
	 * Displays result
	 * 
	 * @param: boolean result return: none
	 */

	public static void printResult(boolean result) {
		if (result) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}

	/**
	 * Takes user input array and displays if there exists element occurring
	 * more than (n/k) times
	 * 
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		// Input from user, number of elements
		int number = sc.nextInt();

		// create array of required size and store elements
		int[] arrayK = new int[number];
		int count = 0;
		while (count < number) {
			arrayK[count] = sc.nextInt();
			count++;
		}

		int maxLength = arrayK.length;
		boolean result;

		// To find majority element occurring greater than n/2 times
		printResult(result = findKthElement(arrayK, maxLength, 2));

		// To find majority element occurring greater than n/3 times
		printResult(result = findKthElement(arrayK, maxLength, 3));

	}

}