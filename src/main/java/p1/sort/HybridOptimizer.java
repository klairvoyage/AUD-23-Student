package p1.sort;

import java.util.Arrays;

/**
 * Optimizes the {@link HybridSort} by trying to find the k-value with the lowest number of read and write operations..
 */
public class HybridOptimizer {

    /**
     * Optimizes the {@link HybridSort} by trying to find the k-value with the lowest number of read and write operations.
     * The method will try all possible values for k starting from 0 and return the k-value with the lowest number of read and write operations.
     * It will stop once if found the first local minimum or reaches the maximum possible k-value for the size of the given array.
     *
     * @param hybridSort the {@link HybridSort} to optimize.
     * @param array the array to sort.
     * @return the k-value with the lowest number of read and write operations.
     * @param <T> the type of the elements to be sorted.
     */
    public static <T> int optimize(HybridSort<T> hybridSort, T[] array) {
        //TODO H1 e): remove if implemented
        int temp = hybridSort.getK();
        int numberOfOperations = Integer.MAX_VALUE;
        int index = -1;
        for(int k=0;k<array.length+1;k++) {
            hybridSort.setK(k);
            ArraySortList<T> yo = new ArraySortList<>(array);
            hybridSort.sort(yo);
            if ((yo.getReadCount()+yo.getWriteCount())<=numberOfOperations) {
                numberOfOperations = yo.getReadCount() + yo.getWriteCount();
                index++;
            }
            else break;
        }
        hybridSort.setK(temp);
        return index;
    }

}
