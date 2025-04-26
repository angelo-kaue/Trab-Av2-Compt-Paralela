import java.util.concurrent.*;

public class QuickSort {

    public static void quickSortSerial(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortSerial(arr, low, pi - 1);
            quickSortSerial(arr, pi + 1, high);
        }
    }

    public static void quickSortParallel(int[] arr, int low, int high, int threads) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        CountDownLatch latch = new CountDownLatch(1);

        executor.submit(() -> {
            parallelQuickSort(arr, low, high, executor);
            latch.countDown();
        });

        latch.await(); // espera a thread principal terminar
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }

    private static void parallelQuickSort(int[] arr, int low, int high, ExecutorService executor) {
        if (low < high) {
            int pi = partition(arr, low, high);

            // Executa recursivamente em paralelo
            executor.submit(() -> parallelQuickSort(arr, low, pi - 1, executor));
            executor.submit(() -> parallelQuickSort(arr, pi + 1, high, executor));
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }


    // Wrapper para SortTestFramework - método serial
    public static void sort(int[] arr, int low, int high) {
        quickSortSerial(arr, low, high);
    }

    // Wrapper para SortTestFramework - método paralelo
    public static void parallelSort(int[] arr, int threads) {
        try {
            quickSortParallel(arr, 0, arr.length - 1, threads);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
