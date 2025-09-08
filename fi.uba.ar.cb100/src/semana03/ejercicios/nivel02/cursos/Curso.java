package semana03.ejercicios.nivel02.cursos;

import semana03.ejercicios.nivel02.cursos.excepciones.CursoException;
import semana03.ejercicios.utils.Validaciones;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un curso académico identificado por un código único, con un nombre
 * descriptivo y un cupo máximo de estudiantes permitido.
 * La clase mantiene la lista de estudiantes inscriptos y ofrece operaciones para
 * inscribir y eliminar estudiantes, verificando que se cumplan las restricciones
 * de cupo y que no haya duplicados.
 * Invariantes de la clase
 * <p>
 * {@code codigo != null && !codigo.trim().isEmpty()}
 * {@code nombre != null && !nombre.trim().isEmpty()}
 * {@code cupoMaximo > 0}
 * {@code 0 <= cantidadEstudiantes <= cupoMaximo}
 * {@code estudiantes.size() == cantidadEstudiantes}
 * No hay estudiantes duplicados en la lista.
 * <p>
 * Relación con {@link Estudiante}
 * Cada inscripción o eliminación en un curso impacta también en la lista de cursos
 * del estudiante asociado. El curso es el encargado de mantener la consistencia:
 * <p>
 * Al inscribir un estudiante, se agrega tanto a la lista interna del curso
 * como a la lista del estudiante.</li>
 * Al eliminar un estudiante, se lo quita de ambas listas.
 * De este modo, la relación curso ↔ estudiante se mantiene sincronizada.
 *
 * @author pdiosquez
 */
public class Curso {
    /**
     * Código único del curso. No puede ser nulo ni vacío.
     */
    private String codigo;

    /**
     * Nombre descriptivo del curso. No puede ser nulo ni vacío.
     */
    private String nombre;

    /**
     * Número máximo de estudiantes que pueden inscribirse.
     * Siempre mayor a cero.
     */
    private int cupoMaximo;

    /**
     * Cantidad actual de estudiantes inscriptos.
     * Debe cumplir: {@code 0 <= cantidadEstudiantes <= cupoMaximo}.
     */
    private int cantidadEstudiantes;

    /**
     * Lista de estudiantes actualmente inscriptos en el curso.
     * No puede contener duplicados.
     */
    private List<Estudiante> estudiantes;

    /**
     * Crea un curso con código, nombre y cupo máximo dados.
     *
     * @param codigo Código único que identifica al curso
     * @param nombre Nombre descriptivo del curso
     * @param cupoMaximo Cantidad máxima de estudiantes permitida
     * @throws IllegalArgumentException si alguno de los parámetros es inválido
     *
     * @pre {@code codigo != null && !codigo.trim().isEmpty()}
     * @pre {@code nombre != null && !nombre.trim().isEmpty()}
     * @pre {@code cupoMaximo > 0}
     *
     * @post {@code getCodigo().equals(codigo)}
     * @post {@code getNombre().equals(nombre)}
     * @post {@code getCupoMaximo() == cupoMaximo}
     * @post {@code getCantidadEstudiantes() == 0}
     */
    public Curso(String codigo, String nombre, int cupoMaximo) {
        setCodigo(codigo);
        setNombre(nombre);
        setCupoMaximo(cupoMaximo);
        this.cantidadEstudiantes = 0;
        this.estudiantes = new ArrayList<>();
    }

    /**
     * Constructor de copia que genera un nuevo curso con los mismos atributos
     * que el curso proporcionado.
     *
     * @param curso Curso original del cual se realiza la copia
     * @throws IllegalArgumentException si el curso es nulo
     *
     * @pre {@code curso != null}
     * @post {@code equals(curso)}
     */
    public Curso(Curso curso) {
        this(curso.getCodigo(), curso.getNombre(), curso.getCupoMaximo());
        this.cantidadEstudiantes = curso.getCantidadEstudiantes();
        this.estudiantes = new ArrayList<>(curso.getEstudiantes());
    }

    /**
     * Inscribe un estudiante en el curso, siempre que:
     * <ul>
     *   <li>El estudiante no esté ya inscripto.</li>
     *   <li>Haya cupo disponible.</li>
     * </ul>
     *
     * Además, actualiza la lista de cursos del estudiante.
     *
     * @param estudiante Estudiante a inscribir
     * @throws CursoException si no hay cupo o si ya estaba inscripto
     *
     * @pre {@code estudiante != null}
     * @post {@code getEstudiantes().contains(estudiante)}
     * @post {@code getCantidadEstudiantes() == old.getCantidadEstudiantes() + 1}
     * @post {@code estudiante.getCursos().contains(this)}
     */
    public void agregarEstudiante(Estudiante estudiante) throws CursoException {
        if (!hayCupo()) {
            throw new CursoException("No hay cupo disponible!");
        }
        if (estaInscripto(estudiante)) {
            throw new CursoException("El estudiante ya está inscripto en este curso!");
        }

        this.estudiantes.add(estudiante);
        estudiante.inscribirseA(this);
        this.cantidadEstudiantes++;
    }

    /**
     * Elimina a un estudiante del curso, siempre que esté inscripto previamente.
     *
     * Además, actualiza la lista de cursos del estudiante.
     *
     * @param estudiante Estudiante a eliminar
     * @throws CursoException si el estudiante no pertenece al curso
     *
     * @pre {@code estudiante != null}
     * @pre {@code getEstudiantes().contains(estudiante)}
     * @post {@code !getEstudiantes().contains(estudiante)}
     * @post {@code getCantidadEstudiantes() == old.getCantidadEstudiantes() - 1}
     * @post {@code !estudiante.getCursos().contains(this)}
     */
    public void eliminarEstudiante(Estudiante estudiante) throws CursoException {
        if (!estaInscripto(estudiante)) {
            throw new CursoException("El estudiante no pertenece al curso!");
        }

        this.estudiantes.remove(estudiante);
        estudiante.eliminar(this);
        this.cantidadEstudiantes--;
    }

    /**
     * Genera un listado textual de los estudiantes inscriptos en el curso.
     * Cada estudiante aparece en una línea separada.
     *
     * @return Cadena con la lista de estudiantes inscriptos
     *
     * @post {@code resultado != null}
     */
    public String listarEstudiantes() {
        StringBuilder sb = new StringBuilder("Estudiantes inscriptos:\n");
        this.estudiantes.forEach(e -> sb.append("- ").append(e).append("\n"));
        return sb.toString();
    }

    /**
     * Verifica si un estudiante ya está inscripto en el curso.
     *
     * @param estudiante Estudiante a verificar
     * @return true si ya está inscripto, false en caso contrario
     */
    private boolean estaInscripto(Estudiante estudiante) {
        return this.estudiantes.stream().anyMatch(e -> e.equals(estudiante));
    }

    /**
     * Indica si aún hay vacantes disponibles en el curso.
     *
     * @return true si {@code cantidadEstudiantes < cupoMaximo}, false en caso contrario
     */
    private boolean hayCupo() {
        return this.cantidadEstudiantes < this.cupoMaximo;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Curso curso)) return false;
        return this.getCodigo().equals(curso.getCodigo());
    }

    // =====================
    // Getters y Setters
    // =====================

    /**
     * Devuelve el código único del curso.
     *
     * @return Código del curso
     */
    public String getCodigo() { return codigo; }

    /**
     * Devuelve el nombre descriptivo del curso.
     *
     * @return Nombre del curso
     */
    public String getNombre() { return nombre; }

    /**
     * Devuelve el cupo máximo del curso.
     *
     * @return Cupo máximo permitido
     */
    public int getCupoMaximo() { return cupoMaximo; }

    /**
     * Devuelve la cantidad actual de estudiantes inscriptos.
     *
     * @return Número de estudiantes actualmente inscriptos
     */
    public int getCantidadEstudiantes() { return cantidadEstudiantes; }

    /**
     * Devuelve una copia defensiva de la lista de estudiantes.
     *
     * @return Nueva lista con los estudiantes inscriptos
     */
    public List<Estudiante> getEstudiantes() { return new ArrayList<>(estudiantes); }

    /**
     * Establece el código único del curso.
     *
     * @param codigo Nuevo código
     * @throws IllegalArgumentException si es nulo o vacío
     */
    public void setCodigo(String codigo) {
        Validaciones.validarNotBlank(codigo, "El código no puede ser vacío");
        this.codigo = codigo;
    }

    /**
     * Establece el nombre descriptivo del curso.
     *
     * @param nombre Nuevo nombre
     * @throws IllegalArgumentException si es nulo o vacío
     */
    public void setNombre(String nombre) {
        Validaciones.validarNotBlank(nombre, "El nombre del curso no puede ser vacío");
        this.nombre = nombre;
    }

    /**
     * Establece el cupo máximo de estudiantes permitido.
     *
     * @param cupoMaximo Nuevo cupo máximo
     * @throws IllegalArgumentException si es menor o igual a cero
     */
    public void setCupoMaximo(int cupoMaximo) {
        Validaciones.validarNumeroMayorACero(cupoMaximo, "El cupo máximo debe ser mayor a cero.");
        this.cupoMaximo = cupoMaximo;
    }
}
