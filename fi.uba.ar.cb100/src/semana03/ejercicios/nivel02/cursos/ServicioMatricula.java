package semana03.ejercicios.nivel02.cursos;

import semana03.ejercicios.nivel02.cursos.excepciones.MatriculaException;
import semana03.ejercicios.utils.Validaciones;

public class ServicioMatricula {

    public void matricularEstudiante(Estudiante estudiante, Curso curso) throws MatriculaException {
        Validaciones.validarNotNull(estudiante, "El estudiante no puede ser null");
        Validaciones.validarNotNull(curso, "El curso no puede ser null");

        if (estudiante.alcanzoLimiteDeCursos()) {
            throw new MatriculaException("El estudiante alcanzó el límite de cursos");
        }

        if (curso.estaLleno()) {
            throw new MatriculaException("El curso alcanzó el cupo máximo de estudiantes");
        }

        // Doble validación - Queda por seguridad
        if (estudiante.estaInscripto(curso)) {
            throw new MatriculaException("El estudiante ya está inscripto en el curso");
        }

        if (curso.estaInscripto(estudiante)) {
            throw new MatriculaException("El estudiante ya está en la lista del curso");
        }

        try {
            estudiante.agregarCursoInternal(curso);
            curso.agregarEstudianteInternal(estudiante);
        } catch (Exception e) {
            // En caso de error, revertir ambas operaciones
            curso.eliminarEstudianteInternal(estudiante);
            estudiante.eliminarCursoInternal(curso);
            throw new MatriculaException("Error en la matriculación: " + e.getMessage());
        }
    }

    public void desmatricularEstudiante(Estudiante estudiante, Curso curso) throws MatriculaException {
        Validaciones.validarNotNull(estudiante, "El estudiante no puede ser null");
        Validaciones.validarNotNull(curso, "El curso no puede ser null");

        if (!estudiante.estaInscripto(curso)) {
            throw new MatriculaException("El estudiante no está inscripto en el curso");
        }

        if (!curso.estaInscripto(estudiante)) {
            throw new MatriculaException("El estudiante no está en la lista del curso");
        }

        try {
            estudiante.eliminarCursoInternal(curso);
            curso.eliminarEstudianteInternal(estudiante);
        } catch (Exception e) {
            curso.agregarEstudianteInternal(estudiante);
            estudiante.agregarCursoInternal(curso);
            throw new MatriculaException("Error en la matriculación: " + e.getMessage());
        }
    }
}
