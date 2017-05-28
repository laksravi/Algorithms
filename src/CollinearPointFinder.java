import java.util.Scanner;

class P1 {
	int x;
	int y;
	int xDiff;
	int yDiff;
	double slopePrevPoint;
	P1(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	

	/**
	 * Set slope according to prev Point
	 * 
	 * @param px
	 *            : prev point x-co-or
	 * @param py
	 *            : prev point y co-or
	 */
	public void setPrevPoint(int px, int py) {
		xDiff=x- px;
		yDiff = y-py;
		if(xDiff== 0){
			slopePrevPoint=Double.MAX_VALUE;
			return;
		}
		slopePrevPoint = (double)(y-py)/(double)(x-px);
	}
}

public class CollinearPointFinder {

	P1 points[];
	int count;
	int maxCollinearPoints;
	
	private P1[] mergeSort(P1[] point, int start, int end) {
		if (start > end) {
			return null;
		}
		if (start == end) {
			P1[] onePoint = new P1[1];
			onePoint[0] = point[start];
			return onePoint;
		}
		int mid = start + (end - start) / 2;
		P1[] left = mergeSort(point, start, mid);
		P1[] right = mergeSort(point, mid + 1, end);
		return merge(left, right);
	}

	public P1[] merge(P1[] left, P1[] right) {
		P1 allPts[] = new P1[left.length + right.length];
		int i = 0, j = 0, k = 0;
		// Add the points in the order of their slope with previous point
		while (i < left.length && j < right.length) {
			if (left[i].slopePrevPoint < right[j].slopePrevPoint) {
				allPts[k++] = left[i++];
			} else {
				allPts[k++] = right[j++];
			}
		}
		// Add remaining points in left and right
		while (i < left.length) {
			allPts[k++] = left[i++];
		}

		while (j < right.length) {
			allPts[k++] = right[j++];
		}
		return allPts;
	}
	
	private boolean sortTest(P1[] pts){
		for(int i=1;i<pts.length;i++){
			if(pts[i].slopePrevPoint < pts[i-1].slopePrevPoint)
				return false;
		}
		return true;
	}

	public void alignPoints() {
		// For Every point : O(n) *
		for (int i = 0; i < count; i++) {
			P1 currentPoint = points[i];
			P1[] otherPoints = new P1[count-1];
			// Collect the other points in an array : O(n)
			int other=0;
			for (int j = 0; j < count; j++) {
					if(j != i){
					otherPoints[other] = points[j];
					otherPoints[other++].setPrevPoint(currentPoint.x, currentPoint.y);
					}
				
			}
			// Sort the array on the basis of slope with current point
			// Done in O(n.logn)
			otherPoints = mergeSort(otherPoints, 0, count - 2);
			if(!(sortTest(otherPoints))){
				System.out.println("ASD");
			}
			/*for(int t=0;t<otherPoints.length;t++){
				System.out.print(otherPoints[t].slopePrevPoint+" ");
			}
			System.out.println(" ");*/
			
			//Find the maximum collinear points w.r.to currentPoint
			int cnt=1;
			for(int m=1;m<otherPoints.length;m++){
				//Same slope : increment the count
				
				if(otherPoints[m].slopePrevPoint == otherPoints[m-1].slopePrevPoint ){
					System.out.println(otherPoints[m].slopePrevPoint+" "+otherPoints[m-1].slopePrevPoint);
					cnt++;
				}
				else{
					//update max if needed
					//System.out.println(""+ i+" "+" "+m +" "+ cnt);
					if(cnt > maxCollinearPoints){
						maxCollinearPoints=cnt;
					}
					//Set the new start point and clear the count
					cnt=1;
				}
			}
			if(cnt > maxCollinearPoints){
				maxCollinearPoints=cnt;
			}
		}
	}

	/**
	 * From the standard Input get the input points
	 */
	public void getInput() {
		Scanner scan = new Scanner(System.in);
		count = Integer.parseInt(scan.next());
		points = new P1[count];
		int i = 0;
		// Get the points
		while (i < count && scan.hasNext()) {
			int x = Integer.parseInt(scan.next());
			int y = Integer.parseInt(scan.next());
			points[i++] = new P1(x, y);
		}
	}

	public void printPoints() {
		for (int i = 0; i < count; i++) {
			System.out.println(points[i].x + " " + points[i].y);
		}
	}

	public static void main(String[] args) {
		CollinearPointFinder aliPt = new CollinearPointFinder();
		aliPt.getInput();
		aliPt.alignPoints();
		System.out.println(aliPt.maxCollinearPoints+1);
	}
}
