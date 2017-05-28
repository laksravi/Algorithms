import java.util.Scanner;

/**
 * Product that needs to be taken in sack
 * 
 * @author Lakshmi Ravi
 * @author Aarti Gorade
 * 
 */
class Product {
	int value;
	int weight;

	Product(int weight, int value) {
		this.value = value;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return this.value + " " + this.weight + " ;";
	}

}

/**
 * Compute the maximum possible value that can be obtained with two sacks
 * 
 * @author Lakshmi Ravi
 * @author Aarti Gorade
 * 
 */
public class DoubleKnapsack {
	int itemCount, sack1Weight, sack2Weight;
	int[][][] sackTrack;
	Product[] items;

	public void setSackTrace() {
		// When both weights are zero
		for (int i = 0; i < itemCount; i++) {
			sackTrack[i][0][0] = 0;
		}
		// when no items are present for all sack weight combo;
		for (int w1 = 0; w1 < sack1Weight; w1++) {
			for (int w2 = 0; w2 < sack2Weight; w2++) {
				sackTrack[0][w1][w2] = 0;
			}
		}
	}

	int max(int a, int b, int c) {
		if (a >= b) {
			return a > c ? a : c;
		}
		return b > c ? b : c;
	}

	/**
	 * Trace the highest value possible for both the sack
	 */
	public void traceHighestValue() {
		setSackTrace();
		for (int i = 1; i <= itemCount; i++) {
			// for every item in the list
			int value = items[i - 1].value;
			int itemWeight = items[i - 1].weight;
			// For all possible weights in sack-1
			for (int w1 = 0; w1 <= sack1Weight; w1++) {
				// for all possible weights in sack-2
				for (int w2 = 0; w2 <= sack2Weight; w2++) {
					// for every possible combination of the sack-weight
					int x = 0, y = 0;
					// if item can be placed in sack-1
					if (w1 >= itemWeight) {
						x = value + sackTrack[i - 1][w1 - itemWeight][w2];
					}
					// if item being placed in sack-2
					if (w2 >= itemWeight) {
						y = value + sackTrack[i - 1][w1][w2 - itemWeight];
					}
					// max of the values possible
					sackTrack[i][w1][w2] = max(sackTrack[i - 1][w1][w2], x, y);
				}
			}
		}
		System.out.println(sackTrack[itemCount][sack1Weight][sack2Weight]);
		print3dMatrix(sackTrack);
	}

	private void print3dMatrix(int[][][] sackTrack2) {
		for(int i=0;i<=itemCount;i++){
			for(int j=0;j<=sack1Weight;j++){
				for(int k=0;k<=sack2Weight;k++){
					System.out.print(sackTrack2[i][j][k]+" ");
				}
				System.out.println("");
			}
			System.out.println("***************");
		}
	}

	/**
	 * Utility to print items - debugging function
	 */
	public void printItems() {
		for (int i = 0; i < itemCount; i++) {
			System.out.println(items[i]);
		}
	}


	/**
	 * Get the input from the console
	 */
	public void getInput() {
		Scanner sc = new Scanner(System.in);
		itemCount = Integer.parseInt(sc.next());
		items = new Product[itemCount];
		sack1Weight = Integer.parseInt(sc.next());
		sack2Weight = Integer.parseInt(sc.next());
		sackTrack = new int[itemCount + 1][sack1Weight + 1][sack2Weight + 1];
		int i = 0;
		while (i < itemCount) {
			int weight = Integer.parseInt(sc.next());
			int cost = Integer.parseInt(sc.next());
			items[i] = new Product(weight, cost);
			i++;
		}
	}

	public static void main(String[] args) {
		DoubleKnapsack dks = new DoubleKnapsack();
		dks.getInput();
		dks.traceHighestValue();

	}

}
