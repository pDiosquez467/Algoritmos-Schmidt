package tdas.lista.teorica.doble;

public class ListaDoblementeEnlazada<T> {
    private NodoDoblementeEnlazado<T> primero;
    private int size;

    public ListaDoblementeEnlazada() {
        this.primero = null;
        this.size    = 0;
    }

    public void addLast(T dato) {
        NodoDoblementeEnlazado<T> nuevo = new NodoDoblementeEnlazado<>(dato);
        if (primero == null) {
            primero = nuevo;
        } else {
            NodoDoblementeEnlazado<T> actual = primero;
            while (actual.haySiguiente()) {
                actual = actual.siguiente();
            }
            nuevo.anterior(actual);
            actual.siguiente(nuevo);
        }
        size++;
    }

    public int size() {
        return size;
    }

    public T get(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Fuera de rango");
        }
        NodoDoblementeEnlazado<T> actual = primero;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente();
        }
        return actual.dato();
    }
}
