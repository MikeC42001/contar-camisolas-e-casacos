package contar.app.tuplo;

import java.util.Arrays;

public class ImmutableTuple { //TODO possivelmente desnecessário

    private final int length;
    private final String []tuple;

    public ImmutableTuple(String []array) {
        this.length = array.length;
        tuple = Arrays.copyOf(array, length);
    }

    public int getLength() {
        return length;
    }

    public String getFst() {
        return tuple[0];
    }

    public String getSnd() {
        return tuple[1];
    }

    public String getValue(int n) {
        if (n-2 < getLength() || n > 2) {
            return tuple[n-1];
        } //TODO Exception

        return null;
    }

    public String getLast() {
        return tuple[length-1];
    }

    public String[] getTuple() {
        return tuple;
    }
}
