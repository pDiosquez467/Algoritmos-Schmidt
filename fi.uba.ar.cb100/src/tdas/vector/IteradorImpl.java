package tdas.vector;

public class IteradorImpl<T> implements Iterador<T>{
    private Vector<T> vector;
    private int indiceActual;

    public IteradorImpl(Vector<T> vector) {
        this.vector = vector;
        this.indiceActual = 0;
    }

    @Override
    public boolean haySiguiente() {
        return indiceActual < vector.size();
    }

    @Override
    public T verActual() throws Exception {
        if (!haySiguiente()) {
            throw new RuntimeException("No hay nada para ver");
        }
        return vector.obtener(indiceActual);
    }

    @Override
    public void siguiente() throws Exception {
        if (!haySiguiente()) {
            throw new RuntimeException("No hay nada para ver");
        }
        indiceActual++;
    }
}
