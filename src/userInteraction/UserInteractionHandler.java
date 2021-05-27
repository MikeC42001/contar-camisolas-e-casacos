package userInteraction;

import config.MyConfiguration;

import java.util.Scanner;

public class UserInteractionHandler {

    public void interact(MyConfiguration config){
        Scanner sc = new Scanner(System.in);

        System.out.println(config.getLanguage().givenOptions());
        System.out.print(config.getLanguage().giveAnswerNumber());
        chooseInteraction(sc.nextInt(), sc, config);

        System.out.println(config.getLanguage().receiveVariable());
        System.out.print(config.getLanguage().giveAnswerNumber());
        int apagar = sc.nextInt();



        System.out.println(config.getLanguage().receiveNameFileRead());
        System.out.print(config.getLanguage().giveAnswerString());

        System.out.println(config.getLanguage().receiveNameFileWrite());
        System.out.print(config.getLanguage().giveAnswerString());

        sc.close();
    }
    public void chooseInteraction(int interaction, Scanner sc, MyConfiguration config) {

            if (config.hasInteractionOption(interaction)){

                if (config.getInteractionOption(interaction) == null) { // TODO arranjar outra maneira por os dois ifs juntos
                    errorIteractionOption(sc, config);
                }

                config.getInteractionOption(interaction).doAction();

            } else{
                errorIteractionOption(sc, config);
            }

    }

    public void errorIteractionOption(Scanner sc, MyConfiguration config){
        System.out.println(config.getLanguage().errorMsgWrongInteractionOption());
        System.out.print(config.getLanguage().giveAnswerNumber());
        chooseInteraction(sc.nextInt(), sc, config);
    }
}


