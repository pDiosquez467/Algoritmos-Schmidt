package examenes.parciales.octubre2024.mem;

public class Main {
    public static void main(String[] args) {
        int A, C, F = 0;
        char G;
        int H;
        int[] L = new int[6];
        int[] M = L;
        H = 69;
        G = 'B';
        A = H;
        L[0] = A;
        M[1] = L[0];
        System.out.println(A + " - " + G + " - " + M[0] + " - " + L[1]);

        G = (char) H;
        M[2] = L[0] + A;
        System.out.println(G + " - " + H + " - " + M[1] + " - " + L[2]);

        while (L[0] > 10) {
            H--;
            A--;
            C = A;
            System.out.println(C + " - " + H + " - " + M[1] + " - " + F);
            F = A;
            C--;
            M[0] -= 30;
        }

        /*
        * Salida:
            69 - B - 69 - 69
            E - 69 - 69 - 138
            68 - 68 - 69 - 0
            67 - 67 - 69 - 68
        */
    }
}
