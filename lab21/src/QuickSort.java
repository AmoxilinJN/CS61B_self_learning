public class QuickSort {

    /**
     * @param arr
     *
     * Sort the array arr using quicksort with the 3-scan partition algorithm.
     * The quicksort algorithm is as follows:
     * 1. Select a pivot, partition array in place around the pivot.
     * 2. Recursively call quicksort on each subsection of the modified array.
     */
    public static int[] sort(int[] arr) {
        quickSort(arr, 0, arr.length);
        return arr;
    }

    /**
     * @param arr
     * @param start
     * @param end
     *
     * Helper method for sort: runs quicksort algorithm on array from [start:end)
     */
    private static void quickSort(int[] arr, int start, int end) {
        // 基本情况：如果子数组长度小于等于1，则已经有序
        if (end - start <= 1) {
            return;
        }
        
        // 分区操作
        int[] boundaries = partition(arr, start, end);
        int lessEnd = boundaries[0];      // 小于部分的结束索引
        int greaterStart = boundaries[1]; // 大于部分的开始索引
        
        // 递归排序小于pivot的部分
        quickSort(arr, start, lessEnd);
        
        // 递归排序大于pivot的部分
        quickSort(arr, greaterStart, end);
        
        // 注意：等于pivot的部分不需要排序，因为所有元素都相等
    }

    /**
     * @param arr
     * @param start
     * @param end
     *
     * Partition the array in-place following the 3-scan partitioning scheme.
     * You may assume that first item is always selected as the pivot.
     * 
     * Returns a length-2 int array of indices:
     * [end index of "less than" section, start index of "greater than" section]
     *
     * Most of the code for quicksort is in this function
     */
    private static int[] partition(int[] arr, int start, int end) {
        // 选择第一个元素作为pivot
        int pivot = arr[start];
        
        // 第一次扫描：统计各部分的元素个数
        int lessCount = 0;
        int equalCount = 0;
        int greaterCount = 0;
        
        for (int i = start; i < end; i++) {
            if (arr[i] < pivot) {
                lessCount++;
            } else if (arr[i] == pivot) {
                equalCount++;
            } else {
                greaterCount++;
            }
        }
        
        // 创建临时数组存储原始数据
        int[] temp = new int[end - start];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = arr[start + i];
        }
        
        // 计算各部分的边界
        int lessEnd = start + lessCount;
        int greaterStart = lessEnd + equalCount;
        
        // 第二次扫描：放置小于pivot的元素
        int lessIndex = start;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] < pivot) {
                arr[lessIndex++] = temp[i];
            }
        }
        
        // 第三次扫描：放置等于pivot的元素
        int equalIndex = lessEnd;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == pivot) {
                arr[equalIndex++] = temp[i];
            }
        }
        
        // 第四次扫描：放置大于pivot的元素
        int greaterIndex = greaterStart;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] > pivot) {
                arr[greaterIndex++] = temp[i];
            }
        }
        
        // 返回边界索引
        return new int[]{lessEnd, greaterStart};
    }

    private static int swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        return b;
    }
}
