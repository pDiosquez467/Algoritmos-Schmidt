package tdas.heap;

import java.util.Comparator;

@SuppressWarnings("all")
public class Heap<T> implements ColaDePrioridad<T> {

    private final static int TAMANIO_INIT = 23;

    private T[] datos;
    private int cantidad;
    private Comparator<T> cmp;

    public Heap(Comparator<T> cmp) {
        this.datos = (T[]) new Object[TAMANIO_INIT];
        this.cantidad = 0;
        this.cmp = cmp;
    }

    public Heap(T[] datos, Comparator<T> cmp) {
        this.datos = datos;
        this.cantidad = datos.length;
        this.cmp = cmp;
        this.heapify(datos, cmp);
    }

    @Override
    public boolean estaVacia() {
        return this.cantidad == 0;
    }

    @Override
    public void encolar(T elemento) {

    }

    @Override
    public T verMaximo() {
        if (this.estaVacia()) {
            throw new RuntimeException("Cola vacía");
        }
        return this.datos[0];
    }

    @Override
    public T desencolar() {
        return null;
    }

    @Override
    public int cantidad() {
        return this.cantidad;
    }

    private void downheap(T[] arr, int pos, Comparator<T> cmp) {
        if (pos == arr.length - 1) {
            return;
        }
        int posicionHijoIzq = this.obtenerPosicionHijoIzq(pos);
        int posicionHijoDer = this.obtenerPosicionHijoDer(pos);
        int maximo = obtenerPosicionMaximo(arr, cmp, pos, posicionHijoIzq, posicionHijoDer);
        if (maximo != pos) {
            swap(arr, pos, maximo);
            this.downheap(arr, maximo, cmp);
        }
    }

    private int obtenerPosicionMaximo(T[] arr, Comparator<T> cmp, int pos, int posicionHijoIzq, int posicionHijoDer) {
        return 0;
    }

    private void upheap(T[] arr, int posicionHijo, Comparator<T> cmp) {
        if (posicionHijo == 0) {
            return;
        }
        int posicionPadre = obtenerPosicionDelPadre(posicionHijo);
        if (cmp.compare(arr[posicionPadre], arr[posicionHijo]) > 0) {
            this.swap(arr, posicionPadre, posicionHijo);
            this.upheap(arr, posicionPadre, cmp);
        }
    }

    private int obtenerPosicionDelPadre(int posicionHijo) {
        return (posicionHijo - 1) / 2;
    }

    private int obtenerPosicionHijoIzq(int posicionPadre) {
        return 2 * posicionPadre + 1;
    }

    private int obtenerPosicionHijoDer(int posicionPadre) {
        return 2 * posicionPadre + 2;
    }

    private void heapify(T[] datos, Comparator<T> cmp) {

    }

    private void redimensionar(int nuevoTamanio) {
        T[] nuevos = (T[]) new Object[nuevoTamanio];
        System.arraycopy(this.datos, 0, nuevos, 0, this.cantidad);
        this.datos = nuevos;
    }

    private void swap(T[] arr, int i, int j) {
        T aux  = arr[i];
        arr[i] = arr[j];
        arr[j] = aux;
    }
}
