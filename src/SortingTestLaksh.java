
public class SortingTestLaksh {

	private void printArray(double[] array){
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
		System.out.println("");
	}
	public double[] mergeSort(double[] input, int start, int end){
		if(start <0 || end >= input.length)
			return null;
		if(start > end)
			return null;
		if (start==end){
			double[] a = new double[1];
			a[0]=input[start];
			return a;
		}
		int mid = start+(end-start)/2;
		double[] arr1= mergeSort(input, start, mid);
		double[] arr2 = mergeSort(input, mid+1, end);
		return merge(arr1, arr2);
	}
	/**
	 * Given two sorted arrays merge them to sorted array
	 * @param a - 1st sorted array
	 * @param b - 2nd sorted array
	 * @return combined sorted array
	 */
	double[] merge(double[] a, double[] b){
		if(a == null)
			return b;
		if( b == null)
			return a;
		double[] output = new double[a.length+b.length];
		int i=0, j=0, k=0;
		while(i<a.length && j< b.length){
			if(a[i] <= b[j]){
			output[k++]=a[i++];
			}
			else{
				output[k++]=b[j++];
			}
		}
		while(i<a.length){
			output[k++]=a[i++];
		}
		while(j<b.length){
			output[k++]=b[j++];
		}
		return output;
	}
	
	
	public void insertionSort(double[] a){
		for(int i=1;i<a.length;i++){
			double elem=a[i];
			int ptr=i-1;
			while(ptr >=0 && a[ptr] > elem){
				a[ptr+1]=a[ptr];
				ptr--;
			}
			a[ptr+1]=elem;	
		}
	}
	
	public void bucketSort(double[] array){
		
	}
	public  void sortTest(){
		double[] a={1,3,4,10,21,6,7,8,2,4,9,11};
		double[] out= mergeSort(a, 0, a.length-1);
		insertionSort(a);
		printArray(out);
		printArray(a);
	}
	 
	public static void main(String[] args){
		 new SortingTestLaksh().sortTest();
	 }
	
}
