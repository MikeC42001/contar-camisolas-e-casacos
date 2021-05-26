package config;

import dataBase.BaseDeDados;
import strategy.IContar_N_VariaveisStrategy;
import userInteraction.UserInteraction;

public class MyConfiguration {

    private static MyConfiguration INSTANCE = null;
    private BaseDeDados contagem;
    private UserInteraction interaction;
    private IContar_N_VariaveisStrategy countNStrategy;

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

    public IContar_N_VariaveisStrategy getContarNStrategy() {
        if (interaction == null) {
            interaction = new UserInteraction();
        }
        return interaction;
    }
}
