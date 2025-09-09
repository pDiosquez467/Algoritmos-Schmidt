package semana03.ejercicios.nivel03.cartas;

import semana03.ejercicios.nivel03.cartas.excepciones.BarajaException;
import semana03.ejercicios.utils.Validaciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa una baraja de cartas simple.
 * Contiene operaciones para barajar, repartir y consultar cartas restantes.
 */
public class Baraja {
    private List<Carta> cartas;

    /**
     * Crea una baraja con la lista de cartas dada (copia defensiva).
     *
     * @param cartas lista de cartas inicial (no puede ser null)
     * @throws IllegalArgumentException si cartas es null
     */
    public Baraja(List<Carta> cartas) {
        setCartas(cartas);
    }

    /**
     * Crea una baraja vacía.
     */
    public Baraja() {
        this(new ArrayList<>());
    }

    // --- Métodos públicos ---

    /**
     * Baraja las cartas del mazo.
     *
     * @throws BarajaException si el mazo interno es null
     */
    public void barajar() throws BarajaException {
        validarMazoNoNull();
        Collections.shuffle(this.cartas);
    }

    /**
     * Reparte cartas entre los jugadores usando algoritmo round-robin.
     * Valida precondiciones antes de extraer cartas del mazo.
     *
     * @param jugadores        lista de jugadores (no null, no vacía, sin elementos null)
     * @param cartasPorJugador cantidad de cartas por jugador (debe ser > 0)
     * @throws BarajaException si no hay suficientes cartas o si alguna validación falla
     */
    public void repartir(List<Jugador> jugadores, int cartasPorJugador) throws BarajaException {
        validarMazoNoNull();
        validarReparto(jugadores, cartasPorJugador);

        // Algoritmo Round-Robin
        for (int i = 0; i < cartasPorJugador; i++) {
            for (Jugador jugador : jugadores) {
                Carta carta = this.sacarTopCarta();
                jugador.recibirCarta(carta);
            }
        }
    }

    /**
     * Devuelve la cantidad de cartas que quedan en la baraja.
     *
     * @return número de cartas restantes
     */
    public int cartasRestantes() throws BarajaException {
        validarMazoNoNull();
        return this.cartas.size();
    }

    /**
     * Devuelve una vista inmodificable de las cartas restantes en la baraja.
     * Protege la lista interna contra modificaciones externas.
     *
     * @return lista inmodificable con las cartas restantes (preserva el orden)
     */
    public List<Carta> verCartasRestantes() throws BarajaException {
        validarMazoNoNull();
        return List.copyOf(this.cartas);
    }

    // --- Helpers privados ---

    /**
     * Indica si hay al menos {@code cantidad} cartas en el mazo.
     *
     * @param cantidad cantidad a comprobar
     * @return true si hay suficientes cartas
     */
    private boolean haySuficientesCartas(int cantidad) throws BarajaException {
        validarMazoNoNull();
        return this.cartas.size() >= cantidad;
    }

    /**
     * Saca y devuelve la carta del tope (última posición).
     *
     * @return carta extraída
     * @throws BarajaException si no hay cartas en el mazo
     */
    private Carta sacarTopCarta() throws BarajaException {
        validarMazoNoNull();
        if (this.cartas.isEmpty()) {
            throw new BarajaException("No hay cartas en la baraja para sacar.");
        }
        return this.cartas.remove(this.cartas.size() - 1);
    }

    /**
     * Valida las precondiciones necesarias antes de repartir:
     * - jugadores no es null
     * - jugadores no está vacío
     * - ningún jugador es null
     * - cartasPorJugador > 0
     * - hay suficientes cartas en el mazo para repartir
     *
     * @param jugadores        lista de jugadores
     * @param cartasPorJugador cantidad de cartas por jugador
     * @throws BarajaException si alguna validación falla
     */
    private void validarReparto(List<Jugador> jugadores, int cartasPorJugador) throws BarajaException {
        Validaciones.validarNotNull(jugadores, "La lista de jugadores no debe ser 'null'.");
        Validaciones.validarNoVacio(Collections.singletonList(jugadores), "La lista de jugadores no debe ser vacía.");
        // comprobar que no hay jugadores null
        for (Jugador j : jugadores) {
            if (j == null) {
                throw new BarajaException("La lista de jugadores contiene un elemento 'null'.");
            }
        }

        Validaciones.validarNumeroMayorACero(cartasPorJugador, "El número de cartas por jugador debe ser mayor a cero.");
        long totalRequerido = (long) jugadores.size() * (long) cartasPorJugador;
        if (!this.haySuficientesCartas((int) totalRequerido)) {
            throw new BarajaException("No hay suficientes cartas para repartir: requeridas="
                    + totalRequerido + ", disponibles=" + this.cartas.size());
        }
    }

    /**
     * Asegura que el mazo interno no sea null.
     *
     * @throws BarajaException si el mazo es null
     */
    private void validarMazoNoNull() throws BarajaException {
        if (this.cartas == null) {
            throw new BarajaException("El mazo de cartas es 'null'. Inicializar con una lista antes de operar.");
        }
    }

    // -- Getters y Setters ---

    /**
     * Devuelve una vista inmodificable de la lista interna de cartas.
     *
     * @return lista inmodificable con las cartas del mazo
     */
    public List<Carta> getCartas() throws BarajaException {
        validarMazoNoNull();
        return Collections.unmodifiableList(this.cartas);
    }

    /**
     * Establece las cartas del mazo haciendo copia defensiva.
     *
     * @param cartas nueva lista de cartas (no puede ser null)
     * @throws IllegalArgumentException si cartas es null
     */
    public void setCartas(List<Carta> cartas) {
        Validaciones.validarNotNull(cartas, "La lista de cartas no puede ser 'null'.");
        // copia defensiva para evitar aliasing
        this.cartas = new ArrayList<>(cartas);
    }
}

