package examenes.parciales.listas.repaso.planes;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;

import java.util.List;

public class PlanComercial {

    private final double precio;
    private final List<Servicio> servicios;

    /**
     * post: Inicializa un plan comercial con el precio indicado
     * y sin servicios asociados.
     */
    public PlanComercial(double precio) {
        this.precio = precio;
        this.servicios = new ListaSimplementeEnlazada<>();
    }

    /**
     * post: Devuelve el precio del servicio.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * post: Devuelve la lista de servicios que brinda el plan.
     */
    public List<Servicio> getServicios() {
        return servicios;
    }
}
