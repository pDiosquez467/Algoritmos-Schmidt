package examenes.parciales.noviembre2024.tablero;

import validaciones.Validaciones;

public class TableroDeAjedrez {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

    private Resultado[][] tablero;

    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa el tablero de ajedrez a partir de la cantidad de
     * jugadores dada.
     * pre: La cantidad de jugadores debe ser mayor a cero.
     * @param cantidadDeJugadores: cantidad de jugadores del torneo.
     */
    public TableroDeAjedrez(int cantidadDeJugadores) {
        Validaciones.validarNumeroMayorACero(cantidadDeJugadores,
                "cantidadDeJugadores");
        this.inicializarTablero(cantidadDeJugadores);
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero[0].length; j++) {
                if (i == j) {
                    res.append("*");
                } else {
                    res.append("(").append(i).append(", ").append(j).append("): ").append(this.tablero[i][j]);
                }
            }
        }
        return res.toString();
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Indica si el torneo está terminado.
     * @return verdadero si el torneo está finalizado.
     */
    public boolean estaFinalizadoElTorneo() {
        for (int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero[0].length; j++) {
                if (i == j) continue;
                if (tablero[i][j] == null) return false;
            }
        }
        return true;
    }

    /**
     * post: Devuelve el puntaje del jugador cuyo número es dado.
     * @param numeroJugador: número del jugador
     * @return el puntaje del jugador.
     * @throws RuntimeException si el número del jugador es inválido.
     */
    public int puntajeJugador(int numeroJugador) {
        Validaciones.validarNumeroEntre(numeroJugador, 1, this.tablero.length,
                "'numeroJugador'");
        int indiceJugador = numeroJugador - 1;
        int total = 0;
        for (int i = 0; i < this.tablero.length; i++) {
            if (i == indiceJugador) continue;

            Resultado resultadoBlancas = this.tablero[indiceJugador][i];
            total += this.getPuntajeBlancas(resultadoBlancas);

            Resultado resultadoNegras  = this.tablero[i][indiceJugador];
            total += this.getPuntajeNegras(resultadoNegras);
        }
        return total;
    }

    /**
     * post: Devuelve el número del jugador ganador del torneo, si el torneo
     * está terminado.
     * @return el número del ganador.
     * @throws RuntimeException si el torneo no está terminado.
     */
    public int obtenerJugadorGanador() {
        this.validarEstadoDelTorneoFinalizado();
        int jugadorGanador = 1;
        int puntajeGanador = this.puntajeJugador(jugadorGanador);

        for (int i = 2; i <= this.tablero.length ; i++) {
            int puntajeActual = this.puntajeJugador(i);
            if (puntajeActual > puntajeGanador) {
                jugadorGanador = i;
                puntajeGanador = puntajeActual;
            }
        }
        return jugadorGanador;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve el tablero de resultados del torneo.
     * @return el tablero de resultados.
     */
    public Resultado[][] tablero() {
        return tablero;
    }

    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------

    /**
     * post: Inicializa el tablero de resultados.
     * @param cantidadDeJugadores la cantidad de jugadores del torneo.
     */
    private void inicializarTablero(int cantidadDeJugadores) {
        this.tablero = new Resultado[cantidadDeJugadores][cantidadDeJugadores];
        for (int i = 0; i < cantidadDeJugadores; i++) {
            for (int j = 0; j < cantidadDeJugadores; j++) {
                this.tablero[i][j] = null;
            }
        }
    }

    /**
     * post: Devuelve el puntaje del jugador con piezas blancas correspondiente,
     * según el resultado.
     * @param resultado: el resultado de la partida.
     * @return el puntaje del jugador con blancas.
     */
    private int getPuntajeBlancas(Resultado resultado) {
        return switch (resultado) {
            case GANAN_BLANCAS -> 3;
            case TABLAS -> 1;
            case ANULADO -> -1;
            default -> 0;
        };
    }

    /**
     * post: Devuelve el puntaje del jugador con piezas negras correspondiente,
     * según el resultado.
     * @param resultado: el resultado de la partida.
     * @return el puntaje del jugador con negras.
     */
    private int getPuntajeNegras(Resultado resultado) {
        return switch (resultado) {
            case GANAN_NEGRAS -> 3;
            case TABLAS -> 1;
            case ANULADO -> -1;
            default -> 0;
        };
    }

    /**
     * post: Determina si el torneo está terminado.
     */
    private void validarEstadoDelTorneoFinalizado() {
        if (!this.estaFinalizadoElTorneo()) {
            throw new RuntimeException("El torneo no está terminado");
        }
    }
}