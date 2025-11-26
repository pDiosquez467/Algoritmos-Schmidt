package examenes.parciales.octubre2023.productos;

import java.util.Objects;

public record Producto(String nombre, String categoria) {
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Producto producto)) return false;
        return Objects.equals(nombre, producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}
