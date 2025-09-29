package examenes.parciales.subasta;

import validaciones.Validaciones;

import java.util.Objects;

public record Articulo(String id, String descripcion) {
    public Articulo {
        Validaciones.validarNotNull(id, "ID");
        Validaciones.validarNotBlank(id, "ID");
        Validaciones.validarNotNull(descripcion, "Descripción");
        Validaciones.validarNotBlank(descripcion, "Descripción");

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Articulo articulo)) return false;
        return Objects.equals(id, articulo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
