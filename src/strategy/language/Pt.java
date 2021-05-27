package strategy.language;

import strategy.Ilanguage;

public class Pt implements Ilanguage {
    @Override
    public String intro() {
        return "Bem-vindo ao teu Contador de Roupa! Vamos Começar :)";
    }

    @Override
    public String receiveVariable() {
        return "Estamos a trabalhar com quantas variáveis?";
    }

    @Override
    public String receiveNameFileRead() {
        return "Por favor indique qual o nome do ficheiro onde vai contar as peças de roupa.";
    }

    @Override
    public String receiveNameFileWrite() {
        return "Por favor indique o nome do ficheiro onde vai aparecer a contagem das peças de roupa.";
    }

    @Override
    public String givenOptions(int n) {
        StringBuilder sb = new StringBuilder("Escolha 1 das " + n + " opções (escolha escrevendo o nome da opção):");

        // Tipo de ficheiro //TODO (indicado antes??)
        String fileType = "(.xlsx)";

        // Opção criar.
        sb.append("   criar - criar um novo ficheiro ").append(fileType).append("onde vai aparecer a strategy.template desejada.\n");

        // Opção atualizar.
        sb.append("   atualizar - adicionar mais unidades de roupa ao ficheiro ").append(fileType).append("onde está a strategy.template desejada.");

        // Opção retirar.
        sb.append("   retirar - retirar as quantidades indicadas ao ficheiro ").append(fileType).append("onde está a strategy.template desejada.");

        // Opção default.
        sb.append("   default - cria um novo ficheiro ").append(fileType).append(" onde vai aparecer a strategy.template desejada.");

        return sb.toString();
    }

    @Override
    public String end() {
        return "Obrigado pela paciência! Bom trabalho :)";
    }
}
