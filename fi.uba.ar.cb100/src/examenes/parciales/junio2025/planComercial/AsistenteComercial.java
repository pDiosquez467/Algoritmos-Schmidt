package examenes.parciales.junio2025.planComercial;

import tdas.conjunto.Conjunto;
import validaciones.Validaciones;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AsistenteComercial {

    /**
     * post: Selecciona de 'planesDisponibles' aquel plan comercial de menor precio
     * y que tenga los servicios indicados en 'serviciosRequeridos' como no adicionales.
     * @param planesDisponibles: planes disponibles.
     * @param serviciosRequeridos: servicios requeridos.
     * @return el plan más económico que cumple las especificaciones.
     */
    public PlanComercial elegirElPlanMasEconomico(List<PlanComercial> planesDisponibles,
                                                  List<String> serviciosRequeridos) {
        Validaciones.validarNotNull(planesDisponibles, "'planesDisponibles'");
        Validaciones.validarNotNull(serviciosRequeridos, "'serviciosRequeridos'");
        Set<PlanComercial> planesFiltrados = new Conjunto<>();
        for (PlanComercial planComercial: planesDisponibles) {
            if ((planComercial != null) && (this.tieneElPlanTodosLosServicios(planComercial, serviciosRequeridos) &&
                    !this.existeAlgunServicioAdicional(planComercial, serviciosRequeridos))) {
                planesFiltrados.add(planComercial);
            }
        }
        return this.buscarPlanMasEconomico(planesFiltrados);
    }

    /**
     * post: Devuelve el plan más económico de la colección de planes comerciales
     * dados. En caso de que la lista sea vacía, devuelve null.
     * @param planesComerciales: planes comerciales disponibles.
     * @return el plan comercial más económico.
     */
    public PlanComercial buscarPlanMasEconomico(Collection<PlanComercial> planesComerciales) {
        Validaciones.validarNotNull(planesComerciales, "'planesComerciales'");
        PlanComercial planMasEconomico = null;
        for (PlanComercial planComercial: planesComerciales) {
            if (planComercial == null) continue;

            if ((planMasEconomico == null) || (planComercial.precio() < planMasEconomico.precio())) {
                planMasEconomico = planComercial;
            }
        }
        return planMasEconomico;
    }

    /**
     * post: Indica si existe alguno de los servicios de la lista
     * de servicios dada es adicional en el plan comercial. Si no encuentra
     * algún servicio o alguno es null devuelve false.
     * @param planComercial: plan comercial.
     * @param serviciosRequeridos: lista de nombres de servicios dados.
     * @return verdadero si alguno de los servicios es adicional en el plan.
     */
    public boolean existeAlgunServicioAdicional(PlanComercial planComercial, List<String> serviciosRequeridos) {
        Validaciones.validarNotNull(planComercial, "'planComercial'");
        Validaciones.validarNotNull(serviciosRequeridos, "'serviciosRequeridos'");
        for (String nombreServicio: serviciosRequeridos) {
            if (nombreServicio == null) return false;

            Servicio servicio = this.obtenerServicioViaNombre(planComercial.servicios(), nombreServicio);
            if (servicio == null) return false;
            if (servicio.esAdicional()) return true;
        }
        return false;
    }

    /**
     * post: Indica si el plan comercial tiene todos los servicios de la
     * lista de servicios dada.
     * @param planComercial: plan comercial.
     * @param serviciosRequeridos: lista de servicios dada.
     * @return verdadero si el plan comercial tiene todos los servicios requeridos.
     */
    public boolean tieneElPlanTodosLosServicios(PlanComercial planComercial, List<String> serviciosRequeridos) {
        Validaciones.validarNotNull(planComercial, "planComercial");
        Validaciones.validarNotNull(serviciosRequeridos, "serviciosRequeridos");
        for (String nombreServicio: serviciosRequeridos) {
            Servicio servicio = this.obtenerServicioViaNombre(planComercial.servicios(), nombreServicio);
            if (servicio == null) return false;
        }
        return true;
    }

    /**
     * post: Devuelve el servicio de la colección de servicios dada que coincida con
     * el nombre de servicio dado.
     * @param servicios: colección de servicios dada.
     * @param nombreServicio: nombre del servicio buscado.
     * @return el servicio buscado o null si no lo encuentra.
     */
    public Servicio obtenerServicioViaNombre(Collection<Servicio> servicios, String nombreServicio) {
        Validaciones.validarNotNull(servicios, "'servicios'");
        Validaciones.validarNotNull(nombreServicio, "'nombreServicio'");
        for (Servicio servicio: servicios) {
            if (servicio.nombre().equals(nombreServicio)) {
                return servicio;
            }
        }
        return null;
    }
}

