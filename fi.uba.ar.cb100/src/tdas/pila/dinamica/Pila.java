package tdas.pila.dinamica;

import tdas.pila.excepciones.PilaException;

public class Pila<T> {
    private static final int CAPACIDAD_INICIAL = 5;
    private static final int FACTOR_REDIMENSION_UP = 2;
    private static final int FACTOR_REDIMENSION_DOWN = 2;

    private T[] elementos;
    private int indiceActual;

    public Pila() {
        this.elementos = (T[]) new Object[CAPACIDAD_INICIAL];
        indiceActual = 0;
    }

    public void apilar(T dato) {
        if (estaLlena()) {
            redimensionar(elementos.length * FACTOR_REDIMENSION_UP);
        }
        elementos[indiceActual++] = dato;
    }

    public T desapilar() throws PilaException {
        if (estaVacia()) {
            throw new PilaException("Pila vacía");
        }
        T dato = elementos[--indiceActual];
        if (hayPocosElementos()) {
            redimensionar(elementos.length / FACTOR_REDIMENSION_DOWN);
        }
        return dato;
    }

    public T verTope() throws PilaException {
        if (estaVacia()) {
            throw new PilaException("Pila vacía");
        }
        return elementos[indiceActual - 1];
    }

    public boolean estaVacia() {
        return indiceActual == 0;
    }

    private boolean estaLlena() {
        return indiceActual == elementos.length;
    }

    private boolean hayPocosElementos() {
        return indiceActual < elementos.length / 4 && elementos.length > CAPACIDAD_INICIAL;
    }

    private void redimensionar(int nuevaCapacidad) {
        T[] nuevos = (T[]) new Object[nuevaCapacidad];
        System.arraycopy(elementos, 0, nuevos, 0, Math.min(indiceActual, nuevaCapacidad));
        elementos = nuevos;
    }
}