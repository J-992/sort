package ok;

import java.util.*;

public class Sort {

	// bubble sort algorithm - Bubble Sort is the simplest sorting algorithm that
	// works by repeatedly swapping the adjacent elements if they are in wrong
	// order.
	public static void bubbleSort(int[] list) {

		// makes sure the array doesn't go out of position
		for (int x = list.length - 1; x > 0; x--) {

			// sets the value of the terms that need to be swapped as false (not swapped)
			boolean swapped = false;

			// as long as the second number is less than x, the swapping algorithm continues
			for (int y = 0; y < x; y++)

				// if the numbers are swapped, set as swapped value as true
				if (list[y] > list[y + 1]) {
					swap(list, y, y + 1);
					swapped = true;
				}
			// if the numbers have not been swapped, break the loop
			if (!swapped)
				break;
		}
	}

	// The selection sort algorithm sorts an array by repeatedly finding the minimum
	// element (considering ascending order) from unsorted part and putting it at
	// the beginning.
	public static void selectionSort(int[] list) {

		for (int x = 0; x < list.length - 1; x++) {

			int smallest = x;

			for (int y = x + 1; y < list.length; y++) {

				// checks to see if the current number is smaller than the smallest number
				if (list[y] < list[smallest]) {
					smallest = y;
				}
			}

			// performs the swap
			swap(list, x, smallest);
		}

	}

	// The array is virtually split into a sorted and an unsorted part. Values from
	// the unsorted part are picked and placed at the correct position in the sorted
	// part.
	public static void insertionSort(int[] list) {

		// goes through the entire list of numbers
		for (int x = 1; x < list.length; x++) {

			// goes through each individual number to see if they're in order
			for (int y = x - 1; y >= 0; y--)

				// if the number is not in the right spot, then swap
				// if the number before: (list[y]) is greater than the number after: (list[x]),
				// then swap
				if (list[x] < list[y])

					swap(list, x--, y);

				// if the numbers before are all in the correct order, then don't swap
				else

					break;

		}

	}

	// Counting sort is a sorting technique based on keys between a specific range.
	// It works by counting the number of objects having distinct key values (kind
	// of hashing). Then doing some arithmetic to calculate the position of each
	// object in the output sequence.
	public static void countingSort(int[] list, int range) {

		// Create a count array to store count of individual
		// characters and initialize count array as 0
		int[] count = new int[range + 1];

		// store count of each character
		for (int x = 0; x < list.length; x++)
			count[list[x]]++;

		int index = 0;

		// Change count[x] so that count[x] now contains actual
		// position of this character in output array
		for (int x = 0; x < count.length; x++)

			if (count[x] > 0)

				// Copy the output array to list, so that list now
				// contains sorted characters
				for (int y = 0; y < count[x]; y++) {
					list[index] = x;
					index++;
				}

	}

	// Bucket Sort is a sorting algorithm that divides the unsorted array elements
	// into several groups called buckets. Each bucket is then sorted by using any
	// of the suitable sorting algorithms or recursively applying the same bucket
	// algorithm.
	public static void bucketSort(int[] list, int range) {

		// Use buckets (groups of 10 numbers (ex. 10-19)) to sort a list set of numbers

		// To find the number of buckets you need, take the range of the list set
		// (rounded to the nearest ten) and square root it..

		int numBuckets = (int) Math.sqrt(range) + 1;

		@SuppressWarnings("unchecked")

		// like an array but can be changing in size
		// creates the array
		ArrayList<Integer>[] bucket = new ArrayList[numBuckets];

		// builds the array (adds values)
		for (int x = 0; x < numBuckets; x++)

			bucket[x] = new ArrayList<Integer>();

		for (int x = 0; x < list.length; x++)

			// add the value of the list array at index x to the bucket array at index x
			bucket[list[x] / numBuckets].add(list[x]);

		int index = 0;

		// After using the bucket sort, use insertion sort to get the numbers in the
		// buckets to their exact “least-to-greatest” values, and then rebuild the
		// array in that least-greatest order.
		for (int x = 0; x < numBuckets; x++) {

			if (bucket[x].size() > 0) {

				// builds a bucket that holds the data to sort
				int[] tempBucket = new int[bucket[x].size()];

				for (int y = 0; y < tempBucket.length; y++)

					tempBucket[y] = bucket[x].get(y);

				// uses insertion sort to sort the values in the temp bucket
				insertionSort(tempBucket);

				// moves the position of the temp bucket to take the values of the original
				// buckets
				for (int y = 0; y < tempBucket.length; y++)

					// grabs the data and sorts it
					list[index++] = tempBucket[y];
			}
		}

	}

	// This array is sorted by the digits from most significant digit to least
	// significant digit,
	// The 100s to the 10s and finally to the 1s.
	public static void radixSort(int[] list, int range) {

		// declare the most significant digit
		int msd = (int) (Math.log10(range) + 1);

		// Setting up the digit in the Arraylist
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] digit = new ArrayList[10];

		// Go through the list and get the value from the list
		for (int x = 0; x < 10; x++)
			digit[x] = new ArrayList<Integer>();

		// Go through the list and check if the integers are in order
		for (int x = 0; x < msd; x++) {

			// sort the 100s
			for (int y = 0; y < list.length; y++)
				digit[(int) (list[y] / Math.pow(10, x) % 10)].add(list[y]);

			// Go through the list and check if there is a empty cell , if yes plug in a
			// value to the 100s
			int index = 0;
			for (int y = 0; y < 10; y++)
				while (!digit[y].isEmpty()) {
					// removes the first item in the array list
					// because it keeps repeating, it removes the top item every time it gets looped
					// it keeps looping around until it removes all the items in the list
					list[index++] = digit[y].remove(0);

				}
		}

	}

	// The Shell Sort algorithm is a derivative of the Bubble Sort algorithm; rather
	// than comparing adjacent items, it compares items that are a ‘gap’ length
	// apart; the gap decreases as the algorithm proceeds
	public static void shellSort(int[] list) {

		boolean swapped;

		// the gap will be half the length of the given dataset of number values
		int gap = list.length / 2;

		// conditional loop
		// keeps repeating as long as there are numbers that still have to be swapped
		do {

			do {

				// sets the value of the numbers being swapped to not swapped yet
				swapped = false;

				// sets the number of comparisions needed
				for (int x = 0; x < list.length - gap; x++) {

					// makes sure the numbers are in order

					// if the leftmost term being checked is greater than the rightmost term being
					// checked, then swap those 2 numbers
					if (list[x] > list[x + gap]) {

						// swaps the numbers
						swap(list, x, x + gap);

						// sets the value of those numbers to already swapped
						swapped = true;
					}
				}

			} while (swapped);

			// once the comparisons have went through the dataset, the gap halves and goes
			// through the dataset again
			gap /= 2;

			// keeps repeating as long as the gap is less than or equal to 1 in order to
			// check if all the numbers are in the right place
		} while (gap >= 1);
	}

	// The QuickSort algorithm is a recursive algorithm (the method calls itself up
	// to run again); the algorithm establishes ‘pivot’ points to create sections
	// that are either less than or more than the ‘pivot’
	public static void quickSort(int[] list, int start, int end) {

		int left = start;
		int right = end;

		// while the left side and right side do not overlap (pivot point), keep
		// repeating
		while (left != right) {

			// if the right side is larger than the value on the left, then move down a
			// value to compare the next
			while (list[right] > list[left])
				right--;

			// check to see if the left and right are on top of each other
			// if they still arent on top, then swap
			if (left != right) {

				// swaps the values because the left is greater than the right
				swap(list, left, right);

				// moves up the list to check the next number
				left++;

				// checks to see if the left value is smaller than the right value.
				while (list[left] < list[right])

					// move up the index to check the next value
					left++;

				// if the left side is not equal to the right side,
				if (left != right) {

					// then swap
					swap(list, left, right);

					// make the right side move down one index
					right--;

				}
			}
		}

		// calls itself using recursion instead of using a loop
		// if the values have already been read, then re-run the algorithm
		if (left > start)

			// makes sure the sorter doesnt go out of bounds
			quickSort(list, start, left - 1);

		if (right < end)

			// makes sure the sorter doesnt go out of bounds
			quickSort(list, right + 1, end);

	}

	// The Merge Sort algorithm is another recursive algorithm (the method calls
	// itself up to run again) that uses a separate ‘driver’ method to begin the
	// algorithm; the algorithm has two basic sections: 1) divide the list
	// recursively into individual parts; 2) merge the sublists back together in
	// order until you get the fully sorted list
	public static void mergeSort(int[] list, int start, int end) {

		// if there is a gap
		if ((end - start) >= 1) {

			// then find the midpoint to split the dataset in half
			int middle = (start + end) / 2;

			// call the mergesort method

			// first half of dataset (begins at the start and ends at the middle)
			mergeSort(list, start, middle);

			// second half of dataset (begins at the middle and ends at the end)
			mergeSort(list, middle + 1, end);

			// merges both sides together
			merge(list, start, end);

		}

	}

	// merges the 2 sides together
	private static void merge(int[] list, int start, int end) {

		int mergeIndex = 0;

		// finds the midpoint of the dataset
		int middle = (start + end) / 2;

		int leftIndex = start;
		int rightIndex = middle + 1;

		// each split portion of the dataset is stored in this subarray
		int[] mergedSubArray = new int[end - start + 1];

		// keeps track of both sides of the subarrays
		while (leftIndex <= middle && rightIndex <= end) {

			// checks to see if the left side is less than the right side
			if (list[leftIndex] < list[rightIndex]) {

				// keeps the first index of the dataset to the first index of the subarray
				mergedSubArray[mergeIndex] = list[leftIndex];

				// moves along one index to check the next
				leftIndex++;

				// if the left side is greater then the right, then swap the 2 values
			} else {

				// moves the right index of the dataset to the first index of the subarray
				mergedSubArray[mergeIndex] = list[rightIndex];

				// moves along the right to heck the next
				rightIndex++;

			}

			// increases the value of the merge index by 1 to sort the next place in the
			// subarray
			mergeIndex++;

		}

		// if a value on the left of the dataset is greater than the middle, then
		if (leftIndex > middle) {

			// place the value of the right index to the value on on merge index
			while (rightIndex <= end) {

				// adds the value of the right index to the merge index
				mergedSubArray[mergeIndex] = list[rightIndex];

				// moves along one index to swap the next
				mergeIndex++;

				// moves along the right index to check if it should be swapped
				rightIndex++;

			}

		} else {

			// if a value on the left of the dataset is less than the middle, then
			while (leftIndex <= middle) {

				// adds the value of the left index to the merge index
				mergedSubArray[mergeIndex] = list[leftIndex];

				// moves along one index to swap the next
				mergeIndex++;

				// moves along the left index to check if it should be swapped
				leftIndex++;
			}

		}

		// puts both arrays together
		for (int i = 0; i < mergedSubArray.length; i++)

			// combines both subarrays
			list[start + i] = mergedSubArray[i];

	}

	//performs the swap between 2 numbers
	// switch to public if u want to use it in a different class
	// sorts the index list shit
	private static void swap(int[] list, int num1, int num2) {

		// sets the temporary term as the first term
		int temp = list[num1];

		// sets the term before as the term after
		list[num1] = list[num2];

		// swaps the term
		list[num2] = temp;

	}
}
