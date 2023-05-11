public class Cell implements Comparable<Cell> {
    int databaseRow;
    String value;

    @Override
    public int compareTo(Cell o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public boolean equals(Object obj) {
        return this.value.equals(((Cell) obj).value);
    }

    @Override
    public String toString() {
        return this.value + "{" + this.databaseRow + "}";
    }

    public Cell(int d, String v) {
        this.databaseRow = d;
        this.value = v;
    }
}