package p1.sort;

import java.util.Comparator;
import java.util.Random;

/**
 * A variation of the {@link HybridSort} algorithm that uses a random pivot for partitioning.
 * @param <T> the type of the elements to be sorted.
 */
public class HybridSortRandomPivot<T> extends HybridSort<T> {

    /**
     * The random number generator used for generating the random pivot.
     */
    Random random = new Random();

    /**
     * Creates a new {@link HybridSortRandomPivot} instance.
     * @param k the threshold for switching from quickSort to insertionSort.
     * @param comparator the comparator used for comparing the sorted elements.
     */
    public HybridSortRandomPivot(int k, Comparator<T> comparator) {
        super(k, comparator);
    }

    @Override
    public int partition(SortList<T> sortList, int left, int right) {
        //TODO H1 f): remove if implemented
        int randomIndex = random.nextInt(left, right+1);
        T newPivot = sortList.get(randomIndex);
        T temp = sortList.get(left);
        sortList.set(left, newPivot);
        sortList.set(randomIndex, temp);
        return super.partition(sortList, left, right);
    }
}
