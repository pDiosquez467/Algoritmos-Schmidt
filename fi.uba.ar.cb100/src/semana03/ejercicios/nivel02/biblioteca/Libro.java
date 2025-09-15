package semana03.ejercicios.nivel02.biblioteca;

import semana03.ejercicios.utils.Validaciones;

import java.util.Objects;

public record Libro(String titulo, String autor, int anioPublicacion) {
    public Libro {
        Validaciones.validarNotNull(titulo, "El título de un libro debe ser no nulo");
        Validaciones.validarNotBlank(titulo, "El título de un libro debe ser no vacío");
        Validaciones.validarNotNull(autor, "El autor de un libro debe ser no nulo");
        Validaciones.validarNotBlank(autor, "El autor de un libro debe ser no vacío");
        Validaciones.validarNumeroMayorACero(anioPublicacion, "El año de publicación de un libro debe ser mayor a");
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
}
