/* ===== Algoritmos de Ordenacao =====
 * - Bolha
 * - Selecao
 * - Insercao
 * - Heapsort
 * - Mergesort
 * - Quicksort
 */

public class Ordenacao
{
    private static void swap(int[] v, int i, int j){
        int tmp = v[i];
        v[i] = v[j];
        v[j] = tmp;
    }

    // Bolha
    public static void bolha(int[] v){
        int n = v.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n - i - 1; j++){
                if(v[j] > v[j+1]) swap(v, j, j+1);
            }
        }
    }

    // Seleção
    public static void selecao(int[] v){
        int n = v.length;
        for(int i = 0; i < n - 1; i++){
            int menor = i;
            for(int j = i; j < n; j++){
                if(v[j] < v[menor]) menor = j;
            }
            swap(v, i, menor);
        }
    }

    // Inserção
    public static void insercao(int[] v){
        int n = v.length;
        for(int i = 1; i < n; i++){
            int chave = v[i];
            int j = i-1;
            while(j >= 0 && v[j] > chave){
                v[j+1] = v[j];
                j--;
            }
            v[j+1] = chave;
        }
    }

    // Heapsort
    public static void heapsort (int[] v) {
        // Montar o Heap
        montarHeap(v,v.length);

        // Extrair elementos
        for (int i = v.length - 1; i > 0; i--) {
            // Mover a raiz para o fim
            swap(v,0,i);

            // Restaurar as propriedades do Heap
            reconstruirHeap(v,0,i);
        }
    }

    private static void montarHeap (int[] v, int len) {
        int ptr = len - 1;
        for (int i = ptr/2; i >= 0; i--) reconstruirHeap(v,i,len);
    }

    private static void reconstruirHeap(int[] v, int i, int len) {
        int maior = i;
        int esq = (2*i)+1;
        int dir = (2*i)+2;

        if (esq < len && v[esq] > v[maior]) maior = esq;
        if (dir < len && v[dir] > v[maior]) maior = dir;
        
        // Chamada recursiva: Maior não está ná raiz
        if (maior != i) { 
            swap(v,i,maior);
            reconstruirHeap(v,maior,len);
        }
    }

    // Mergesort
    public static void mergesort (int[] v) { mergesort(v, v.length); }
       
    private static void mergesort (int[] v, int len) {
        if (len >= 2) {
            int meio = len/2;

            int[] esq = new int[meio];
            int[] dir = new int[len - meio];

            for (int i = 0; i < len; i++) {
                if (i < meio) esq[i] = v[i];
                else dir[i - meio] = v[i];
            }

            mergesort(esq, meio);
            mergesort(dir, len - meio);
            intercalar(v,esq,meio,dir,len - meio);
        }
    }

    private static void intercalar (int[] v, int[] esq, int tamEsq, int[] dir, int tamDir) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < tamEsq && j < tamDir) {
            if (esq[i] <= dir[j]) v[k++] = esq[i++];
            else v[k++] = dir[j++];
        }

        while (i < tamEsq) v[k++] = esq[i++];
        while (j < tamDir) v[k++] = dir[j++];
    }
       
    // Quicksort
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
