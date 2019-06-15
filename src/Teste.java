public class Teste {
    public static void main(String[] args) {

        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.insere(2);
        lista.imprimeRecursivo();
        lista.insere(1);
        lista.imprimeRecursivo();
        lista.insere(3);
        lista.imprimeRecursivo();
        lista.insere(4);
        lista.imprimeRecursivo();
        lista.insere(0);
        lista.imprimeRecursivo();

        System.out.println(lista.remover(4).getKey());
        System.out.println(lista.length());
    }
}
