package semana03.ejercicios.nivel03.banco;

import semana03.ejercicios.nivel03.banco.excepciones.CuentaBancariaException;
import semana03.ejercicios.nivel03.banco.excepciones.UsuarioException;
import validaciones.Validaciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Usuario {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final String id;
    private final String nombre;
    private final List<CuentaBancaria> cuentasBancarias;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa un usuario con el id y el nombre dados,
     * sin cuentas bancarias asociadas.
     * pre: El id y el nombre deben ser no nulos y no vacíos.
     * @param id: el id del usuario.
     * @param nombre: el nombre del usuario.
     */
    public Usuario(String id, String nombre) {
        Validaciones.validarNotNull(id, "id");
        Validaciones.validarNotNull(nombre, "nombre");
        Validaciones.validarNotBlank(id, "id");
        Validaciones.validarNotBlank(nombre, "nombre");
        this.id = id;
        this.nombre = nombre;
        this.cuentasBancarias = new ArrayList<>();
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Agrega una cuenta bancaria a la lista de cuentas del usuario.
     * @param cuentaBancaria: cuenta a agregar.
     * @return verdadero si la cuenta se ha agregado exitosamente.
     */
    public boolean agregarCuenta(CuentaBancaria cuentaBancaria) {
        Validaciones.validarNotNull(cuentaBancaria, "cuentaBancaria");
        Validaciones.validarVerdadero(!this.pertenece(cuentaBancaria), "Cuenta duplicada");
        return this.cuentasBancarias.add(cuentaBancaria);
    }

    /**
     * post: Remueve la cuenta indicada de la lista de cuentas del usuario.
     * pre: La lista debe pertenecer al usuario y estar vacía.
     * @param cuentaBancaria: la cuenta a remover.
     * @return verdadero si la cuenta se ha removido exitosamente.
     */
    public boolean removerCuenta(CuentaBancaria cuentaBancaria) {
        Validaciones.validarNotNull(cuentaBancaria, "cuentaBancaria");
        Validaciones.validarVerdadero(this.pertenece(cuentaBancaria), "Cuenta no encontrada");
        Validaciones.validarVerdadero(cuentaBancaria.estaVacia(), "La cuenta no está vacía");
        return this.cuentasBancarias.remove(cuentaBancaria);
    }

    /**
     * post: Deposita el monto indicado en la cuenta bancaria dada.
     * pre: La cuenta dada debe ser no nula y debe pertenecer al usuario.
     * @param monto: el monto a depositar.
     * @param cuentaBancaria: la cuenta bancaria del usuario donde depositar el monto.
     */
    public void depositar(double monto, CuentaBancaria cuentaBancaria) {
        Validaciones.validarNotNull(cuentaBancaria, "cuentaBancaria");
        Validaciones.validarVerdadero(this.pertenece(cuentaBancaria), "cuentaBancaria");
        cuentaBancaria.depositar(monto);
    }

    /**
     * post: Retira el monto indicado en la cuenta bancaria dada.
     * pre: La cuenta dada debe ser no nula y debe pertenecer al usuario.
     * @param monto: el monto a retirar.
     * @param cuentaBancaria: la cuenta bancaria del usuario de la cual retirar el monto.
     */
    public void retirar(double monto, CuentaBancaria cuentaBancaria) {
        Validaciones.validarNotNull(cuentaBancaria, "cuentaBancaria");
        Validaciones.validarVerdadero(this.pertenece(cuentaBancaria), "cuentaBancaria");
        cuentaBancaria.retirar(monto);
    }

    /**
     * post: Transfiere el monto indicado desde la cuenta 'origen' a la cuenta
     * 'destino'.
     * pre: Las cuentas deben ser no nulas y deben pertenecer al usuario.
     * @param monto: el monto a transferir.
     * @param origen: la cuenta de origen.
     * @param destino: la cuenta de destino.
     */
    public void transferir(double monto, CuentaBancaria origen, CuentaBancaria destino) {
        Validaciones.validarNotNull(origen, "origen");
        Validaciones.validarNotNull(destino, "destino");
        Validaciones.validarVerdadero(this.pertenece(origen), "origen");
        Validaciones.validarVerdadero(this.pertenece(destino), "destino");
        origen.transferir(monto, destino);
    }

    /**
     * post: Indica si la cuenta bancaria pertenece al usuario.
     * @param cuentaBancaria: la cuenta a evaluar.
     * @return verdadero si la cuenta pertenece al usuario.
     */
    public boolean pertenece(CuentaBancaria cuentaBancaria) {
        Validaciones.validarNotNull(cuentaBancaria, "cuentaBancaria");
        return this.cuentasBancarias.contains(cuentaBancaria);
    }

    /**
     * post: Devuelve el saldo total del usuario, considerando todas sus
     * cuentas bancarias.
     * @return el saldo total del usuario.
     */
    public double calcularSaldoTotal() {
        return this.cuentasBancarias.stream()
                .mapToDouble(CuentaBancaria::saldoActual)
                .sum();
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve el id del usuario.
     * @return el id del usuario.
     */
    public String id() {
        return id;
    }

    /**
     * post: Devuelve el nombre del usuario.
     * @return el nombre del usuario.
     */
    public String nombre() {
        return nombre;
    }

    /**
     * post: Devuelve una copia inmutable de la lista de cuentas bancarias
     * del usuario.
     * @return una copia de las cuentas del usuario.
     */
    public List<CuentaBancaria> cuentasBancarias() {
        return Collections.unmodifiableList(this.cuentasBancarias);
    }
}