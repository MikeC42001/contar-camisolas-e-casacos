package strategy.language;

import strategy.Ilanguage;

public class Pt implements Ilanguage {

    private int nOptions;

    public Pt(int n) {
        nOptions = n;
    }

    @Override
    public int numberOfOptionsGiven() {
        return nOptions;
    }

    @Override
    public String intro() {
        return "Bem-vindo ao teu Contador de Roupa! Vamos Começar :)";
    }

    @Override
    public String receiveVariable() {
        return "Estamos a trabalhar com quantas variáveis?\n";
    }

    @Override
    public String receiveNameFileRead() {
        return "Por favor indique qual o nome do ficheiro onde vai contar as peças de roupa.\n";
    }

    @Override
    public String receiveNameFileWrite() {
        return "Por favor indique o nome do ficheiro onde vai aparecer a contagem das peças de roupa.\n";
    }

    @Override
    public String givenOptions() {
        StringBuilder sb = new StringBuilder("\nEscolha 1 das " + nOptions + " opções (escolha escrevendo o número da opção):\n");

        // Tipo de ficheiro //TODO (tipo indicado antes??)
        String fileType = "(.xlsx)";

        // Opção criar.
        sb.append("   1 - criar     - criar um novo ficheiro ").append(fileType).append("onde vai aparecer a strategy.template desejada.\n");

        // Opção atualizar.
        sb.append("   2 - atualizar - adicionar mais unidades de roupa ao ficheiro ").append(fileType).append("onde está a strategy.template desejada.\n");

        // Opção retirar.
        sb.append("   3 - retirar   - retirar as quantidades indicadas ao ficheiro ").append(fileType).append("onde está a strategy.template desejada.\n");

        // Opção default.
        sb.append("   4 - default   - cria um novo ficheiro ").append(fileType).append(" onde vai aparecer a strategy.template desejada.\n");

        return sb.toString();
    }

    @Override
    public String giveAnswerNumber() {
        return "Digite aqui: ";
    }

    @Override
    public String giveAnswerString() {
        return "Escreva aqui: ";
    }

    @Override
    public String end() {
        return "Obrigado pela paciência! Bom trabalho :)";
    }

    @Override
    public String errorMsgWrongInteractionOption() {
        return "Opção não encontrada.\nDigite de novo a opção que quer.\n";
    }
}
