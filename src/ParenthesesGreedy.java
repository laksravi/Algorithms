import java.util.Scanner;

/**
 * Finding the right paranthesis to get maximum result
 * @author LAkshmi Ravi
 * @author Aarti Gorade
 *
 */
public class ParenthesesGreedy {

	String[] expression=null;
	/**
	 * Get input expression
	 */
	public void getInput(){
		String ip=null;
		Scanner sc = new Scanner(System.in);
		ip=sc.nextLine();
		expression = ip.split(" ");
	}
	
	/**
	 * Find the maximum result possible for the given expression
	 */
	public void findMaxResult(){
		String[] productOfSum= new String[expression.length];
		int count=0;
		
		//take a product of sums in th expression
		
		//compute the sums in the given expression and store as product of sums
		for(int i=0; i< expression.length;i++){
			if(expression[i].equals("+")){
				int a = Integer.parseInt(productOfSum[count-1]);
				int b = Integer.parseInt(expression[i+1]);
				productOfSum[count-1]=""+(a+b);
				i++;
			}
			else{
				productOfSum[count++]=expression[i];
			}
		}
		
		//compute the product of the expression
		int product=Integer.parseInt(productOfSum[0]);
		for(int i=1;i<count;i++){
			if(productOfSum[i].equals("*")){
				int x = Integer.parseInt(productOfSum[i+1]);
				product*=x;
			}
		}
		//product of sums displayed
		System.out.println(product);
	}
	
	public void printExpression(String[] expression){
		for(int i=0;i<expression.length;i++){
			System.out.print(expression[i]+" ");
		}
	}
	
	public static void main(String[] args){
		ParenthesesGreedy pg = new ParenthesesGreedy();
		pg.getInput();
		//pg.printExpression(pg.expression);
		pg.findMaxResult();
	}
}
