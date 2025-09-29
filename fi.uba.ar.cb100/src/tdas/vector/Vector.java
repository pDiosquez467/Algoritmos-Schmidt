package tdas.vector;

import validaciones.Validaciones;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

public class Vector<T> {
    private static final int CAPACIDAD_INICIAL = 23;

    private T[] datos;
    private int tamanio;

    @SuppressWarnings("unchecked")
    public Vector(int capacidadInicial) {
        Validaciones.validarNumeroMayorACero(capacidadInicial,
                "La capacidad inicial debe ser mayor a cero");
        this.datos = (T[]) new Object[capacidadInicial];
        this.tamanio = 0;
    }

    public void insertar(int indice, T dato) {
        if (indice < 0 || indice > tamanio) {
            throw new IndexOutOfBoundsException("Fuera de rango");
        }

        if (tamanio == datos.length) {
            redimensionar(datos.length * 2);
        }

        for (int i = tamanio; i > indice; i--) {
            datos[i] = datos[i-1];
        }

        datos[indice] = dato;
        tamanio++;
    }

    public void agregar(T dato) {
        insertar(tamanio, dato);
    }

    public T obtener(int indice) {
        validarIndice(indice);
        return datos[indice];
    }

    public T remover(int indice) {
        validarIndice(indice);
        T removido = datos[indice];
        compactarDesde(indice);
        return removido;
    }

    public int indiceDe(T dato) {
        for (int i = 0; i < tamanio; i++) {
            if (Objects.equals(datos[i], dato)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contiene(T dato) {
        return indiceDe(dato) != -1;
    }

    public int ultimoIndiceDe(T dato) {
        for (int i = tamanio - 1; i >= 0; i--) {
            if (Objects.equals(datos[i], dato)) {
                return i;
            }
        }
        return -1;
    }

    public void agregarTodos(Collection<T> datos) {
        asegurarCapacidad(tamanio + datos.size());
        for (T dato : datos) {
            this.datos[tamanio++] = dato;
        }
    }

    public void agregarTodos(T[] datos) {
        asegurarCapacidad(tamanio + datos.length);
        for (T dato: datos) {
            this.agregar(dato);
        }
    }

    public Vector() {
        this(CAPACIDAD_INICIAL);
    }

    public int size() {
        return tamanio;
    }

    public boolean estaVacio() {
        return tamanio == 0;
    }

    public Iterador<T> iterador() {
        return new IteradorImpl<>(this);
    }

    public void paraCada(Consumer<T> accion) {
        for (int i = 0; i < tamanio; i++) {
            accion.accept(datos[i]);
        }
    }

    public void limpiar() {
        for (int i = 0; i < tamanio; i++) {
            datos[i] = null;
        }
        tamanio = 0;
    }

    private void validarIndice(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Fuera de rango");
        }
    }

    @SuppressWarnings("unchecked")
    private void redimensionar(int nuevaCapacidad) {
        T[] nuevos = (T[]) new Object[nuevaCapacidad];
        System.arraycopy(datos, 0, nuevos, 0, Math.min(nuevaCapacidad, tamanio));
        datos = nuevos;
    }

    private void compactarDesde(int desde) {
        for (int i = desde; i < tamanio - 1; i++) {
            datos[i] = datos[i+1];
        }
        datos[--tamanio] = null;
    }

    private void asegurarCapacidad(int capacidadMinima) {
        if (capacidadMinima > datos.length) {
            redimensionar(Math.max(datos.length * 2, capacidadMinima));
        }
    }

}
