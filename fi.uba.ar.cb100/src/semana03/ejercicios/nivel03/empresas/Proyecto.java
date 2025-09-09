package semana03.ejercicios.nivel03.empresas;

import semana03.ejercicios.nivel03.empresas.excepciones.ProyectoException;
import semana03.ejercicios.utils.Validaciones;

import java.util.ArrayList;
import java.util.List;

public class Proyecto {
    enum EstadoProyecto {
        INICIALIZADO,
        EN_PROGRESO,
        FINALIZADO
    }

    private String id;
    private String nombre;
    private EstadoProyecto estadoProyecto;
    private final List<Empleado> empleados;

    public Proyecto(String id, String nombre) {
        this.setId(id);
        this.setNombre(nombre);
        this.estadoProyecto = EstadoProyecto.INICIALIZADO;
        this.empleados = new ArrayList<>();
    }

    // Métodos de comportamiento

    public void agregarEmpleado(Empleado empleado) throws ProyectoException {
        if (esEmpleadoDelProyecto(empleado)) {
            throw new ProyectoException("EL empleado " + empleado + " ya forma parte de la nómina.");
        }
        this.empleados.add(empleado);
    }

    public void removerEmpleado(Empleado empleado) throws ProyectoException {
        if (!esEmpleadoDelProyecto(empleado)) {
            throw new ProyectoException("EL empleado " + empleado + " no forma parte de la nómina.");
        }
        this.empleados.remove(empleado);
    }

    public double costoTotal() {
        return this.empleados.stream()
                .map(Empleado::getSalario)
                .reduce(0.0, Double::sum);
    }

    public boolean esEmpleadoDelProyecto(Empleado empleado) {
        return this.buscar(empleado) != null;
    }

    private Empleado buscar(Empleado empleado) {
        return this.empleados.stream()
                .filter(e -> e.equals(empleado))
                .findAny().orElse(null);
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public EstadoProyecto getEstadoProyecto() {
        return estadoProyecto;
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
