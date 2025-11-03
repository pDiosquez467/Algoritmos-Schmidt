package semana03.ejercicios.nivel03.cartas;

import validaciones.Validaciones;

import java.util.*;

public class Jugador {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final String nombre;
    private final List<Carta> manoDeCartas;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa el jugador con el nombre dado y una mano de cartas vacía.
     * pre: El nombre del jugador no puede ser nulo ni vacío.
     * @param nombre: el nombre del jugador.
     */
    public Jugador(String nombre) {
        Validaciones.validarNotNull(nombre, "nombre");
        this.nombre = nombre;
        this.manoDeCartas = new ArrayList<>();
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Jugador jugador)) return false;
        return Objects.equals(nombre, jugador.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Agrega una o varias cartas a la mano del jugador.
     * pre: Las cartas no deben ser nulas.
     * @param cartas: las cartas a agregar.
     * @return verdadero si se agregaron exitosamente.
     */
    public boolean agregar(Carta ...cartas) {
        Validaciones.validarNotNull(cartas, "cartas");
        return this.manoDeCartas.addAll(List.of(cartas));
    }

    /**
     * post: Remueve de la mano de cartas la carta dada.
     * pre: La carta no debe ser nula, y debe pertenecer a
     * la mano del jugador.
     * @param carta: la carta a jugar.
     * @return la carta jugada.
     */
    public Carta jugar(Carta carta) {
        Validaciones.validarNotNull(carta, "carta");
        boolean ok = this.manoDeCartas.remove(carta);
        if (!ok) {
            throw new RuntimeException("La carta no pertenece a la mano del jugador");
        }
        return carta;
    }

    /**
     * post: Indica si la carta dada pertenece a la mano del jugador.
     * @param carta: la carta indicada.
     * @return verdadero si la carta pertenece a la mano del jugador.
     */
    public boolean contiene(Carta carta) {
        Validaciones.validarNotNull(carta, "carta");
        return this.manoDeCartas.contains(carta);
    }

    /**
     * post: Calcula la puntuación total de la mano del jugador.
     * NOTA: La lógica específica de la puntuación (ej. Blackjack, Tute, etc.).
     * @return la puntuación total de la mano.
     */
    public int puntuacionTotal() {
        return 0;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve el nombre del jugador.
     * @return el nombre del jugador.
     */
    public String nombre() {
        return nombre;
    }

    /**
     * post: Devuelve una copia inmutable de la mano de cartas del jugador.
     * @return la mano de cartas del jugador.
     */
    public List<Carta> manoDeCartas() {
        return Collections.unmodifiableList(this.manoDeCartas);
    }
}
