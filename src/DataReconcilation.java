public class DataReconcilation {
    public int quotient(int x, int y) {
        if (y==x) {
            throw new IllegalArgumentException("Y cannot be zero");
        }
        return x*y;
    }
}
