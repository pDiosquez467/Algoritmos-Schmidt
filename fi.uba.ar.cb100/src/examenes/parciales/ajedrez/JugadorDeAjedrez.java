package examenes.parciales.ajedrez;

import java.util.Objects;

public class JugadorDeAjedrez {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final int numeroDeJugador;
    private int puntajeTotal;
    private final PartidaDeAjedrez[] partidasConBlancas;
    private final PartidaDeAjedrez[] partidasConNegras;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    public JugadorDeAjedrez(int numeroDeJugador, int cantidadPartidas) {
        this.numeroDeJugador    = numeroDeJugador;
        this.puntajeTotal       = 0;
        this.partidasConBlancas = new PartidaDeAjedrez[cantidadPartidas];
        this.partidasConNegras  = new PartidaDeAjedrez[cantidadPartidas];

        for (int i = 0; i < cantidadPartidas; i++) {
            this.partidasConBlancas[i] = new PartidaDeAjedrez(i);
            this.partidasConBlancas[i].setJugadorBlancas(this);
        }

        for (int i = 0; i < cantidadPartidas; i++) {
            this.partidasConNegras[i] = new PartidaDeAjedrez(i);
            this.partidasConBlancas[i].setJugadorNegras(this);
        }
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof JugadorDeAjedrez that)) return false;
        return numeroDeJugador == that.numeroDeJugador;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroDeJugador);
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    public void actualizarPuntaje(int puntaje) {
        this.puntajeTotal += puntaje;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES ----------

    public int numero() {
        return numeroDeJugador;
    }

    public int puntajeTotal() {
        return puntajeTotal;
    }

    public PartidaDeAjedrez[] partidasConBlancas() {
        return partidasConBlancas;
    }

    public PartidaDeAjedrez[] partidasConNegras() {
        return partidasConNegras;
    }
}
