package examenes.parciales.noviembre2021.quimico;

import java.util.Objects;

public class Compuesto {
    private final String nombre;
    private final UnidadDeMedida unidadDeMedida;
    private double cantidad;

    /**
     * post: Inicializa el compuesto, identificado por 'nombre',
     * con cantidad 0 de la unidad de medida 'unidadDeMedida'.
     * @param nombre: el nombre del compuesto.
     * @param unidadDeMedida: la unidad de medida del compuesto.
     */
    public Compuesto(String nombre, UnidadDeMedida unidadDeMedida) {
        this.nombre         = nombre;
        this.unidadDeMedida = unidadDeMedida;
        this.cantidad       = 0;
    }

    /**
     * post: Devuelve el nombre del compuesto.
     * @return el nombre del compuesto.
     */
    public String nombre() {
        return nombre;
    }

    /**
     * post: Devuelve la unidad de medida del compuesto.
     * @return la unidad de medida del compuesto.
     */
    public UnidadDeMedida unidadDeMedida() {
        return unidadDeMedida;
    }

    /**
     * post: Devuelve la cantidad de compuesto.
     * @return la cantidad de compuesto.
     */
    public double cantidad() {
        return cantidad;
    }

    /**
     * post: Cambia la cantidad de compuesto, por la cantidad dada.
     * @param cantidad: la nueva cantidad de compuesto.
     * @return el compuesto actualizado.
     */
    public Compuesto setCantidad(double cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Compuesto compuesto)) return false;
        return Objects.equals(nombre, compuesto.nombre) && unidadDeMedida == compuesto.unidadDeMedida;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, unidadDeMedida);
    }
}
