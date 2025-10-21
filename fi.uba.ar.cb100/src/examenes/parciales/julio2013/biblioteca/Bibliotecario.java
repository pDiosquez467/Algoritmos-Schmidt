package examenes.parciales.julio2013.biblioteca;

import tdas.conjunto.Conjunto;
import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Bibliotecario {

    /**
     * post: Busca en 'estantes' todos los libros NO prestados tales que uno de
     * sus autores es 'autorBuscado'.
     * Devuelve una nueva lista con todos los libros en esa condición.
     */
    public List<Libro> buscarLibrosNoPrestadosPorAutor(List<Estante> estantes, String autorBuscado) {
        Validaciones.validarNotNull(estantes, "estantes");
        Validaciones.validarNotNull(autorBuscado, "autorBuscado");
        Set<Libro> librosFiltrados = new Conjunto<>();
        for (Estante estante: estantes) {
            if (estante == null) continue;
            List<Libro> librosNoPrestados = obtenerLibrosNoPrestados(estante);
            librosFiltrados.addAll(obtenerTodosLosLibrosDeAutor(librosNoPrestados, autorBuscado));
        }
        return librosFiltrados.stream().toList();
    }

    /**
     * post: Devuelve una lista con todos los libros de 'libros' tales que uno de sus
     * autores es 'autorBuscado'.
     * @param libros: lista de libros en la que buscar.
     * @param autorBuscado: autor buscado.
     * @return una nueva lista con los libros que cumplen la condición especificada.
     */
    public List<Libro> obtenerTodosLosLibrosDeAutor(List<Libro> libros, String autorBuscado) {
        Validaciones.validarNotNull(libros, "libros");
        Validaciones.validarNotNull(autorBuscado, "autorBuscado");
        Validaciones.validarVerdadero(!autorBuscado.isEmpty(), "autorBuscado");
        List<Libro> librosDelAutor = new ListaSimplementeEnlazada<>();
        for (Libro libro: libros) {
            if ((libro != null) && (libro.autores().contains(autorBuscado))) {
                librosDelAutor.add(libro);
            }
        }
        return librosDelAutor;
    }

    /**
     * post: Devuelve una nueva lista con todos los libros no prestados del estante
     * dado.
     * @param estante: el estante donde buscar.
     * @return la lista de libros no prestados.
     */
    public List<Libro> obtenerLibrosNoPrestados(Estante estante) {
        Validaciones.validarNotNull(estante, "estante");
        List<Libro> librosNoPrestados = new ListaSimplementeEnlazada<>();
        for (Libro libro: estante.libros()) {
            if ((libro != null) && (!estante.getLibrosPrestados().contains(libro))) {
                librosNoPrestados.add(libro);
            }
        }
        return librosNoPrestados;
    }
}
