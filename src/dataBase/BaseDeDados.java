package dataBase;

import contar.app.facade.classes.Tabela;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BaseDeDados {
    private List<String> ordemTamanhos = Arrays.asList("XS", "S", "M", "L", "XL", "XXL"); //TODO
    private HashMap<Array, Tabela> templateData;

    public List<String> getOrdemTamanhos() {
        return ordemTamanhos;
    }

    public HashMap<Array, Tabela> getTemplateData() {
        return templateData;
    }

    public Tabela getTabela(Array corERoupa){
        return this.templateData.get(corERoupa);
    }

    public void addTabela(Array corERoupa, Tabela tabela) {
        this.templateData.put(id, templateData);
    }
}
