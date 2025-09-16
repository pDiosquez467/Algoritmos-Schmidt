package semana03.ejercicios.nivel02.pedidos;

import semana03.ejercicios.utils.Validaciones;

import java.util.Objects;

public record Producto(String codigo, String nombre, double precio) {
    public Producto {
        Validaciones.validarNotNull(codigo, "El código de un producto debe ser no nulo");
        Validaciones.validarNotBlank(codigo, "El código de un producto debe ser no vacío");
        Validaciones.validarNotNull(nombre, "El nombre de un producto debe ser no nulo");
        Validaciones.validarNotBlank(nombre, "El nombre de un producto debe ser no vacío");
        Validaciones.validarNumeroMayorACero(precio, "El precio de un producto debe ser mayor a cero");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Producto producto)) return false;
        return Objects.equals(codigo, producto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }
}
