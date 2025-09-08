package semana03.ejercicios.nivel03.banco;

import semana03.ejercicios.utils.Validaciones;

import java.time.LocalDateTime;
import java.util.Date;

public class Transaccion {
    public enum TipoTransferencia {
        TRANSFERENCIA,
        DEPOSITO,
        RETIRO
    }

    private double monto;
    private final TipoTransferencia tipo;
    private final LocalDateTime fecha;

    public Transaccion(double monto, TipoTransferencia tipo) {
        this.setMonto(monto);
        this.tipo = tipo;
        this.fecha = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "monto=" + monto +
                ", tipo=" + tipo +
                ", fecha=" + fecha +
                '}';
    }

    public double getMonto() {
        return monto;
    }

    public TipoTransferencia getTipo() {
        return tipo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    private void setMonto(double monto) {
        Validaciones.validarNumeroMayorACero(monto, "El monto de una transacción debe ser mayor a cero");
        this.monto = monto;
    }

}
