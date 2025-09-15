package semana03.ejercicios.nivel02.cursos;

import semana03.ejercicios.nivel02.cursos.excepciones.CursoException;
import semana03.ejercicios.utils.Validaciones;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un estudiante identificado por una matrícula única y con una lista
 * de cursos en los que está inscrito.
 */
public class Estudiante {
    private String matricula;
    private String nombre;
    private final List<Curso> cursos;

    /**
     * Crea un nuevo estudiante con la matrícula y nombre dados.
     *
     * @param matricula Identificador único del estudiante
     * @param nombre Nombre del estudiante
     * @throws IllegalArgumentException si matrícula o nombre son nulos o vacíos
     */
    public Estudiante(String matricula, String nombre) {
        this.setMatricula(matricula);
        this.setNombre(nombre);
        this.cursos = new ArrayList<>();
    }

    /**
     * Inscribe al estudiante en un curso.
     * <p>⚠️ Este método debería ser llamado únicamente desde {@link Curso#agregarEstudiante(Estudiante)}.</p>
     *
     * @param curso Curso al que se inscribe
     * @throws CursoException si ya estaba inscrito en el curso
     */
    void inscribirseA(Curso curso) throws CursoException {
        if (estaInscripto(curso)) {
            throw new CursoException("El estudiante ya está inscripto a este curso!");
        }
        this.cursos.add(curso);
    }

    /**
     * Elimina al estudiante de un curso.
     * <p>⚠️ Este método debería ser llamado únicamente desde {@link Curso#eliminarEstudiante(Estudiante)}.</p>
     *
     * @param curso Curso del que se elimina
     * @throws CursoException si no estaba inscrito en el curso
     */
    void eliminar(Curso curso) throws CursoException {
        if (!estaInscripto(curso)) {
            throw new CursoException("El estudiante no está inscripto en este curso!");
        }
        this.cursos.remove(curso);
    }

    /**
     * Verifica si el estudiante está inscrito en un curso.
     *
     * @param curso Curso a verificar
     * @return true si ya está inscrito, false en caso contrario
     */
    private boolean estaInscripto(Curso curso) {
        return this.cursos.stream().anyMatch(c -> c.equals(curso));
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "matricula='" + matricula + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Estudiante estudiante)) return false;
        return this.getMatricula().equals(estudiante.getMatricula());
    }

    public String getNombre() {
        return nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        Validaciones.validarNotNull(matricula, "La matrícula no puede ser null!");
        Validaciones.validarNotBlank(matricula, "La matrícula no puede estar vacía!");
        this.matricula = matricula;
    }

    public void setNombre(String nombre) {
        Validaciones.validarNotNull(nombre, "El nombre no puede ser null!");
        Validaciones.validarNotBlank(nombre, "El nombre no puede estar vacío!");
        this.nombre = nombre;
    }
}
