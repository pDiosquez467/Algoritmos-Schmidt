package semana03.ejercicios.nivel03.empresas;

import semana03.ejercicios.nivel03.empresas.excepciones.ProyectoException;
import semana03.ejercicios.utils.Validaciones;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Representa un proyecto dentro de una empresa.
 * Contiene ID, nombre, estado y empleados.
 */
public class Proyecto {
    private static final int CAPACIDAD_INICIAL = 10;

    public enum EstadoProyecto {
        INICIALIZADO,
        EN_PROGRESO,
        FINALIZADO
    }

    private final String id;
    private final String nombre;
    private EstadoProyecto estadoProyecto;
    private Empleado[] empleados;
    private int indiceProximoEmpleado;

    public Proyecto(String id, String nombre) {
        Validaciones.validarNotNull(id, "El 'id' de un proyecto debe ser no nulo");
        Validaciones.validarNotBlank(id, "El 'id' de un proyecto debe ser no vacío");
        Validaciones.validarNotNull(nombre, "El 'nombre' de un proyecto debe ser no nulo");
        Validaciones.validarNotBlank(nombre, "El 'nombre' de un proyecto debe ser no vacío");

        this.id = id;
        this.nombre = nombre;
        this.estadoProyecto = EstadoProyecto.INICIALIZADO;
        this.empleados = new Empleado[CAPACIDAD_INICIAL];
        this.indiceProximoEmpleado = 0;
    }

    public double costoTotal() {
        double total = 0.0;
        for (int i = 0; i < indiceProximoEmpleado; i++) {
            total += this.empleados[i].salario();
        }
        return total;
    }

    public List<Empleado> empleados() {
        Empleado[] copias = new Empleado[indiceProximoEmpleado];
        for (int i = 0; i < indiceProximoEmpleado; i++) {
            Empleado almacenado = this.empleados[i];
            copias[i] = new Empleado(almacenado.id(), almacenado.nombre(), almacenado.salario());
        }
        return List.of(copias);
    }

    public boolean contieneEmpleado(Empleado empleado) {
        return this.indiceEmpleadoInternal(empleado) > -1;
    }

    public EstadoProyecto estadoProyecto() {
        return estadoProyecto;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Proyecto proyecto)) return false;
        return Objects.equals(id, proyecto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public void agregarEmpleado(Empleado empleado) throws ProyectoException {
        Validaciones.validarNotNull(empleado, "El empleado debe ser no nulo");

        if (this.contieneEmpleado(empleado)) {
            throw new ProyectoException("El empleado ya forma parte del proyecto");
        }

        if (this.debeAumentarCapacidadInternal()) {
            this.ajustarCapacidadInternal(this.empleados.length * 2);
        }
        this.empleados[indiceProximoEmpleado++] =
                new Empleado(empleado.id(), empleado.nombre(), empleado.salario());
    }

    public void eliminarEmpleado(Empleado empleado) throws ProyectoException {
        Validaciones.validarNotNull(empleado, "El empleado debe ser no nulo");

        if (!this.contieneEmpleado(empleado)) {
            throw new ProyectoException("El empleado no forma parte del proyecto");
        }

        int indiceDeEliminado = this.indiceEmpleadoInternal(empleado);
        this.compactarEmpleadosDesdeInternal(indiceDeEliminado);
        if (this.debeReducirCapacidadInternal()) {
            this.ajustarCapacidadInternal(this.empleados.length / 2);
        }
    }

    void setEstadoProyecto(EstadoProyecto estadoProyecto) throws ProyectoException {
        if (this.estadoProyecto == EstadoProyecto.FINALIZADO && estadoProyecto != EstadoProyecto.FINALIZADO) {
            throw new ProyectoException("Proyecto finalizado no puede cambiar estado");
        }
        this.estadoProyecto = estadoProyecto;
    }

    // --- Helpers privados ---

    private int indiceEmpleadoInternal(Empleado empleado) {
        for (int i = 0; i < indiceProximoEmpleado; i++) {
            if (this.empleados[i].equals(empleado)) {
                return i;
            }
        }
        return -1;
    }

    private boolean debeAumentarCapacidadInternal() {
        return indiceProximoEmpleado >= this.empleados.length;
    }

    private boolean debeReducirCapacidadInternal() {
        return this.empleados.length > CAPACIDAD_INICIAL && indiceProximoEmpleado < (this.empleados.length / 4);
    }

    private void ajustarCapacidadInternal(int nuevaCapacidad) {
        Empleado[] nuevos = new Empleado[nuevaCapacidad];
        System.arraycopy(this.empleados, 0, nuevos, 0, Math.min(nuevaCapacidad, indiceProximoEmpleado));
        this.empleados = nuevos;
    }

    private void compactarEmpleadosDesdeInternal(int desde) {
        for (int i = desde; i < indiceProximoEmpleado - 1; i++) {
            this.empleados[i] = this.empleados[i+1];
        }
        this.empleados[--indiceProximoEmpleado] = null;
    }
}