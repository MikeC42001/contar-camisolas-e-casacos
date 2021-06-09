package strategy.options.create_1;

import config.MyConfiguration;
import strategy.options.CreateOptionAbstract;

public class CreateOptionExcel extends CreateOptionAbstract {
    @Override
    public void doAction(MyConfiguration config, String readFileName, String writeFileName) {
        try {//TODO

            config.getContarNStrategy().contarVariaveis(config, readFileName);

            config.getContarNStrategy().construirTemplate(config, writeFileName);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("CreateOptionExcelJava");
        }
    }
}
