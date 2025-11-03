package semana03.ejercicios.nivel03.cartas;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Baraja {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final List<Carta> cartas;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa la baraja de cartas sin cartas asociadas.
     */
    public Baraja() {
        this.cartas = new ArrayList<>();
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Agrega una o varias cartas a la baraja.
     * @param cartas: cartas a agregar.
     * @return verdadero si se agregaron exitosamente.
     */
    public boolean agregar(Carta ...cartas) {
        Validaciones.validarNotNull(cartas, "cartas");
        return this.cartas.addAll(Set.of(cartas));
    }

    /**
     * post: Reparte una carta de la baraja.
     * pre: La baraja no debe estar vacía.
     * @return la carta dada.
     */
    public Carta repartir() {
        Validaciones.validarVerdadero(!this.cartas.isEmpty(), "cartas");
        return this.cartas.remove(0);
    }

    /**
     * post: Baraja la lista de cartas (genera una permutación de las cartas).
     */
    public void barajar() {
        Collections.shuffle(this.cartas);
    }

    public boolean contiene(Carta carta) {
        Validaciones.validarNotNull(carta, "carta");
        return this.cartas.contains(carta);
    }

    /**
     * post: Indica si la baraja de cartas está vacía.
     * @return verdadero si la baraja está vacía.
     */
    public boolean estaVacia() {
        return this.cartas.isEmpty();
    }

    /**
     * post: Devuelve una lista de las cartas de la baraja.
     * @return una lista con las cartas que tiene la baraja.
     */
    public List<Carta> ver() {
        return Collections.unmodifiableList(this.cartas);
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
}
