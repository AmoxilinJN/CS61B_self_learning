public class MergeSort {


    /**
     * @param arr
     *
     * Sort the array arr using merge sort.
     * The merge sort algorithm is as follows:
     * 1. Split the collection to be sorted in half.
     * 2. Recursively call merge sort on each half.
     * 3. Merge the sorted half-lists.
     *
     */
    public static int[] sort(int[] arr) {
        // TODO: Implement merge sort
        if (arr.length > 1) {
            int[] tempLeft = new int[arr.length / 2];
            int[] tempRight = new int[arr.length - arr.length / 2];
            for (int i = 0; i < arr.length; i++) {
                if (i < arr.length / 2) {
                    tempLeft[i] = arr[i];
                } else {
                    tempRight[i - arr.length / 2] = arr[i];
                }
            }
            tempLeft = sort(tempLeft);
            tempRight = sort(tempRight);
            return merge(tempLeft,tempRight);
        }
        return arr;
    }

    /**
     * @param a
     * @param b
     *
     * Merge the sorted half-lists.
     *
     * Suggested helper method that will make it easier for you to implement merge sort.
     */
    private static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        // TODO: Implement merge
        int countA = 0;
        int countB = 0;
        while (countA != a.length || countB != b.length) {
            if (countB == b.length || (countA != a.length && a[countA] <= b[countB])) {
                c[countA + countB] = a[countA];
                countA++;
            } else {
                c[countA + countB] = b[countB];
                countB++;
            }
        }
        return c;
    }
}

