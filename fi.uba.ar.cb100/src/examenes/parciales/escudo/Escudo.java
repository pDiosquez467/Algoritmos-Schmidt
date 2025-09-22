package examenes.parciales.escudo;

import examenes.parciales.escudo.ex.EscudoException;
import semana03.ejercicios.utils.Validaciones;

/**
 * Representa un escudo con una cantidad de puntos de resistencia,
 * y capacidad para soportar ataques.
 */
public class Escudo {
    private final int puntosDeResistenciaOriginales;
    private int puntosDeResistenciaActuales;
    private int cantidadDeAtaquesSoportados;

    /**
     * Crea un objeto escudo con capacidad para soportar una cantidad
     * limitada de ataques.
     * @param puntosDeResistenciaOriginales: cantidad de puntos de resistencia
     *                                     originales del escudo.
     */
    public Escudo(int puntosDeResistenciaOriginales) {
        Validaciones.validarNumeroMayorACero(puntosDeResistenciaOriginales, "Los puntos de resistencia de un escudo" +
                "deben ser mayores a cero");
        this.puntosDeResistenciaOriginales = puntosDeResistenciaOriginales;
        this.puntosDeResistenciaActuales   = puntosDeResistenciaOriginales;
        this.cantidadDeAtaquesSoportados   = 0;
    }

    /**
     * Consume puntos de resistencia del escudo al absorber el ataque.
     * @param puntosDeAtaque: la cantidad de puntos de ataque recibidos.
     * @throws EscudoException: lanza una excepción si el escudo está destruido.
     */
    public void defenderAtaque(int puntosDeAtaque) throws EscudoException {
        Validaciones.validarNumeroMayorACero(puntosDeAtaque, "Los puntos de ataque deben ser mayores a cero");
        if (this.estaDestruido()) {
            throw new EscudoException("El escudo está destruido");
        }
        this.puntosDeResistenciaActuales = Math.max(0, this.puntosDeResistenciaActuales - puntosDeAtaque);
        if (puntosDeResistenciaActuales > 0) {
            this.cantidadDeAtaquesSoportados++;
        }
    }

    /**
     * Indica si el escudo está destruido.
     * @return verdadero si el escudo está destruido.
     */
    public boolean estaDestruido() {
        return this.puntosDeResistenciaActuales == 0;
    }

    /**
     * Restaura los puntos de resistencia del escudo a su valor original.
     * @throws EscudoException: lanza una excepción si el escudo está destruido.
     */
    public void restaurar() throws EscudoException {
        if (this.estaDestruido()) {
            throw new EscudoException("El escudo está destruido");
        }
        this.puntosDeResistenciaActuales = this.puntosDeResistenciaOriginales;
    }

    /**
     * Devuelve la cantidad de ataques soportados por el escudo sin destruirse.
     * @return la cantidad de ataques soportados por el escudo.
     */
    public int contarAtaquesSoportados() throws EscudoException {
        return this.cantidadDeAtaquesSoportados;
    }

    /**
     * Devuelve la cantidad de puntos de resistencia originales del escudo.
     * @return cantidad de puntos de resistencia originales.
     */
    public int getPuntosDeResistenciaActuales() {
        return this.puntosDeResistenciaActuales;
    }

    /**
     * Devuelve la cantidad de puntos de resistencia actuales del escudo.
     * @return cantidad de puntos de resistencia actuales.
     */
    public int getPuntosDeResistenciaOriginales() {
        return this.puntosDeResistenciaOriginales;
    }
}
