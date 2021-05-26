package contar.app.main;

import config.MyConfiguration;

public class Main {

    public static void main(String[] args) {


        System.out.println("Bem-vindo ao teu Contador de Roupa! Vamos Começar :)");

        MyConfiguration config = MyConfiguration.getINSTANCE();

        config.getUserInteraction().interact(config);

        System.out.println("Obrigado pela paciência! Vamos Começar :)");


    }
}
