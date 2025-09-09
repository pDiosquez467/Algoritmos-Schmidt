package semana03.ejercicios.nivel03.empresas;

import semana03.ejercicios.nivel03.empresas.excepciones.ProyectoException;
import semana03.ejercicios.utils.Validaciones;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a una empresa.
 * Contiene nombre y una lista de proyectos con diferentes estados de desarrollo.
 */
public class Empresa {
    private String nombre;
    private final List<Proyecto> proyectos;

    /**
     * Crea una empresa con el nombre dado.
     * @param nombre: El nombre de la empresa. Debe ser no vacío.
     */
    public Empresa(String nombre) {
        this.setNombre(nombre);
        this.proyectos = new ArrayList<>();
    }

    // --- Métodos de comportamiento (ṕublico) ---

    public void agregarProyecto(Proyecto proyecto) throws ProyectoException {
        if (this.esProyectoDeLaEmpresa(proyecto)) {
            throw new ProyectoException("El proyecto dado ya forma parte de los proyectos de la empresa.");
        }
        this.proyectos.add(proyecto);
    }

    public void removerProyecto(Proyecto proyecto) throws ProyectoException {
        if (!this.esProyectoDeLaEmpresa(proyecto)) {
            throw new ProyectoException("El proyecto dado no forma parte de los proyectos de la empresa.");
        }
        this.proyectos.remove(proyecto);
    }

    public void agregarEmpleado(Proyecto proyecto, Empleado empleado) throws ProyectoException {
        if (!this.esProyectoDeLaEmpresa(proyecto)) {
            throw new ProyectoException("El proyecto dado no forma parte de los proyectos de la empresa.");
        }
        proyecto.agregarEmpleado(empleado);
    }

    public void removerEmpleado(Proyecto proyecto, Empleado empleado) throws ProyectoException {
        if (!this.esProyectoDeLaEmpresa(proyecto)) {
            throw new ProyectoException("El proyecto dado no forma parte de los proyectos de la empresa.");
        }
        proyecto.removerEmpleado(empleado);
    }

    /**
     * Devuelve el costo total de los proyectos 'activos' (EN_PROGRESO) de la empresa.
     * @return el costo total de los proyectos.
     */
    public double costoTotal() {
        return this.proyectos.stream()
                .filter(proyecto -> proyecto.getEstadoProyecto() == Proyecto.EstadoProyecto.EN_PROGRESO)
                .mapToDouble(Proyecto::costoTotal)
                .sum();
    }

    /**
     * Informe de progreso: representa cada proyecto en una línea.
     */
    public String informeDeProgreso() {
        StringBuilder sb = new StringBuilder();
        this.proyectos.forEach(p -> sb.append(p).append(System.lineSeparator()));
        return sb.toString();
    }

    // -- Helpers privados ---

    private boolean esProyectoDeLaEmpresa(Proyecto proyecto) {
        return this.buscar(proyecto.getId()) != null;
    }

    private Proyecto buscar(String idProyecto) {
        return this.proyectos.stream()
                .filter(proyecto -> proyecto.getId().equals(idProyecto))
                .findAny().orElse(null);
    }

    // --- Getters y Setters ---

    public String getNombre() {
        return nombre;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setNombre(String nombre) {
        Validaciones.validarNotBlank(nombre, "El nombre de una empresa no puede ser vacío.");
        this.nombre = nombre;
    }
}
