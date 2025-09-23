package examenes.parciales.intervalo;

import java.util.Objects;

/**
 * Representa un subconjunto conexo de la recta real (intervalo abierto o cerrado).
 * Hipótesis: solo tipos {ABIERTO, CERRADO}. No se permiten intervalos degenerados
 * (limiteInferior < limiteSuperior estrictamente).
 */
public final class IntervaloNumerico {

    private static final double EPS = 1e-9;

    private double limiteInferior;
    private double limiteSuperior;
    private final TipoDeIntervaloNumerico tipoDeIntervaloNumerico;

    /**
     * Constructor.
     * @throws IllegalArgumentException si limites inválidos o tipo null.
     */
    public IntervaloNumerico(double limiteInferior, double limiteSuperior,
                             TipoDeIntervaloNumerico tipoDeIntervaloNumerico) {
        validarNumeroFinito(limiteInferior, "limiteInferior inválido");
        validarNumeroFinito(limiteSuperior, "limiteSuperior inválido");
        if (!(limiteInferior < limiteSuperior)) {
            throw new IllegalArgumentException("Debe ser 'limInf < limSup'");
        }
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.tipoDeIntervaloNumerico = Objects.requireNonNull(tipoDeIntervaloNumerico,
                "'tipo de intervalo' no puede ser null");
    }

    public double longitud() {
        return limiteSuperior - limiteInferior;
    }

    /**
     * Comprueba si 'x' pertenece al intervalo según su tipo (abierto/cerrado).
     * Usa tolerancia EPS para comparaciones de dobles.
     */
    public boolean perteneceAlIntervalo(double x) {
        validarNumeroFinito(x, "x inválido");
        // Si el intervalo es cerrado, aceptar proximidad a los límites
        if (esCerrado()) {
            if (esIgual(x, limiteInferior) || esIgual(x, limiteSuperior)) {
                return true;
            }
        }
        // interior estricto
        return (limiteInferior + EPS) < x && x < (limiteSuperior - EPS);
    }

    /**
     * Devuelve true si 'this' está incluido en 'otro'.
     * Bajo las hipótesis del enunciado, basta comprobar que ambos extremos de
     * this pertenecen a 'otro'.
     */
    public boolean estaIncluidoEn(IntervaloNumerico otro) {
        if (otro == null) {
            throw new IllegalArgumentException("otro no puede ser null");
        }
        return otro.perteneceAlIntervalo(this.limiteInferior)
                && otro.perteneceAlIntervalo(this.limiteSuperior);
    }

    public boolean incluyeA(IntervaloNumerico otro) {
        if (otro == null) {
            throw new IllegalArgumentException("otro no puede ser null");
        }
        return this.perteneceAlIntervalo(otro.limiteInferior)
                && this.perteneceAlIntervalo(otro.limiteSuperior);
    }

    public double obtenerLimiteInferior() {
        return limiteInferior;
    }

    public double obtenerLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteInferior(double limiteInferior) {
        validarNumeroFinito(limiteInferior, "limiteInferior inválido");
        if (!(limiteInferior < this.limiteSuperior)) {
            throw new IllegalArgumentException("Debe ser 'limInf < limSup'");
        }
        this.limiteInferior = limiteInferior;
    }

    public void setLimiteSuperior(double limiteSuperior) {
        validarNumeroFinito(limiteSuperior, "limiteSuperior inválido");
        if (!(this.limiteInferior < limiteSuperior)) {
            throw new IllegalArgumentException("Debe ser 'limInf < limSup'");
        }
        this.limiteSuperior = limiteSuperior;
    }

    private boolean esCerrado() {
        return this.tipoDeIntervaloNumerico == TipoDeIntervaloNumerico.CERRADO;
    }

    private static boolean esIgual(double a, double b) {
        return Math.abs(a - b) <= EPS;
    }

    private static void validarNumeroFinito(double v, String msg) {
        if (Double.isNaN(v) || Double.isInfinite(v)) {
            throw new IllegalArgumentException(msg);
        }
    }
}

