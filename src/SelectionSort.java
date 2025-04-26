public class SelectionSort {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int temp = arr[i]; arr[i] = arr[minIdx]; arr[minIdx] = temp;
        }
    }

    public static void parallelSort(int[] arr, int numThreads) throws InterruptedException {
        int length = arr.length / numThreads;
        Thread[] threads = new Thread[numThreads];

        for (int t = 0; t < numThreads; t++) {
            int start = t * length;
            int end = (t == numThreads - 1) ? arr.length : start + length;

            threads[t] = new Thread(() -> {
                int[] temp = new int[end - start];
                System.arraycopy(arr, start, temp, 0, temp.length);
                sort(temp);
                synchronized (arr) {
                    System.arraycopy(temp, 0, arr, start, temp.length);
                }
            });
            threads[t].start();
        }

        for (Thread thread : threads) thread.join();
        sort(arr); // passo final
    }
}