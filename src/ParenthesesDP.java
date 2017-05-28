
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Solve the right parentheses using
 * Dynamic programming approach
 * @author Lakshmi Ravi
 * @author Aarti Gorade
 *
 */
public class ParenthesesDP {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String inputLine = sc.nextLine();
		StringTokenizer tokens = new StringTokenizer(inputLine);
		int count = tokens.countTokens();

		String[] inputArray = new String[count + 1];
		// 0th position initialized to blank
		inputArray[0] = "";

		// inputs are taken as tokens and stored into input array from 1st
		// position onwards
		for (int index = 1; index < count + 1; index++) {
			inputArray[index] = (String) tokens.nextElement();
		}

		// Stores final maximum result possible
		int[][] result = new int[count + 1][count + 1];
		// Stores maximum possible result possible till that point
		int[][] maxResult = new int[count + 1][count + 1];
		// Stores minimum possible result possible till that point
		int[][] minResult = new int[count + 1][count + 1];

		// result[i][i] positions are initialized with individual input operands
		for (int i = 1; i <= count; i = i + 2) {
			result[i][i] = Integer.parseInt(inputArray[i]);
		}

		// maxResult[i][i] positions are initialized with individual input
		// operands
		for (int i = 1; i <= count; i = i + 2) {
			maxResult[i][i] = Integer.parseInt(inputArray[i]);
		}

		// minResult[i][i] positions are initialized with individual input
		// operands
		for (int i = 1; i <= count; i = i + 2) {
			minResult[i][i] = Integer.parseInt(inputArray[i]);
		}

		for (int j = 1; j < count + 1; j = j + 2) {
			for (int i = j - 2; i > 0; i = i - 2) {

				int k = i + 1;
				// Initial maximum value
				int maxValue = Integer.MIN_VALUE;
				// Initial minimum value
				int minValue = Integer.MAX_VALUE;
				int temp = 0;

				/**
				 * Four different combinations are required to be checked to
				 * find out maximum possible result. As due to (-) operand
				 * involved, result can be negative but later on positive - (
				 * negative )could result into larger positive result.
				 * 
				 * To consider all these possible scenarios, We will consider
				 * below combinations:
				 * 
				 * 1) max (opertor) max 2) max (opertor) min 3) min (opertor)
				 * max 4) min (opertor) min
				 * 
				 */
				while (k < j) {

					// max (operator) max
					temp = calculate(maxResult[i][k - 1],
							inputArray[k].charAt(0), maxResult[k + 1][j]);

					maxValue = Math.max(maxValue, temp);
					minValue = Math.min(minValue, temp);

					// max (operator) min
					temp = calculate(maxResult[i][k - 1],
							inputArray[k].charAt(0), minResult[k + 1][j]);

					maxValue = Math.max(maxValue, temp);
					minValue = Math.min(minValue, temp);

					// min (operator) max
					temp = calculate(minResult[i][k - 1],
							inputArray[k].charAt(0), maxResult[k + 1][j]);

					maxValue = Math.max(maxValue, temp);
					minValue = Math.min(minValue, temp);

					// min (operator) min
					temp = calculate(minResult[i][k - 1],
							inputArray[k].charAt(0), minResult[k + 1][j]);

					maxValue = Math.max(maxValue, temp);
					minValue = Math.min(minValue, temp);

					// Operands will be present at even positions
					k = k + 2;

					// maxResult and minResult array are updated with new values
					// accordingly.
					maxResult[i][j] = maxValue;
					minResult[i][j] = minValue;

				}
				// final result array is updated with maximum value possible
				result[i][j] = Math.max(maxValue, minValue);
			}
		}

		System.out.println(result[1][count]);
	}

	/**
	 * Performs arithmetic operation
	 * 
	 * @param operand
	 *            1
	 * @param operator
	 * @param operand
	 *            2
	 * @return arithmetic result
	 */
	private static int calculate(int operand_1, char operator, int operand_2) {

		switch (operator) {
		case '+':
			return operand_1 + operand_2;
		case '-':
			return operand_1 - operand_2;
		}
		return 0;
	}
}