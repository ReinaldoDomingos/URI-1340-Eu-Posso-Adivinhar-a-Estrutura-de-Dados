public class NoHeap {
    private int key;
    private NoHeap direita;
    private NoHeap esquerda;

    public NoHeap(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public int getPosPai() {
        return (key + 1) / 2;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public NoHeap getDireita() {
        return direita;
    }

    public void setDireita(NoHeap direita) {
        this.direita = direita;
    }

    public NoHeap getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoHeap esquerda) {
        this.esquerda = esquerda;
    }

}
