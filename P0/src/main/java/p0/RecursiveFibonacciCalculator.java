package p0;

public class RecursiveFibonacciCalculator implements FibonacciCalculator {

    public RecursiveFibonacciCalculator() {
        super();
    }

    @Override
    public int get(int n) {
        //TODO H5: remove if implemented
        //throw new UnsupportedOperationException("Not implemented yet");
        if (n<=0) throw new IllegalArgumentException("n must be positive!");
        else return calculateFibonacci(n);
    }
    private int calculateFibonacci(int n) {
        if (n<=1) return n;
        return calculateFibonacci(n-1)+calculateFibonacci(n-2);
    }
}
