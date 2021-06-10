package contar.app.facade.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Tabela {
    private final String vestuario;
    private final String cor;
    private List<String> tamanhos = new ArrayList<>();
    private List<String> cursos = new ArrayList<>();
    private final static List<String> desiredOrderTamanhos = Arrays.asList("XS", "S", "M", "L", "XL", "XXL");
    private final Comparator<String> sizeOrder = Comparator.comparingInt(desiredOrderTamanhos::indexOf);

    public Tabela(String vestuario, String cor) {
        this.vestuario = vestuario;
        this.cor = cor;
    }

    public String getVestuario() {
        return vestuario;
    }

    /*public void setVestuario(String vestuario) {
        this.vestuario = vestuario;
    }*/

    public String getCor() {
        return cor;
    }

    /*public void setCor(String cor) {
        this.cor = cor;
    }*/

    public List<String> getTamanhos() {
        return tamanhos;
    }

    /*public void setTamanhos(List<String> tamanhos) {
        this.tamanhos = tamanhos;
    }*/

    public void addTamanho(String tamanho) {
        if (!this.tamanhos.contains(tamanho)) {
            this.tamanhos.add(tamanho);
        }
    }

    public List<String> getCursos() {
        return cursos;
    }

    public void addCurso(String curso) {
        if (!this.cursos.contains(curso)) {
            this.cursos.add(curso);
        }
    }

    public void sortInOrderTamanhos() {
        tamanhos.sort(sizeOrder);

        //tamanhos.sort(Comparator.comparing(Tabela::numerical));
    }
    /*public void setCursos(List<String> cursos) {
        this.cursos = cursos;
    }*/

    @Override
    public String toString() {
        return "Tabela{" +
                "vestuario='" + vestuario + '\'' +
                ", cor='" + cor + '\'' +
                ", tamanhos=" + tamanhos.toString() +
                ", cursos=" + cursos.toString() +
                '}';
    }
}
