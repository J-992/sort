package ok;

import java.util.*;

public class SearchSortApp {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		System.out.println("Enter the size of the array: ");
		int size = input.nextInt();

		// CREATING DATA SETS//

		// creates a 2d array that holds 4 different types of data sets
		int[][] data = new int[4][size];

		// randomized order
		for (int x = 0; x < size; x++)
			data[0][x] = (int) (Math.random() * size) + 1;

		// ordered
		for (int x = 0; x < size; x++)
			data[1][x] = x + 1;

		// 90% ordered - first creates an ordered list and then randomizes 10% of items
		for (int x = 0; x < size; x++)
			data[2][x] = x + 1;

		for (int x = 0; x < size / 20; x++)
			data[2][(int) (Math.random() * size)] = (int) (Math.random() * size) + 1;

		// reverse order
		for (int x = 0; x < size; x++)
			data[3][x] = size - x;

		int start = 0;
		int end = data[0].length - 1;

		// Display the data sets prior to running the algorithms
		for (int[] list : data) {

			// make sure console output is only with smaller data sets
			if (size <= 25)
				System.out.println(Arrays.toString(list));
			else
				System.out.println("Array Created");

		}

		// prompt the type of algorithm
		System.out.println("Select: \n1) - Search:\n  or  \n2) - Sort:");
		int type = input.nextInt();

		// searching algorithms
		if (type == 1) {

			// ask for specific searching algorithm
			System.out.println("Enter the type of search algorithm: "
					+ "\n1) - Linear Search\n2) - Binary Search\n3) - Exponential Search");
			int algorithm = input.nextInt();

			// stores the index of the item to be fount (-1 means the item is not on the
			// list)
			int index = 0;

			// ask user for integer to be found
			System.out.println("Enter item to be found: ");
			int item = input.nextInt();

			// begin timer for analysis
			long timeStart = System.nanoTime();

			// run the selected algorithm
			if (algorithm == 1)
				index = Search.LinearSearch(data[1], item);
			else {

				// Arrays.sort(data[0]); //not needed if using the ordered data set - data[1]

				if (algorithm == 2)
					index = Search.BinarySearch(data[1], item);
				else if (algorithm == 3)
					index = Search.ExponentialSearch(data[1], item);

			}

			// displays the elapsed time and the result of the search
			System.out.printf("Elapsed time: %d nanoseconds\n", System.nanoTime() - timeStart);

			if (index == -1)
				System.out.println("Item not found");
			else
				System.out.println("Item found in index " + index);

		}

		// sorting Algorithms
		else {
			// prompt for the specific searching algorithm
			System.out.println("Enter the type of sort algorithm:\n" + "1) Bubble Sort\n" + "2) Selection Sort\n"
					+ "3) Insertion Sort\n" + "4) Counting Sort\n" + "5) Bucket Sort\n" + "6) Radix Sort\n"
					+ "7) Shell Sort\n" + "8) Quick Sort\n" + "9) Merge Sort\n");

			int algorithm = input.nextInt();

			for (int[] list : data) {
				long timeStart = System.nanoTime();

				// run the selected algorithm; not e that some algorithms may require additional
				// arguments
				if (algorithm == 1)
					Sort.bubbleSort(list);
				else if (algorithm == 2)
					Sort.selectionSort(list);
				else if (algorithm == 3)
					Sort.insertionSort(list);
				else if (algorithm == 4)
					Sort.countingSort(list, size);
				else if (algorithm == 5)
					Sort.bucketSort(list, size);
				else if (algorithm == 6)
					Sort.radixSort(list, size);
				else if (algorithm == 7)
					Sort.shellSort(list);
				else if (algorithm == 8)
					Sort.quickSort(list, start, end);
				else if (algorithm == 9)
					Sort.mergeSort(list, start, end);

				// displays the elapsed time and the result of the sort
				System.out.printf("Elapsed time: %d nanoseconds \n", System.nanoTime() - timeStart);

				// limit console output to only smaller data sets
				if (size <= 25)
					System.out.println(Arrays.toString(list));
				else
					System.out.println("Array sorted");

			}
		}

	}
}
