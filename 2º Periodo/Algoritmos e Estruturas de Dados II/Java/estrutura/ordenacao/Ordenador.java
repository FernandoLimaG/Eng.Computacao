package estrutura.ordenacao;

public class Ordenador
{
    private int capacidade;
    private final int[] original;

    public Ordenador (int[] original) {
        this.original = original;
        this.capacidade = original.length;
    }

    public int[] getOriginal() { return original; }
    public int getCapacidade() { return capacidade; }

    private void trocar (int[] v, int i, int j) {
        int tmp = v[i];
        v[i] = v[j];
        v[j] = tmp;
    }

    public int[] bubble () {
        boolean trocou = true;
        int[] v = original.clone();

        for (int i = 0; i < capacidade - 1 && trocou; i++) {
            trocou = false;
            for (int j = 0; j < (capacidade - i - 1); j++) {
                if (v[j] > v[j+1]) {
                    trocar(v,j,j+1);
                    trocou = true;
                }
            }
        }

        return v;
    }

    public int[] selection () {
        int[] v = original.clone();
        for (int i = 0; i < capacidade - 1; i++) {
            int menor = i;
            for (int j = i+1; j < capacidade; j++) {
                if (v[j] < v[menor]) menor = j;
            }
            trocar (v,i,menor);
        }
        return v;
    }

    public int[] insertion () {
        int[] v = original.clone();
        for (int i = 1; i < capacidade; i++) {
            int j = i-1;
            int chave = v[i];
            while (j >= 0 && v[j] > chave) {
                v[j+1] = v[j];
                j--;
            }
            v[j+1] = chave;
        }
        return v;
    }

    public int[] mergesort() {
        int[] v = original.clone();
        mergesort(v, 0, v.length - 1);
        return v;
    }

    private void mergesort(int[] v, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;

            mergesort(v, esq, meio);
            mergesort(v, meio + 1, dir);
            intercalar(v, esq, meio, dir);
        }
    }

    private void intercalar(int[] v, int esq, int meio, int dir) {
        int[] temp = new int[dir - esq + 1];

        int i = esq;
        int j = meio + 1;
        int k = 0;

        while (i <= meio && j <= dir) {
            if (v[i] <= v[j]) {
                temp[k++] = v[i++];
            } else {
                temp[k++] = v[j++];
            }
        }

        while (i <= meio) {
            temp[k++] = v[i++];
        }

        while (j <= dir) {
            temp[k++] = v[j++];
        }

        for (i = 0; i < temp.length; i++) {
            v[esq + i] = temp[i];
        }
    }

    public int[] heapsort() {
        int[] v = original.clone();
        heapsort(v);
        return v;
    }

    private void heapsort(int[] v) {
        int n = v.length;

        // Construção do heap máximo
        for (int i = n / 2 - 1; i >= 0; i--) {
            reconstruir(v, n, i);
        }

        // Ordenação e reconstrução do heap
        for (int i = n - 1; i > 0; i--) {
            trocar(v, 0, i);
            reconstruir(v, i, 0);
        }
    }

    private void reconstruir(int[] v, int n, int i) {
        int maior = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        if (esq < n && v[esq] > v[maior]) {
            maior = esq;
        }

        if (dir < n && v[dir] > v[maior]) {
            maior = dir;
        }

        if (maior != i) {
            trocar(v, i, maior);
            reconstruir(v, n, maior);
        }
    }

    public int[] quicksort () {
        int[] v = original.clone();
        quicksort(v,0,capacidade-1);
        return v;
    }

    private void quicksort (int[] v, int esq, int dir) {
        int i = esq;
        int j = dir;
        int pivo = v[(esq + dir)/2];

        while (i <= j) {
            while (v[i] < pivo) i++;
            while (v[j] > pivo) j--;

            if (i <= j) {
                trocar(v,i,j);
                i++;
                j--;
            }
        }

        if (dir > i) quicksort(v,i,dir);
        if (esq < j) quicksort(v,esq,j);
    }
}
