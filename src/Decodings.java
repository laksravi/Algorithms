import java.util.Scanner;

/***
 * Class to find the number of decodes available for a given code-word
 * @author Lakshmi Ravi
 * @author Aarti Gorade
 *
 */
public class Decodings {

	public String[] CODES = { "0", "1", "01", "10", "111", "011" };
	public char[] input;
	int[] possiblities;

	// Check if it is a code
	public boolean isCode(String x) {
		for (int i = 0; i < CODES.length; i++) {
			if (CODES[i].equals(x))
				return true;
		}
		return false;
	}

	/**
	 * Prints the possible codes
	 */
	public void findPossibleCode() {
		
		/*
		 * Possibility array holds the possible-number of codes till that Length of input code
		 * Possibility[0] is a special case which says the count of a new code
		 */
		//Base-case definition
		possiblities[0] = 1;
		//actual code indexing starts from "1" to input.length
		//The number of ways a code word of length "1" be interpreted in only "1" way in our input-case
		possiblities[1]=1;
		
		//interpretations for code-word of length 2 is either alone or with previous element
		if (input.length > 2) {
			String y = "" + input[1] + input[2];
			if (isCode(y)) {
				possiblities[2] = 2;
			} else {
				possiblities[2] = 1;
			}

		}
		//Dynamic-definition
		for (int i = 3; i < input.length; i++) {
			String y = "" + input[i - 1] + input[i];
			String z = "" + input[i - 2] + input[i - 1] + input[i];

			possiblities[i] = possiblities[i-1];
			if (isCode(y)) {
				possiblities[i] += possiblities[i - 2];
			}
			if(isCode(z)){
					possiblities[i]+=possiblities[i-3];
			}

		}

		System.out.println(possiblities[input.length - 1]);
	}

	/**
	 * Get the input from console
	 */
	public void getNumber() {
		Scanner sc = new Scanner(System.in);
		String inp = sc.next();
		//append a pseudo character at "0" th position
		inp="X"+inp;
		possiblities = new int[inp.length()];
		input = inp.toCharArray();
	}

	public static void main(String[] args) {
		Decodings dec = new Decodings();
		dec.getNumber();
		dec.findPossibleCode();
	}
}
