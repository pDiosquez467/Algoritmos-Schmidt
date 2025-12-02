package examenes.parciales.noviembre2015.montacarga;

import validaciones.Validaciones;

public class Montacarga {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private final double pesoMaximo;
    private double cargaActual;
    private double distanciaRecorrida;
    private double cargaTotalMovida;
    private int cargasCompletadas;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa un montacargas con el peso máximo dado.
     * @param pesoMaximo: el peso máximo del montacargas.
     */
    public Montacarga(double pesoMaximo) {
        Validaciones.validarNumeroMayorACero(pesoMaximo, "pesoMaximo");
        this.pesoMaximo  = pesoMaximo;
        this.cargaActual = 0;
        this.distanciaRecorrida = 0;
        this.cargaTotalMovida   = 0;
        this.cargasCompletadas  = 0;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Sube al montacargas una carga con el peso indicado.
     * Lanza una excepción si la carga excede el peso máximo permitido.
     * @param carga: la nueva carga.
     */
    public void cargar(double carga) {
        Validaciones.validarNumeroMayorACero(carga, "carga");
        this.validarCarga(carga);
        this.cargaActual += carga;
    }

    /**
     * post: Devuelve el peso (en KG) de la carga que tiene actualmente.
     * @return la carga actual.
     */
    public double obtenerCarga() {
        return this.cargaActual;
    }

    /**
     * post: Mueve el montacargas la distancia (en metros) indicada.
     * @param distancia: la distancia a recorrer.
     */
    public void trasladar(double distancia) {
        Validaciones.validarNumeroMayorACero(distancia, "distancia");
        this.distanciaRecorrida += distancia;
    }

    /**
     * post: Baja del montacargas toda la carga que tiene y devuelve la
     * distancia total que la trasladó.
     * @return la distancia de traslado de la carga.
     */
    public double descargar() {
        this.validarEstadoOcupado();
        double distanciaDeTraslado = this.distanciaRecorrida;
        this.cargaTotalMovida += this.cargaActual;
        this.cargasCompletadas++;
        this.cargaActual        = 0;
        this.distanciaRecorrida = 0;
        return distanciaDeTraslado;
    }

    /**
     * post: Devuelve el peso (en KG) promedio de las cargas completadas
     * (cargadas, trasladadas y descargadas).
     * @return el peso promedio de las cargas completadas.
     */
    public double calcularCargaPromedio() {
        return (this.cargasCompletadas > 0) ?
                this.cargaTotalMovida / this.cargasCompletadas : 0;
    }

    /**
     * post: Indica si el montacargas está desocupado.
     * @return verdadero si el montacargas está desocupado.
     */
    public boolean estaDesocupado() {
        return this.cargaActual == 0;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------

    private void validarCarga(double carga) {
        if (this.cargaActual + carga > this.pesoMaximo) {
            throw new RuntimeException("Peso máximo excedido");
        }
    }

    private void validarEstadoOcupado() {
        if (this.estaDesocupado()) {
            throw new RuntimeException("Montacargas desocupado");
        }
    }
}
