import java.util.Arrays;
import java.util.Random;

public class SortTestFramework {
    private static final Random rand = new Random();

    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = rand.nextInt(100000);
        return arr;
    }

    public static void testAll(int size, int threads) throws InterruptedException {
        runTest("bubble", BubbleSort::sort, BubbleSort::parallelSort, size, threads);
        runTest("quick", (arr) -> QuickSort.sort(arr, 0, arr.length - 1), QuickSort::parallelSort, size, threads);
        runTest("merge", MergeSort::sort, MergeSort::parallelSort, size, threads);
        runTest("selection", SelectionSort::sort, SelectionSort::parallelSort, size, threads);
    }

    private static void runTest(String name, Sorter serial, ParallelSorter parallel, int size, int threads) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            int[] arr1 = generateRandomArray(size);
            int[] arr2 = Arrays.copyOf(arr1, arr1.length);

            long t1 = System.nanoTime();
            serial.sort(arr1);
            long t2 = System.nanoTime();

            long t3 = System.nanoTime();
            parallel.sort(arr2, threads);
            long t4 = System.nanoTime();

            CSVWriter.write(name + "_results.csv",
                size + "," + threads + "," + ((t2 - t1) / 1e6) + "," + ((t4 - t3) / 1e6));
        }
    }

    interface Sorter { void sort(int[] arr); }
    interface ParallelSorter { void sort(int[] arr, int threads) throws InterruptedException; }
}
