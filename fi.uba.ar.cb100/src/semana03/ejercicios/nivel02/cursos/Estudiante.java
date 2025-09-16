package semana03.ejercicios.nivel02.cursos;

import semana03.ejercicios.nivel02.cursos.excepciones.CursoException;
import semana03.ejercicios.nivel02.cursos.excepciones.EstudianteException;
import semana03.ejercicios.utils.Validaciones;

import java.util.ArrayList;
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
        this.cursos = new ArrayList<>(NUMERO_MAXIMO_CURSOS);
    }

    public void inscribirse(Curso curso) throws CursoException {
        Validaciones.validarNotNull(curso, "El curso no puede ser null");

        if (this.estaInscripto(curso)) {
            throw new CursoException("El estudiante ya está inscripto a este curso!");
        }

        if (cursos.size() >= NUMERO_MAXIMO_CURSOS) {
            throw new CursoException("Límite de cursos alcanzado");
        }

        cursos.add(curso.copy());
    }

    public void eliminar(Curso curso) throws CursoException {
        Validaciones.validarNotNull(curso, "El curso no puede ser null");

        if (!cursos.removeIf(c -> c.equals(curso))) {
            throw new CursoException("El estudiante no está inscripto en este curso!");
        }
    }

    public List<Curso> cursosActivos() {
        return cursos.stream()
                .map(Curso::copy)
                .collect(Collectors.toList());
    }

    private boolean estaInscripto(Curso curso) {
        return cursos.stream().anyMatch(c -> c.equals(curso));
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
}
