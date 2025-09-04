package semana03.ejercicios.nivel02.biblioteca;

import java.util.function.Predicate;

public class Biblioteca {
    private int indiceGuardado;
    private int cantidadMaximaLibros;
    private final Libro[] libros;

    public Biblioteca(int cantidadMaximaLibros) {
        this.indiceGuardado = 0;
        this.libros = new Libro[cantidadMaximaLibros];
    }

    public void agregarLibro(Libro libro) throws Exception {
        if(indiceGuardado == cantidadMaximaLibros) {
            throw new Exception("Límite de libros alcanzado! Probá con agrandar la biblioteca!");
        }
        this.libros[indiceGuardado++] = libro;
    }

    public Libro buscarLibro(Libro libroBuscado) {
        return buscarLibro(libro -> libro.equals(libroBuscado));
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        return buscarLibro(libro -> libro.getTitulo().equals(titulo));
    }

    public Libro buscarLibroPorAutor(String autor) {
        return buscarLibro(libro -> libro.getAutor().equals(autor));
    }

    public void prestarLibro(Libro libro) throws Exception {
        if(!libroDisponible(libro)) {
            throw new Exception("El libro no está disponible en la biblioteca!");
        }
        // usuario.tomarLibroPrestado(libro)
        libro.setCopiasDisponibles(libro.getCopiasDisponibles()-1);
    }

    public void devolverLibro(Libro libro) throws Exception {
        if(!perteneceALaBiblioteca(libro)) {
            throw new Exception("El libro no pertenece a la biblioteca!");
        }
        // usuario.devolverLibroPrestado(libro)
        libro.setCopiasDisponibles(libro.getCopiasDisponibles()+1);
    }

    private Boolean libroDisponible(Libro libro) {
        return perteneceALaBiblioteca(libro) && libro.estaDisponible();
    }

    private Boolean perteneceALaBiblioteca(Libro libro) {
        return buscarLibro(libro) != null;
    }

    private Libro buscarLibro(Predicate<Libro> criterio) {
        for (Libro libro: this.libros) {
            if (criterio.test(libro)) {
                return libro;
            }
        }
        return null;
    }

}
