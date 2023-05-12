package p1.sort;

import p1.comparator.CountingComparator;

import java.util.Comparator;

/**
 * A sorting algorithm that uses the stoneSort algorithm.
 * <p>
 * StoneSort is a variation of the bubbleSort algorithm where "low elements are falling down like stones" instead of
 * "high element are rising up like bubbles".
 * @param <T> the type of the elements to be sorted.
 */
public class StoneSort<T> implements Sort<T> {

    /**
     * The comparator used for comparing the sorted elements.
     */
    private final CountingComparator<T> comparator;

    /**
     * Creates a new {@link StoneSort} instance.
     * @param comparator the comparator used for comparing the sorted elements.
     */
    public StoneSort(Comparator<T> comparator) {
        this.comparator = new CountingComparator<>(comparator);
    }

    @Override
    public void sort(SortList<T> sortList) {
        comparator.reset();
        //TODO H2 a): remove if implemented
        for (int i=sortList.getSize()-1;i>=0;i--) {
            for (int j=sortList.getSize()-2;j>sortList.getSize()-2-i;j--) {
                if (comparator.compare(sortList.get(j),sortList.get(j+1))>0) {
                    T temp = sortList.get(j);
                    sortList.set(j,sortList.get(j+1));
                    sortList.set(j+1,temp);
                }
            }
        }
    }

    @Override
    public int getComparisonsCount() {
        return comparator.getComparisonsCount();
    }

}
