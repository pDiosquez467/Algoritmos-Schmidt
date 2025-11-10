package examenes.parciales.listas.repaso.viajes;

import java.util.List;

public class Itinerario {

    private double costo;
    private List<Escala> escalas;

    /**
     * post: Inicializa el itinerario con el costo indicado y sin escalas.
     */
    public Itinerario(double costo, List<Escala> escalas) {
        this.costo = costo;
        this.escalas = escalas;
    }

    /**
     * post: Devuelve el costo del itinerario.
     * @return el costo del itinerario.
     */
    public double getCosto() {
        return costo;
    }

    /**
     * post: Devuelve todas las escalas que conforman el itinerario.
     * @return todas las escalas del itinerario.
     */
    public List<Escala> getEscalas() {
        return escalas;
    }
}
