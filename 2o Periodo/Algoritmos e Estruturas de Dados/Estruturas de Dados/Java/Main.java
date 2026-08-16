import auxiliar.*;
import menu.*;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        while(true){
            int op = Menu.estruturas(sc);
            System.out.println();
            if(op == 0) break;
            switch(op){
                case 1 -> Menu.lineares(sc);
                case 2 -> Menu.flexiveis(sc);
                case 3 -> Menu.arvores(sc);
                //case 4 -> Menu.abelas(sc);
                default -> Auxiliar.opcaoInvalida();
            }
        }
        System.out.println("===== FIM DO PROGRAMA =====");
        sc.close();
    }
}
