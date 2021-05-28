package contar.app.tuplo;

import java.util.Arrays;

public class ImmutableTuple<T> { //TODO possivelmente desnecessário

    private final int length;
    private final T []tuple;

    public ImmutableTuple(T []array) {
        this.length = array.length;
        tuple = Arrays.copyOf(array, length);
    }

    public int getLength() {
        return length;
    }

    public T getFst() {
        return tuple[0];
    }

    public T getSnd() {
        return tuple[1];
    }

    public T getValue(int n) {
        if (n <= getLength() || n > 0) {
            return tuple[n-1];
        } //TODO Exception

        return null;
    }

    public T getLast() {
        return tuple[length-1];
    }

    public T[] getTuple() {
        return tuple;
    }
}
