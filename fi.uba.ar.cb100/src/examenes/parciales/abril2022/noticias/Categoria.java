package examenes.parciales.abril2022.noticias;

import java.util.Objects;

public record Categoria(String nombre, String descripcion) {
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Categoria categoria)) return false;
        return Objects.equals(nombre, categoria.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}
