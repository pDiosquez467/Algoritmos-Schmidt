package semana03.ejercicios.nivel03.banco;

import validaciones.Validaciones;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CuentaBancaria {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final String id;
    private double saldoActual;
    private boolean estaBloqueada;
    private final List<Transaccion> transacciones;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa una cuenta bancaria con el identificador, y el saldo dado,
     * sin transacciones y desbloqueada.
     * @param id: el id de la cuenta.
     * @param saldoActual: el saldo inicial de la cuenta.
     */
    public CuentaBancaria(String id, double saldoActual) {
        Validaciones.validarNotNull(id, "id");
        Validaciones.validarNumeroMayorOIgualACero(saldoActual, "saldo");
        this.id = id;
        this.saldoActual = saldoActual;
        this.estaBloqueada = false;
        this.transacciones = new ArrayList<>();
    }

    /**
     * post: Inicializa una cuenta bancaria con el identificador, con el
     * saldo igual a cero, sin transacciones y desbloqueada.
     * @param id: el id de la cuenta.
     */
    public CuentaBancaria(String id) {
        this(id, 0);
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CuentaBancaria that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "id='" + id + '\'' +
                '}';
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------

    /**
     * post: Deposita en la cuenta el monto dado.
     * pre: El monto dado debe ser mayor a cero.
     * @param monto: el monto a depositar.
     */
    public void depositar(double monto) {
        Validaciones.validarNumeroMayorACero(monto, "monto");
        Validaciones.validarVerdadero(!this.estaBloqueada(), "Cuenta BLOQUEADA");
        this.saldoActual += monto;
        this.transacciones.add(new Transaccion(monto, LocalDateTime.now(), TipoDeTransaccion.DEPOSITO));
    }

    /**
     * post: Retira de la cuenta el monto indicado.
     * pre: El monto dado debe ser mayor a cero y debe haber suficiente saldo.
     * @param monto: el monto a retirar.
     */
    public void retirar(double monto) {
        Validaciones.validarNumeroMayorACero(monto, "monto");
        Validaciones.validarVerdadero(!this.estaBloqueada(), "Cuenta BLOQUEADA");
        Validaciones.validarVerdadero(this.saldoSuficiente(monto), "Saldo insuficiente");
        this.saldoActual -= monto;
        this.transacciones.add(new Transaccion(monto, LocalDateTime.now(), TipoDeTransaccion.RETIRO));
    }

    /**
     * post: Transfiere el monto indicado a la cuenta destino dada.
     * pre: El monto dado debe ser mayor a cero y debe haber suficiente saldo.
     * @param monto: el monto a transferir.
     * @param destino: la cuenta destino.
     */
    public void transferir(double monto, CuentaBancaria destino) {
        Validaciones.validarNumeroMayorACero(monto, "monto");
        Validaciones.validarVerdadero(!this.estaBloqueada(), "Cuenta BLOQUEADA (origen)");
        Validaciones.validarVerdadero(this.saldoSuficiente(monto), "Saldo insuficiente");
        Validaciones.validarVerdadero(!destino.estaBloqueada(), "Cuenta destino BLOQUEADA");
        this.saldoActual -= monto;
        destino.saldoActual += monto;
        LocalDateTime momento = LocalDateTime.now();
        this.transacciones.add(new Transaccion(monto, momento, TipoDeTransaccion.TRANSACCION));
        destino.transacciones.add(new Transaccion(monto, momento, TipoDeTransaccion.TRANSACCION));
    }

    /**
     * post: Indica si la cuenta está vacía.
     * @return verdadero si la cuenta no tiene saldo.
     */
    public boolean estaVacia() {
        return this.saldoActual == 0;
    }

    /**
     * post: Indica si la cuenta tiene el saldo suficiente para retirar
     * o transferir el monto indicado.
     * @param monto: el monto a retirar.
     * @return verdadero si el saldo es suficiente.
     */
    public boolean saldoSuficiente(double monto) {
        Validaciones.validarNumeroMayorOIgualACero(monto, "monto");
        return this.saldoActual - monto >= 0;
    }

    /**
     * post: Bloquea la cuenta.
     */
    public void bloquear() {
        this.estaBloqueada = true;
    }

    /**
     * post: Desbloquea la cuenta.
     */
    public void desbloquear() {
        this.estaBloqueada = false;
    }

    /**
     * post: Indica si la cuenta está bloqueada.
     * @return verdadero si la cuenta está bloqueada.
     */
    public boolean estaBloqueada() {
        return this.estaBloqueada;
    }

    /**
     * post: Devuelve una lista con todas las transacciones del tipo indicado.
     * pre: El tipo indicado debe ser no nulo.
     * @param tipoDeTransaccion: lista de transacciones del tipo indicado.
     * @return las transacciones del tipo dado.
     */
    public List<Transaccion> obtenerTransaccionesPorTipo(TipoDeTransaccion tipoDeTransaccion) {
        Validaciones.validarNotNull(tipoDeTransaccion, "tipoDeTransaccion");
        return this.transacciones.stream()
                .filter(transaccion -> transaccion.tipoDeTransaccion().equals(tipoDeTransaccion))
                .toList();
    }

    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve el id de la cuenta.
     * @return el id de la cuenta.
     */
    public String id() {
        return id;
    }

    /**
     * post: Devuelve el saldo actual de la cuenta.
     * @return el saldo actual de la cuenta.
     */
    public double saldoActual() {
        return saldoActual;
    }

    /**
     * post: Devuelve una copia inmutable de la lista de transacciones realizadas.
     * @return la lista de transacciones.
     */
    public List<Transaccion> transacciones() {
        return Collections.unmodifiableList(this.transacciones);
    }
}
