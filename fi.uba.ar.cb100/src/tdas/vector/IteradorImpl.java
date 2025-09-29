package tdas.vector;

/**
 * Implementación concreta del iterador para la clase Vector.
 * Permite recorrer secuencialmente los elementos del vector desde el inicio al final.
 *
 * @param <T> el tipo de elementos contenidos en el vector
 */
public class IteradorImpl<T> implements Iterador<T>{
    private final Vector<T> vector;
    private int indiceActual;

    /**
     * Construye un nuevo iterador para el vector especificado.
     * El iterador comienza en la posición 0 (primero elemento).
     *
     * @param vector el vector sobre el cual se iterará
     */
    public IteradorImpl(Vector<T> vector) {
        this.vector = vector;
        this.indiceActual = 0;
    }

    /**
     * Verifica si existe un siguiente elemento en el vector.
     *
     * @return true si el índice actual es menor al tamaño del vector
     */
    @Override
    public boolean haySiguiente() {
        return indiceActual < vector.size();
    }

    /**
     * Retorna el elemento en la posición actual del iterador.
     *
     * @return el elemento actual
     * @throws RuntimeException si no hay elemento actual (iterador al final)
     */
    @Override
    public T verActual() throws Exception {
        if (!haySiguiente()) {
            throw new RuntimeException("No hay nada para ver");
        }
        return vector.obtener(indiceActual);
    }

    /**
     * Avanza el iterador a la siguiente posición.
     *
     * @throws RuntimeException si se intenta avanzar más allá del final del vector
     */
    @Override
    public void siguiente() throws Exception {
        if (!haySiguiente()) {
            throw new RuntimeException("No hay nada para ver");
        }
        indiceActual++;
    }
}