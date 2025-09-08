package semana03.ejercicios.nivel01.rectangulo;

import semana03.ejercicios.utils.Validaciones;

/**
 * Representa un rectángulo geométrico con propiedades básicas como base y altura.
 * Esta clase proporciona métodos para calcular área y perímetro, así como para
 * modificar las dimensiones del rectángulo.
 *
 * @author pdioquez
 */
public class Rectangulo {
    private double base;
    private double altura;

    /**
     * Constructor que crea un nuevo rectángulo con la base y altura especificadas.
     *
     * Precondiciones:
     * - La base debe ser mayor a cero
     * - La altura debe ser mayor a cero
     *
     * Postcondición:
     * - El objeto Rectangulo se ha creado correctamente con los valores proporcionados
     *
     * @param base la base del rectángulo (> 0)
     * @param altura la altura del rectángulo (> 0)
     * @throws IllegalArgumentException si base o altura son menores o iguales a cero
     */
    public Rectangulo(double base, double altura) throws Exception {
        setBase(base);
        setAltura(altura);
    }

    /**
     * Constructor de copia que crea una nueva instancia del rectángulo dado.
     *
     * Precondición:
     * - El parámetro rectangulo no puede ser null
     *
     * Postcondición:
     * - Se ha creado una nueva instancia independiente con las mismas dimensiones
     *
     * @param rectangulo el rectángulo a copiar
     * @throws NullPointerException si rectangulo es null
     */
    public Rectangulo(Rectangulo rectangulo) throws Exception {
        this(rectangulo.getBase(), rectangulo.getAltura());
    }

    @Override
    public String toString() {
        return "Rectangulo{" +
                "base=" + base +
                ", altura=" + altura +
                '}';
    }

    /**
     * Calcula y retorna el área del rectángulo.
     *
     * Precondición:
     * - El objeto debe estar en un estado válido (base > 0 y altura > 0)
     *
     * Postcondición:
     * - Retorna el área calculada como base × altura
     *
     * @return el área del rectángulo
     */
    public double area() {
        return this.base * this.altura;
    }

    /**
     * Calcula y retorna el perímetro del rectángulo.
     *
     * Precondición:
     * - El objeto debe estar en un estado válido (base > 0 y altura > 0)
     *
     * Postcondición:
     * - Retorna el perímetro calculado como 2 × (base + altura)
     *
     * @return el perímetro del rectángulo
     */
    public double perimetro() {
        return 2 * (this.base + this.altura);
    }

    /**
     * Retorna el valor de la base del rectángulo.
     *
     * Precondición:
     * - El objeto debe estar en un estado válido
     *
     * Postcondición:
     * - Retorna el valor actual de la base (> 0)
     *
     * @return la base del rectángulo
     */
    public double getBase() {
        return base;
    }

    /**
     * Retorna el valor de la altura del rectángulo.
     *
     * Precondición:
     * - El objeto debe estar en un estado válido
     *
     * Postcondición:
     * - Retorna el valor actual de la altura (> 0)
     *
     * @return la altura del rectángulo
     */
    public double getAltura() {
        return altura;
    }

    /**
     * Modifica el valor de la base del rectángulo.
     *
     * Precondición:
     * - El parámetro base debe ser mayor a cero
     *
     * Postcondición:
     * - La nueva base se ha establecido correctamente
     * - Se lanza IllegalArgumentException si base es menor o igual a cero
     *
     * @param base el nuevo valor para la base (> 0)
     * @throws IllegalArgumentException si base ≤ 0
     */
    public void setBase(double base) throws Exception {
        Validaciones.validarNumeroMayorACero(base, "La base debe ser mayor a cero");
        this.base = base;
    }

    /**
     * Modifica el valor de la altura del rectángulo.
     *
     * Precondición:
     * - El parámetro altura debe ser mayor a cero
     *
     * Postcondición:
     * - La nueva altura se ha establecido correctamente
     * - Se lanza IllegalArgumentException si altura ≤ 0
     *
     * @param altura el nuevo valor para la altura (> 0)
     * @throws IllegalArgumentException si altura ≤ 0
     */
    public void setAltura(double altura) throws Exception {
        Validaciones.validarNumeroMayorACero(altura, "La altura debe ser mayor a cero");
        this.altura = altura;
    }
}