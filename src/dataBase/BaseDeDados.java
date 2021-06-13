package dataBase;

import contar.app.facade.classes.Tabela;
import contar.app.tuplo.ImmutableTuple;

import java.util.*;

public class BaseDeDados {

    private List<String> cores = new ArrayList<>();
    private List<String> roupas = new ArrayList<>();
    private HashMap<ImmutableTuple<String>, Tabela> templateData = new HashMap<ImmutableTuple<String>, Tabela>();
    private HashMap<ImmutableTuple<String>, Integer> counter = new HashMap<ImmutableTuple<String>, Integer>();
    private final static List<String> desiredOrderRoupa = Arrays.asList("Hoodie", "Casaco");
    private final static Comparator<String> sizeOrderRoupa = Comparator.comparingInt(desiredOrderRoupa::indexOf);

    public List<String> getCores() {
        return cores;
    }

    public List<String> getRoupas() {
        return roupas;
    }

    public HashMap<ImmutableTuple<String>, Integer> getCounter() {
        return counter;
    }

    public HashMap<ImmutableTuple<String>, Tabela> getTemplateData() {
        return templateData;
    }

    public synchronized Tabela getTabela(ImmutableTuple<String> roupaECor) {
        if (this.templateData.get(roupaECor) == null) {
            addTabela(roupaECor, new Tabela(roupaECor.getFst(), roupaECor.getSnd())); // add table if there isnt some
            addRoupa(roupaECor.getFst()); // add roupa if there isnt
            addCor(roupaECor.getSnd()); // add color if there isnt
        }

        return this.templateData.get(roupaECor);
    }

    private synchronized void addRoupa(String roupa) {
        if (!this.roupas.contains(roupa)) {
            this.roupas.add(roupa);
        }
    }

    public synchronized void addCor(String cor) {
        if (!this.cores.contains(cor)) {
            this.cores.add(cor);
        }
    }

    public synchronized void addTabela(ImmutableTuple<String> corERoupa, Tabela tabela) {
        templateData.putIfAbsent(corERoupa, tabela);
    }

    public synchronized void addCountPlus1(ImmutableTuple<String> RoupaCorTamanhoCurso) {
        counter.merge(RoupaCorTamanhoCurso, 1, Integer::sum);
    }

    public void counterToStringSystemOutPut() {
        counter.keySet().forEach(sting -> System.out.println(sting.toString() + " = " + counter.get(sting)));
    }

    public int sumCounter() {
        return counter.values().stream().reduce(0, Integer::sum);
    }

    public void tabelasToStringSystemOutPut() {
        templateData.keySet().forEach(sting -> System.out.println(sting.toString() + " = " + templateData.get(sting).toString()));
    }

    public void sortTamanhosDeTabelas() {
        templateData.values().forEach(Tabela::sortInOrderTamanhos);
    }

    public void sortRoupaDeTabelas() {
        roupas.sort(sizeOrderRoupa);
    }

    public void sortListaDeCores() {
        java.util.Collections.sort(cores);
    }

    public void coresToStringSystemOutPut() {
        System.out.println(cores.toString());
    }
}
