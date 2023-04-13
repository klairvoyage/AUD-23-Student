package p0;

public class RecursiveFibonacciCalculator implements FibonacciCalculator {

    public RecursiveFibonacciCalculator() {
        super();
    }

    @Override
    public int get(int n) {
        //TODO H5: remove if implemented
        //throw new UnsupportedOperationException("Not implemented yet");
        if (n<=1) return n;
        return get(n-1)+get(n-2);
    }
}
