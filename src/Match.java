import java.util.Scanner;
import java.util.Stack;

/**
 * @author Lakshmi Ravi
 * @author Aarti Gorade
 * 
 * To implement stable matching algorithm and also check if multiple-matching pairs are available
 * Main class : Match
 *  
 */
public class Match {
	Stack<Integer>[] MenPreference;
	private int[][]  menRank;
	private int[][] womenRank;
	Stack<Integer>[] womenPreference;
	int setSize;

	private void printArray(int[] arr){
		for(int i=0;i<setSize;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println("");
	}
	
	private void printStack(Stack[] s, int n){
		int i=0;
		for(int j=0;j<n;j++){
		while(!s[j].isEmpty()){
			System.out.print(s[j].pop()+" ");
		}
		System.out.println("");
		}
	}

	/**
	 * Get the size of input sets and preference matrix
	 */
	public void getInput() {
		Scanner scan = new Scanner(System.in);
		// Get the size
		if (scan.hasNext()) {
			setSize = Integer.parseInt(scan.nextLine());
		}
		MenPreference = new Stack[setSize];
		menRank= new int[setSize][setSize];
		womenPreference = new Stack[setSize];
		womenRank = new int[setSize][setSize];
		// Get the preference matrix
		for (int i = 0; i < setSize; i++) {
			String preference = scan.nextLine();
			String[] prefArr = preference.split(" ");
			MenPreference[i] = new Stack<Integer>();
			for (int j = 0; j < setSize; j++) {
				int w=Integer.parseInt(prefArr[setSize-j-1]);
				MenPreference[i].push(w);
				menRank[i][w]=j;
			}
		}

		// Get the women preference matrix
		for (int i = 0; i < setSize; i++) {
			String preference = scan.nextLine();
			String[] prefArr = preference.split(" ");
			womenPreference[i]= new Stack<Integer>();
			for (int j = 0; j < setSize; j++) {
				int m=Integer.parseInt(prefArr[setSize-j-1]);
				womenPreference[i].push(m);
				womenRank[i][m]=j;
			}
		}
	}

	/**
	 * Returns the stable matched for the list of proposers
	 * @param proposerStack : list of proposers
	 * @param proposerMatrix : preference of the proposers
	 * @param convMatrix : preference of conv
	 * @param n : number of elements
	 * @return
	 */
	public int[] getStablePairing(Stack<Integer> proposerStack, Stack<Integer>[] proposerPrefStack, int[][] convRank, int n) {
		int PairProposers[] = new int[n];
		// Set all persons to be Single -1 as pair
		for (int i = 0; i < n; i++) {
			PairProposers[i] = -1;
		}
		
		//Till all proposers have a pair
		while(!proposerStack.isEmpty()){
			//Get the first proposer
			//System.out.println("Proposer pending");
			int proposer=proposerStack.pop();
			
			//For the convy in his stack
			while(!proposerPrefStack[proposer].isEmpty()){
				//Get next conveyor
				int convy = proposerPrefStack[proposer].pop();
				//If convy is single
				if(PairProposers[convy] == -1){
					PairProposers[convy]=proposer;
					break;
				}
				int prevPair=PairProposers[convy];
				//Check conveyor rank
				if(convRank[convy][prevPair]>convRank[convy][proposer]){
					continue;
				}
				else{
					PairProposers[convy]=proposer;
					proposerStack.push(prevPair);
					break;
				}		
			}	
		}
		
		return PairProposers;
	}
	
	/**
	 * Check if the pairing set are the same
	 * @param index
	 * @param array
	 * @return
	 */
	public boolean checkIndexesSame(int[] index, int[] array){
	for(int i=0;i<setSize;i++){
		int indi= index[i];
		if(array[indi]!=i)
			return false;
	}
		return true;
	}
	public void checkPairMaking(){
		Stack<Integer> ProposerStack = new Stack<Integer>();
		//Men turn
		for(int i=0;i<setSize;i++){
		ProposerStack.push(setSize-i-1);
		}
		int arr1[]= getStablePairing(ProposerStack, MenPreference, womenRank, setSize);
		printArray(arr1);
		//women turn
		for(int i=0;i<setSize;i++){
			ProposerStack.push(setSize-i-1);
		}
		int arr2[]= getStablePairing(ProposerStack, womenPreference, menRank, setSize);
		printArray(arr2);
		if(checkIndexesSame(arr1, arr2)){
			System.out.println("NO");
		}else{
			System.out.println("YES");
		}
	}
	
	public static void main(String[] args){
		Match gsalgo = new Match();
		gsalgo.getInput();
		gsalgo.checkPairMaking();
		/*gsalgo.printStack(gsalgo.MenPreference, gsalgo.setSize);
		System.out.println("Women");
		gsalgo.printStack(gsalgo.womenPreference, gsalgo.setSize);*/
		
	}

}