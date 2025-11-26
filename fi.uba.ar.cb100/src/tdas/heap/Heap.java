package tdas.heap;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class Heap<T> implements ColaDePrioridad<T> {
    private final static int TAMANIO_INIT = 23;

    private T[] datos;
    private int cantidad;
    private final Comparator<T> cmp;

    public Heap(Comparator<T> cmp) {
        this.datos    = (T[]) new Object[TAMANIO_INIT];
        this.cantidad = 0;
        this.cmp      = cmp;
    }

    public Heap(T[] datos, Comparator<T> cmp) {
        this.datos    = datos;
        this.cantidad = datos.length;
        this.cmp      = cmp;
        this.heapify();
    }

    @Override
    public boolean estaVacia() {
        return this.cantidad == 0;
    }

    @Override
    public void encolar(T elemento) {
        if (this.cantidad == this.datos.length) {
            this.redimensionar(this.datos.length * 2);
        }
        this.datos[this.cantidad++] = elemento;
        this.upheap(this.cantidad - 1);
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
        if (this.estaVacia()) {
            throw new RuntimeException("Cola vacía");
        }
        T maximo = this.datos[0];
        swap(0, this.cantidad - 1);
        this.cantidad--;
        this.datos[cantidad] = null;
        downheap(0);
        if ((this.datos.length > TAMANIO_INIT) && (this.cantidad < this.datos.length / 4)) {
            this.redimensionar(this.datos.length / 2);
        }
        return maximo;
    }

    @Override
    public int cantidad() {
        return this.cantidad;
    }

    private void downheap(int posicionPadre) {
        if (this.obtenerPosicionHijoIzq(posicionPadre) >= this.cantidad) {
            return;
        }
        int posicionHijoIzq = this.obtenerPosicionHijoIzq(posicionPadre);
        int posicionHijoDer = this.obtenerPosicionHijoDer(posicionPadre);
        int maximo = obtenerPosicionMaximo(posicionPadre, posicionHijoIzq, posicionHijoDer);
        if (maximo != posicionPadre) {
            swap(posicionPadre, maximo);
            this.downheap(maximo);
        }
    }

    private int obtenerPosicionMaximo(int posicionPadre, int posicionHijoIzq, int posicionHijoDer) {
        int posicionMax = posicionPadre;
        if (posicionHijoIzq < this.cantidad) {
            if (this.cmp.compare(this.datos[posicionPadre], this.datos[posicionHijoIzq]) < 0) {
                posicionMax = posicionHijoIzq;
            }
        }
        if (posicionHijoDer < this.cantidad) {
            if (this.cmp.compare(this.datos[posicionMax], this.datos[posicionHijoDer]) < 0) {
                posicionMax = posicionHijoDer;
            }
        }
        return posicionMax;
    }

    private void upheap(int posicionHijo) {
        if (posicionHijo == 0) {
            return;
        }
        int posicionPadre = obtenerPosicionDelPadre(posicionHijo);
        if (this.cmp.compare(this.datos[posicionPadre], this.datos[posicionHijo]) < 0) {
            swap(posicionPadre, posicionHijo);
            upheap(posicionPadre);
        }
    }

    private int obtenerPosicionDelPadre(int posicionHijo) {
        return (posicionHijo - 1) / 2;
    }

    private int obtenerPosicionHijoIzq(int posicionPadre) {
        return (2 * posicionPadre) + 1;
    }

    private int obtenerPosicionHijoDer(int posicionPadre) {
        return (2 * posicionPadre) + 2;
    }

    private void heapify() {
        for (int i = this.obtenerPosicionDelPadre(this.cantidad - 1); i >= 0 ; i--) {
            this.downheap(i);
        }
    }

    private void redimensionar(int nuevoTamanio) {
        T[] nuevos = (T[]) new Object[nuevoTamanio];
        System.arraycopy(this.datos, 0, nuevos, 0, this.cantidad);
        this.datos = nuevos;
    }

    private void swap(int i, int j) {
        T aux         = this.datos[i];
        this.datos[i] = this.datos[j];
        this.datos[j] = aux;
    }
}
