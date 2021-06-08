package strategy;

import config.MyConfiguration;

public interface IInteractionOption {

    public void doAction(MyConfiguration config, String readFileName, String writeFileName);
}
