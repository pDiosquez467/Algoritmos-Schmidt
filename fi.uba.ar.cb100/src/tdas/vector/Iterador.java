package tdas.vector;

public interface Iterador<T> {
    boolean haySiguiente();

    T verActual() throws Exception;

    void siguiente() throws Exception;
}
