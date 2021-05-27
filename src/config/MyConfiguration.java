package config;

import dataBase.BaseDeDados;
import strategy.IInteractionOption;
import strategy.Ilanguage;
import strategy.read.IContar_N_VariaveisStrategy;
import userInteraction.UserInteractionHandler;

public class MyConfiguration {

    private static MyConfiguration INSTANCE = null;
    private BaseDeDados contagem;
    private UserInteractionHandler interaction;

    private Factory factory;
    private Ilanguage language;
    private IContar_N_VariaveisStrategy countNStrategy;
    private IInteractionOption interactionOption;
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

    public UserInteractionHandler getUserInteraction() {
        if (interaction == null) {
            interaction = new UserInteractionHandler();
        }
        return interaction;
    }

    public Factory getFactory() {
        if (factory == null) {
            factory = new Factory();
        }
        return factory;
    }

    public Ilanguage getLanguage() {
        if (language == null) {
            language = getFactory().getLanguage();
        }
        return language;
    }

    public void setNumberCount(int n) { //TODO delete
        this.n = n;
    }
    public IContar_N_VariaveisStrategy getContarNStrategy() {
        if (countNStrategy == null) {
            countNStrategy = getFactory().getCountNStrategyByIntGiven(n);
        }
        return countNStrategy;
    }

    public boolean hasInteractionOption(int interaction) { //TODO juntar esta função com a prox
        if (interactionOption == null) {
                return getFactory().hasInteractionOption(interaction);
        }
        return true;
    }

    public IInteractionOption getInteractionOption(int interaction){
        if (interactionOption == null) {
            interactionOption = getFactory().getInteractionOption(interaction);
        }
        return interactionOption;
    }
}
