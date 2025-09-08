package semana03.ejercicios.nivel01.rectangulo;

import semana03.ejercicios.utils.Validaciones;

public class Rectangulo {
    private double base;
    private double altura;

    /**
     * Constructor de la clase.
     *
     * @param base: Debe ser mayor a cero.
     * @param altura: Debe ser mayor a cero.
     */
    public Rectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    /**
     * Devuelve una copia de la instancia de Rectangulo dada.
     *
     * @param rectangulo: Debe ser una instancia de la clase Rectangulo.
     */
    public Rectangulo(Rectangulo rectangulo) {
        this(rectangulo.getBase(), rectangulo.getAltura());
    }

    @Override
    public String toString() {
        return "Rectangulo{" +
                "base=" + base +
                ", altura=" + altura +
                '}';
    }

    // Métodos de comportamiento

    /**
     * @return devuelve el área del rectángulo que recibe el mensaje.
     */
    public double area() {
        return this.base * this.altura;
    }

    /**
     * @return devuelve el perímetro del rectángulo que recibe el mensaje.
     */
    public double perimetro() {
        return 2 * (this.base + this.altura);
    }

    /**
     * Devuelve la base del rectángulo.
     *
     * @return base
     */
    public double getBase() {
        return base;
    }

    /**
     * Devuelve la altura del rectángulo.
     *
     * @return altura
     */
    public double getAltura() {
        return altura;
    }

    /**
     * Permite modificar el valor de la base del rectángulo.
     *
     * @param base: Debe ser mayor a cero.
     * @throws Exception
     */
    public void setBase(double base) throws Exception {
        Validaciones.validarNumeroMayorACero(base, "La base debe ser mayor a cero. Se ingresó " + base);
        this.base = base;
    }

    /**
     * Permite modificar el valor de la altura del rectángulo.
     *
     * @param altura: Debe ser mayor a cero.
     * @throws Exception
     */
    public void setAltura(double altura) throws Exception {
        Validaciones.validarNumeroMayorACero(base,"La altura debe ser mayor a cero. Se ingresó " + altura);
        this.altura = altura;
    }
}
