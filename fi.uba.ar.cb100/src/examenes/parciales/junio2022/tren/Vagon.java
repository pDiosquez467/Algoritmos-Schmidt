package examenes.parciales.junio2022.tren;

import validaciones.Validaciones;

public class Vagon {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private double cargaMaxima;
    private int cantidadMaximaDePasajeros;
    private double longitud;
    private double cargaActual;
    private int cantidadDePasajerosActuales;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa el vagón con la carga máxima, la cantidad máxima de pasajeros y la longitud dadas.
     * @param cargaMaxima: la carga máxima que el vagón puede soportar.
     * @param cantidadMaximaDePasajeros: la cantidad máxima de pasajeros que el vagón puede llevar.
     * @param longitud: la longitud del vagón.
     */
    public Vagon(double cargaMaxima, int cantidadMaximaDePasajeros, double longitud) {
        this.setCargaMaxima(cargaMaxima);
        this.setCantidadMaximaDePasajeros(cantidadMaximaDePasajeros);
        this.setLongitud(longitud);
        this.cargaActual = 0;
        this.cantidadDePasajerosActuales = 0;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Agrega un nuevo pasajero al vagón.
     * @throws RuntimeException si la cantidad máxima de pasajeros ha sido alcanzada.
     */
    public void agregarPasajero() {
        this.validarDisponibilidadDeLugar();
        this.cantidadDePasajerosActuales++;
    }

    /**
     * post: Quita un pasajero del vagón.
     * @throws RuntimeException si no hay pasajeros en el vagón.
     */
    public void quitarPasajero() {
        this.validarVagonConAlMenosUnPasajero();
        this.cantidadDePasajerosActuales--;
    }

    /**
     * post: Indica si el vagón tiene al menos un pasajero.
     * @return verdadero si el vagón tiene pasajeros.
     */
    public boolean tienePasajeros() {
        return this.cantidadDePasajerosActuales > 0;
    }

    /**
     * post: Indica si el vagón lleva carga.
     * @return verdadero si el vagón lleva una carga.
     */
    public boolean estaCargado() {
        return this.cargaActual > 0;
    }

    /**
     * post: Agrega al vagón la nueva carga dada.
     * @param nuevaCarga: la nueva carga dada.
     * @throws RuntimeException si la carga dada no puede ser soportada por el vagón.
     */
    public void agregarCarga(double nuevaCarga) {
        Validaciones.validarNumeroMayorACero(nuevaCarga, "nuevaCarga");
        this.validarIngresoDeCarga(nuevaCarga);
        this.cargaActual += nuevaCarga;
    }

    /**
     * post: Quita la carga dada del vagón.
     * @param carga: la carga a remover del vagón.
     * @throws RuntimeException si la cantidad de carga llevada por el vagón es menor que la
     * carga a quitar.
     */
    public void quitarCarga(double carga) {
        Validaciones.validarNumeroMayorACero(carga, "nuevaCarga");
        this.validarQuitaDeCarga(carga);
        this.cargaActual -= carga;
    }

    /**
     * post: Vacia el vagón.
     * @throws RuntimeException si el vagón ya está vacío.
     */
    public void vaciar() {
        this.validarVagonConCarga();
        this.cargaActual = 0;
    }

    /**
     * post: Indica si el vagón tiene al menos un lugar disponible para llevar un pasajero.
     * @return verdadero si el vagón tiene lugar disponible.
     */
    public boolean hayLugarDisponible() {
        return this.cantidadDePasajerosActuales < this.cantidadMaximaDePasajeros;
    }

    /**
     * post: Indica si el vagón puede llevar la carga dada.
     * @param nuevaCarga: la nueva carga ha ser llevada.
     * @return verdadero si el vagón soporta la nueva carga.
     */
    public boolean puedeLlevarLaCarga(double nuevaCarga) {
        Validaciones.validarNumeroMayorACero(nuevaCarga, "nuevaCarga");
        return this.cargaActual + nuevaCarga <= this.cargaMaxima;
    }

    /**
     * post: Indica si el vagón tiene al menos la carga dada.
     * @param carga: la carga a ser evaluada.
     * @return verdadero si el vagón tiene al menos la carga dada.
     */
    public boolean tieneSuficienteCarga(double carga) {
        Validaciones.validarNumeroMayorACero(carga, "carga");
        return this.cargaActual - carga >= 0;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve la capacidad máxima de carga.
     * @return la carga máxima que el vagón puede llevar.
     */
    public double cargaMaxima() {
        return cargaMaxima;
    }

    /**
     * post: Devuelve la longitud del vagón.
     * @return la longitud del vagón.
     */
    public double longitud() {
        return longitud;
    }

    /**
     * post: Devuelve la carga actual que lleva el vagón.
     * @return la carga actual.
     */
    public double cargaActual() {
        return cargaActual;
    }

    /**
     * post: Devuelve la cantidad de pasajeros que lleva el vagón actualmente.
     * @return la cantidad de pasajeros actuales.
     */
    public int cantidadDePasajeros() {
        return cantidadDePasajerosActuales;
    }

    /**
     * post: Devuelve la cantidad máxima de pasajeros que el vagón puede llevar.
     * @return la cantidad máxima de pasajeros que el vagón puede llevar.
     */
    public int cantidadMaximaDePasajeros() {
        return cantidadMaximaDePasajeros;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

    private void setCargaMaxima(double cargaMaxima) {
        Validaciones.validarNumeroMayorACero(cargaMaxima, "capacidadMaximaDeCarga");
        this.cargaMaxima = cargaMaxima;
    }

    private void setCantidadMaximaDePasajeros(int cantidadMaximaDePasajeros) {
        Validaciones.validarNumeroMayorACero(cantidadMaximaDePasajeros, "capacidadMaximaDePasajeros")
        ;this.cantidadMaximaDePasajeros = cantidadMaximaDePasajeros;
    }

    private void setLongitud(double longitud) {
        Validaciones.validarNumeroMayorACero(longitud, "longitud");
        this.longitud = longitud;
    }

    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------

    /**
     * post: Valida que el vagón pueda recibir un nuevo pasajero.
     */
    private void validarDisponibilidadDeLugar() {
        if (this.cantidadDePasajerosActuales == this.cantidadMaximaDePasajeros) {
            throw new RuntimeException("Cantidad máxima de pasajeros alcanzada");
        }
    }

    /**
     * post: Valida que haya al menos un pasajero en el vagón.
     */
    private void validarVagonConAlMenosUnPasajero() {
        if (!this.tienePasajeros()) {
            throw new RuntimeException("NO hay pasajeros en el vagón");
        }
    }

    /**
     * post: Valida que el vagón pueda llevar la carga dada.
      * @param nuevaCarga: la nueva carga a llevar.
     */
    private void validarIngresoDeCarga(double nuevaCarga) {
        if (this.cargaActual + nuevaCarga > this.cargaMaxima) {
            throw new RuntimeException("Capacidad máxima de carga excedida");
        }
    }

    /**
     * post: Valida que en el vagón haya suficiente carga como la dada para ser removida.
     * @param carga: la carga a ser removida.
     */
    private void validarQuitaDeCarga(double carga) {
        if (this.cargaActual - carga < 0) {
            throw new RuntimeException("No hay suficiente carga en el vagón");
        }
    }

    /**
     * post: Valida que el vagón tenga carga (ie, no esté vacío).
     */
    private void validarVagonConCarga() {
        if (!this.estaCargado()) {
            throw new RuntimeException("No hay carga");
        }
    }

}
