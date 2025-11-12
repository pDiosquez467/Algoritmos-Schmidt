package examenes.parciales.listas.repaso.noticiasV2;

import java.util.Objects;

public class Categoria {

    private final String nombre;
    private String descripcion;

    /**
     * post: Inicializa una categoría con el nombre y la descripción indicadas.
     *
     */
    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Categoria categoria)) return false;
        return Objects.equals(nombre, categoria.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    /**
     * post: Devuelve el nombre de la categoría.
     * @return el nombre de la categoría.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * post: Devuelve la descripción de la categoría.
     * @return la descripción de la categoría.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * post: Modifica la descripción de la categoría.
     * @param descripcion: la nueva descripción.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
