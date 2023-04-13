package p0;

public class IterativeFibonacciCalculator implements FibonacciCalculator {

    public IterativeFibonacciCalculator() {
        super();
    }

    @Override
    public int get(int n) {
        //TODO H5: remove if implemented
        //throw new UnsupportedOperationException("Not implemented yet");
        int prev = 0;
        int fibi = 1;
        int temp;
        for (int i=1;i<n;i++) {
            temp = fibi;
            fibi += prev;
            prev = temp;
        }
        return fibi;
    }
}
