package strategy;

public interface Ilanguage { // não sei se ponha tudo static ou não

    public String intro();

    public String receiveVariable();

    public String receiveNameFileRead();

    public String receiveNameFileWrite();

    public String givenOptions(int n);

    public String end();
}
