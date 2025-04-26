import java.util.concurrent.*;

public class MergeSort {
    public static void sort(int[] arr) {
        if (arr.length <= 1) return;

        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);

        sort(left);
        sort(right);
        merge(arr, left, right);
    }

    public static void parallelSort(int[] arr, int numThreads) {
        try (ForkJoinPool pool = new ForkJoinPool(numThreads)) {
            pool.invoke(new MergeTask(arr));
            pool.shutdown();
        }
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    private static class MergeTask extends RecursiveAction {
        int[] arr;

        MergeTask(int[] arr) { this.arr = arr; }

        @Override
        protected void compute() {
            if (arr.length <= 1000) {
                sort(arr);
                return;
            }
            int mid = arr.length / 2;
            int[] left = new int[mid];
            int[] right = new int[arr.length - mid];
            System.arraycopy(arr, 0, left, 0, mid);
            System.arraycopy(arr, mid, right, 0, arr.length - mid);

            MergeTask leftTask = new MergeTask(left);
            MergeTask rightTask = new MergeTask(right);
            invokeAll(leftTask, rightTask);
            merge(arr, left, right);
        }
    }
}
