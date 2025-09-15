package semana03.ejercicios.nivel02.biblioteca;

import semana03.ejercicios.utils.Validaciones;

import java.util.Objects;

public class Libro {
    private String titulo;
    private String autor;
    private int anioPublicacion;

    public Libro(String titulo, String autor, int anioPublicacion) {
        this.setTitulo(titulo);
        this.setAutor(autor);
        this.setAnioPublicacion(anioPublicacion);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Libro libro)) return false;
        return anioPublicacion == libro.anioPublicacion && Objects.equals(titulo, libro.titulo) && Objects.equals(autor, libro.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, autor, anioPublicacion);
    }

    // --- Getters y Setters ---

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    private void setTitulo(String titulo) {
        Validaciones.validarNotNull(titulo, "El título de un libro debe ser no nulo");
        Validaciones.validarNotBlank(titulo, "El título de un libro debe ser no vacío");
        this.titulo = titulo;
    }

    private void setAutor(String autor) {
        Validaciones.validarNotNull(autor, "El autor de un libro debe ser no nulo");
        Validaciones.validarNotBlank(autor, "El autor de un libro debe ser no vacío");
        this.autor = autor;
    }

    private void setAnioPublicacion(int anioPublicacion) {
        Validaciones.validarNumeroMayorACero(anioPublicacion, "El año de publicación de un libro debe ser mayor a");
        this.anioPublicacion = anioPublicacion;
    }
}
