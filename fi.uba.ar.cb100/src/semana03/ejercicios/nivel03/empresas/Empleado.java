package semana03.ejercicios.nivel03.empresas;

import semana03.ejercicios.utils.Validaciones;

import java.util.Objects;

public class Empleado {
    private String id;
    private String nombre;
    private String salario;

    public Empleado(String id, String nombre, String salario) {
        this.setId(id);
        this.setNombre(nombre);
        this.setSalario(salario);
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", salario='" + salario + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Empleado empleado)) return false;
        return Objects.equals(this.id, empleado.id) && Objects.equals(this.nombre, empleado.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public String getSalario() {
        return salario;
    }

    public void setId(String id) {
        Validaciones.validarNotBlank(id, "El identificador 'id' de un empleado no puede ser vacío.");
        this.id = id;
    }

    public void setNombre(String nombre) {
        Validaciones.validarNotBlank(nombre, "El nombre de un empleado no debe ser vacío.");
        this.nombre = nombre;
    }

    public void setSalario(String salario) {
        Validaciones.validarNumeroMayorACero(salario, "EL salario de un empleado debe ser mayor a cero.");
        this.salario = salario;
    }
}
