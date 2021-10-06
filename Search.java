package ok;

import java.util.*;

public class Search {

	public static int ExponentialSearch(int data[], int item) {
		// if x is present at the first location in the array
		if (data[0] == item)
			return 0;

		// find range for binary search by repeated doubling
		int exp = 1;
		while (exp < data.length && data[exp] <= item)
			exp *= 2;

		data = Arrays.copyOfRange(data, exp / 2, Math.min(exp, data.length));

		int index = BinarySearch(data, item);

		if (index >= 0)
			index += exp / 2;

		return index;
	}

	public static int BinarySearch(int[] data, int item) {

		int lowIndex = 0;
		int highIndex = data.length - 1;

		while (lowIndex <= highIndex) {
			int midpoint = (lowIndex + highIndex) / 2;

			if (item == data[midpoint])
				return midpoint;
			else if (item > data[midpoint])
				lowIndex = midpoint + 1;
			else if (item < data[midpoint])
				highIndex = midpoint - 1;
		}

		return -1;

	}

	public static int LinearSearch(int[] data, int item) {

		for (int x = 0; x < data.length; x++)
			return x;

		return -1;

	}
}
