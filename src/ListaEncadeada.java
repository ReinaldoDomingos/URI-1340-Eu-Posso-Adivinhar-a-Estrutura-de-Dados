public class ListaEncadeada<T extends Comparable<T>> {

    private int length = 0;
    private No<T> inicio;

    public ListaEncadeada() {
        this.inicio = null;
    }

    private boolean isNull(No<T> ptr) {
        return (ptr == null);
    }

    public boolean busca(T valor) {
        No<T> ptr = this.inicio;

        while (!isNull(ptr) && (ptr.getKey().compareTo(valor)) != 0) {
            ptr = ptr.getNext();
        }

        return (!isNull(ptr));
    }

    private No buscaAnterior(T valor) {
        No<T> ant = null;
        No<T> ptr = this.inicio;

        while (!isNull(ptr) && (ptr.getKey().compareTo(valor)) < 0) {
            ant = ptr;
            ptr = ptr.getNext();
        }
        return ant;
    }

    public void insere(T valor) {
        if (isNull(this.inicio)) {
            this.inicio = new No<>(valor);
            addLength();

        } else {
            No<T> novo = new No<>(valor);
            No<T> ant = buscaAnterior(valor);

            if (isNull(ant)) {
                novo.setNext(this.inicio);
                this.inicio = novo;
                addLength();

            } else {
                novo.setNext(ant.getNext());
                ant.setNext(novo);
                addLength();
            }
        }
    }

    public void imprimeIterativo() {

        No<T> ptr = this.inicio;

        while (!isNull(ptr)) {
            System.out.print(ptr.getKey() + " ");
            ptr = ptr.getNext();
        }

        System.out.println("\n");
    }

    public void imprimeRecursivo() {

        imprimeRecursivo(this.inicio);
        System.out.println("\n");

    }

    public void imprimeRecursivo(No<T> ptr) {
        if (isNull(ptr)) {

            return;

        }

        System.out.print(ptr.getKey() + " ");
        imprimeRecursivo(ptr.getNext());
    }

    public No<T> remover(T valor) {

        No<T> antRemovido = buscaAnterior(valor);
        No<T> noRemovido = null;

        if (!isNull(antRemovido)) {
            noRemovido = antRemovido.getNext();

            if (!isNull(antRemovido) && valor.compareTo(noRemovido.getKey()) == 0) {
                No<T> proxRemovido = antRemovido.getNext().getNext();
                antRemovido.setNext(proxRemovido);
            }
        } else if (valor.compareTo(this.inicio.getKey()) == 0) {

            noRemovido = this.inicio;
            this.inicio = this.inicio.getNext();

        } else {
            return null;
        }

        lessLength();
        return noRemovido;
    }

    public int length() {
        return this.length;
    }

    private void addLength() {
        this.length++;
    }

    private void lessLength() {
        this.length--;
    }
}

