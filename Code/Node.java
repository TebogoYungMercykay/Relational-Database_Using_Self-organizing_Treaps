import java.util.Random;

public class Node<T extends Comparable<T>> {
    public T data;
    public Node<T> left;
    public Node<T> right;
    public int priority = getPriority();
    private static Random random = new Random(123456789);
    private static int getPriority() {
        return random.nextInt(128);
    }
    @Override
    public String toString() {
        return this.data.toString() + "[" + this.priority + "]";
    }

    /*
     * Don't change anything above this line
     */

    public Node(T d) {
        this.data = d;
        this.right = null;
        this.left = null;
    }

    public T getData() {
        return this.data;
    }
}