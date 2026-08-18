/* ===== Algoritmos de Ordenacao =====
 * - Bolha
 * - Selecao
 * - Insercao
 * - Quicksort
 */

public class Ordenacao
{
    private static void swap(int[] v, int i, int j){
        int tmp = v[i];
        v[i] = v[j];
        v[j] = tmp;
    }
    
    public static void bolha(int[] v){
        int n = v.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n - i - 1; j++){
                if(v[j] > v[j+1]) swap(v, j, j+1);
            }
        }
    }

    public static void selecao(int[] v){
        int n = v.length;
        for(int i = 0; i < n-1; i++){
            int menor = i;
            for(int j = i; j < n; j++){
                if(v[j] < v[menor]) menor = j;
            }
            swap(v, i, menor);
        }
    }

    public static void insercao(int[] v){
        int n = v.length;
        for(int i = 1; i < n; i++){
            int chave = v[i];
            int j = i-1;
            while(j >= 0 && v[j] > chave){
                v[j+1] = v[j];
                j--;
            }
            v[j+1] = v[j];
            v[j] = chave;
        }
    }

    public static void quicksort(int[] v){
        int len = v.length;
        quicksort(v, 0, len - 1);
    }

    private static void quicksort(int[] v, int esq, int dir){
        int i = esq;
        int j = dir;
        int pivo = v[(esq + dir)/2];

        while(i <= j){
            while(v[i] < pivo){ i++; }
            while(v[j] > pivo){ j--; }
            if(i <= j){
                swap(v, i, j);
                i++;
                j--;
            }
        }

        if(dir > i){ quicksort(v, i, dir); }
        if(esq < j){ quicksort(v, esq, j); }
    }
}
