package semana03.ejercicios.nivel03.cartas;

import validaciones.Validaciones;

import java.util.Objects;


public class Carta {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final Palo palo;
    private final int numero;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa una carta con el palo y el número dados.
     * pre: El número de la carta debe estar comprendido entre el rango establecido para esta implementación.
     * @param palo: el palo de la carta.
     * @param numero: el número de la carta.
     */
    public Carta(Palo palo, int numero) {
        Validaciones.validarNotNull(palo, "palo");
        Validaciones.validarNumeroMayorACero(numero, "numero");
        this.palo = palo;
        this.numero = numero;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Carta carta)) return false;
        return numero == carta.numero && palo == carta.palo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(palo, numero);
    }

    @Override
    public String toString() {
        return numero + " de " + palo;
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve el palo de la carta.
     * @return el palo de la carta.
     */
    public Palo palo() {
        return palo;
    }

    /**
     * post: Devuelve el número de la carta.
     * @return el número de la carta.
     */
    public int numero() {
        return numero;
    }
}
