package hWFour;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.*;

public class CommonElements {
	private static int comparisons = 0;
	static Comparable[] common;

	public static void main(String[] args) {
		//Comparable[][] collections = { { "A", "B", "C"}, { "A", "B" }, { "A", "B", "C" } };
		//Comparable[][] collections = { { "A", "B", "C", "D", "E"}, { "A", "B" }, { "A", "B", "C" } };
		//Comparable[][] collections = { { "A", "B", "C"}, { "A", "B", "C" }, { "A", "B", "C" } };
		//Comparable[][] collections = { { "A", "B"}, { "A", "B", "C" } };
		Comparable[][] collections = { {3, 4, 9, 8, 12, 15, 7, 13}, {3, 15, 24, 50, 12, 9},
				{3, 78, 65, 24, 13, 9, 12}, {3, 15, 78, 14, 2, 9, 44, 12}};
		findCommonElements(collections);
	}
	
	public static int getComparisons() {

		return comparisons;
	}
	
	public static int sort(Comparable[][] collections) {
		int query = 0;
		int size = 0;
		for (int i = 0; i < collections.length; i++) {
			
			Arrays.sort((Comparable[]) collections[i]);
			size = ((Comparable[]) collections[i]).length;
			
			if (size < ((Comparable[]) collections[query]).length) {
				query = i;
			}
		}
		return query;
	}
	
	public static boolean binarySearch(Comparable[] collections,
			Comparable item) {

		boolean found = false;
		Integer position = -1;
		Integer low = 0;
		Integer mid = 0;
		Integer high = collections.length - 1;

		while (low <= high && position == -1) {
			mid = (low + high) / 2;
			comparisons++;
			if (collections[mid].compareTo(item) == 0) {
				position = mid;
				found = true;
				break;
			}
			comparisons++;
			if (collections[mid].compareTo(item) > 0) {
				high = mid - 1;
			}
			comparisons++;
			if (collections[mid].compareTo(item) < 0) {
				low = mid + 1;
			}
		}
		comparisons++;
		if (position == -1) {
			position = low;
		}

		return found;

	}
	
	public static Comparable[] findCommonElements(Comparable[][] collections) {
		int queryC = sort(collections);
		int compare;
		
		Comparable[] z = {};
		Comparable[] temp = ((Comparable[]) collections[queryC]);
		Comparable[] queryA = ((Comparable[]) collections[queryC]);
		common = new Comparable[queryA.length];
		boolean found = false;
		
		for (int i = 0; i < queryA.length; i++) {
			
			int count = 1;

			for (int j = 0; j < collections.length; j++) {
				comparisons++;
				if (j == queryC) {
					j++;
				}

				temp = (Comparable[]) collections[j];
				found = binarySearch(temp, queryA[i]);
				comparisons++;
				if (found) {
					count += 1;
				}
				comparisons++;
				if (!found) {
					break;
				}
			}
			comparisons++;
			if (count == collections.length) {
				common[i] = queryA[i];
				
			}
		}

		compare = getComparisons();
		System.out.println(Arrays.deepToString(common));
		System.out.println("Comparisons: " + compare);
		return common;
	}

}
