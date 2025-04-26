public class BubbleSort {
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void parallelSort(int[] arr, int numThreads) throws InterruptedException {
        Thread[] threads = new Thread[numThreads];
        int length = arr.length / numThreads;

        for (int t = 0; t < numThreads; t++) {
            final int start = t * length;
            final int end = (t == numThreads - 1) ? arr.length : start + length;

            threads[t] = new Thread(() -> sort(arr, start, end));
            threads[t].start();
        }

        for (Thread thread : threads) thread.join();
        sort(arr); // passo final para garantir ordenação global
    }

    private static void sort(int[] arr, int start, int end) {
        for (int i = start; i < end-1; i++) {
            for (int j = start; j < end-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
