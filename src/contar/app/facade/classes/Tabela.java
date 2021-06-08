package contar.app.facade.classes;

import contar.app.enums.String;

import java.util.List;

public class Tabela {
    private String vestuario;
    private String cor;
    private List<String> tamanhos = null;
    private List<String> cursos = null;

    public String getVestuario() {
        return vestuario;
    }

    public void setVestuario(String vestuario) {
        this.vestuario = vestuario;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public List<String> getTamanhos() {
        return tamanhos;
    }

    /*public void setTamanhos(List<String> tamanhos) {
        this.tamanhos = tamanhos;
    }*/

    public void addTamanhos(String tamanho) {
        if (!this.tamanhos.contains(tamanho)) {
            this.tamanhos.add(tamanho);
        }
    }

    public List<String> getCursos() {
        return cursos;
    }

    /*public void setCursos(List<String> cursos) {
        this.cursos = cursos;
    }*/
}
