package dataBase;

import contar.app.facade.classes.Tabela;
import contar.app.tuplo.ImmutableTuple;

import java.util.*;

public class BaseDeDados {
    private List<String> ordemTamanhos = Arrays.asList("XS", "S", "M", "L", "XL", "XXL"); //TODO
    private List<String> cores = new ArrayList<>();
    private HashMap<ImmutableTuple<String>, Tabela> templateData = new HashMap<ImmutableTuple<String>, Tabela>();
    private HashMap<ImmutableTuple<String>, Integer> counter = new HashMap<ImmutableTuple<String>, Integer>();

    public synchronized List<String> getOrdemTamanhos() {
        return ordemTamanhos;
    }

    public synchronized List<String> getCores() {
        return cores;
    }

    public synchronized HashMap<ImmutableTuple<String>, Integer> getCounter() {
        return counter;
    }

    public synchronized HashMap<ImmutableTuple<String>, Tabela> getTemplateData() {
        return templateData;
    }

    public synchronized Tabela getTabela(ImmutableTuple<String> roupaECor) {
        if (this.templateData.get(roupaECor) == null) {
            addTabela(roupaECor, new Tabela(roupaECor.getFst(), roupaECor.getSnd())); // add table if there isnt some
            addCor(roupaECor.getSnd()); // add color if there isnt
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

    public synchronized void addTabela(ImmutableTuple<String> corERoupa, Tabela tabela) {
        templateData.putIfAbsent(corERoupa, tabela);
    }

    public void addCountPlus1(ImmutableTuple<String> RoupaCorTamanhoCurso) {
        synchronized(counter) {
            int count = counter.getOrDefault(RoupaCorTamanhoCurso, 0); // ensure count will be one of 0,1,2,3,...
            counter.put(RoupaCorTamanhoCurso, count + 1);

            //counter.merge(RoupaCorTamanhoCurso, 1, (a, b) -> a + b);
        }

    }

    public synchronized void counterToString() {
        Collection<ImmutableTuple<String>> stringsDoMap = counter.keySet();
        //mapCounter.values();

        stringsDoMap.stream().forEach(sting -> System.out.println(sting.toString() + " = " + counter.get(sting)));
    }
}
