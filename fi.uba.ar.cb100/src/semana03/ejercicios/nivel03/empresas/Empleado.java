package semana03.ejercicios.nivel03.empresas;

import semana03.ejercicios.utils.Validaciones;

import java.util.Objects;

/**
 * Representa un empleado de la empresa.
 * Contiene ID, nombre, salario.
 */
public record Empleado(String id, String nombre, double salario) {
    public Empleado {
        Validaciones.validarNotNull(id, "El 'id' debe ser no nulo");
        Validaciones.validarNotBlank(id, "El 'id' debe ser no vacío");
        Validaciones.validarNotNull(nombre, "El 'nombre' debe ser no nulo");
        Validaciones.validarNotBlank(nombre, "El 'nombre' debe ser no vacío");
        Validaciones.validarNumeroMayorACero(salario, "El salario debe ser mayor a cero");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Empleado empleado)) return false;
        return Objects.equals(id, empleado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
