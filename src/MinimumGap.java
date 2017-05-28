import java.util.Scanner;

/**
 * Interval class compared by the finish time
 * @author Lakshmi Ravi
 *  @author Aarti Gorade
 *
 */
class Interval {
	double start;
	double finish;

	Interval(double s, double e) {
		start = s;
		finish = e;
	}

	public double getTime() {
		return finish - start;
	}

	public double compareTo(Object arg0) {
		Interval other = (Interval) arg0;
		return this.finish - other.finish;
	}

	//checks if given interval overlaps with the current one
	public boolean doesOverLap(Interval x) {
		if (finish <= x.start || x.finish <= start)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.start + " " + this.finish + "; ";
	}
}

/**
 * Find the Minimum gap in the timeline that could be left un-used
 * @author Lakshmi Ravi
 * @author Aarti Gorade
 * 
 */
public class MinimumGap {
	Interval[] activities;
	int activityCount;
	double timelineStart;
	double timelineEnd;
	//time used holds the maximum time that could be used for all activities before 'i' ending with activity 'i' 
	Double[] timeUsed;

	/**
	 * Prints the start and end time of every activity
	 */
	public void printActivities() {
		for (int i = 0; i < activityCount; i++) {
			System.out.println(activities[i]);
		}
	}

	/**
	 * Function to sort the elements
	 * @param activities
	 * @param start
	 * @param end
	 * @return intervals sorted on their finish time
	 */
	public Interval[] mergeSort(Interval[] activities, int start, int end) {
		if (start > end) {
			return null;
		}
		if (start == end) {
			Interval[] p = new Interval[1];
			p[0] = activities[start];
			return p;
		}
		int mid = start + (end - start) / 2;
		Interval[] p1 = mergeSort(activities, start, mid);
		Interval[] p2 = mergeSort(activities, mid + 1, end);
		return Merge(p1, p2);
	}

	/**
	 * Merge two sorted arrays
	 * 
	 * @param i1
	 *            - first sorted array
	 * @param i2
	 *            - second sorted array
	 * @return sorted array
	 */
	public Interval[] Merge(Interval[] i1, Interval[] i2) {
		if (i1 == null) {
			return i2;
		}
		if (i2 == null) {
			return i1;
		}
		Interval[] all = new Interval[i1.length + i2.length];
		int i = 0, j = 0, k = 0;

		while (i < i1.length && j < i2.length) {
			// Check what comes before and insert in the major array
			if (i1[i].compareTo(i2[j]) <= 0) {
				all[k++] = i1[i++];
			} else {
				all[k++] = i2[j++];
			}
		}
		while (i < i1.length) {
			all[k++] = i1[i++];
		}
		while (j < i2.length) {
			all[k++] = i2[j++];
		}
		return all;
	}

	
	public void printArray(Integer[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println("");
	}

	public void printArray(Double[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println("");
	}

	

	public double findMax(Double[] minTime2) {
		double max = minTime2[0];
		for (int i = 1; i < minTime2.length; i++) {
			if (minTime2[i] > max) {
				max = minTime2[i];
			}
		}
		return max;
	}

	/**
	 * Finds the minimum time-gap possibly left idle in-between the activities
	 * @return the minimum gap
	 */
	public int minTime() {
		// sort the activities on the basis of their end time - n.log(n)
		// operation
		activities = mergeSort(activities, 0, activityCount - 1);

		double totalTime = timelineEnd - timelineStart;
		for (int i = 0; i < activityCount; i++) {
			timeUsed[i] = activities[i].getTime();
			//for all activities before i, select activity that is compatible with i
			// and has highest timeUsed
			
			double maxTimeUsedPrevious =0;
			for(int j=i-1;j>=0;j--){
				if(!activities[i].doesOverLap(activities[j])){
					 if ( maxTimeUsedPrevious < timeUsed[j]){
						 maxTimeUsedPrevious = timeUsed[j];
					 }
				}
			}
			timeUsed[i]+=maxTimeUsedPrevious;
		}
		return (int)( totalTime-findMax(timeUsed));
	}

	/**
	 * Get the input of activity intervals from console
	 */
	public void getInput() {
		Scanner sc = new Scanner(System.in);
		activityCount = Integer.parseInt(sc.next());
		activities = new Interval[activityCount];
		timeUsed = new Double[activityCount];
		timelineStart = Double.parseDouble(sc.next());
		timelineEnd = Double.parseDouble(sc.next());

		int i = 0;
		while (i < activityCount) {
			double s = Double.parseDouble(sc.next());
			double e = Double.parseDouble(sc.next());
			activities[i] = new Interval(s, e);
			i++;
		}
	}

	public static void main(String[] s) {
		MinimumGap mg = new MinimumGap();
		mg.getInput();
		System.out.println(mg.minTime());
		// mg.printActivities();
	}
}
