package contar.app.main;

import config.MyConfiguration;

public class Main {

    public static void main(String[] args) {

        MyConfiguration config = MyConfiguration.getINSTANCE();



        System.out.println(config.getLanguage().intro());

        config.getUserInteraction().interact(config);

        System.out.println(config.getLanguage().end());


    }
}
