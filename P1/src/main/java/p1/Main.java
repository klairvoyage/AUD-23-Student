package p1;

import java.util.Arrays;
import p1.card.Card;
import p1.card.CardColor;
import p1.comparator.CardComparator;
import java.util.Comparator;
import p1.sort.HybridSort;
import p1.sort.HybridOptimizer;

public class Main {
    private static final int[] array1 = {88,-4,4,25,2,0};
    private static final int[] array2 = {88,-4,4,25,2,0};
    private static final int[] array3 = {88,-4,4,25,2,0};
    public static <T> void main(String[] args) {
        System.out.println();
        System.out.println("Test für die 1a) (CardComparator): sortiert von Index 0-3");
        System.out.println("\tvorher: "+ Arrays.toString(array1));
        insertionsort1(array1, 0, 3);
        System.out.println("\tnachher: "+Arrays.toString(array1));
        System.out.println("\t-------------vs--------------");
        System.out.println("\tvorher: "+Arrays.toString(array2));
        insertionsort2(array2, 0, 3);
        System.out.println("\tnachher: "+Arrays.toString(array2)+"\n");
        System.out.println("!!! Kein Test für die 1b) (CountingComparator)\n");
        System.out.println("Test für die 1c) (Insertionsort): 6_SPADES(3) vs 5_SPADES(3)");
        //CLUBS(4)>>>SPADES(3)>>>HEARTS(2)>>>DIAMONDS(1)
        Card o1 = new Card(CardColor.SPADES, 6);
        Card o2 = new Card(CardColor.SPADES, 5);
        CardComparator test = new CardComparator();
        String s = test.compare(o1, o2)<0 ? "1.Karte ist kleiner!" : test.compare(o1, o2)>0 ?
            "2.Karte ist kleiner" : "beiden Karten sind identisch!";
        System.out.println("\t- "+s+" [ACHTUNG: Rule 5 & 6 manuell überprüfen!]\n");



        System.out.println("Test für die 1e) (HybridOptimizer):");
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a.compareTo(b);
            }
        };
        HybridSort<Integer> hybridSort = new HybridSort<>(10, comparator);
        Integer[] array = {5,4,3};
        System.out.println("\t- "+HybridOptimizer.optimize(hybridSort, array));
        System.out.println("\t- k bzw. lokales Minimum am Index: "+HybridOptimizer.optimize_P1(hybridSort, array)+" ("+
            HybridOptimizer.numberOfOperationsForK[HybridOptimizer.optimize_P1(hybridSort, array)]+
            " Lese- und Schreiboperationen)\n");



        System.out.println("Test für die 1f) (HybridSortRandomPivot):\n\t- n/a yet");
    }
    public static void insertionsort1(int[] a, int l, int r) {
        int key;
        int j;
        for (int i=l;i<=r;i++) {
            key = a[i];
            j = i-1;
            while (j>=l && a[j]>key) {
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = key;
        }
    }
    public static void insertionsort2(int[] a, int l, int r) {
        int key;
        int j;
        for (int i=l+1;i<=r;i++) {
            key = a[i];
            j = i-1;
            while (j>=l && a[j]>key) {
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = key;
        }
    }
    //System.out.println(Arrays.toString(array3));quicksort(array3, 5, 4);System.out.println(Arrays.toString(array3));
}
