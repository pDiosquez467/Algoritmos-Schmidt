package tdas.vector;

import validaciones.Validaciones;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Implementación de un vector dinámico genérico que redimensiona automáticamente
 * cuando se alcanza su capacidad máxima. Proporciona operaciones de inserción,
 * eliminación, búsqueda y recorrido de elementos.
 *
 * @param <T> el tipo de elementos almacenados en el vector
 */
public class Vector<T> {
    /** Capacidad inicial por defecto del vector */
    private static final int CAPACIDAD_INICIAL = 23;

    /** Arreglo interno donde se almacenan los elementos */
    private T[] datos;
    /** Cantidad actual de elementos en el vector */
    private int tamanio;

    /**
     * Construye un vector con la capacidad inicial especificada.
     *
     * @param capacidadInicial la capacidad inicial del vector (debe ser mayor a cero)
     * @throws IllegalArgumentException si la capacidad inicial no es mayor a cero
     */
    @SuppressWarnings("unchecked")
    public Vector(int capacidadInicial) {
        Validaciones.validarNumeroMayorACero(capacidadInicial,
                "La capacidad inicial debe ser mayor a cero");
        this.datos = (T[]) new Object[capacidadInicial];
        this.tamanio = 0;
    }

    /**
     * Inserta un elemento en la posición especificada, desplazando los elementos
     * existentes hacia la derecha. Redimensiona automáticamente si es necesario.
     *
     * @param indice la posición donde insertar (0 ≤ indice ≤ tamanio)
     * @param dato el elemento a insertar
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */
    public void insertar(int indice, T dato) {
        if (indice < 0 || indice > tamanio) {
            throw new IndexOutOfBoundsException("Fuera de rango");
        }

        if (tamanio == datos.length) {
            redimensionar(datos.length * 2);
        }

        // Desplazar elementos a la derecha
        for (int i = tamanio; i > indice; i--) {
            datos[i] = datos[i-1];
        }

        datos[indice] = dato;
        tamanio++;
    }

    /**
     * Agrega un elemento al final del vector.
     *
     * @param dato el elemento a agregar
     */
    public void agregar(T dato) {
        insertar(tamanio, dato);
    }

    /**
     * Obtiene el elemento en la posición especificada.
     *
     * @param indice la posición del elemento a obtener
     * @return el elemento en la posición indicada
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */
    public T obtener(int indice) {
        validarIndice(indice);
        return datos[indice];
    }

    /**
     * Elimina y retorna el elemento en la posición especificada.
     *
     * @param indice la posición del elemento a eliminar
     * @return el elemento eliminado
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */
    public T remover(int indice) {
        validarIndice(indice);
        T removido = datos[indice];
        compactarDesde(indice);
        return removido;
    }

    /**
     * Busca la primera ocurrencia del elemento especificado.
     *
     * @param dato el elemento a buscar
     * @return el índice de la primera ocurrencia, o -1 si no se encuentra
     */
    public int indiceDe(T dato) {
        for (int i = 0; i < tamanio; i++) {
            if (Objects.equals(datos[i], dato)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Verifica si el vector contiene el elemento especificado.
     *
     * @param dato el elemento a buscar
     * @return true si el elemento está presente, false en caso contrario
     */
    public boolean contiene(T dato) {
        return indiceDe(dato) != -1;
    }

    /**
     * Busca la última ocurrencia del elemento especificado.
     *
     * @param dato el elemento a buscar
     * @return el índice de la última ocurrencia, o -1 si no se encuentra
     */
    public int ultimoIndiceDe(T dato) {
        for (int i = tamanio - 1; i >= 0; i--) {
            if (Objects.equals(datos[i], dato)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Agrega todos los elementos de una colección al final del vector.
     *
     * @param coleccion la colección de elementos a agregar
     */
    public void agregarTodos(Collection<T> coleccion) {
        asegurarCapacidad(tamanio + coleccion.size());
        for (T dato : coleccion) {
            this.datos[tamanio++] = dato;
        }
    }

    /**
     * Agrega todos los elementos de un arreglo al final del vector.
     *
     * @param arreglo el arreglo de elementos a agregar
     */
    public void agregarTodos(T[] arreglo) {
        for (T dato: arreglo) {
            this.datos[tamanio++] = dato;
        }
    }

    /**
     * Construye un vector con la capacidad inicial por defecto.
     */
    public Vector() {
        this(CAPACIDAD_INICIAL);
    }

    /**
     * Retorna la cantidad de elementos en el vector.
     *
     * @return el número de elementos actualmente en el vector
     */
    public int size() {
        return tamanio;
    }

    /**
     * Verifica si el vector está vacío.
     *
     * @return true si el vector no contiene elementos, false en caso contrario
     */
    public boolean estaVacio() {
        return tamanio == 0;
    }

    /**
     * Crea un nuevo iterador para recorrer los elementos del vector.
     *
     * @return un iterador posicionado al inicio del vector
     */
    public Iterador<T> iterador() {
        return new IteradorImpl<>(this);
    }

    /**
     * Ejecuta una acción para cada elemento del vector.
     *
     * @param accion la acción a ejecutar para cada elemento
     */
    public void paraCada(Consumer<T> accion) {
        for (int i = 0; i < tamanio; i++) {
            accion.accept(datos[i]);
        }
    }

    /**
     * Elimina todos los elementos del vector y restablece su tamaño a cero.
     */
    public void limpiar() {
        for (int i = 0; i < tamanio; i++) {
            datos[i] = null;
        }
        tamanio = 0;
    }

    /**
     * Valida que un índice esté dentro del rango válido [0, tamanio-1].
     *
     * @param indice el índice a validar
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    private void validarIndice(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Fuera de rango");
        }
    }

    /**
     * Redimensiona el arreglo interno a la nueva capacidad especificada.
     *
     * @param nuevaCapacidad la nueva capacidad del arreglo
     */
    @SuppressWarnings("unchecked")
    private void redimensionar(int nuevaCapacidad) {
        T[] nuevos = (T[]) new Object[nuevaCapacidad];
        System.arraycopy(datos, 0, nuevos, 0, Math.min(nuevaCapacidad, tamanio));
        datos = nuevos;
    }

    /**
     * Compacta el vector eliminando un elemento y desplazando los elementos
     * posteriores una posición hacia la izquierda.
     *
     * @param desde la posición desde donde comenzar el compactado
     */
    private void compactarDesde(int desde) {
        for (int i = desde; i < tamanio - 1; i++) {
            datos[i] = datos[i+1];
        }
        datos[--tamanio] = null;
    }

    /**
     * Garantiza que el vector tenga al menos la capacidad mínima especificada.
     * Redimensiona si es necesario.
     *
     * @param capacidadMinima la capacidad mínima requerida
     */
    private void asegurarCapacidad(int capacidadMinima) {
        if (capacidadMinima > datos.length) {
            redimensionar(Math.max(datos.length * 2, capacidadMinima));
        }
    }
}