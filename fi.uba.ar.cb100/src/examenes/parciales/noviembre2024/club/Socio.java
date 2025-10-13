package examenes.parciales.noviembre2024.club;

import java.util.Objects;

public class Socio {

    private final String grupoFamiliar;
    private boolean tuvoDeuda;

    public Socio(String grupoFamiliar) {
        this.grupoFamiliar = grupoFamiliar;
        this.tuvoDeuda = false;
    }

    public boolean tuvoDeuda() {
        return tuvoDeuda;
    }

    public String grupoFamiliar() {
        return grupoFamiliar;
    }

    public Socio setTuvoDeuda(boolean tuvoDeuda) {
        this.tuvoDeuda = tuvoDeuda;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Socio socio)) return false;
        return Objects.equals(grupoFamiliar, socio.grupoFamiliar);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(grupoFamiliar);
    }
}
