package tdas.lista.teorica.doble;

public class NodoDoblementeEnlazado<T> {
    private T dato;
    private NodoDoblementeEnlazado<T> anterior;
    private NodoDoblementeEnlazado<T> siguiente;

    public NodoDoblementeEnlazado(T dato, NodoDoblementeEnlazado<T> anterior, NodoDoblementeEnlazado<T> siguiente) {
        this.dato = dato;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }

    public NodoDoblementeEnlazado(T dato) {
        this(dato, null, null);
    }

    public T dato() {
        return dato;
    }

    public NodoDoblementeEnlazado<T> anterior() {
        return anterior;
    }

    public NodoDoblementeEnlazado<T> siguiente() {
        return siguiente;
    }

    public void dato(T dato) {
        this.dato = dato;
    }

    public void anterior(NodoDoblementeEnlazado<T> anterior) {
        this.anterior = anterior;
    }

    public void siguiente(NodoDoblementeEnlazado<T> proximo) {
        this.siguiente = proximo;
    }

    public boolean haySiguiente() {
        return siguiente != null;
    }

    public boolean hayAnterior() {
        return anterior != null;
    }
}
