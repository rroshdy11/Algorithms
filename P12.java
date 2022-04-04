import java.util.Arrays;
import java.util.Comparator;

public class P12{

	public static void main(String[] args) {

		Point[] points = new Point[6];

		points[0] = new Point(2, 3);
		points[1] = new Point(12, 30);
		points[2] = new Point(40, 50);
		points[3] = new Point(5, 1);
		points[4] = new Point(12, 10);
		points[5] = new Point(3, 4);

		double minDist = closest(points);
		System.out.println(minDist);
		double minDistBruteForce = bruteForce(points);
		System.out.println(minDistBruteForce);

	}

	//A utility function to find the distance between two points
	private static double dist(Point p1, Point p2) {
		return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y)* (p1.y - p2.y));
	}

	//A Brute Force method to return the smallest distance between two points
	//in P[] of size n
	public static double bruteForce(Point[] P) {
		double min = Double.MAX_VALUE;
		int n = P.length;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				min = Math.min(min, dist(P[i], P[j]));
			}
		}
		return min;
	}

	//The main function that finds the smallest distance
	//This method mainly uses closestUtil()
	public static double closest(Point[] P) {
		System.out.println("Before sorting:");
		System.out.println(Arrays.toString(P));
		Arrays.sort(P, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				return p1.x - p2.x;
			}
		});
		System.out.println("After sorting in x axis:");
		System.out.println(Arrays.toString(P));

		return closestUtil(P, 0, P.length - 1);
	}

	//A recursive function to find the smallest distance. The array P contains
	//all points sorted according to x coordinate
	private static double closestUtil(Point[] P, int begin, int end) {
		int n = end - begin + 1;
		if (n <= 3) {	//If there are 2 or 3 points, then use brute force
			return bruteForce(P);
		}

		int mid = n/2;		//Find the middle point
		Point midPoint = P[mid];

		//Consider the vertical line passing through the middle point
	   //calculate the smallest distance dl on left of middle point and
	   //dr on right side
		double left = closestUtil(P, begin, mid);
		double right = closestUtil(P, mid, end);

		//Find the smaller of two distances
		double distMin = Math.min(left, right);
		Point[] strip = new Point[n];
		int stripLen = 0;
		
		//Build an array strip[] that contains points close (closer than d)
	   //to the line passing through the middle point
		for (int i = 0; i < n; i++) {
			if (Math.abs(P[i].x - midPoint.x) < distMin) {
				strip[stripLen] = P[i];
				stripLen++;
			}
		}

		//Find the closest points in strip.  Return the minimum of d and closest
	   //distance is strip[]
		return Math.min(distMin, stripCloest(strip, stripLen, distMin));
	}

	//A utility function to find the distance between the closest points of
	//strip of given size. All points in strip[] are sorted according to
	//y coordinate. They all have an upper bound on minimum distance as d.
	//Note that this method seems to be a O(n^2) method, but it's a O(n)
	//method as the inner loop runs at most 6 times
	private static double stripCloest(Point[] strip, int stripLen,
			double distMin) {
		
		Arrays.sort(strip, 0, stripLen, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				return p1.y - p2.y;
			}
		});

		System.out.println("After sorting in y axis for strip:");
		System.out.println(Arrays.toString(strip));
		double min = distMin;
		
		//O(C*n) time complexity, not O(n^2)
		//Pick all points one by one and try the next points till the difference
	   //between y coordinates is smaller than d.
	   //This is a proven fact that this loop runs at most 6 times
		for (int i=0; i<stripLen; i++) {
			for(int j=i+1; j<stripLen && (strip[j].y - strip[i].y < min); j++) {
				min = Math.min(min, dist(strip[i], strip[j]));
			}
		}
		
		return min;
	}

	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "[x=" + x + ", y=" + y + "]";
		}
	}

}