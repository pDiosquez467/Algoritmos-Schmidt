package tdas.vector;

/**
 * Interfaz que define el contrato para un iterador sobre colecciones.
 * Proporciona operaciones básicas para recorrer elementos secuencialmente.
 *
 * @param <T> el tipo de elementos que retorna el iterador
 */
public interface Iterador<T> {

    /**
     * Verifica si existe un siguiente elemento disponible en la iteración.
     *
     * @return true si hay más elementos por recorrer, false en caso contrario
     */
    boolean haySiguiente();

    /**
     * Retorna el elemento actual en la posición del iterador sin avanzarlo.
     *
     * @return el elemento actual
     * @throws Exception si no hay elemento actual disponible (iterador al final)
     */
    T verActual() throws Exception;

    /**
     * Avanza el iterador al siguiente elemento de la colección.
     *
     * @throws Exception si no hay más elementos por recorrer
     */
    void siguiente() throws Exception;
}
