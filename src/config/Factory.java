package config;

import strategy.IInteractionOption;
import strategy.Ilanguage;
import strategy.language.Pt;
import strategy.read.IContar_N_VariaveisStrategy;
import strategy.read.variaveis.Contar_4_DefaultAE;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;
import java.util.Scanner;

public class Factory {

    private final static int numberOptions = 4;
    //TODO apagar static?

    private final Properties pro = new Properties();
    private Object Exception;

    public IContar_N_VariaveisStrategy getCountNStrategyByIntGiven(int n) {

        try (FileInputStream f = new FileInputStream("preferences.properties")) {
            pro.load(f);

            String classes = pro.getProperty("COUNT_N_CLASSES");

            String[] className = classes.split(",");

            for (int i = 0; i < className.length; i++) {
                System.out.println("Opção " + (i+1) + ": " + className[i]);
            }

            Scanner iSc = new Scanner(System.in);
            System.out.println("DIGITE! a Opção que quer");

            Class<?> classCountVariables = Class.forName(className[iSc.nextInt()-1]);
            Constructor<?> constructor = classCountVariables.getConstructor();

            return (IContar_N_VariaveisStrategy) constructor.newInstance();
        } catch (Exception e) {
            System.out.println("Could not read Count Class.");
            return new Contar_4_DefaultAE();
        }
    }

    public Ilanguage getLanguage() {

        try (FileInputStream f = new FileInputStream("preferences.properties")) {
            pro.load(f);

            String className = pro.getProperty("LANGUAGE_CLASS");

            Class<?> classLanguage = Class.forName(className);
            Constructor<?> constructor = classLanguage.getConstructor(int.class);

            return (Ilanguage) constructor.newInstance(numberOptions);
        } catch (Exception e) {
            System.out.println("Could not read Language Class. Started with default language (Pt)");
            return new Pt(numberOptions);
        }
    }

    public boolean hasInteractionOption(int interaction) {
        try (FileInputStream f = new FileInputStream("preferences.properties")) {
            pro.load(f);

            String className = pro.getProperty("OPTION_" + interaction + "_CLASS");

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public IInteractionOption getInteractionOption(int interaction){
        try (FileInputStream f = new FileInputStream("preferences.properties")) {
            pro.load(f);

            String className = pro.getProperty("OPTION_" + interaction + "_CLASS");

            Class<?> classInteractionOption = Class.forName(className);
            Constructor<?> constructor = classInteractionOption.getConstructor();

            return (IInteractionOption) constructor.newInstance();
        } catch (Exception e) {
            System.out.println("Could not read Option Class."); //TODO msg de erro q se pode tirar aqui ou em UserIneractionHAndler.
            return null;
        }
    }
}
