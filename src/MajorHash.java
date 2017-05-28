import java.util.HashMap;
import java.util.Scanner;
/**
 * Finds the majority element in an array
 * @author Lakshmi Ravi
 * @author Aarti Gorade
 *
 */
public class MajorHash {
	HashMap<Integer, Integer> contentTable = new HashMap<Integer, Integer>();
	int size = 0;
	int[] array;

	/**
	 * Reads the element one by one and finds if there are more than expected count
	 * @param expecCount expected count of any of the element
	 */
	public void countElements(int expecCount) {
		for (int i = 0; i < size; i++) {
			//checks if the element is there in the contentTable
			if (contentTable.containsKey(array[i])) {
				int count = contentTable.get(array[i]);
				//If incremented count is greater than expected count, print result
				if (count + 1 > expecCount) {
					System.out.println("YES");
					return;
				}
				//update content count
				contentTable.put(array[i], count + 1);
			} else {
				//insert new content
				contentTable.put(array[i],1);
			}
		}
		//None of the elements were up to expected count
		System.out.println("NO");
	}

	/**
	 * Gets the array from system input
	 */
	public void getInput() {
		Scanner scan = new Scanner(System.in);
		// Get the range
		if (scan.hasNext()) {
			size = Integer.parseInt(scan.nextLine());
		}
		// Initialize the array
		array = new int[size];
		// Get the array elements
		int i = 0;
		while (i < size) {
			array[i++] = Integer.parseInt(scan.next());
		}
	}

	public static void main(String[] args) {
		MajorHash major = new MajorHash();
		major.getInput();
		major.countElements(major.size/2);
		major.countElements(major.size/3);
	}

}
