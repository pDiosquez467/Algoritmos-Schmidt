package semana03.ejercicios.nivel03.empresas;

import semana03.ejercicios.nivel03.empresas.excepciones.ProyectoException;
import semana03.ejercicios.utils.Validaciones;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un proyecto dentro de una empresa.
 * Contiene ID, nombre, estado y empleados.
 */
public class Proyecto {
    public enum EstadoProyecto {
        INICIALIZADO,
        EN_PROGRESO,
        FINALIZADO
    }

    private String id;
    private String nombre;
    private EstadoProyecto estadoProyecto;
    private final List<Empleado> empleados;

    /**
     * Crea un proyecto nuevo. El estado inicial es INICIALIZADO.
     * @param id Identificador del proyecto (no vacío).
     * @param nombre Nombre del proyecto (no vacío).
     */
    public Proyecto(String id, String nombre) {
        this.setId(id);
        this.setNombre(nombre);
        this.estadoProyecto = EstadoProyecto.INICIALIZADO;
        this.empleados = new ArrayList<>();
    }

    // --- Métodos de comportamiento (públicos) ---

    /**
     * Agrega un empleado al proyecto, si éste no forma parte de la nómina.
     * @param empleado: El empleado a agregar al proyecto.
     * @throws ProyectoException si el empleado ya forma parte del proyecto o {@code empleado} es null.
     */
    public void agregarEmpleado(Empleado empleado) throws ProyectoException {
        if (empleado == null) {
            throw new ProyectoException("El empleado dado no puede ser 'null'");
        }
        if (esEmpleadoDelProyecto(empleado)) {
            throw new ProyectoException("EL empleado " + empleado.getId() + " ya forma parte de la nómina.");
        }
        this.empleados.add(empleado);
    }

    /**
     * Remueve un empleado del proyecto.
     * @throws ProyectoException si el empleado no forma parte del proyecto o es null.
     */
    public void removerEmpleado(Empleado empleado) throws ProyectoException {
        if (empleado == null) {
            throw new ProyectoException("El empleado dado no puede ser 'null'");
        }
        if (!esEmpleadoDelProyecto(empleado)) {
            throw new ProyectoException("EL empleado " + empleado + " no forma parte de la nómina.");
        }
        this.empleados.remove(empleado);
    }

    /**
     * Devuelve el costo total del proyecto teniendo en cuenta solamente el salario de los empleados.
     * @return el costo total del proyecto.
     */
    public double costoTotal() {
        return this.empleados.stream()
                .mapToDouble(Empleado::getSalario)
                .sum();
    }

    /**
     * Indica si el empleado dado forma parte del proyecto.
     * @param empleado: ¿está este empleado en la nómina?
     * @return true si el empleado forma parte del proyecto. En caso contrario, retorna false.
     */
    public boolean esEmpleadoDelProyecto(Empleado empleado) {
        return this.buscar(empleado.getId()) != null;
    }

    // --- Helpers privados ---

    /**
     *
     * @param idEmpleado: Código de identificación del empleado buscado.
     * @return el empleado buscado o, en caso de no formar parte de la nómina, 'null'.
     */
    private Empleado buscar(String idEmpleado) {
        return this.empleados.stream()
                .filter(e -> e.getId().equals(idEmpleado))
                .findAny().orElse(null);
    }

    // --- Overrides ---

    @Override
    public String toString() {
        return "Proyecto{" +
                "ID='" + this.id + '\'' +
                ", Nombre ='" + this.nombre + '\'' +
                ", Estado =" + this.estadoProyecto +
                ", Total empleados =" + this.empleados.size() +
                '}';
    }

    // --- Getters y Setters ---

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public EstadoProyecto getEstadoProyecto() {
        return estadoProyecto;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setId(String id) {
        Validaciones.validarNotBlank(id, "El identificador 'id' de un proyecto no puede ser vacío.");
        this.id = id;
    }

    public void setNombre(String nombre) {
        Validaciones.validarNotBlank(nombre, "El nombre de un proyecto no puede ser vacío.");
        this.nombre = nombre;
    }

    public void setEstadoProyecto(EstadoProyecto estadoProyecto) {
        this.estadoProyecto = estadoProyecto;
    }
}