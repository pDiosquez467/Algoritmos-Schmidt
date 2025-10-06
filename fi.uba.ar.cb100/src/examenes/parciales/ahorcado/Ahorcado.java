package examenes.parciales.ahorcado;

import tdas.vector.Iterador;
import tdas.vector.IteradorImpl;
import tdas.vector.Vector;
import validaciones.Validaciones;

public class Ahorcado {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private final String palabraClave;
    private final int cantidadErroresPermitidos;
    private int cantidadErroresCometidos;
    private EstadoDeJuego estadoDeJuego;
    private final Vector<Character> letrasUsadas;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * post: Inicializa el juego con la palabra clave dada y la cantidad
     * especificada de errores permitidos.
     * @param palabraClave: palabra clave a adivinar.
     * @param cantidadErroresPermitidos: cantidad máxima de errores permitidos.
     */
    public Ahorcado(String palabraClave, int cantidadErroresPermitidos) {
        Validaciones.validarNotNull(palabraClave, "palabraClave");
        Validaciones.validarNumeroMayorACero(cantidadErroresPermitidos, "cantidadErrores");
        this.palabraClave              = palabraClave;
        this.cantidadErroresPermitidos = cantidadErroresPermitidos;
        this.cantidadErroresCometidos  = 0;
        this.estadoDeJuego             = EstadoDeJuego.JUGANDO;
        this.letrasUsadas = new Vector<Character>();
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Devuelve la cantidad de letras que tiene la palabra clave.
     * @return el largo de la palabra clave.
     */
    public int cantidadLetrasPalabraClave() {
        return this.palabraClave.length();
    }

    /**
     * post: Devuelve la palabra clave si el juego está GANADO o PERDIDO;
     * en caso contrario, devuelve una cadena vacía.
     * @return la palabra clave o una cadena vacía.
     */
    public String getPalabraClave() {
        return (this.estaTerminado()) ? this.palabraClave : "";
    }

    /**
     * post: Devuelve la cantidad de apariciones que tiene la letra dada
     * en la palabra clave. Si la letra no está en la palabra, actualiza
     * la cantidad de errores cometidos y el estado del juego eventualmente.
     * @param letra: letra propuesta.
     * @return cantidad de apariciones de la letra propuesta.
     * @throws RuntimeException si el juego está terminado.
     */
    public int arriesgarLetra(char letra) {
        validarLetra(letra);
        validarJuego();

        int apariciones = (int) this.palabraClave.chars()
                .filter(caracter -> caracter == letra)
                .count();

        if (apariciones == 0) this.actualizarJuego();
        if (!this.letrasUsadas.contiene(letra)) this.letrasUsadas.agregar(letra);

        return apariciones;
    }

    /**
     * post: Indica si la palabra dada es la palabra clave. Actualiza el estado
     * del juego en cualquier caso:
     * - Si es correcta, GANADO.
     * - Si es incorrecta, PERDIDO.
     * @param palabra: palabra arriesgada.
     * @return verdadero si la palabra arriesgada es la palabra clave.
     * pre: La palabra debe ser not null.
     */
    public boolean arriesgarPalabra(String palabra) {
        Validaciones.validarNotNull(palabra, "'palabra' debe ser not null");
        validarJuego();

        if (this.palabraClave.equalsIgnoreCase(palabra)) {
            this.setEstadoDeJuego(EstadoDeJuego.GANADO);
            return true;
        }
        this.setEstadoDeJuego(EstadoDeJuego.PERDIDO);
        return false;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------

    /**
     * post: Indica si el juego está terminado (GANADO o PERDIDO).
     * @return verdadero si el juego está terminado.
     */
    public boolean estaTerminado() {
        return this.estadoDeJuego == EstadoDeJuego.GANADO ||
                this.estadoDeJuego == EstadoDeJuego.PERDIDO;
    }

    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES ----------

    /**
     * post: Devuelve el estado del juego.
     * @return el estado del juego.
     */
    public EstadoDeJuego getEstadoDeJuego() {
        return estadoDeJuego;
    }

    /**
     * post: Devuelve la cantidad de errores cometidos por el jugador.
     * @return la cantidad de errores cometidos.
     */
    public int getCantidadErroresCometidos() {
        return cantidadErroresCometidos;
    }

    /**
     * post: Devuelve la cantidad de errores permitidos por el juego.
     * @return la cantidad de errores permitidos.
     */
    public int getCantidadErroresPermitidos() {
        return cantidadErroresPermitidos;
    }

    /**
     * post: Devuelve un vector con las letras usadas por el jugador.
     * @return las letras usadas por el jugador.
     */
    public Vector<Character> getLetrasUsadas() {
        Vector<Character> copia = new Vector<>();
        Iterador<Character> it = this.letrasUsadas.iterador();
        while (it.haySiguiente()) {
            copia.agregar(it.verActual());
            it.siguiente();
        }
        return copia;
    }

    //MÉTODOS PRIVADOS ----------

    private void validarJuego() {
        if (this.estaTerminado()) {
            throw new RuntimeException("JUEGO TERMINADO");
        }
    }

    /**
     * post: Valida si el carácter dado es una letra (incluye mayúsculas,
     * minúsculas y caracteres acentuados de Unicode).
     * @param caracter El carácter a validar.
     * @throws RuntimeException Si el carácter no es considerado una letra.
     */
    private void validarLetra(char caracter) {
        if (!Character.isLetter(caracter)) {
            throw new RuntimeException("El caracter dado no es una letra.");
        }
    }

    /**
     * post: Aumenta la cantidad de errores cometidos y cambia el estado
     * del juego a PERDIDO si se alcanzan el límite de errores permitidos.
     */
    private void actualizarJuego() {
        this.cantidadErroresCometidos++;
        if (this.cantidadErroresCometidos >= this.cantidadErroresPermitidos) {
            this.estadoDeJuego = EstadoDeJuego.PERDIDO;
        }
    }

    private void setEstadoDeJuego(EstadoDeJuego estadoDeJuego) {
        this.estadoDeJuego = estadoDeJuego;
    }
}
