package semana02.ejercicios;

public class Ejercicio04 {
    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();

        // Estado inicial
        long inicio = rt.totalMemory() - rt.freeMemory();
        System.out.println("Memoria usada inicial: " + inicio);

        int[] primitivos = new int[10_000_000]; // 10 millones
        long despuesPrimitivos = rt.totalMemory() - rt.freeMemory();
        System.out.println("Después de primitivos: " + (despuesPrimitivos - inicio));

        Integer[] objetos = new Integer[10_000_000];
        for (int i = 0; i < objetos.length; i++) {
            objetos[i] = i;
        }
        long despuesObjetos = rt.totalMemory() - rt.freeMemory();
        System.out.println("Después de objetos: " + (despuesObjetos - despuesPrimitivos));
    }
}
