package indices.arvores;

import entidade.Chave;

import java.io.*;

public class B
{
    // Atributos básicos
    private int ordem;
    private Pagina raiz;
    private final String caminho;

    // Atributos para split e promoção
    private Pagina direita;
    private Chave promovida;
    private boolean cresceu;

    public B (int ordem, String caminho) throws IOException {
        if (ordem < 3) throw new IllegalArgumentException ("Ordem inválida. A ordem da árvore B deve ser maior que 2 (dois)");

        this.ordem = ordem;
        this.caminho = caminho;
        this.raiz = new Pagina(ordem,true);

        File arq = new File (caminho);
        //if (arq.exists() && arq.length() > 0) carregar();
    }

    public boolean isEmpty () { return raiz.isEmpty(); }

    public long buscar (int alvo) { return buscar(alvo,raiz); }

    private long buscar (int alvo, Pagina pagina) {
        long resp;

        if (pagina == null) resp = -1;
        else {
            int i = 0;

            while (i < pagina.n && alvo > pagina.chaves[i].getId()) i++;

            if (i < pagina.n && alvo == pagina.chaves[i].getId()) {
                resp = pagina.chaves[i].getPos();
            } else {
                resp = buscar(alvo,pagina.filhos[i]);
            }
        }
        return resp;
    }

    public void inserir (int id, long pos) throws IOException {
        Chave novaChave = new Chave(id,pos);

        cresceu = false;
        inserir (raiz,novaChave);

        if (cresceu) {
            Pagina novaRaiz = new Pagina(ordem, false);
            novaRaiz.chaves[0] = promovida;
            novaRaiz.filhos[0] = raiz;
            novaRaiz.filhos[1] = direita;
            novaRaiz.n = 1;
            raiz = novaRaiz;
        }

        // Salvar no arquivo binário
        salvar();
    }

    private void inserir (Pagina pagina, Chave chave) {
        if (pagina == null) {
            promovida = chave;
            direita = null;
            cresceu = true;
        } else {
            int i = 0;

            while (i < pagina.n && chave.getId() > pagina.chaves[i].getId()) i++;
               
            if (i < pagina.n && chave.getId() == pagina.chaves[i].getId()) {
                throw new IllegalArgumentException("ID duplicado");
            }

            if (pagina.folha) {
                promovida = chave;
                direita = null;
                cresceu = true;
            } else {
                inserir(pagina.filhos[i], chave);
            }

            if (cresceu) inserirNaPagina(pagina, i);
        }
    }

    private void inserirNaPagina(Pagina pagina, int i) {
        if (pagina.n < ordem - 1) {

            for (int j = pagina.n; j > i; j--) {
                pagina.chaves[j] = pagina.chaves[j - 1];
                pagina.filhos[j + 1] = pagina.filhos[j];
            }

            pagina.chaves[i] = promovida;
            pagina.filhos[i + 1] = direita;
            pagina.n++;

            if (direita != null) pagina.folha = false;

            cresceu = false;

        } else {
            dividirPagina(pagina, i);
        }
    }

    // DIVISÃO DE PÁGINA CHEIA

    private void dividirPagina(Pagina pagina, int i) {

        // Arranjos temporários para acomodar o excesso
        Chave[] tempChaves = new Chave[ordem];
        Pagina[] tempFilhos = new Pagina[ordem + 1];

        for (int j = 0; j < pagina.n; j++) {
            tempChaves[j] = pagina.chaves[j];
        }

        for (int j = 0; j <= pagina.n; j++) {
            tempFilhos[j] = pagina.filhos[j];
        }

        // Abre espaço para a nova chave
        for (int j = pagina.n; j > i; j--) {
            tempChaves[j] = tempChaves[j - 1];
            tempFilhos[j + 1] = tempFilhos[j];
        }

        tempChaves[i] = promovida;
        tempFilhos[i + 1] = direita;

        int meio = ordem / 2;

        Pagina nova = new Pagina(ordem,pagina.folha);

        // Limpa e reconstrói a página esquerda
        for (int j = 0; j < ordem - 1; j++) {
            pagina.chaves[j] = null;
        }

        for (int j = 0; j < ordem; j++) {
            pagina.filhos[j] = null;
        }

        pagina.n = meio;

        for (int j = 0; j < meio; j++) {
            pagina.chaves[j] = tempChaves[j];
        }

        for (int j = 0; j <= meio; j++) {
            pagina.filhos[j] = tempFilhos[j];
        }

        // Constrói a página direita
        nova.n = ordem - meio - 1;

        for (int j = 0; j < nova.n; j++) {
            nova.chaves[j] = tempChaves[meio + 1 + j];
        }

        for (int j = 0; j <= nova.n; j++) {
            nova.filhos[j] = tempFilhos[meio + 1 + j];
        }

        // Chave central sobe para o pai
        promovida = tempChaves[meio];
        direita = nova;
        cresceu = true;
    }

    // PERSISTÊNCIA

    private void salvar() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(caminho))) {

            out.writeInt(ordem);
            out.writeObject(raiz);
        }
    }

    private void carregar() throws IOException {
        try (ObjectInputStream in = new ObjectInputStream( new FileInputStream(caminho))) {

            int ordemArquivo = in.readInt();

            if (ordemArquivo != ordem) {
                throw new IOException("Ordem incompatível");
            }

            try {
                raiz = (Pagina) in.readObject();
            } catch (ClassNotFoundException e) {
                throw new IOException("Erro ao carregar índice", e);
            }
        }
    }
}
