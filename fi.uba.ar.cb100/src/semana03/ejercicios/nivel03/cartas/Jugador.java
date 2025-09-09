package semana03.ejercicios.nivel03.cartas;

import semana03.ejercicios.utils.Validaciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa a un jugador del juego.
 * Contiene nombre y una mano de cartas.
 */
public class Jugador {
    private String nombre;
    private List<Carta> cartas;

    /**
     * Crea una instancia de jugador.
     * @param nombre: El nombre del jugador. Debe ser válido (no vacío y not null)
     */
    public Jugador(String nombre) {
        this.setNombre(nombre);
        this.cartas = new ArrayList<>();
    }

    /**
     * Agrega una carta a la mano del jugador.
     * @param carta: Carta que se agrega a la mano del jugador. Debe ser not null.
     */
    public void recibirCarta(Carta carta) {
        this.cartas.add(carta);
    }

    /**
     * Agrega todas las cartas recibidas a la mano de cartas del jugador.
     * @param cartas: Una mano de cartas válidas del juego.
     */
    public void recibirMano(List<Carta> cartas) {
        this.cartas.addAll(cartas);
    }

    /**
     * Devuelve la mano de cartas del jugador.
     * @return la mano de cartas del jugador.
     */
    public List<Carta> verMano() {
        return Collections.unmodifiableList(this.cartas);
    }

    /**
     * Limpia la mano de cartas del jugador dejándola sin cartas (vacía).
     */
    public void limpiarMano() {
        this.cartas.clear();
    }

    // --- GETTERS Y SETTERS ---

    /**
     * Devuelve el nombre del jugador.
     * @return nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Permite modificar el nombre del jugador.
     * @param nombre: Nombre del jugador. Debe ser not null y no vacío.
     */
    public void setNombre(String nombre) {
        Validaciones.validarNotNull(nombre, "El nombre del jugador debe ser not 'null'");
        Validaciones.validarNotBlank(nombre, "El nombre del jugador debe ser no vacío");
        this.nombre = nombre;
    }
}
