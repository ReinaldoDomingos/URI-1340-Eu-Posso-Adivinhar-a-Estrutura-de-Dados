import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);//Teste URI
//        FileReader ir = new FileReader("teste.txt");//Teste com arquivo
        BufferedReader in = new BufferedReader(ir);
        String str;
        while ((str = in.readLine()) != null) {
            ArrayList list = new ArrayList();
            ArrayList list2 = new ArrayList();
            ArrayList remocoes = new ArrayList();
            Heap heap = new Heap();
            Fila fila = new Fila();
            Pilha pilha = new Pilha();
            int isHeap = -1, isPilha = -1, isFila = -1, isImpossivel = -1;
            int n = Integer.parseInt(str);

            for (; n > 0; n--) {
                str = in.readLine();
                String[] s = str.split(" ");
                int op = Integer.parseInt(s[0]);
                int x = Integer.parseInt(s[1]);

                if (op == 1) {
                    list.add(x);
                    list2.add(x);
                    heap.inserir(x);
                    fila.inserir(x);
                    pilha.inserir(x);
                } else if (op == 2) {
                    int ti = list2.size();
                    if (list2.size() > 0) {
                        list2.remove(new Integer(x));
                        if (list2.size() == ti) isImpossivel = 0;
                        else
                            remocoes.add(x);
                    }
                    boolean h = heap.remover(x);
                    boolean f = fila.remover(x);
                    boolean p = pilha.remover(x);
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
            Collections.sort(list2);
            String list_r = "" + remocoes;
            Collections.reverse(list);
            String list_o = "" + list;
            String listaHF = "" + heap;
            String listaFF = "" + fila;
            String listaPF = "" + pilha;

            if (isImpossivel == 0) {
                System.out.println("impossible");
                continue;
            }
            if ((isHeap == -1 && isFila == -1) || (isHeap == -1 && isPilha == -1)
                    || (isHeap == -1 && isFila == -1 && isPilha == -1) || (list_r.equals(list_o) && isPilha != -1)) {
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

    static class NoSimples<T> {
        private int key;
        private NoSimples proximo;

        public NoSimples(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public void setProximo(NoSimples proximo) {
            this.proximo = proximo;
        }

        public NoSimples getProximo() {
            return proximo;
        }

        @Override
        public String toString() {
            return String.valueOf(key);
        }
    }

    static class Fila<T> {
        private NoSimples inicio, fim;

        boolean inserir(int key) {
            NoSimples no = new NoSimples(key);
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

        public boolean remover(int x) {
            if (inicio.getKey() == x) {
                remover();
                return true;
            }
            return false;
        }

        public boolean remover() {
            if (!isNull(inicio)) {
                inicio = inicio.getProximo();
                return true;
            }
            return false;
        }

        private boolean isNull(NoSimples<T> no) {
            return no == null;
        }

        @Override
        public String toString() {
            String retorno = "[" + ((!isNull(inicio)) ? inicio : "");
            NoSimples ptr = (!isNull(inicio)) ? inicio.getProximo() : null;
            while (!isNull(ptr)) {
                retorno += ", " + ptr;
                ptr = ptr.getProximo();
            }
            retorno += "]";
            return retorno;
        }
    }

    static class Pilha<T> {
        private NoSimples<T> inicio, fim;

        boolean inserir(int key) {
            NoSimples no = new NoSimples(key);
            if (isNull(inicio)) {
                inicio = fim = no;
                return true;
            }
            inicio.setProximo(no);
            inicio = no;
            return true;
        }

        public boolean remover(int x) {
            if (isNull(inicio)) {
                return false;
            } else if (inicio.getKey() == x) {
                remover();
                return true;
            }
            return false;
        }

        public boolean remover() {
            if (!isNull(inicio) && !isNull(inicio.getProximo())) {
                inicio = inicio.getProximo();
                return true;
            } else if (isNull(inicio.getProximo())) {
                inicio = fim;
            }
            return false;
        }

        private boolean isNull(NoSimples<T> no) {
            return no == null;
        }

        @Override
        public String toString() {
            String retorno = ((!isNull(fim)) ? fim : "") + "]";
            NoSimples ptr = (!isNull(fim)) ? fim.getProximo() : null;
            while (!isNull(ptr)) {
                retorno = ptr + ", " + retorno;
                ptr = ptr.getProximo();
            }
            retorno = "[" + retorno;
            return retorno;
        }
    }

    static class Heap<T extends Comparable<T>> {
        ArrayList<NoSimples> lista = new ArrayList<>();
        NoSimples raiz;

        boolean remover(int x) {
            if (x == lista.get(0).getKey()) {
                remover();
                return true;
            }
            return false;
        }

        void remover() {
            troca(0, lista.size() - 1);
            lista.remove(lista.size() - 1);
            descer(0);
        }

        void inserir(int key) {
            NoSimples novo = new NoSimples(key);
            if (lista.size() == 0) {
                lista.add(novo);
            } else {
                raiz = novo;
                lista.add(raiz);
                subir(lista.size() - 1);
            }
        }

        void subir(int pos) {
            int pai = getPai(pos);
            if (lista.get(pai).getKey() < lista.get(pos).getKey()) {
                troca(pai, pos);
                subir(pai);
            }
        }

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

        void troca(Integer pos1, Integer pos2) {
            NoSimples pai = new NoSimples(lista.get(pos1).getKey());
            NoSimples filho = new NoSimples(lista.get(pos2).getKey());

            lista.set(pos1, filho);
            lista.set(pos2, pai);
        }

        int getPai(int pos) {
            if (pos > 2)
                pos /= 2;
            else
                pos = 0;
            return pos;
        }

        @Override
        public String toString() {
            return "" + this.lista;
        }
    }
}