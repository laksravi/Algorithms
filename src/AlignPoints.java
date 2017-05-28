import java.util.Scanner;

/**
 * 
 * @author Lakshmi Ravi
 * @author Aarti Gorade 
 * 2D- point in space
 * 
 */
class Point {
	int m_x;
	int m_y;

	Point(int x, int y) {
		m_x = x;
		m_y = y;
	}

}

/**
 * 
 * @author Lakshmi Ravi
 * @author Aarti Gorade 
 * Line in the 2D space
 */
class Line {
	double slope;
	double xIntercept;
	double Yintercept;

	Line(double sl, double xint, double yint) {
		slope = sl;
		xIntercept = xint;
		Yintercept = yint;
	}

	/**
	 * Get perpendicular bisector of a given line
	 * 
	 * @param x1
	 *            : pt-1 X and Yco-ordinate
	 * @param y1
	 * @param x2
	 *            : pt-2 X and Y co-ordinate
	 * @param y2
	 * @return bisector Line
	 */
	static Line getPerpendicularBisector(int x1, int y1, int x2, int y2) {
		double s1 = (double) (y2 - y1) / (double) (x2 - x1);

		double sPer = (-1.0 / s1);
		double xmid = ((double) (x2 + x1)) / 2.0;
		double ymid = ((double) (y2 + y1)) / 2.0;
		double Xinter = Double.MAX_VALUE;
		if (sPer != 0.0) {
			Xinter = (xmid) - (ymid / sPer);
		}

		double Yinter = Double.MAX_VALUE;
		if (s1 != 0.0) {
			Yinter = sPer * xmid - ymid;
		}
		Line p = new Line(sPer, Xinter, Yinter);
		return p;
	}

	/**
	 * define comparator for the line
	 * 
	 * @param p
	 *            line to be compared
	 * @return current line is greater : boolean value
	 */
	public boolean isGreater(Line p) {
		if (this.slope == p.slope) {
			if (this.xIntercept == p.xIntercept) {
				return this.Yintercept >= p.Yintercept;
			}
			return this.xIntercept >= p.xIntercept;
		}
		return this.slope >= p.slope;
	}

	@Override
	public boolean equals(Object l) {
		Line lin = (Line) l;
		if (lin.slope == this.slope && this.xIntercept == lin.xIntercept
				&& this.Yintercept == lin.Yintercept)
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "" + this.slope + " " + this.xIntercept + " Y "
				+ this.Yintercept;
	}
}

/**
 * Checks the alignment of the given points in space
 * 
 * @author Lakshmi Ravi
 * @author Aarti Gorade
 * 
 */
public class AlignPoints {

	Point points[];
	int count;
	int maxPointAligned;

	/**
	 * Performs merge sort on the array of lines
	 * 
	 * @param lines
	 *            to be sorted
	 * @param start
	 *            : start index
	 * @param end
	 *            : end index
	 * @return sorted line array
	 */
	private Line[] mergeSort(Line[] lines, int start, int end) {
		if (start > end) {
			return null;
		}
		if (start == end) {
			Line[] oneL = new Line[1];
			oneL[0] = lines[start];
			return oneL;
		}
		int mid = start + (end - start) / 2;
		Line[] left = mergeSort(lines, start, mid);
		Line[] right = mergeSort(lines, mid + 1, end);
		return merge(left, right);
	}

	/**
	 * Merge two sorted arrays of lines
	 * 
	 * @param left
	 *            sorted line array
	 * @param right
	 *            sorted line array
	 * @return combined sorted array
	 */
	public Line[] merge(Line[] left, Line[] right) {
		Line allLines[] = new Line[left.length + right.length];
		int i = 0, j = 0, k = 0;
		// Add the lines in the order of their slope
		while (i < left.length && j < right.length) {
			if (left[i].isGreater(right[j])) {
				allLines[k++] = left[i++];
			} else {
				allLines[k++] = right[j++];
			}
		}
		// Add remaining lines in left and right
		while (i < left.length) {
			allLines[k++] = left[i++];
		}
		// Add remaining lines to right
		while (j < right.length) {
			allLines[k++] = right[j++];
		}
		return allLines;
	}

	/**
	 * Align the points by computing the perpendicular bisector
	 */
	public void getAlignedPoints() {
		// Creates a n*n-1 bisector : O(n ^2)
		Line[] perBisect = new Line[((count) * (count - 1)) / 2];
		int bisectCount = 0;
		// Get the bisector of that line : O( n^2)
		for (int i = 0; i < count - 1; i++) {
			for (int j = i + 1; j < count; j++) {
				perBisect[bisectCount++] = Line.getPerpendicularBisector(
						points[i].m_x, points[i].m_y, points[j].m_x,
						points[j].m_y);

			}
		}

		// 2.Sort the perpendicular bisector using merge sort
		// This step could be done in O(n^2 . log (n^2) ) = O( n^2 . log n)
		perBisect = mergeSort(perBisect, 0, bisectCount - 1);
		// 3.count the lines that are same
		int mCount = 1;
		for (int k = 1; k < bisectCount; k++) {
			// Checks if the slope, and X and Y intercepts are same
			if (perBisect[k].equals(perBisect[k - 1])) {
				mCount += 1;
			} else {
				if (mCount > maxPointAligned) {
					maxPointAligned = mCount;
				}
				mCount = 1;
			}
		}
		if (mCount > maxPointAligned) {
			maxPointAligned = mCount;
		}

		System.out.println(maxPointAligned);
	}

	/**
	 * Get input from system console
	 */
	public void getInput() {
		Scanner scan = new Scanner(System.in);
		count = Integer.parseInt(scan.next());
		points = new Point[count];
		int i = 0;
		// Get the points
		while (i < count && scan.hasNext()) {
			int x = Integer.parseInt(scan.next());
			int y = Integer.parseInt(scan.next());
			points[i++] = new Point(x, y);
		}
	}

	/**
	 * Utility to print points
	 */
	public void printPoints() {
		for (int i = 0; i < count; i++) {
			System.out.println(points[i].m_x + " " + points[i].m_y);
		}
	}

	/**
	 * Helper utility to print the lines
	 * 
	 * @param l
	 *            : array of lines
	 */
	public void PrintLines(Line[] l) {
		int s = l.length;
		for (int i = 0; i < s; i++) {
			System.out.println(l[i]);
		}
	}

	public static void main(String[] args) {
		AlignPoints algnPts = new AlignPoints();
		algnPts.getInput();
		algnPts.getAlignedPoints();
	}

}
