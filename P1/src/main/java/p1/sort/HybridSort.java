package p1.sort;

import p1.comparator.CountingComparator;

import java.util.Comparator;

/**
 * A hybrid sorting algorithm. It uses a combination of quickSort and insertionSort.
 * <p>
 * quickSort is used for sorting the lists of size greater than or equal to k.
 * <p>
 * insertionSort is used for sorting the lists of size less than k.
 *
 * @param <T> the type of the elements to be sorted.
 *
 * @see Sort
 */
public class HybridSort<T> implements Sort<T> {

    /**
     * The threshold for switching from quickSort to insertionSort.
     */
    private int k;

    /**
     * The comparator used for comparing the sorted elements.
     */
    private final CountingComparator<T> comparator;

    /**
     * Creates a new {@link HybridSort} instance.
     *
     * @param k          the threshold for switching from quickSort to insertionSort.
     * @param comparator the comparator used for comparing the sorted elements.
     */
    public HybridSort(int k, Comparator<T> comparator) {
        this.k = k;
        this.comparator = new CountingComparator<>(comparator);
    }

    @Override
    public void sort(SortList<T> sortList) {
        comparator.reset();
        quickSort(sortList, 0, sortList.getSize() - 1);
    }

    @Override
    public int getComparisonsCount() {
        return comparator.getComparisonsCount();
    }

    /**
     * Returns the current threshold for switching from quickSort to insertionSort.
     * @return the current threshold for switching from quickSort to insertionSort.
     */
    public int getK() {
        return k;
    }

    /**
     * Sets the threshold for switching from quickSort to insertionSort.
     * @param k the new threshold.
     */
    public void setK(int k) {
        this.k = k;
    }

    /**
     * Sorts the given {@link SortList} using the quickSort algorithm.
     * It will only consider the elements between the given left and right indices (both inclusive).
     * Elements with indices less than left or greater than right will not be altered.
     * <p>
     * Once the size of the list is less than k, the algorithm switches to insertionSort.
     * @param sortList the {@link SortList} to be sorted.
     * @param left The leftmost index of the list to be sorted.
     * @param right The rightmost index of the list to be sorted.
     */
    public void quickSort(SortList<T> sortList, int left, int right) {
        //TODO H1 d): remove if implemented
        if (left<right) {
            if ((right-left+1)<getK()) insertionSort(sortList, left, right);
            else {
                int p = partition(sortList, left, right);
                quickSort(sortList, left, p);
                quickSort(sortList, p+1, right);
            }
        }
    }

    /**
     * Partitions the given {@link SortList} between the given left and right indices (both inclusive).
     * Elements with indices less than left or greater than right will not be altered.
     * <p>
     * The pivot is the element at the given left index.
     *
     * @param sortList the {@link SortList} to be partitioned.
     * @param left The leftmost index of the list to be partitioned.
     * @param right The rightmost index of the list to be partitioned.
     * @return An index between left and right (both inclusive) such that all elements to the left or at the index are less than or equal to the pivot,
     * and all elements to the right of the index are greater than or equal to the pivot.
     */
    public int partition(SortList<T> sortList, int left, int right) {
        //TODO H1 d): remove if implemented
        T pivot = sortList.get(left);
        int pl = left-1;
        int pr = right+1;
        int p = -1;
        while (pl<pr) {
            do pl++;
            while (comparator.compare(sortList.get(pl),pivot)<0);
            do pr--;
            while (comparator.compare(sortList.get(pr),pivot)>0);
            if (pl<pr) {
                T temp = sortList.get(pr);
                sortList.set(pr,sortList.get(pl));
                sortList.set(pl,temp);
            }
            p = pr;
        }
        return p;
    }

    /**
     * Sorts the given {@link SortList} using the insertionSort algorithm.
     * It will only consider the elements between the given left and right indices (both inclusive).
     * Elements with indices less than left or greater than right will not be altered.
     *
     * @param sortList the {@link SortList} to be sorted.
     * @param left The leftmost index of the list to be sorted.
     * @param right The rightmost index of the list to be sorted.
     */
    public void insertionSort(SortList<T> sortList, int left, int right) {
        //TODO H1 c): remove if implemented
        T key;
        int j;
        for (int i=left+1;i<=right;i++) {
            key = sortList.get(i);
            j = i-1;
            while (j>=left && comparator.compare(sortList.get(j),key)>0) {
                sortList.set(j+1,sortList.get(j));
                j--;
            }
            sortList.set(j+1,key);
        }
    }

}
