package semana03.ejercicios.nivel03.banco;

import semana03.ejercicios.nivel03.banco.excepciones.CuentaBancariaException;
import semana03.ejercicios.nivel03.banco.excepciones.UsuarioException;
import semana03.ejercicios.utils.Validaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Usuario {
    private String id;
    private String nombre;
    private final List<CuentaBancaria> cuentasBancarias;

    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.cuentasBancarias = new ArrayList<>();
    }

    public void agregarCuentaBancaria(CuentaBancaria cuentaBancaria) throws CuentaBancariaException {
        if (this.esCuentaAsociada(cuentaBancaria)) {
            throw new CuentaBancariaException("La cuenta dada ya es una cuenta del usuario");
        }
        this.cuentasBancarias.add(cuentaBancaria);
    }

    public void eliminarCuentaBancaria(CuentaBancaria cuentaBancaria) throws CuentaBancariaException {
        if (!this.esCuentaAsociada(cuentaBancaria)) {
            throw new CuentaBancariaException("El usuario no tiene asociada esta cuenta");
        }
        this.cuentasBancarias.remove(cuentaBancaria);
    }

    public double obtenerSaldoTotal() {
        return this.cuentasBancarias.stream()
                .map(CuentaBancaria::getSaldo)
                .reduce(0.0, Double::sum);
    }

    public void transferir(CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino, Double monto) throws CuentaBancariaException, UsuarioException {
        Validaciones.validarNumeroMayorACero(monto, "El monto de la transferencia debe ser mayor a cero!");

        if (!esCuentaAsociada(cuentaOrigen)) {
            throw new UsuarioException("La cuenta de origen no está asociada al usuario");
        }

        if (cuentaOrigen.saldoInsuficiente(monto)) {
            throw new UsuarioException("La cuenta de origen no tiene saldo suficiente!");
        }

        cuentaOrigen.transferir(cuentaDestino, monto);
    }

    public CuentaBancaria buscarCuentaBancaria(long numeroCuenta) {
        return buscar(c -> c.getNumeroCuenta() == numeroCuenta);
    }

    public String obtenerHistorialTransacciones() {
        StringBuilder sb = new StringBuilder();
        this.cuentasBancarias
                .forEach(cuentaBancaria -> sb.append(cuentaBancaria.obtenerHistorialTransacciones()));

        return sb.toString();
    }

    private boolean esCuentaAsociada(CuentaBancaria cuentaBancaria) {
        return buscar(c -> c.equals(cuentaBancaria)) != null;
    }

    private CuentaBancaria buscar(Predicate<CuentaBancaria> criterio) {
        return this.cuentasBancarias.stream()
                .filter(criterio)
                .findAny()
                .orElse(null);
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}