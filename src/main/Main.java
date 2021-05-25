package main;

import config.MyConfiguration;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bem-vindo ao teu Contador de Roupa! Vamos Começar :)");

        System.out.println("Estamos a trabalhar com quantas variáveis?");
        int apagar = sc.nextInt();

        System.out.println( "Escolha 1 das 3 opções (escolha escrevendo o nome da opção):" +
                            "   criar - criar um novo ficheiro " + "(.xlsx)" + "onde vai aparecer a template desejada." +
                            "   atualizar - adicionar mais unidades de roupa ao ficheiro " + "(.xlsx)" + "onde está a template desejada." +
                            "   retirar - retirar as quantidades indicadas ao ficheiro " + "(.xlsx)" + "onde está a template desejada." +
                            "   automático - cria um novo ficheiro " + "(.xlsx)" + " onde vai aparecer a template desejada.");

        MyConfiguration config = MyConfiguration.getINSTANCE();

        config.getUserInteraction().choosenInteraction(sc.next(), sc);

        System.out.println("Por favor indique qual o nome do ficheiro onde vai contar as peças de roupa.");

        System.out.println("Por favor indique o nome do ficheiro onde vai aparecer a contagem das peças de roupa.");

        System.out.println("Obrigado pela paciência! Vamos Começar :)");


    }
}
