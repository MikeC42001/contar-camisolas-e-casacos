package userInteraction;

import config.MyConfiguration;

import java.util.Scanner;

public class UserInteractionHandler {

    public void interact(MyConfiguration config, String filesDirectory){
        Scanner sc = new Scanner(System.in);

        System.out.println(config.getLanguage().givenOptions());    // Opções
        System.out.print(config.getLanguage().giveAnswerNumber());  // Digite aqui:
        chooseInteraction(sc.nextInt(), sc, config, filesDirectory);

        //TODO inicializar o ficheiro que se vai ler

        //TODO inicializar o ficheiro onde se vai escrever

        sc.close();
    }

    private int askVariable(Scanner sc, MyConfiguration config) {
        System.out.println(config.getLanguage().receiveVariable());
        System.out.print(config.getLanguage().giveAnswerNumber());  // Digite aqui:
        return sc.nextInt();
    }

    private String askReadFileName(Scanner sc, MyConfiguration config, String filesDirectory) {
        System.out.println(config.getLanguage().receiveNameFileRead());
        System.out.print(config.getLanguage().giveAnswerString());  // Digite aqui:
        return filesDirectory + "/" + sc.next();
    }

    private String askWriteFileName(Scanner sc, MyConfiguration config, String filesDirectory) {
        System.out.println(config.getLanguage().receiveNameFileWrite());
        System.out.print(config.getLanguage().giveAnswerString());  // Digite aqui:
        return filesDirectory + "/" + sc.next();
    }

    private void chooseInteraction(int interaction, Scanner sc, MyConfiguration config, String filesDirectory) {

            if (config.hasInteractionOption(interaction)){

                if (config.getInteractionOption(interaction) == null) { // TODO arranjar outra maneira por os dois ifs juntos
                    errorIteractionOption(sc, config, filesDirectory);
                } else {
                    int nVariable = askVariable(sc, config);

                    //askReadFileName(sc, config, filesDirectory); //TODO FACTORY ALTERNATIVE
                    String ReadFileName = filesDirectory + "/Encomenda 20_21 (Respostas).xlsx";

                    //askWriteFileName(sc, config, filesDirectory); //TODO FACTORY ALTERNATIVE
                    String WriteFileName = filesDirectory + "/Contagem 20_21 JAVA.xlsx";

                    config.getInteractionOption(interaction).doAction(nVariable, ReadFileName, WriteFileName);
                }

            } else{
                errorIteractionOption(sc, config, filesDirectory);
            }

    }

    public void errorIteractionOption(Scanner sc, MyConfiguration config, String filesDirectory){
        System.out.println(config.getLanguage().errorMsgWrongInteractionOption());
        System.out.print(config.getLanguage().giveAnswerNumber());  // Digite aqui:
        chooseInteraction(sc.nextInt(), sc, config, filesDirectory);
    }
}


