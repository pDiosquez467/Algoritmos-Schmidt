package examenes.parciales.indicadorDeCanal;

import validaciones.Validaciones;

import java.util.Objects;

public class Canal {

    private final int numero;
    private EstadoDeCanal estadoDeCanal;

    public Canal(int numero) {
        Validaciones.validarNumeroMayorACero(numero, "'numero' debe ser > 0");
        this.numero = numero;
        this.estadoDeCanal = EstadoDeCanal.ACTIVO;
    }

    public int getNumero() {
        return numero;
    }

    public EstadoDeCanal getEstadoDeCanal() {
        return estadoDeCanal;
    }

    public void setEstadoDeCanal(EstadoDeCanal estadoDeCanal) {
        Validaciones.validarNotNull(estadoDeCanal, "'estadoDeCanal' no puede ser nulo");
        this.estadoDeCanal = estadoDeCanal;
    }

    public boolean estaActivo() {
        return estadoDeCanal == EstadoDeCanal.ACTIVO;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Canal canal)) return false;
        return numero == canal.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numero);
    }
}
