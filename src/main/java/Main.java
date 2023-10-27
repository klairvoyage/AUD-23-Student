import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("minimale Anzahl an Schritten: " + hanoi(0,0,2).toArray().length);
        System.out.println(Arrays.deepToString(hanoi(0,0,2).toArray()));
    }

    public static ArrayList<int[]> hanoi(int n, int i, int j) {
        if (i==j) return new ArrayList<>();
        else if (n==0) {
            ArrayList<int[]> yo = new ArrayList<>();
            yo.add(new int[] {i, j});
            return yo;
        } else {
            int k = 3 - i - j;
            ArrayList<int[]> a = hanoi(n-1, i, k);
            ArrayList<int[]> b = hanoi(n-1, k, j);
            ArrayList<int[]> yo = new ArrayList<>(a);
            yo.add(new int[] {i, j});
            yo.addAll(b);
            return yo;
        }
    }
}
