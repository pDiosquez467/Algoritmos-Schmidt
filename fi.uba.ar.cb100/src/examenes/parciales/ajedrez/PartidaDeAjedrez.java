package examenes.parciales.ajedrez;

public class PartidaDeAjedrez {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final int numeroDePartida;
    private JugadorDeAjedrez jugadorBlancas;
    private JugadorDeAjedrez jugadorNegras;
    private ResultadoDeLaPartida resultadoDeLaPartida;
    private boolean estaFinalizada;
    private boolean estaAnulada;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    public PartidaDeAjedrez(int numeroDePartida) {
        this.numeroDePartida      = numeroDePartida;
        this.jugadorBlancas       = null;
        this.jugadorNegras        = null;
        this.resultadoDeLaPartida = null;
        this.estaFinalizada       = false;
        this.estaAnulada          = false;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    public int numeroDePartida() {
        return numeroDePartida;
    }

    public JugadorDeAjedrez jugadorBlancas() {
        return jugadorBlancas;
    }

    public JugadorDeAjedrez jugadorNegras() {
        return jugadorNegras;
    }

    public ResultadoDeLaPartida resultadoDeLaPartida() {
        return resultadoDeLaPartida;
    }

    public boolean estaFinalizada() {
        return estaFinalizada;
    }

    public boolean estaAnulada() {
        return estaAnulada;
    }

    public PartidaDeAjedrez setResultadoDeLaPartida(ResultadoDeLaPartida resultadoDeLaPartida) {
        this.resultadoDeLaPartida = resultadoDeLaPartida;
        return this;
    }

    public PartidaDeAjedrez setEstaFinalizada(boolean estaFinalizada) {
        this.estaFinalizada = estaFinalizada;
        return this;
    }

    public void setEstaAnulada(boolean estaAnulada) {
        this.estaAnulada = estaAnulada;
    }

    public PartidaDeAjedrez setJugadorBlancas(JugadorDeAjedrez jugadorBlancas) {
        this.jugadorBlancas = jugadorBlancas;
        return this;
    }

    public PartidaDeAjedrez setJugadorNegras(JugadorDeAjedrez jugadorNegras) {
        this.jugadorNegras = jugadorNegras;
        return this;
    }
}
