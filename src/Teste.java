public class Teste {
    public static void main(String[] args) {
        String s1 = "1";
        int n = Integer.parseInt(s1);
        System.out.println(n);

        String s2 = "1 10";
        String[] s = s2.split(" ");
        int op = Integer.parseInt(s[0]);
        int x = Integer.parseInt(s[1]);
        System.out.println(op);
        System.out.println(x);
    }
}
