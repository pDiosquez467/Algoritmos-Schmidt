package examenes.parciales.junio2025.escudo;

import validaciones.Validaciones;

import java.util.Objects;

public class Escudo {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private final int puntosDeResistenciaOriginales;
    private int puntosDeResistenciaActuales;
    private int cantidadDeAtaquesSop;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa el escudo con la cantidad de puntos de resistencia
     * dados y con cantidad de ataques soportados igual a 0.
     * pre: Los puntos de resistencia dados deben ser mayores o iguales a 0.
     * @param puntosDeResistenciaOriginales: puntos originales de resistencia.
     */
    public Escudo(int puntosDeResistenciaOriginales) {
        Validaciones.validarNumeroMayorACero(puntosDeResistenciaOriginales,
                "'puntosDeResistencia'");
        this.puntosDeResistenciaOriginales = puntosDeResistenciaOriginales;
        this.puntosDeResistenciaActuales   = puntosDeResistenciaOriginales;
        this.cantidadDeAtaquesSop          = 0;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Escudo escudo)) return false;
        return puntosDeResistenciaOriginales == escudo.puntosDeResistenciaOriginales;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(puntosDeResistenciaOriginales);
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Consume puntos de resistencia, recibiendo como parámetro
     * la cantidad de puntos de ataque.
     * @param puntosDeAtaque: puntos de ataque recibidos.
     * @throws RuntimeException lanza una excepción si el escudo está destruido.
     */
    public void defenderAtaque(int puntosDeAtaque) {
        Validaciones.validarNumeroMayorACero(puntosDeAtaque,
                "'puntosDeAtaque'");
        this.validarEstadoDelEscudo();

        if (this.puntosDeResistenciaActuales - puntosDeAtaque >= 0) {
            this.cantidadDeAtaquesSop++;
        }
        this.puntosDeResistenciaActuales = Math.max(0, this.puntosDeResistenciaActuales - puntosDeAtaque);
    }

    /**
     * post: Indica si el escudo está destruido.
     * @return verdadero si la cantidad de puntos de resistencia actuales
     * es 0.
     */
    public boolean estaDestruido() {
        return this.puntosDeResistenciaActuales == 0;
    }

    /**
     * post: Restaura la cantidad de puntos originales del escudo, siempre
     * que el escudo no haya sido destruido.
     * @throws RuntimeException: lanza una excepción si el escudo está destruido.
     */
    public void restaurar() {
        this.validarEstadoDelEscudo();
        this.puntosDeResistenciaActuales = this.puntosDeResistenciaOriginales;
    }

    /**
     * post: Devuelve la cantidad de ataques que pudo defender el escudo antes de
     * ser destruido.
     * @return la cantidad de ataques soportados.
     */
    public int contarAtaquesSoportados() {
        return this.cantidadDeAtaquesSop;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve los puntos actuales de resistencia del escudo.
     * @return la resistencia actual.
     */
    public int puntosDeResistenciaActuales() {
        return puntosDeResistenciaActuales;
    }

    /**
     * post: Devuelve los puntos de resistencia originales del escudo.
     * @return la resistencia original.
     */
    public int puntosDeResistenciaOriginales() {
        return puntosDeResistenciaOriginales;
    }

    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------

    /**
     * @throws RuntimeException si el escudo está destruido.
     */
    private void validarEstadoDelEscudo() {
        if (this.estaDestruido()) {
            throw new RuntimeException("Escudo destruido");
        }
    }
}
