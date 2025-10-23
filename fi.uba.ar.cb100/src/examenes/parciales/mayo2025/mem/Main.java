package examenes.parciales.mayo2025.mem;

public class Main {

    public static void main(String[] args) {
        int X, Y, Z = 3;
        char W;
        int T;
        int[] A = new int[4];
        int[] B = A;

        T = 77;
        W = 'C';
        X = T;
        A[0] = X;
        B[1] = A[0] / 2;

        System.out.println(X + " - " + W + " - " + B[0] + " - " + A[1]);

        W = (char) (T + 1);
        B[2] = A[0] * 2;

        System.out.println(W + " - " + T + " - " + B[1] + " - " + A[2]);

        while (A[0] > 30) {
            T -= 2;
            X -= 3;
            Y = X + Z;
            System.out.println(Y + " - " + T + " - " + B[1] + " - " + Z);
            Z = Y;
            A[0] -= 10;
        }
    }

    /* Salida:
        77 - C - 77 - 38
        N - 77 - 38 - 154
        77 - 75 - 38 - 3
        148 - 73 - 38 - 77
        216 - 71 - 38 - 148
        281 - 69 - 38 - 216
        343 - 67 - 38 - 281
     */
}
