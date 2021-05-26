package config;

import strategy.IContar_N_VariaveisStrategy;
import strategy.variaveis.Contar_4_DefaultAE;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;
import java.util.Scanner;

public class Factory {
    private final Properties pro = new Properties();

    public IContar_N_VariaveisStrategy getCountNStrategyByIntGiven(int n) {

        try (FileInputStream f = new FileInputStream("preferences.properties")) {
            pro.load(f);

            String classes = pro.getProperty("COUNT_N_CLASSES");

            String[] className = classes.split(",");

            for (int i = 0; i < className.length; i++) {
                System.out.println("Opção " + (i+1) + ": " + className[i]);
            }

            //Scanner iSc = new Scanner(System.in); "tem a certeza que pretende escolher esta interface: I...?"

            Class<?> classDiscord = Class.forName(className[1]); //iSc.nextInt()-1
            Constructor<?> constructor = classDiscord.getConstructor();

            return (IContar_N_VariaveisStrategy) constructor.newInstance();
        } catch (Exception e) {
            System.out.println("Could not read Count Class.");
            return new Contar_4_DefaultAE();
        }
    }
}
