package examenes.parciales.listas.repaso.materias;

import java.util.Objects;

public record Alumno(int padron, String nombre) {
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Alumno alumno)) return false;
        return padron == alumno.padron;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(padron);
    }
}
