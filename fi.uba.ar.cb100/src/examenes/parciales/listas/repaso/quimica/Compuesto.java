package examenes.parciales.listas.repaso.quimica;

import java.util.Objects;

public class Compuesto {

    private final String nombre;
    private final UnidadDeMedidaDeCompuesto unidadDeMedida;
    private double cantidad;

    /**
     * post: Inicializa el compuesto con el nombre y la unidad de medida dada,
     * y con cantidad igual a cero.
     * @param nombre: el nombre del compuesto.
     * @param unidadDeMedida: la unidad de medida.
     */
    public Compuesto(String nombre, UnidadDeMedidaDeCompuesto unidadDeMedida) {
        this.nombre = nombre;
        this.unidadDeMedida = unidadDeMedida;
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

    /**
     * post: Devuelve el nombre del compuesto.
     * @return el nombre del compuesto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * post: Devuelve la unidad de medida del compuesto.
     * @return la unidad de medida del compuesto.
     */
    public UnidadDeMedidaDeCompuesto getUnidadDeMedida() {
        return unidadDeMedida;
    }

    /**
     * post: Devuelve la cantidad del compuesto.
     * @return la cantidad del compuesto.
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * post: Modifica la cantidad de compuesto.
     * @param cantidad: la nueva cantidad de compuesto.
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
