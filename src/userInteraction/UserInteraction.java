package userInteraction;

import config.MyConfiguration;

import java.util.Scanner;

public class UserInteraction {

    public void interact(MyConfiguration config){
        Scanner sc = new Scanner(System.in);

        System.out.println( "Escolha 1 das 3 opções (escolha escrevendo o nome da opção):" +
                "   criar - criar um novo ficheiro " + "(.xlsx)" + "onde vai aparecer a strategy.template desejada." +
                "   atualizar - adicionar mais unidades de roupa ao ficheiro " + "(.xlsx)" + "onde está a strategy.template desejada." +
                "   retirar - retirar as quantidades indicadas ao ficheiro " + "(.xlsx)" + "onde está a strategy.template desejada." +
                "   default - cria um novo ficheiro " + "(.xlsx)" + " onde vai aparecer a strategy.template desejada.");

        System.out.println("Estamos a trabalhar com quantas variáveis?");
        int apagar = sc.nextInt();

        config.getUserInteraction().choosenInteraction(sc.next(), sc);

        System.out.println("Por favor indique qual o nome do ficheiro onde vai contar as peças de roupa.");

        System.out.println("Por favor indique o nome do ficheiro onde vai aparecer a contagem das peças de roupa.");

        sc.close();
    }
    public void choosenInteraction(String interaction, Scanner sc){

            if (interaction.equals("criar")){

            } else if (interaction.equals("atualizar")){

            } else if (interaction.equals("retirar")){

            } else if (interaction.equals("automático")){

            } else{
                choosenInteraction(sc.next(),sc);
            }

    }
}
