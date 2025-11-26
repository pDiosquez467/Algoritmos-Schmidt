package tdas.heap;

import java.util.Comparator;
/**
 * Clase que implementa una Cola de Prioridad con la semántica de Min-Heap.
 * Reutiliza la implementación de Max-Heap (Heap<T>) invirtiendo el Comparator.
 */
public class MinHeap<T> extends Heap<T> {

    public MinHeap(Comparator<T> cmp) {
        super(invertir(cmp));
    }

    public MinHeap(T[] datos, Comparator<T> cmp) {
        super(datos, invertir(cmp));
    }

    private static <T> Comparator<T> invertir(Comparator<T> cmp) {
        return (c, otro) -> cmp.compare(c, otro) * (-1);
    }
}
