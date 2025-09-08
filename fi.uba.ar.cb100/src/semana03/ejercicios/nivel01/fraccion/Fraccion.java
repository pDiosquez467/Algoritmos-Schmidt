package semana03.ejercicios.nivel01.fraccion;

import semana03.ejercicios.utils.Validaciones;

/**
 * Clase que representa una fracción matemática.
 * Implementa operaciones básicas aritméticas y métodos auxiliares.
 *
 * @author pdiosquez
 */
public class Fraccion {
    private int numerador;
    private int denominador;

    /**
     * Constructor que crea una nueva fracción.
     *
     * @param numerador El numerador de la fracción
     * @param denominador El denominador de la fracción
     * @throws IllegalArgumentException si el denominador es cero
     * @pre numerador != null && denominador != null && denominador != 0.
     * @post getNumerador() == numerador && getDenominador() == denominador
     */
    public Fraccion(int numerador, int denominador) {
        this.setNumerador(numerador);
        this.setDenominador(denominador);
    }

    /**
     * Suma esta fracción con otra.
     *
     * @param otra La fracción a sumar
     * @return Una nueva fracción que representa la suma
     * @throws IllegalArgumentException si otra es null
     * @pre otra != null
     */
    public Fraccion sumar(Fraccion otra) {
        Validaciones.validarNull(otra, "La fracción a sumar no puede ser nula");

        int numeradorSuma   = this.getNumerador() * otra.getDenominador() +
                this.getDenominador() * otra.getNumerador();
        int denominadorSuma = this.getDenominador() * otra.getDenominador();

        return (new Fraccion(numeradorSuma, denominadorSuma)).simplificar();
    }

    /**
     * Multiplica esta fracción por otra.
     *
     * @param otra La fracción multiplicadora
     * @return Una nueva fracción que representa el producto
     * @throws IllegalArgumentException si otra es null
     * @pre otra != null
     */
    public Fraccion multiplicar(Fraccion otra) {
        Validaciones.validarNull(otra, "La fracción para multiplicar no puede ser nula");

        int numeradorProducto   = this.getNumerador() * otra.getNumerador();
        int denominadorProducto = this.getDenominador() * otra.getDenominador();

        return (new Fraccion(numeradorProducto, denominadorProducto)).simplificar();
    }

    /**
     * Resta una fracción de esta.
     *
     * @param otra La fracción a restar
     * @return Una nueva fracción que representa la diferencia
     * @throws IllegalArgumentException si otra es null
     * @pre otra != null
     */
    public Fraccion restar(Fraccion otra) {
        Validaciones.validarNull(otra, "La fracción a restar no puede ser nula");

        Fraccion opuesta = new Fraccion((-1) * otra.getNumerador(), otra.getDenominador());
        return this.sumar(opuesta);
    }

    /**
     * Divide esta fracción por otra.
     *
     * @param otra La fracción divisor
     * @return Una nueva fracción que representa el cociente
     * @throws IllegalArgumentException si otra es null o su numerador es cero
     * @pre otra != null && otra.getNumerador() != 0
     * @post resultado.getDenominador() > 0
     */
    public Fraccion dividir(Fraccion otra) {
        Validaciones.validarNull(otra, "La fracción divisor no puede ser nula");

        Fraccion inversa = new Fraccion(otra.getDenominador(), otra.getNumerador());
        return this.multiplicar(inversa);
    }

    /**
     * Obtiene el valor decimal de la fracción.
     *
     * @return El valor decimal de la fracción
     */
    public double obtenerDecimal() {
        return (double) numerador / denominador;
    }

    /**
     * Determina si esta fracción es igual a otra.
     *
     * @param obj La objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Fraccion otra)) return false;
        return this.simplificar().getNumerador() == otra.simplificar().getNumerador() &&
                this.simplificar().getDenominador() == otra.simplificar().getDenominador();
    }

    /**
     * Compara esta fracción con otra para determinar si es menor que ella.
     *
     * @param otra La fracción comparada
     * @return true si esta fracción es menor que la otra
     * @pre otra != null
     */
    public boolean menorQue(Fraccion otra) {
        Validaciones.validarNull(otra, "La fracción comparada no puede ser nula");
        return this.obtenerDecimal() < otra.obtenerDecimal();
    }

    /**
     * Compara esta fracción con otra para determinar si es mayor que ella.
     *
     * @param otra La fracción comparada
     * @return true si esta fracción es mayor que la otra
     * @pre otra != null
     */
    public boolean mayorQue(Fraccion otra) {
        Validaciones.validarNull(otra, "La fracción comparada no puede ser nula");
        return this.obtenerDecimal() > otra.obtenerDecimal();
    }

    @Override
    public String toString() {
        return String.format("%d/%d", numerador, denominador);
    }

    public int getNumerador() {
        return numerador;
    }

    public int getDenominador() {
        return denominador;
    }

    public void setNumerador(int numerador) {
        this.numerador = numerador;
    }

    public void setDenominador(int denominador) {
        Validaciones.validarNumeroDistintoCero(denominador, "El denominador debe ser un número distinto de cero!");
        this.denominador = denominador;
    }

    public Fraccion simplificar() {
        int gcd = gcd(this.numerador, this.denominador);
        return new Fraccion(
                this.numerador / gcd,
                this.denominador / gcd
        );
    }

    private int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

}
