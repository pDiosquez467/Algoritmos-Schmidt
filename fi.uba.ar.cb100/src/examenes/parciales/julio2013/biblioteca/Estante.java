package examenes.parciales.julio2013.biblioteca;

import java.util.List;
import tdas.lista.teorica.simple.ListaSimplementeEnlazada;

public class Estante {
    private final List<Libro> librosAsignados;

    /**
     * post: Inicializa el estante sin libros asignados.
     */
    public Estante() {
        this.librosAsignados = new ListaSimplementeEnlazada<>();
    }

    /**
     * post: Devuelve la lista de libros del estante.
     * @return la lista de libros del estante.
     */
    public List<Libro> libros() {
        return librosAsignados;
    }

    /**
     * post: Devuelve los libros que fueron prestados, pero que están
     * asignados al estante.
     * @return los libros prestados del estante.
     */
    public List<Libro> getLibrosPrestados() {
        return null;
    }
}
