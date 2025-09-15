package semana03.ejercicios.nivel02.biblioteca;

import semana03.ejercicios.utils.Validaciones;

public class LibroEnBiblioteca {
    private Libro libro;
    private int copiasDisponibles;

    public LibroEnBiblioteca(Libro libro, int copiasDisponibles) {
        this.libro = libro;
        this.copiasDisponibles = copiasDisponibles;
    }

    public LibroEnBiblioteca(Libro libro) {
        this(libro, 0);
    }

    // --- Getters y Setters ---

    public Libro getLibro() {
        return new Libro(this.libro.getTitulo(), this.libro.getAutor(), this.libro.getAnioPublicacion());
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    private void setLibro(Libro libro) {
        Validaciones.validarNotNull(libro, "Un libro de la biblioteca debe ser no nulo");
        this.libro = libro;
    }

    private void setCopiasDisponibles(int copiasDisponibles) {
        Validaciones.validarNumeroMayorACero(copiasDisponibles, "El número de copias disponibles de un libro debe ser mayor a cero");
        this.copiasDisponibles = copiasDisponibles;
    }
}
