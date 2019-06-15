import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {
    ArrayList<NoHeap> lista = new ArrayList<>();
    NoHeap raiz;



    boolean remover() {
        troca(0,lista.size() - 1);
        lista.remove(lista.size() - 1);
        descer(0);
        System.out.println(lista);                
        return false;
    }

    int adicionar(int key) {
        NoHeap novo = new NoHeap(key);
        if (lista.size() == 0) {
            lista.add(novo);
        } else {
            raiz = novo;
            lista.add(raiz);
            subir(lista.size()-1);
        }
        System.out.println(lista);
        return 0;
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
    esq = (pos>0)?(pos+1) * 2 - 1:1;
    dir = (pos>0)?esq + 1:2;
    // System.out.println("Filhos: " + lista.get(esq)+" "+lista.get(dir));                
    if (esq <= lista.size())
        maior = esq;
    if (dir <= lista.size() && lista.get(dir).getKey() > lista.get(maior).getKey())
        maior = dir;
    if (pos != maior && lista.get(maior).getKey() > lista.get(pos).getKey()) {
        System.out.println(lista);                
        System.out.println("["+pos+"]="+lista.get(pos)+
            " - Maior Filho " + lista.get(maior));                        
        troca(pos, maior);
        descer(maior);
    }
}

void  troca(Integer pos1, Integer pos2) {
    NoHeap pai = new NoHeap(lista.get(pos1).getKey());
    NoHeap filho = new NoHeap(lista.get(pos2).getKey());

    System.out.println(pai+"-"+filho);
    lista.set(pos1,filho);
    lista.set(pos2,pai);
}

int getPai(int pos){
    System.out.print("["+pos+"]="+lista.get(pos));
    if(pos>2)
        pos/=2;
    pos=  0;    
    System.out.println(" - Pai " + lista.get(pos));
    return pos;
}
int getEsquerda(int posPai){
    return (posPai+1)*2 -1;
}

int getDireita(int posPai){
    return (posPai+1)*2;
}

@Override
public String toString(){
    return "" + this.lista;
}
}
