package examenes.parciales.abril2022;

import validaciones.Validaciones;

public record IntervaloNumerico(double limiteInferior, double limiteSuperior,
                                TipoDeIntervaloNumerico tipoDeIntervaloNumerico) {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa un intervalo numérico conexo con el límite inferior y el límite superior dados,
     * y de un tipo inmutable (ABIERTO, CERRADO).
     *
     * @param limiteInferior: el límite inferior del intervalo.
     * @param limiteSuperior: el límite superior del intervalo.
     * @param tipoDeIntervaloNumerico: el tipo de intervalo.
     * @throws RuntimeException si 'límiteInferior' >= 'límiteSuperior'.
     */
    public IntervaloNumerico {
        Validaciones.validarNumeroMenorA(limiteInferior, limiteSuperior, "limiteInferior < limiteSuperior");
        Validaciones.validarNotNull(tipoDeIntervaloNumerico, "estadoDeIntervalo");
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Devuelve la longitud del intervalo.
     *
     * @return la longitud del intervalo.
     */
    public double getLongitud() {
        return this.limiteSuperior - this.limiteInferior;
    }

    /**
     * post: Indica si el número dado pertenece al intervalo.
     *
     * @param x: el número a evaluar.
     * @return verdadero si el número pertenece al intervalo.
     */
    public boolean pertenece(double x) {
        if (this.tipoDeIntervaloNumerico.equals(TipoDeIntervaloNumerico.CERRADO)) {
            return this.limiteInferior <= x && x <= this.limiteSuperior;
        } else {
            return this.limiteInferior < x && x < this.limiteSuperior;
        }
    }

    /**
     * post: Indica si el intervalo dado está incluido en el intervalo que
     * recibe el mensaje.
     *
     * @param intervalo: el intervalo a evaluar.
     * @return verdadero si el intervalo dado está incluído en el que recibe el
     * mensaje.
     */
    public boolean estaIncluidoEn(IntervaloNumerico intervalo) {
        Validaciones.validarNotNull(intervalo, "intervalo");
        return intervalo.limiteInferior <= this.limiteInferior &&
                this.limiteSuperior <= intervalo.limiteSuperior;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve el límite inferior del intervalo.
     *
     * @return el límite inferior del intervalo.
     */
    @Override
    public double limiteInferior() {
        return limiteInferior;
    }

    /**
     * post: Devuelve el límite superior del intervalo.
     *
     * @return el límite superior del intervalo.
     */
    @Override
    public double limiteSuperior() {
        return limiteSuperior;
    }

    /**
     * post: Devuelve el tipo del intervalo (ABIERTO, CERRADO).
     *
     * @return el tipo del intervalo.
     */
    public TipoDeIntervaloNumerico estadoDeIntervaloNumerico() {
        return tipoDeIntervaloNumerico;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------
}
