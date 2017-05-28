import java.util.Scanner;

/**
 * Convert a given string to another reference string in minimum cost
 * @author Lakshmi Ravi
 * @author Aarti Gorade
 *
 */
public class StringConvert {
	//string to be converted
	public char[] str1;
	//reference string
	public char[] str2;
	public int[][] s;

	/**
	 * Get input to-convert string and reference string
	 */
	public void getInput() {
		Scanner sc = new Scanner(System.in);
		String s1 = "_"+sc.next();
		str1 = s1.toCharArray();
		String s2 ="_"+ sc.next();
		str2 = s2.toCharArray();
	}

	private int min(int a, int b) {
		return a < b ? a : b;
	}
	
	public void print(){
		for(int i=0;i<str1.length;i++){
			for(int j=0;j<str2.length;j++){
				System.out.print(s[i][j]+" ");
			}
			System.out.println("");
		}
	}

	public void getPossiblities() {
		getInput();
		s = new int[str1.length][str2.length];
		int l1 = str1.length;
		int l2 = str2.length;
		// when reference string is empty - delete all chars in given string
		for (int i = 0; i < l1; i++) {
			s[i][0] = 3 * i;
		}
		//when given string is empty insert the chars to match reference string
		for (int i = 0; i < l2; i++) {
			s[0][i] = 4 * i;
		}

		// Find minimum cost for that char-combination
		//i- index of given string
		for (int i = 1; i < l1; i++) {

			//j - reference string
			for (int j = 1; j < l2; j++) {

				if (str1[i] == str2[j]) {
					s[i][j] = s[i - 1][j - 1];
				}
				else 
				{
					//compute costs of deleting the char, inserting a char, 
					//insert new char in-place of 2 chars
					int del = 3 + s[i-1][j];
					int insert = 4 + s[i][j-1];
					
					int singChang = min(del, insert);
					s[i][j] = singChang;
					
					//2-char deletion possible iff referenced index is atleast 2
					if(i !=1){
					 int doubDel2 = 5 + s[i-2][j-1];
					 s[i][j] = min(singChang, doubDel2);
					}
					
				}

			}
		}
		//print the min cost of conversion
		System.out.println(s[l1 - 1][l2 - 1]);

	}

	public static void main(String[] args) {
		new StringConvert().getPossiblities();
	}

}
