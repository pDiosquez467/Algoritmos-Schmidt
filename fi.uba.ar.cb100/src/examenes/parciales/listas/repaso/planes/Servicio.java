package examenes.parciales.listas.repaso.planes;

import java.util.Objects;

public class Servicio {

    private final String nombre;
    private boolean esAdicional;

    /**
     * post: Inicializa un servicio con el nombre indicado.
     */
    public Servicio(String nombre, boolean esAdicional) {
        this.nombre = nombre;
        this.esAdicional = esAdicional;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Servicio servicio)) return false;
        return Objects.equals(nombre, servicio.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    /**
     * post: Devuelve el nombre del servicio.
     * @return el nombre del servicio.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * post: Indica si el servicio es adicional.
     * @return es adicional?
     */
    public boolean esAdicional() {
        return esAdicional;
    }

    /**
     * post: Permite modificar si el servicio es adicional o no.
     */
    public void setEsAdicional(boolean esAdicional) {
        this.esAdicional = esAdicional;
    }
}
