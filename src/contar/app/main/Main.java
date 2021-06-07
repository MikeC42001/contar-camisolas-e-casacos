package contar.app.main;

import config.MyConfiguration;

public class Main {

    public static void main(String[] args) {

        MyConfiguration config = MyConfiguration.getINSTANCE();

        String filesDirectory = "/home/miguel-linux/Desktop/MiguelL/My_Own_PlusProj/ExcelFiles";

        System.out.println(config.getLanguage().intro());

        config.getUserInteraction().interact(config, filesDirectory);

        System.out.println(config.getLanguage().end());


    }
}
