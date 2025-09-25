package tdas.lista.teorica;

public class Lista<T> {

    private NodoSimplementeEnlazado<T> primero;
    private NodoSimplementeEnlazado<T> cursor;
    private int size = 0;

    public Lista() {
        this.primero = null;
        this.cursor  = null;
        this.size = 0;
    }

    public boolean add(T element) {
        addLast(element);
        return true;
    }

    public void addLast(T element) {
        NodoSimplementeEnlazado<T> node = new NodoSimplementeEnlazado<>(element);
        if (primero == null) {
            primero = node;
        } else {
            NodoSimplementeEnlazado<T> current = primero;
            while (primero.tieneProximo()) {
                current = current.getProximo();
            }
            current.setProximo(node);
        }
        size++;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
}
