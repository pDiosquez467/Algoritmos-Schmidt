package semana03.ejercicios.nivel03.banco;

import semana03.ejercicios.nivel03.banco.excepciones.CuentaBancariaException;
import semana03.ejercicios.nivel03.banco.excepciones.UsuarioException;
import semana03.ejercicios.utils.Validaciones;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CuentaBancaria {
    private long numeroCuenta;
    private Usuario titular;
    private double saldo;
    private final List<Transaccion> transacciones;

    public CuentaBancaria(long numeroCuenta, Usuario titular) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = 0.0;
        this.transacciones = new ArrayList<>();
    }

    public void depositar(double monto) {
        Validaciones.validarNumeroMayorACero(monto, "El monto a depositar debe ser mayor a cero!");
        this.saldo += monto;
        this.transacciones.add(new Transaccion(monto, Transaccion.TipoTransferencia.DEPOSITO));
    }

    public void retirar(double monto) throws CuentaBancariaException {
        if (this.saldoInsuficiente(monto)) {
            throw new CuentaBancariaException("Saldo insuficiente!");
        }
        this.saldo -= monto;
        this.transacciones.add(new Transaccion(monto, Transaccion.TipoTransferencia.RETIRO));
    }

    public void transferir(CuentaBancaria cuentaDestino, double monto) throws CuentaBancariaException {
        if (this.saldoInsuficiente(monto)) {
            throw new CuentaBancariaException("Saldo insuficiente!");
        }

        this.setSaldo(this.getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
        this.transacciones.add(new Transaccion(monto, Transaccion.TipoTransferencia.TRANSFERENCIA));
    }

    public boolean saldoInsuficiente(double monto) {
        return this.saldo <= monto;
    }

    public String obtenerHistorialTransacciones() {
        StringBuilder sb = new StringBuilder();
        this.transacciones.forEach(sb::append);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CuentaBancaria cuentaBancaria)) return false;
        return this.getNumeroCuenta() == cuentaBancaria.getNumeroCuenta() &&
                this.getTitular().equals(cuentaBancaria.getTitular());
    }

    public long getNumeroCuenta() {
        return numeroCuenta;
    }

    public Usuario getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        Validaciones.validarNumeroMayorACero(saldo, "El saldo debe ser mayor a cero!");
        this.saldo = saldo;
    }
}
