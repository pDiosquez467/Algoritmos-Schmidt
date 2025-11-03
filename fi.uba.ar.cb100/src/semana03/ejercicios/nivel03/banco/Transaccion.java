package semana03.ejercicios.nivel03.banco;

import validaciones.Validaciones;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaccion {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final double monto;
    private final LocalDateTime fecha;
    private final TipoDeTransaccion tipoDeTransaccion;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa una transacción con el monto, la fecha y el tipo dados.
     * @param monto: el monto de la transacción.
     * @param fecha: la fecha en la que se realizó.
     * @param tipoDeTransaccion: el tipo de transacción.
     */
    public Transaccion(double monto, LocalDateTime fecha, TipoDeTransaccion tipoDeTransaccion) {
        Validaciones.validarNumeroMayorACero(monto, "monto");
        Validaciones.validarNotNull(fecha, "fecha");
        Validaciones.validarNotNull(tipoDeTransaccion, "tipoDeTransaccion");
        this.monto = monto;
        this.fecha = fecha;
        this.tipoDeTransaccion = tipoDeTransaccion;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Transaccion that)) return false;
        return Double.compare(monto, that.monto) == 0 && Objects.equals(fecha, that.fecha) && tipoDeTransaccion == that.tipoDeTransaccion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(monto, fecha, tipoDeTransaccion);
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "monto=" + monto +
                ", fecha=" + fecha +
                ", tipoDeTransaccion=" + tipoDeTransaccion +
                '}';
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve el monto de la transacción.
     * @return el monto de la transacción.
     */
    public double monto() {
        return monto;
    }

    /**
     * post: Devuelve la fecha de realización de la transacción.
     * @return la fecha de realización.
     */
    public LocalDateTime fecha() {
        return fecha;
    }

    /**
     * post: Devuelve el tipo de la transacción.
     * @return el tipo de transacción.
     */
    public TipoDeTransaccion tipoDeTransaccion() {
        return tipoDeTransaccion;
    }
}
