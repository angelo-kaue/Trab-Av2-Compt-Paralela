import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] sizes = {1000, 5000, 10000};
        int[] threads = {2, 4};

        for (int size : sizes) {
            for (int t : threads) {
                SortTestFramework.testAll(size, t);
                System.out.println("Testes com size " + size + " e threads " + t + " concluídos.");
            }
        }
    

    try {
    ProcessBuilder pb = new ProcessBuilder("python", "graficos.py");
    pb.inheritIO(); // isso faz com que a janela do Python apareça normalmente
    pb.start();
} catch (IOException e) {
    e.printStackTrace();
}

}
}
