package semana03.ejercicios.nivel03.empresas;

import semana03.ejercicios.nivel03.empresas.excepciones.ProyectoException;
import semana03.ejercicios.utils.Validaciones;

import java.util.*;

/**
 * Representa a una empresa.
 * Contiene nombre y una lista de proyectos con diferentes estados de desarrollo.
 */
public class Empresa {
    private final String nombre;
    private final List<Proyecto> proyectos;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.proyectos = new ArrayList<>();
    }

    public void agregarProyecto(Proyecto proyecto) throws ProyectoException {
        Validaciones.validarNotNull(proyecto, "El proyecto a agregar debe ser no nulo");

        if (this.contieneProyecto(proyecto)) {
            throw new ProyectoException("El proyecto ya forma parte de la empresa");
        }

        this.proyectos.add(proyecto);
    }

    public void eliminarProyecto(Proyecto proyecto) throws ProyectoException {
        Validaciones.validarNotNull(proyecto, "El proyecto a eliminar debe ser no nulo");

        if (!this.contieneProyecto(proyecto)) {
            throw new ProyectoException("El proyecto no forma parte de la empresa");
        }

        this.proyectos.remove(proyecto);
    }

    public List<Proyecto> proyectos() {
        return Collections.unmodifiableList(this.proyectos);
    }

    public Proyecto.EstadoProyecto verEstadoProyecto(Proyecto proyecto) throws ProyectoException {
        if (!this.contieneProyecto(proyecto)) {
            throw new ProyectoException("El proyecto no pertenece a la empresa");
        }
        return proyecto.estadoProyecto();
    }

    public void cambiarEstadoProyecto(Proyecto proyecto, Proyecto.EstadoProyecto estadoProyecto) throws ProyectoException {
        if (!this.contieneProyecto(proyecto)) {
            throw new ProyectoException("El proyecto no pertenece a la empresa");
        }
        proyecto.setEstadoProyecto(estadoProyecto);
    }

    public void agregarEmpleado(Empleado empleado, Proyecto proyecto) throws ProyectoException {
        Validaciones.validarNotNull(empleado, "El empleado debe ser no nulo");
        Validaciones.validarNotNull(proyecto, "El proyecto debe ser no nulo");

        if (!this.contieneProyecto(proyecto)) {
            throw new ProyectoException("El proyecto no forma parte de la empresa");
        }

        proyecto.agregarEmpleado(empleado);
    }

    public void eliminarEmpleado(Empleado empleado, Proyecto proyecto) throws ProyectoException {
        Validaciones.validarNotNull(empleado, "El empleado debe ser no nulo");
        Validaciones.validarNotNull(proyecto, "El proyecto debe ser no nulo");

        if (!this.contieneProyecto(proyecto)) {
            throw new ProyectoException("El proyecto no forma parte de la empresa");
        }

        proyecto.eliminarEmpleado(empleado);
    }

    public double costoProyectos() {
        return this.proyectos.stream()
                .mapToDouble(Proyecto::costoTotal)
                .sum();
    }

    public Optional<Proyecto> proyectoMasCaro() {
        return this.proyectos.stream()
                .max(Comparator.comparingDouble(Proyecto::costoTotal));
    }

    public boolean contieneProyecto(Proyecto proyecto) {
        return this.proyectos.stream()
                .anyMatch(p -> p.equals(proyecto));
    }
}
