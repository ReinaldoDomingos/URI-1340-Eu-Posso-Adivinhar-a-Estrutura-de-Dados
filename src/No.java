public class No<T> {
    private T key;
    private No<T> proximo;

    public No(T key) {
        this.key = key;
        this.proximo = null;
    }

    public void setkey(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public No getNext() {
        return proximo;
    }

    public void setNext(No<T> proximo) {
        this.proximo = proximo;
    }

    @Override
    public String toString() {
        return "" + key;
    }
}