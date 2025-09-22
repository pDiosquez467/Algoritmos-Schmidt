package semana03.ejercicios.nivel03.cartas;

import semana03.ejercicios.utils.Validaciones;

import java.util.Objects;

public record Carta(Palo palo, int valor) {
    public enum Palo {
        BASTO,
        COPA,
        ESPADA,
        ORO
    }

    public Carta {
        Validaciones.validarNumeroEntre(valor, 1, 12, "El valor de la carta debe estar entre 1 y 12");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Carta carta)) return false;
        return valor == carta.valor && palo == carta.palo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(palo, valor);
    }
}
