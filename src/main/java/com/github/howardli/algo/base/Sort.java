package com.github.howardli.algo.base;

import com.github.howardli.algo.util.ArrayUtil;

import java.util.*;

/**
 * 排序算法合集
 * https://www.cnblogs.com/onepixel/p/7674659.html
 * <p>
 * 交换
 * 冒泡 - 时间n2，空间1，时间最优n，稳定
 * 快排 - 时间nlogn，空间logn(因为栈递归调用)，时间最优nlogn，不稳定，较难理解
 * <p>
 * 插入
 * 插入 - 时间n2，空间1，时间最优n，稳定
 * 希尔 - 时间n1.3，空间n，时间最优n，不稳定，难理解
 * <p>
 * 选择
 * 选择 - 时间n2，空间1，时间最优n2，不稳定
 * 堆 - 时间nlogn，空间1，时间最优nlogn，不稳定，难理解
 * <p>
 * 归并
 * 归并 - 时间nlogn，空间n，时间最优nlogn，稳定
 * <p>
 * 普通人 - 选择排序
 * 空间要求高 - 冒泡或者插入
 * 时间要求高 - 归并
 * 空间和时间 - 快排或者堆排序
 *
 *
 * 非比较排序
 * 计数排序 - 时间n+k，空间n+k，时间最优n+k，稳定。k是最大元素
 * 基数排序 -
 * 桶排序   - 时间n+k，空间n+k，时间最优n，稳定。k是桶的个数
 *
 * 当输入的元素是 n 个 0到 k 之间的整数时, 当k不是很大并且序列比较集中时，计数排序是一个很有效的排序算法。
 * 没啥用，非整型怎么办???
 */
public class Sort {
    public static void main(String[] args) {
        int bound = 50;
//        int[] arr = ArrayUtil.randomIntArray(10, bound);
        int[] arr = ArrayUtil.randomIntArray(new Random(System.currentTimeMillis()).nextInt(20)+1, 50);
//        int[] arr = ArrayUtil.sortedIntArray(8, 50, true);
//        int [] arr = new int[]{9, 42, 4, 22, 3, 39, 14, 5, 45, 35};
        System.out.println(Arrays.toString(arr));
//        CompareSort.heapSort(arr, true);
        NoCompareSort.countingSort(arr, bound);
        String realResult = Arrays.toString(arr);
        System.out.println(realResult);
        Arrays.sort(arr);
        String expectResult = Arrays.toString(arr);
        System.out.println(expectResult);
        assert expectResult.equals(realResult);
    }

    /**
     * 非比较排序，可以突破基于比较排序的时间下界，以线性时间运行，因此也称为线性时间非比较类排序
     */
    public static class NoCompareSort {
        /**
         * 冒泡排序
         * 平均时间复杂度 - n+maxVal
         * 最好时间复杂度 - n+maxVal
         * 最坏时间复杂度 - n+maxVal
         * 空间复杂度 - n+maxVal
         * 稳定
         *
         * @param arr
         * @param maxVal - 元素最大值
         */
        public static void countingSort(int[] arr, int maxVal) {
            int[] bucket = new int[maxVal+1];
            int sortedIndex = 0;
            int len = arr.length;
            int bucketLen = maxVal+1;
            for (int i = 0; i < len; i++) {
                bucket[arr[i]]++;
            }
            for (int i = 0; i < bucketLen; i++) {
                while(bucket[i]>0){
                    arr[sortedIndex++]=i;
                    bucket[i]--;
                }
            }
        }

        /**
         * 桶排序 -
         * 平均时间复杂度 - n+bucketCount
         * 最好时间复杂度 - n
         * 最坏时间复杂度 - n2
         * 空间复杂度 - n+bucketCount
         * 稳定
         *
         * @param arr
         * @param bucketSize - 桶中不同元素最大个数
         */
        public static void bucketSort(int[] arr, int bucketSize) {
            int min = arr[0], max=arr[0],len = arr.length;
            for (int i = 1; i < len; i++) {
                if(arr[i]<min){
                    min = arr[i];
                }else if(arr[i]>max){
                    max = arr[i];
                }
            }
            int bucketCount = (max-min)/bucketSize+1;
            List<List<Integer>> buckets = new ArrayList<List<Integer>>(bucketCount);
            for (int i = 0; i < bucketCount; i++) {
                buckets.add(new ArrayList<Integer>());
            }
            for (int i = 0; i < len; i++) {
                int bucketIndex = (arr[i]-min)/bucketSize;
                buckets.get(bucketIndex).add(arr[i]);
            }
            int sortedIndex = 0;
            for (int i = 0; i < bucketCount; i++) {
                List<Integer> bucketArr = buckets.get(i);
                Collections.sort(bucketArr);
                for (int i1 = 0; i1 < bucketArr.size(); i1++) {
                    arr[sortedIndex++]=bucketArr.get(i1);
                }
            }
        }




    }

    /**
     * 基于比较的排序，时间复杂度不能突破nlogn
     */
    public static class CompareSort {
        /**
         * 冒泡排序
         * 平均时间复杂度 - n2
         * 最好时间复杂度 - n
         * 最坏时间复杂度 - n2
         * 空间复杂度 - 1
         * 稳定
         *
         * @param arr
         */
        public static void bubbleSort(int[] arr, boolean debug) {
            int len = arr.length;
            for (int i = 0; i < len - 1; i++) {
                boolean swap = false;
                for (int j = 0; j < len - 1 - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int tmp = arr[j + 1];
                        arr[j + 1] = arr[j];
                        arr[j] = tmp;
                        swap = true;
                    }
                }
                if (debug) {
                    System.out.println(String.format("mid res : %s ", Arrays.toString(arr)));
                }
                if (!swap) {
                    break;
                }
            }
        }

        /**
         * 先确定最小的，最大的一步步“冒泡”到数组的尾元素
         *
         * @param arr
         * @param debug
         */
        public static void bubbleSortMinFirst(int[] arr, boolean debug) {
            int len = arr.length;
            for (int i = len - 1; i > 0; i--) {
                boolean swap = false;
                for (int j = len - 1; j > len - 1 - i; j--) {
                    if (arr[j - 1] > arr[j]) {
                        int tmp = arr[j];
                        arr[j] = arr[j - 1];
                        arr[j - 1] = tmp;
                        swap = true;
                    }
                }
                if (debug) {
                    System.out.println(String.format("mid res : %s ", Arrays.toString(arr)));
                }
                if (!swap) {
                    break;
                }
            }
        }

        /**
         * 拿第1个元素分别跟后面的元素比较，最优情况下也不能在第一轮循环中结束排序
         * 而且也不稳定，2,2,1,3
         *
         * @param arr
         */
        public static void bubbleSortBad(int[] arr) {
            int len = arr.length;
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    if (arr[i] > arr[j]) {
                        int tmp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = tmp;
                    }
                }
            }
        }

        /**
         * 选择排序
         * 平均时间复杂度 - n2
         * 最好时间复杂度 - n2
         * 最坏时间复杂度 - n2
         * 空间复杂度 - 1
         * 不稳定
         *
         * @param arr
         */
        public static void selectSort(int[] arr, boolean debug) {
            int len = arr.length;
            for (int i = 0; i < len; i++) {
                int minIndex = i;
                for (int j = i + 1; j < len; j++) {
                    if (arr[j] < arr[minIndex]) {
                        minIndex = j;
                    }
                }
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
                if (debug) {
                    System.out.println(String.format("mid res : %s ", Arrays.toString(arr)));
                }
            }
        }

        /**
         * 简单插入排序
         * 平均时间复杂度 - n2
         * 最好时间复杂度 - n
         * 最坏时间复杂度 - n2
         * 空间复杂度 - 1
         * 稳定
         *
         * @param arr
         */
        public static void insertSort(int[] arr, boolean debug) {
            int len = arr.length;
            int preIndex, current;
            for (int i = 1; i < len; i++) {
                preIndex = i - 1;
                current = arr[i];
                while (preIndex >= 0 && arr[preIndex] > current) {
                    arr[preIndex + 1] = arr[preIndex];
                    preIndex--;
                }
                arr[preIndex + 1] = current;
                if (debug) {
                    System.out.println(String.format("mid res : %s ", Arrays.toString(arr)));
                }
            }
        }

        /**
         * 希尔排序
         * 平均时间复杂度 - n1.3
         * 最好时间复杂度 - n
         * 最坏时间复杂度 - n2
         * 空间复杂度 - 1
         * 不稳定
         * todo:
         *
         * @param arr
         */
        public static void shellSort(int[] arr, boolean debug) {
            int len = arr.length;
            for (int gap = len / 2; gap > 0; gap = gap / 2) {
                for (int i = gap; i < len; i++) {
                    int j = i;
                    int current = arr[i];
                    while (j - gap >= 0 && current < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j = j - gap;
                    }
                    arr[j] = current;
                    if (debug) {
                        System.out.println(String.format("mid res(gap:%s) : %s ", gap, Arrays.toString(arr)));
                    }
                }

            }
        }

        /**
         * 归并排序
         * 平均时间复杂度 - nlogn
         * 最好时间复杂度 - nlogn
         * 最坏时间复杂度 - nlogn
         * 空间复杂度 - n
         * 稳定
         *
         * @param arr
         */
        public static void mergeSort(int[] arr, boolean debug) {
            int[] aux = new int[arr.length];
            mergeSortSub1(arr, aux, 0, arr.length - 1, debug);
        }

        private static void mergeSortSub1(int[] arr, int[] aux, int left, int right, boolean debug) {
            if (left < right) {
                int mid = (left + right) / 2;
                mergeSortSub1(arr, aux, left, mid, debug);
                mergeSortSub1(arr, aux, mid + 1, right, debug);
                mergeSortSub2(arr, aux, left, mid + 1, right, debug);
            }
        }

        /**
         * @param arr
         * @param aux
         * @param left
         * @param mid   - left < mid <=right
         * @param right
         * @param debug
         */
        private static void mergeSortSub2(int[] arr, int[] aux, int left, int mid, int right, boolean debug) {
            for (int i = left; i <= right; i++) {
                aux[i] = arr[i];
            }
            int i = left, j = mid;
            for (int k = left; k <= right; k++) {
                if (i >= mid) {
                    arr[k] = aux[j++];
                } else if (j > right) {
                    arr[k] = aux[i++];
                } else if (aux[i] <= aux[j]) {
                    arr[k] = aux[i++];
                } else {
                    arr[k] = aux[j++];
                }
            }
            if (debug) {
                System.out.println(String.format("mid res : %s ", Arrays.toString(arr)));
            }
        }

        /**
         * 空间复杂度不优，每次merge都会新增空间。
         * 严格说也不能说不优，merge调用完，可以回收相应资源。
         *
         * @param arr
         * @param debug
         */
        public static void mergeSortBad(int[] arr, boolean debug) {
            mergeSortBadSub1(arr, 0, arr.length - 1, debug);
        }


        private static void mergeSortBadSub1(int[] arr, int left, int right, boolean debug) {
            if (left < right) {
                int mid = (left + right) / 2;
                mergeSortBadSub1(arr, left, mid, debug);
                mergeSortBadSub1(arr, mid + 1, right, debug);
                mergeSortBadSub2(arr, left, mid + 1, right, debug);
            }
        }

        private static void mergeSortBadSub2(int[] arr, int left, int mid, int right, boolean debug) {
            int[] leftArr = Arrays.copyOfRange(arr, left, mid);
            int[] rightArr = Arrays.copyOfRange(arr, mid, right + 1);
            if (debug) {
                System.out.println(String.format("merge sort space : %d ", leftArr.length + rightArr.length));
            }
            int i = 0, j = 0;
            for(int k = left;k<=right;k++){
                if(i>=leftArr.length){
                    arr[k] = leftArr[j++];
                }else if(j >=rightArr.length){
                    arr[k] = leftArr[i++];
                }else if(leftArr[i] <= rightArr[j]){
                    arr[k] = leftArr[i++];
                }else{
                    arr[k] = leftArr[j++];
                }
            }
            if (debug) {
                System.out.println(String.format("mid res : %s ", Arrays.toString(arr)));
            }
        }

        /**
         * 快速排序
         * 平均时间复杂度 - nlogn
         * 最好时间复杂度 - nlogn
         * 最坏时间复杂度 - n2
         * 空间复杂度 - logn
         * 不稳定
         *
         * @param arr
         */
        public static void quickSort(int[] arr, boolean debug) {
            quickSortSub1(arr, 0, arr.length - 1, debug);
        }

        private static void quickSortSub1(int[] arr, int left, int right, boolean debug) {
            if (left < right) {
//                int parIndex = quickSortSub2(arr, left, right, debug);
                int i = left, j = right;
                int base = arr[right]; // 基准取最右，则从最左开始检查
                while(i<j){
                    while(i<j&&arr[i]<=base){
                        i++;
                    }
                    if(i<j){
                        ArrayUtil.swapArray(arr,i,j);
                        if (debug) {
                            System.out.println(String.format("mid res i=%d,j=%d: %s ",i,j, Arrays.toString(arr)));
                        }
                    }
                    while(i<j&&arr[j]>=base){
                        j--;
                    }
                    if(i<j){
                        ArrayUtil.swapArray(arr,i,j);
                        if (debug) {
                            System.out.println(String.format("mid res i=%d,j=%d: %s ",i,j, Arrays.toString(arr)));
                        }
                    }

                }
                int parIndex = i;
                if (debug) {
                    System.out.println(String.format("arr[%d]=%d settled",parIndex,arr[parIndex]));
                }
                quickSortSub1(arr, left, parIndex - 1, debug);
                quickSortSub1(arr, parIndex + 1, right, debug);
            }
        }

        private static int quickSortSub2(int[] arr, int left, int right, boolean debug) {
            int base = arr[right];
            while (left < right) {
                while (left < right && arr[left] <= base) {
                    left++;
                }
                if (left < right) {
                    int tmp = arr[right];
                    arr[right] = arr[left];
                    arr[left] = tmp;
                    right--;
                }
                if (debug) {
                    System.out.println(String.format("mid res : %s ", Arrays.toString(arr)));
                }
                while (left < right && arr[right] >= base) {
                    right--;
                }
                if (left < right) {
                    int tmp = arr[right];
                    arr[right] = arr[left];
                    arr[left] = tmp;
                    left++;
                }
                if (debug) {
                    System.out.println(String.format("mid res : %s ", Arrays.toString(arr)));
                }
            }
            return left;
        }

        /**
         * 堆排序
         * 平均时间复杂度 - nlogn
         * 最好时间复杂度 - nlogn
         * 最坏时间复杂度 - nlogn
         * 空间复杂度 - 1
         * 不稳定
         * todo:
         * @param arr
         */
        public static void heapSort(int[] arr, boolean debug) {
            heapSortBuildMaxHeap(arr,debug);
            int len = arr.length;
            for(int i = len-1;i>0;i--){
                ArrayUtil.swapArray(arr,0,i);
                len --;
                if (debug) {
                    System.out.println(String.format("mid res len=%d,i=%d : %s ",len,i, Arrays.toString(arr)));
                }
                heapSortHeapify(arr,0, len,debug);
            }
        }


        private static void heapSortHeapify(int[] arr, int i, int len, boolean debug){
            int left = 2*i+1;
            int right = 2*i+2;
            int largest = i;
            if(left<len&& arr[left]>arr[largest]){
                largest = left;
            }
            if(right<len&& arr[right]>arr[largest]){
                largest = right;
            }
            if(largest!=i){
                ArrayUtil.swapArray(arr,i,largest);
                if (debug) {
                    System.out.println(String.format("mid res len=%d,i=%d,larget=%d : %s ",len,i,largest, Arrays.toString(arr)));
                }
                heapSortHeapify(arr,largest, len,debug);
            }
        }

        private static void heapSortBuildMaxHeap(int[] arr, boolean debug){
            int len = arr.length;
            for(int i =len/2;i>=0;i--){
                heapSortHeapify(arr,i,len,debug);
            }
        }


    }
}

