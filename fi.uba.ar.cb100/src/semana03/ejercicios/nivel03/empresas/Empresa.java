package semana03.ejercicios.nivel03.empresas;

import semana03.ejercicios.nivel03.empresas.excepciones.ProyectoException;
import semana03.ejercicios.utils.Validaciones;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nombre;
    private final List<Proyecto> proyectos;

    public Empresa(String nombre) {
        this.setNombre(nombre);
        this.proyectos = new ArrayList<>();
    }

    // Métodos de comportamiento

    public void agregarEmpleado(Proyecto proyecto, Empleado empleado) throws ProyectoException {
        proyecto.agregarEmpleado(empleado);
    }

    public double costoTotal() {
        return this.proyectos.stream()
                .filter(proyecto -> proyecto.getEstadoProyecto() == Proyecto.EstadoProyecto.EN_PROGRESO)
                .map(Proyecto::costoTotal)
                .reduce(0.0, Double::sum);
    }

    public String informeDeProgreso() {
        StringBuilder sb = new StringBuilder();
        this.proyectos.forEach(sb::append);
        return sb.toString();
    }

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Validaciones.validarNotBlank(nombre, "El nombre de una empresa no puede ser vacío.");
        this.nombre = nombre;
    }
}
