package examenes.parciales.junio2025.planComercial;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

import java.util.Collections;
import java.util.List;

public class PlanComercial {
    private final String codigo;
    private final double precio;
    List<Servicio> servicios;

    /**
     * post: Inicializa el plan comercial con el código 'codigo' y el precio dado.
     * @param codigo: código del plan (ID).
     * @param precio: precio del plan.
     */
    public PlanComercial(String codigo, double precio) {
        Validaciones.validarNotNull(codigo, "'codigo");
        Validaciones.validarNotBlank(codigo, "'codigo'");
        Validaciones.validarNumeroMayorACero(precio, "'precio'");

        this.codigo = codigo;
        this.precio = precio;
        this.servicios = new ListaSimplementeEnlazada<>();
    }

    /**
     * post: Devuelve el código del plan comercial.
     * @return el código del plan.
     */
    public String codigo() {
        return codigo;
    }

    /**
     * post: Devuelve el precio del plan comercial.
     * @return el precio del plan.
     */
    public double precio() {
        return precio;
    }

    /**
     * post: Devuelve la lista de servicios asociados al plan comercial.
     * @return la lista de servicios ofrecidos.
     */
    public List<Servicio> servicios() {
        return Collections.unmodifiableList(this.servicios);
    }
}
