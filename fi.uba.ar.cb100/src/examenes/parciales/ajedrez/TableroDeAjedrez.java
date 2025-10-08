package examenes.parciales.ajedrez;

public class TableroDeAjedrez {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private JugadorDeAjedrez[] jugadores;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    public TableroDeAjedrez(int cantidadDeJugadores) {
        this.jugadores = new JugadorDeAjedrez[cantidadDeJugadores];
        for (int i = 0; i < cantidadDeJugadores; i++) {
            this.jugadores[i] = new JugadorDeAjedrez(i+1, cantidadDeJugadores - 1);
        }
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    public void anularPartida(PartidaDeAjedrez partidaDeAjedrez, Juez juez) {
        // TODO: Validar que la partida sea una del torneo.

    }

    public boolean estaTerminado() {
        return false;
    }

    public int puntajeDeJugador(int numeroDeJugador) {
        return this.jugadores[numeroDeJugador - 1].puntajeTotal();
    }

    public JugadorDeAjedrez obtenerGanador() {
        if (!this.estaTerminado()) {
            throw new RuntimeException("El torneo no está finalizado");
        }

        JugadorDeAjedrez ganador = null;
        for (JugadorDeAjedrez jugadorDeAjedrez: this.jugadores) {
            if ((ganador == null) ||
            jugadorDeAjedrez.puntajeTotal() > ganador.puntajeTotal()) {
                ganador = jugadorDeAjedrez;
            }
        }
        return ganador;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
}
