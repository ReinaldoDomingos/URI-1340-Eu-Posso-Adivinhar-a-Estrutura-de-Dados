import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class Main {
    /**
     * Metodo principal a qual executara o código
     * - Será feita a leitura de um arquivo com todos os casos de testes a ser executado executados
     * - Para cada cado de teste será lido um inteiro n e ns inteiros acao e valor
     * n - representa o numero de acoes a ser executada naquele caso de teste
     * acao - representa a acão a ser executada com o valor na lista: 1 - Insere o valor e 2 - Remove o valor
     * valor - valor a ser inserido ou removido da lista
     * Ao final dos testes para ca um dos n teste será imprimida uma unica string com o valor da estruturada de dados
     * achado naquela lista de valores da lista de teste.
     * DESCRIÇÂO DOS RESUSLTADOS:
     * stack - É definitivamente uma pilha.
     * queue - É definitivamente uma fila.
     * priority queue - É definitivamente uma fila de prioridade.
     * impossible - Não pode ser uma pilha, uma fila ou uma fila de prioridade.
     * not sure - Pode ser mais de uma das três estruturas mencionadas acima.
     * -
     */
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);//Teste URI
//        FileReader ir = new FileReader("src/teste3.txt");//Teste com arquivo teste.txt, teste2.txt ou teste3.txt
        BufferedReader in = new BufferedReader(ir);
        String str;
        while ((str = in.readLine()) != null) {
            ArrayList lista = new ArrayList();
            ArrayList lista2 = new ArrayList();
            ArrayList lista_remocoes = new ArrayList();
            Heap heap = new Heap();
            Fila fila = new Fila();
            Pilha pilha = new Pilha();
            int isHeap = -1, isPilha = -1, isFila = -1, isImpossivel = -1;
            int n = Integer.parseInt(str);

            for (int i = n; i > 0; i--) {
                str = in.readLine();
                String[] s = str.split(" ");
                int acao = Integer.parseInt(s[0]);
                int valor = Integer.parseInt(s[1]);

                if (acao == 1) {
                    lista.add(valor);
                    lista2.add(valor);
                    heap.inserir(valor);
                    fila.inserir(valor);
                    pilha.inserir(valor);
                } else if (acao == 2) {
                    int tamanhoInicial = lista2.size();
                    if (tamanhoInicial > 0) {
                        lista2.remove(new Integer(valor));
                        if (lista2.size() == tamanhoInicial) isImpossivel = 0;
                        lista_remocoes.add(valor);
                    }
                    boolean h = heap.remover(valor);
                    boolean f = fila.remover(valor);
                    boolean p = pilha.remover(valor);
                    if (!h) {
                        isHeap = 0;
                    }
                    if (!f) {
                        isFila = 0;
                    }
                    if (!p) {
                        isPilha = 0;
                    }
                }
            }
            Collections.sort(lista2);
            String list_remocoes = "" + lista_remocoes;
            Collections.reverse(lista);
            String lista_ordenada_inversamente = "" + lista;
            Collections.sort(lista);
            String lista_ordenada = "" + lista;
            String listaHF = "" + heap;
            String listaFF = "" + fila;
            String listaPF = "" + pilha;

            if (isImpossivel == 0) {
                System.out.println("impossible");
                continue;
            }
            if ((isHeap == -1 && isFila == -1) || (isHeap == -1 && isPilha == -1)
                    || (isHeap == -1 && isFila == -1 && isPilha == -1)
                    || (list_remocoes.equals(lista_ordenada_inversamente) && isPilha != -1)
                    || (list_remocoes.equals(lista_ordenada) && isFila != -1 && isPilha != -1)) {
                System.out.println("not sure");
                continue;
            }
            if ((listaHF.equals("[]") || isHeap == -1) && isFila != -1 && isPilha != -1) {
                System.out.println("priority queue");
                continue;
            }
            if ((listaFF.equals("[]") || isFila == -1) && isPilha != -1 && isHeap != -1) {
                System.out.println("queue");
                continue;
            }
            if ((listaPF.equals("[]") || isPilha == -1) && isHeap != -1 && isFila != -1) {
                System.out.println("stack");
                continue;
            }
            System.out.println("not sure");
        }

    }

    /**
     * @param <T> Tipo que do objeto No armazenará
     *            No representa  um item de uma lista encadeada a qual possui uma chave (key)
     *            e um ponteiro para o proximo item
     */
    static class No<T> {
        private int key;
        private No proximo;

        /**
         * Construtor da classe
         *
         * @param key -  valor/chave que o No contem
         */
        public No(int key) {
            this.key = key;
        }

        /**
         * @return Retorna valor/chave que o No contem
         */
        public int getKey() {
            return key;
        }

        /**
         * Coloca o valor next para o atributo next
         *
         * @param proximo - Ponteiro para o Proximo No
         */
        public void setProximo(No proximo) {
            this.proximo = proximo;
        }

        /**
         * @return - Retorna o ponteiro para o Proximo No
         */
        public No getProximo() {
            return proximo;
        }

        /**
         * Formata a impressão padrão para o No
         *
         * @return - Retorna String contendo o valor da key
         */
        @Override
        public String toString() {
            return String.valueOf(key);
        }
    }

    static class Fila<T> {
        private No inicio, fim;

        /**
         * Insere o valor em um novo No e adiciona na lista
         *
         * @param key - valor a ser atribuido ao novo No
         */
        boolean inserir(int key) {
            No no = new No(key);
            if (!isNull(inicio) && !isNull(inicio.getProximo())) {
                fim.setProximo(no);
                fim = no;
                return true;
            } else if (!isNull(inicio)) {
                inicio.setProximo(no);
                fim = no;
                return true;
            } else {
                fim = inicio = no;
                return true;
            }
        }

        /**
         * Chama o outro metodo remover caso o primeiro no da Fila tenha o valor x
         *
         * @param x - Valor do No a ser removido
         * @return - Retorna se o No foi removido
         */
        public boolean remover(int x) {
            if (inicio.getKey() == x) {
                remover();
                return true;
            }
            return false;
        }

        /**
         * Remove o No que foi inserido primeiro na Fila
         *
         * @return - Retorna se o No foi removido
         */
        public boolean remover() {
            if (!isNull(inicio)) {
                inicio = inicio.getProximo();
                return true;
            }
            return false;
        }

        /**
         * Verifica se um No esta vazio
         *
         * @param no - Um Objeto do tipo No
         * @return - Retorna um booolean
         */
        private boolean isNull(No<T> no) {
            return no == null;
        }

        /**
         * Formata a impressão padrão para a lista
         *
         * @return - Retorna String contendo o valor da key
         */
        @Override
        public String toString() {
            String retorno = "[" + ((!isNull(inicio)) ? inicio : "");
            No ptr = (!isNull(inicio)) ? inicio.getProximo() : null;
            while (!isNull(ptr)) {
                retorno += ", " + ptr;
                ptr = ptr.getProximo();
            }
            retorno += "]";
            return retorno;
        }
    }

    static class Pilha<T> {
        private No<T> inicio, fim;

        /**
         * Insere o valor em um novo No e adiciona na lista
         *
         * @param key - valor a ser atribuido ao novo No
         */
        boolean inserir(int key) {
            No no = new No(key);
            if (isNull(inicio)) {
                inicio = fim = no;
                return true;
            }
            inicio.setProximo(no);
            inicio = no;
            return true;
        }

        /**
         * Chama o outro Metodo Remover caso a lista não esteja vazia e o valor de x esteja no topo da pilha
         *
         * @param x - Valor do No a ser removido
         * @return - Retorna se o No foi removido ou não
         */
        public boolean remover(int x) {
            if (isNull(inicio)) {
                return false;
            } else if (inicio.getKey() == x) {
                remover();
                return true;
            }
            return false;
        }

        /**
         * Remove o No do topo da pilha
         *
         * @return - Retorna se o No foi removido
         */
        public boolean remover() {
            if (!isNull(inicio) && !isNull(inicio.getProximo())) {
                inicio = inicio.getProximo();
                return true;
            } else if (isNull(inicio.getProximo())) {
                inicio = fim;
            }
            return false;
        }

        /**
         * Verifica se um No esta vazio
         *
         * @param no - Um Objeto do tipo No
         * @return - Retorna um booolean
         */
        private boolean isNull(No<T> no) {
            return no == null;
        }

        /**
         * Formata a impressão padrão para a lista
         *
         * @return - Retorna String contendo o valor da key
         */
        @Override
        public String toString() {
            String retorno = ((!isNull(fim)) ? fim : "") + "]";
            No ptr = (!isNull(fim)) ? fim.getProximo() : null;
            while (!isNull(ptr)) {
                retorno = ptr + ", " + retorno;
                ptr = ptr.getProximo();
            }
            retorno = "[" + retorno;
            return retorno;
        }
    }

    static class Heap<T extends Comparable<T>> {
        ArrayList<No> lista = new ArrayList<>();
        No raiz;

        /**
         * chama o outro metodo remover caso o No com o valor x esteja na raiz do Heap
         *
         * @param x - Valor do No a ser removido
         * @return - Retorna o No removido
         */
        boolean remover(int x) {
            if (x == lista.get(0).getKey()) {
                remover();
                return true;
            }
            return false;
        }

        /**
         * Remove o No da raiz
         *
         * @return - Retorna se o No foi removido
         */
        void remover() {
            troca(0, lista.size() - 1);
            lista.remove(lista.size() - 1);
            descer(0);
        }

        /**
         * Insere o valor em um novo No e adiciona na lista
         *
         * @param key - valor a ser atribuido ao novo No
         */
        void inserir(int key) {
            No novo = new No(key);
            if (lista.size() == 0) {
                lista.add(novo);
            } else {
                raiz = novo;
                lista.add(raiz);
                subir(lista.size() - 1);
            }
        }

        /**
         * Move um na posição pos para cima no Heap caso precise
         *
         * @param pos - Posição do No
         */
        void subir(int pos) {
            int pai = getPai(pos);
            if (lista.get(pai).getKey() < lista.get(pos).getKey()) {
                troca(pai, pos);
                subir(pai);
            }
        }

        /**
         * Move um na posição pos para baixa no Heap caso precise
         *
         * @param pos - Posição do No
         */
        void descer(int pos) {
            int esq, dir, maior = pos;
            esq = (pos > 0) ? (pos + 1) * 2 - 1 : 1;
            dir = (pos > 0) ? esq + 1 : 2;
            if (lista.size() > 2) {
                if (esq < lista.size())
                    maior = esq;
                if (dir < lista.size()) if (lista.get(dir).getKey() > lista.get(maior).getKey())
                    maior = dir;
                if (pos != maior && lista.get(maior).getKey() > lista.get(pos).getKey()) {
                    troca(pos, maior);
                    descer(maior);
                }
            }
        }

        /**
         * Troca dois No de posições em um Heap
         *
         * @param pos1 - Posição do primeiro No
         * @param pos2 - Posição do primeiro No
         */
        void troca(Integer pos1, Integer pos2) {
            No pai = new No(lista.get(pos1).getKey());
            No filho = new No(lista.get(pos2).getKey());

            lista.set(pos1, filho);
            lista.set(pos2, pai);
        }

        /**
         * @param pos - Posicão do filho
         * @return - Retorna posição do pai
         */
        int getPai(int pos) {
            if (pos > 2)
                pos /= 2;
            else
                pos = 0;
            return pos;
        }

        /**
         * Formata a impressão padrão para a lista
         *
         * @return - Retorna String contendo o valor da key
         */
        @Override
        public String toString() {
            return "" + this.lista;
        }
    }
}