package semana03.ejercicios.nivel03.cartas;

import semana03.ejercicios.utils.Validaciones;

/**
 * Representa una carta en un juego de cartas españolas.
 */
public class Carta {
    public enum Palo {
        BASTO,
        COPA,
        ESPADA,
        ORO
    }

    private int valor;
    private Palo palo;

    /**
     * Crea una instancia de una Carta.
     * @param valor: Un número que representa el valor de la carta. Debe estar comprendido entre 1 y 12.
     * @param palo: El palo de la carta.
     */
    public Carta(int valor, Palo palo) {
        this.valor = valor;
        this.palo = palo;
    }

    // --- GETTERS Y SETTERS ---

    /**
     * Devuelve el valor de la carta.
     * @return el valor de la carta.
     */
    public int getValor() {
        return valor;
    }

    /**
     * Devuelve el palo de la carta.
     * @return el palo de la carta.
     */
    public Palo getPalo() {
        return palo;
    }

    /**
     * Permite modificar el valor de la carta.
     * @param valor: Debe ser un número comprendido entre 1 y 12.
     */
    public void setValor(int valor) {
        Validaciones.validarNumeroEntre(valor, 1, 12, "El valor de una carta debe estar entre 1 y 12");
        this.valor = valor;
    }

    /**
     * Permite modificar el palo de la carta.
     * @param palo: el palo de la carta (BASTO, COPA, ESPADA, ORO).
     */
    public void setPalo(Palo palo) {
        this.palo = palo;
    }
}
