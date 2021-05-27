package strategy;

public interface Ilanguage { // não dá static

    public int numberOfOptionsGiven();

    public String intro();

    public String receiveVariable();

    public String receiveNameFileRead();

    public String receiveNameFileWrite();

    public String givenOptions();

    public String giveAnswerNumber();

    public String giveAnswerString();

    public String end();

    public String errorMsgWrongInteractionOption();
}
