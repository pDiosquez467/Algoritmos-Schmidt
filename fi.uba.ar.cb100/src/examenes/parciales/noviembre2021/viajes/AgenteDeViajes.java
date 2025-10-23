package examenes.parciales.noviembre2021.viajes;

import validaciones.Validaciones;

import java.util.List;

public class AgenteDeViajes {

    /**
     * post: Busca en 'itinerariosDisponibles' aquel itinerario de menor costo, que
     * al menos tenga escalas en los lugares indicados en 'lugaresPorVisitar' por
     * 'duracionVisita' como mínimo.
     */
    public Itinerario buscarMejorItinerario(List<Itinerario> itinerariosDisponibles,
                                            List<String> lugaresPorVisitar,
                                            int duracionVisita) {
        Validaciones.validarNotNull(itinerariosDisponibles, "itinerariosDisponibles");
        Validaciones.validarNotNull(lugaresPorVisitar, "lugaresPorVisitar");
        Validaciones.validarNumeroMayorACero(duracionVisita, "duraciónVisita");
        Itinerario itinerarioDeMenorCosto = null;
        for (Itinerario itinerario: itinerariosDisponibles) {
            if (itinerario == null) continue;
            if (tieneEscalaEnTodosLosLugaresPorTiempoMinimo(itinerario, lugaresPorVisitar, duracionVisita)) {
                if ((itinerarioDeMenorCosto == null)
                        || (itinerario.costo() < itinerarioDeMenorCosto.costo())) {
                    itinerarioDeMenorCosto = itinerario;
                }
            }
        }
        return itinerarioDeMenorCosto;
    }

    /**
     * post: Indica si el itinerario dado tiene escala en todos los lugares dados,
     * por al menos la duración mínima requerida.
     * @param itinerario: el itinerario a evaluar.
     * @param lugaresPorVisitar: la lista de lugares por visitar.
     * @param duracionVisitaMinima: la duración mínima requerida para cada escala.
     * @return verdadero si el itinerario cumple lo requerido.
     */
    public boolean tieneEscalaEnTodosLosLugaresPorTiempoMinimo(Itinerario itinerario,
                                                               List<String> lugaresPorVisitar,
                                                               int duracionVisitaMinima) {
        Validaciones.validarNotNull(itinerario, "itinerario");
        Validaciones.validarNotNull(lugaresPorVisitar, "lugaresPorVisitar");
        Validaciones.validarNumeroMayorACero(duracionVisitaMinima, "duraciónMinima");
        for (String lugar: lugaresPorVisitar) {
            if (lugar == null) return false;
            if (!tieneEscalaEnPorTiempoMinimo(itinerario, lugar, duracionVisitaMinima)) return false;
        }
        return true;
    }

    /**
     * post: Indica si el itinerario dado tiene escala en el lugar solicitado
     * por el tiempo mínimo requerido.
     * @param itinerario: el itinerario a evaluar.
     * @param lugar: el lugar buscado.
     * @param duracionVisitaMinima: el tiempo mínimo requerido.
     * @return verdadero si el itinerario tiene los requisitos indicados.
     */
    public boolean tieneEscalaEnPorTiempoMinimo(Itinerario itinerario,
                                                String lugar,
                                                int duracionVisitaMinima) {
        Validaciones.validarNotNull(itinerario, "itinerario");
        Validaciones.validarNotNull(lugar, "lugar");
        Validaciones.validarNumeroMayorACero(duracionVisitaMinima, "duraciónVisitaMinima");
        Escala escala = obtenerEscalaViaLugar(itinerario.escalas(), lugar);
        return (escala != null) && (escala.duracion() >= duracionVisitaMinima);
    }

    /**
     * post: Devuelve la escala de la lista de escalas dada con el lugar solicitado.
     * @param escalas: lista de escalas.
     * @param lugar: lugar buscado.
     * @return la escala con el lugar dado o null en caso de no encontrarla.
     */
    public Escala obtenerEscalaViaLugar(List<Escala> escalas,
                                        String lugar) {
        Validaciones.validarNotNull(escalas, "escalas");
        Validaciones.validarNotNull(lugar, "lugar");
        for (Escala escala: escalas) {
            if (escala.lugar().equals(lugar)) {
                return escala;
            }
        }
        return null;
    }

}
