package dataBase;

import contar.app.facade.classes.Tabela;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BaseDeDados {
    private List<String> ordemTamanhos = Arrays.asList("XS", "S", "M", "L", "XL", "XXL"); //TODO
    private List<String> cores = new ArrayList<>();
    private HashMap<String[], Tabela> templateData = new HashMap<>();
    private HashMap<String[], Integer> counter = new HashMap<>();

    public synchronized List<String> getOrdemTamanhos() {
        return ordemTamanhos;
    }

    public synchronized List<String> getCores() {
        return cores;
    }

    public synchronized HashMap<String[], Integer> getCounter() {
        return counter;
    }

    public synchronized HashMap<String[], Tabela> getTemplateData() {
        return templateData;
    }

    public synchronized Tabela getTabela(String[] roupaECor) {
        if (this.templateData.get(roupaECor) == null) {
            addTabela(roupaECor, new Tabela(roupaECor[0], roupaECor[1])); // add table if there isnt some
            addCor(roupaECor[1]); // add color if there isnt
        }

        return this.templateData.get(roupaECor);
    }

    public synchronized boolean addCor(String cor) {
        if (!this.cores.contains(cor)) {
            this.cores.add(cor);
            return true;
        }
        return false;
    }

    public synchronized void addTabela(String[] corERoupa, Tabela tabela) {
        templateData.putIfAbsent(corERoupa, tabela);
    }

    public synchronized void addCountPlus1(String[] RoupaCorTamanhoCurso) {
        counter.merge(RoupaCorTamanhoCurso, 1, Integer::sum);
    }
}
