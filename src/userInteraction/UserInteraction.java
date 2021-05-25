package userInteraction;

import java.util.Scanner;

public class UserInteraction {

    public void choosenInteraction(String interaction, Scanner sc){

            if (interaction.equals("criar")){

            } else if (interaction.equals("atualizar")){

            } else if (interaction.equals("retirar")){

            } else if (interaction.equals("automático")){

            } else{
                choosenInteraction(sc.next(),sc);
            }

    }
}
