package examenes.parciales.listas.repaso.viajes;

import validaciones.Validaciones;

import java.util.List;

public class AgenteDeViajes {

    /**
     * post: Busca en 'itinerariosDisponibles' aquel itinerario de menor costo,
     * que al menos tenga escalas en los lugares indicados en 'lugaresPorVisitar'
     * por 'duracionVisita' como mínimo.
     */
    public Itinerario buscarMejorItinerario(List<Itinerario> itinerariosDisponibles,
                                           List<String> lugaresPorVisitar,
                                           int duracionVisita) {
        Validaciones.validarNotNull(itinerariosDisponibles, "itinerariosDisponibles");
        Validaciones.validarNotNull(lugaresPorVisitar, "lugaresPorVisitar");
        Validaciones.validarNumeroMayorACero(duracionVisita, "duraciónVisita");
        Itinerario itinerarioOptimo = null;
        for (Itinerario itinerario: itinerariosDisponibles) {
            if (itinerario == null) continue;
            if (tieneTodasLasEscalas(itinerario, lugaresPorVisitar, duracionVisita)) {
                if ((itinerarioOptimo == null)
                        || (itinerario.getCosto() < itinerarioOptimo.getCosto())) {
                    itinerarioOptimo = itinerario;
                }
            }
        }
        return itinerarioOptimo;
    }

    /**
     * post: Indica si el itinerario dado tiene al menos todas las escalas en
     * los lugares indicados en 'lugares' por el tiempo de duración indicado.
     */
    public boolean tieneTodasLasEscalas(Itinerario itinerario, List<String> lugares, int duracionVisita) {
        Validaciones.validarNotNull(itinerario, "itinerario");
        Validaciones.validarNotNull(itinerario.getEscalas(), "Escalas de itinerario");
        Validaciones.validarNotNull(lugares, "lugares");
        Validaciones.validarNumeroMayorACero(duracionVisita, "duraciónVisita");
        List<Escala> escalas = itinerario.getEscalas();
        for (String lugar: lugares) {
            if (lugar == null) continue;
            if (!esEscalaDeDuracion(escalas, lugar, duracionVisita)) return false;
        }
        return true;
    }

    /**
     * post: Indica si el lugar dado es escala de la lista de escalas dada,
     *  por el tiempo solicitado.
     */
    public boolean esEscalaDeDuracion(List<Escala> escalas, String lugar, int duracionVisita) {
        Validaciones.validarNotNull(escalas, "escalas");
        Validaciones.validarNotNull(lugar, "lugar");
        Validaciones.validarNumeroMayorACero(duracionVisita, "duraciónVisita");
        for (Escala escala: escalas) {
            if ((escala == null) || (escala.getLugar() == null)) continue;
            if ((escala.getLugar().equals(lugar)) && (escala.getDuracion() >= duracionVisita))
                return true;
        }
        return false;

        // return escalas.stream()
        //        .anyMatch(escala -> escala.getLugar().equals(lugar) && escala.getDuracion() >= duracionVisita);
    }
}
