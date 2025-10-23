package examenes.parciales.noviembre2021.viajes;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;

import java.util.List;

public class Itinerario {
    private final double costo;
    private final List<Escala> escalas;

    /**
     * post: Crea un itinerario por el costo indicado sin escalas.
     */
    public Itinerario(double costo) {
        this.costo = costo;
        this.escalas = new ListaSimplementeEnlazada<>();
    }

    /**
     * post: Devuelve el costo del itinerario.
     */
    public double costo() {
        return costo;
    }

    /**
     * post: Devuelve la lista de escalas del itinerario.
     */
    public List<Escala> escalas() {
        return escalas;
    }
}
