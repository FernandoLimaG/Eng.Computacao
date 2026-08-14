import arvores.trie.*;
import auxiliar.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Trie trie = new Trie();

        while(true){
            int op = Auxiliar.menu(sc);
            System.out.println();
            if(op == 0) break;
            switch(op){
                case 1:{
                    trie.inserir(Auxiliar.lerString("Nome para inserir: ", sc));
                    break;
                }
                case 2:{
                    if(trie.pesquisar(Auxiliar.lerString("Nome para pesquisar: ", sc))){
                        System.out.println("Nome encontrado");
                    } else{
                        System.out.println("Nome nao encontrado");
                    }
                    break;
                }
                case 3:{
                    trie.imprimir();
                    break;
                }
                default:{
                    Auxiliar.opcaoInvalida();
                    break;
                }
            }
            System.out.println();
        }

        System.out.println("FIM DO PROGRAMA");
        sc.close();
    }
}
