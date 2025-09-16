package semana03.ejercicios.nivel02.cursos;

import semana03.ejercicios.nivel02.cursos.excepciones.CursoException;
import semana03.ejercicios.utils.Validaciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Representa a un estudiante identificado por una matrícula única y con una lista
 * de cursos en los que está inscrito.
 * Hay un número máximo de cursos a los que cada estudiante puede inscribirse.
 */
public class Estudiante {
    private static final int NUMERO_MAXIMO_CURSOS = 5;

    private final String legajo;
    private final String nombre;
    private final List<Curso> cursos;

    public Estudiante(String legajo, String nombre) {
        Validaciones.validarNotNull(legajo, "El legajo no puede ser null!");
        Validaciones.validarNotBlank(legajo, "El legajo no puede estar vacío!");
        Validaciones.validarNotNull(nombre, "El nombre no puede ser null!");
        Validaciones.validarNotBlank(nombre, "El nombre no puede estar vacío!");

        this.legajo = legajo;
        this.nombre = nombre;
        this.cursos = new ArrayList<>();
    }

    public List<Curso> cursos() {
        return Collections.unmodifiableList(this.cursos);
    }

    public boolean estaInscripto(Curso curso) {
        return cursos.stream().anyMatch(c -> c.equals(curso));
    }

    public boolean alcanzoLimiteDeCursos() {
        return this.cursos.size() == NUMERO_MAXIMO_CURSOS;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Estudiante that)) return false;
        return Objects.equals(legajo, that.legajo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(legajo);
    }

    // Helpers package-private

    void agregarCursoInternal(Curso curso) throws CursoException {
        if (!this.cursos.contains(curso)) {
            this.cursos.add(curso);
        }
    }

    void eliminarCursoInternal(Curso curso) throws CursoException {
        this.cursos.remove(curso);
    }
}
