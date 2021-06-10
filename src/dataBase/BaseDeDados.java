package dataBase;

import contar.app.facade.classes.Tabela;
import contar.app.tuplo.ImmutableTuple;

import java.util.*;

public class BaseDeDados {

    private List<String> cores = new ArrayList<>();
    private HashMap<ImmutableTuple<String>, Tabela> templateData = new HashMap<ImmutableTuple<String>, Tabela>();
    private HashMap<ImmutableTuple<String>, Integer> counter = new HashMap<ImmutableTuple<String>, Integer>();

    public /*synchronized*/ List<String> getCores() {
        return cores;
    }

    public /*synchronized*/ HashMap<ImmutableTuple<String>, Integer> getCounter() {
        return counter;
    }

    public /*synchronized*/ HashMap<ImmutableTuple<String>, Tabela> getTemplateData() {
        return templateData;
    }

    public /*synchronized*/ Tabela getTabela(ImmutableTuple<String> roupaECor) {
        if (this.templateData.get(roupaECor) == null) {
            addTabela(roupaECor, new Tabela(roupaECor.getFst(), roupaECor.getSnd())); // add table if there isnt some
            addCor(roupaECor.getSnd()); // add color if there isnt
        }

        return this.templateData.get(roupaECor);
    }

    public /*synchronized*/ void addCor(String cor) {
        if (!this.cores.contains(cor)) {
            this.cores.add(cor);
        }
    }

    public /*synchronized*/ void addTabela(ImmutableTuple<String> corERoupa, Tabela tabela) {
        templateData.putIfAbsent(corERoupa, tabela);
    }

    public void addCountPlus1(ImmutableTuple<String> RoupaCorTamanhoCurso) {
        //synchronized(counter) {
            //int count = counter.getOrDefault(RoupaCorTamanhoCurso, 0); // ensure count will be one of 0,1,2,3,...
            //counter.put(RoupaCorTamanhoCurso, count + 1);

            counter.merge(RoupaCorTamanhoCurso, 1, Integer::sum);
        //}

    }

    public /*synchronized*/ void counterToStringSystemOutPut() {
        counter.keySet().forEach(sting -> System.out.println(sting.toString() + " = " + counter.get(sting)));
    }

    public /*synchronized*/ int sumCounter() {
        return counter.values().stream().reduce(0, Integer::sum);
    }

    public void tabelasToStringSystemOutPut() {
        templateData.keySet().forEach(sting -> System.out.println(sting.toString() + " = " + templateData.get(sting).toString()));
    }

    public void sortTamanhosDeTabelas() {
        templateData.values().forEach(Tabela::sortInOrderTamanhos);
    }
}
