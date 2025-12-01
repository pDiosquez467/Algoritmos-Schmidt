package examenes.parciales.mayo2017.historiador;

import java.util.Objects;

public record Hito(int anio, String descripcion) {
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Hito hito)) return false;
        return anio == hito.anio && Objects.equals(descripcion, hito.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anio, descripcion);
    }
}
