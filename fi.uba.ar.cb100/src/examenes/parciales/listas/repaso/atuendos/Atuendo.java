package examenes.parciales.listas.repaso.atuendos;

import java.util.Objects;

public class Atuendo {

    private String tipo;
    private String color;
    private double precio;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Atuendo atuendo)) return false;
        return Double.compare(precio, atuendo.precio) == 0 && Objects.equals(tipo, atuendo.tipo) && Objects.equals(color, atuendo.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, color, precio);
    }

    /**
     * post: Devuelve el tipo de atuendo.
     * @return el tipo del atuendo.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * post: Devuelve el color del atuendo.
     * @return el color del atuendo.
     */
    public String getColor() {
        return color;
    }

    /**
     * post: Devuelve el precio del atuendo.
     * @return el precio del atuendo.
     */
    public double getPrecio() {
        return precio;
    }
}
