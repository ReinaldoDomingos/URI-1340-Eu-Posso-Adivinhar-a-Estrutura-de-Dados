import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {
    ArrayList<NoHeap> lista = new ArrayList<>();
    NoHeap raiz;

    boolean remover() {
        lista.set(0, lista.get(lista.size() - 1));
        lista.remove(lista.size() - 1);
    }

    boolean adicionar(int key) {
        NoHeap novo = new NoHeap(key);
        if (lista.size() == 0) {
            lista.add(novo);
        } else {
            raiz = novo;
            lista.set(0, raiz);
        }
        return false;
    }

    void subir(int pos) {
        int pai = pos / 2;
        if (lista.get(pai).getKey() < lista.get(pos).getKey()) {
            troca(lista.get(pai), lista.get(pos));
            subir(pai);
        }
    }

    void descer(int pos) {
        int esq, dir, maior = pos;
        esq = pos * 2;
        dir = esq + 1;

        if (esq <= lista.size())
            maior = esq;
        if (dir <= lista.size() && lista.get(dir).getKey() > lista.get(maior).getKey())
            maior = dir;
        if (pos != maior && lista.get(maior).getKey() > lista.get(pos).getKey()) {
            troca(lista.get(pos), lista.get(maior));
            descer(maior);
        }
    }

    private void troca(NoHeap item1, NoHeap item2) {
        troca(item1.getKey(), item2.getKey());
    }

    private void troca(Integer item1, Integer item2) {
        Integer aux = item1;
        item1 = item2;
        item2 = aux;
    }

}
