package strategy.read;

import config.MyConfiguration;

public interface IContar_N_VariaveisStrategy {
    // TODO Criar outra interface nas "variaveis" para que seja possível ler outros ficheiros para além de EXCEL

    public void contarVariaveis(MyConfiguration config, String readFileName);

    public void construirTemplate(MyConfiguration config, String writeFileName);


}
