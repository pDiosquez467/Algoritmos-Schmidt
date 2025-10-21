package examenes.parciales.julio2013.biblioteca;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;

import java.util.List;
import java.util.Objects;

public class Libro {
    private final String titulo;
    private final List<String> autores;

    /**
     * post: Inicializa el libro con el título dado, sin autores
     * asociados.
     * @param titulo: el título del libro.
     */
    public Libro(String titulo) {
        this.titulo = titulo;
        this.autores = new ListaSimplementeEnlazada<>();
    }

    /**
     * post: Devuelve el título del libro.
     * @return el título del libro.
     */
    public String titulo() {
        return titulo;
    }

    /**
     * post: Devuelve la lista con los nombres de los autores del libro.
     * @return la lista de autores del libro.
     */
    public List<String> autores() {
        return autores;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Libro libro)) return false;
        return Objects.equals(titulo, libro.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titulo);
    }
}
