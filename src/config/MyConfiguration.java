package config;

import dataBase.BaseDeDados;
import strategy.IContar_N_VariaveisStrategy;
import userInteraction.UserInteraction;

public class MyConfiguration {

    private static MyConfiguration INSTANCE = null;
    private BaseDeDados contagem;
    private UserInteraction interaction;
    private IContar_N_VariaveisStrategy countNStrategy;
    private Factory factory;
    private int n;

    private MyConfiguration() {    }

    public static MyConfiguration getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MyConfiguration();
        }
        return INSTANCE;
    }

    public BaseDeDados getBaseDeDados() {
        if (contagem == null) {
            contagem = new BaseDeDados();
        }
        return contagem;
    }

    public UserInteraction getUserInteraction() {
        if (interaction == null) {
            interaction = new UserInteraction();
        }
        return interaction;
    }

    public Factory getFactory() {
        if (factory == null) {
            factory = new Factory();
        }
        return factory;
    }

    public void setNumberCount(int n) { //delete
        this.n = n;
    }
    public IContar_N_VariaveisStrategy getContarNStrategy() {
        if (countNStrategy == null) {
            countNStrategy = getFactory().getCountNStrategyByIntGiven(n);
        }
        return countNStrategy;
    }
}
