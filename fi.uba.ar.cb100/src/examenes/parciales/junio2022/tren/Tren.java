package examenes.parciales.junio2022.tren;

import validaciones.Validaciones;

import java.util.function.Function;

public class Tren {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final Vagon[] vagones;
    private int indiceVagonActual;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa el tren con la cantidad máxima de vagones indicada.
     * @param cantidadDeVagones: la máxima cantidad de vagones que puede tener el tren.
     * @throws RuntimeException si la cantidad de vagones dada es menor o igual a cero.
     */
    public Tren(int cantidadDeVagones) {
        Validaciones.validarNumeroMayorACero(cantidadDeVagones, "cantidadDeVagones");
        this.vagones = new Vagon[cantidadDeVagones];
        this.indiceVagonActual = 0;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Agrega un nuevo vagón al tren en el último lugar.
     * @param vagon: el nuevo vagón a agregar.
     * @throws RuntimeException si no hay más lugar para añadir vagones.
     */
    public void agregarVagon(Vagon vagon) {
        Validaciones.validarNotNull(vagon, "vagón");
        this.validarAdicionDeVagon();
        this.vagones[indiceVagonActual++] = vagon;
    }

    /**
     * post: Quita el último vagón del tren.
     * @throws RuntimeException si el tren está vacío.
     */
    public void quitarVagon() {
        this.validarNoVacio();
        this.vagones[--indiceVagonActual] = null;
    }

    /**
     * post: Indica si es posible agregar un nuevo vagón al tren.
     * @return verdadero si la cantidad máxima de vagones no ha sido alcanzada.
     */
    public boolean esPosibleAgregarVagon() {
        return this.indiceVagonActual < this.vagones.length;
    }

    /**
     * post: Indica si el tren está vacío.
     * @return verdadero si el tren está vacío.
     */
    public boolean estaVacio() {
        return this.indiceVagonActual == 0;
    }

    /**
     * post: Agrega un nuevo pasajero al tren.
     * @throws RuntimeException si el tren no tiene más lugares disponibles.
     */
    public void agregarPasajero() {
        for (int i = 0; i < indiceVagonActual; i++) {
            Vagon vagon = this.vagones[i];
            if (vagon.hayLugarDisponible()) {
                vagon.agregarPasajero();
            }
        }
        throw new RuntimeException("No hay lugar disponible en el tren.");
    }

    /**
     * post: Quita un pasajero del tren.
     * @throws RuntimeException si el tren no tiene pasajeros.
     */
    public void quitarPasajero() {
        for (int i = 0; i < indiceVagonActual; i++) {
            Vagon vagon = this.vagones[i];
            if (vagon.tienePasajeros()) {
                vagon.quitarPasajero();
            }
        }
        throw new RuntimeException("El tren NO tiene pasajeros");
    }

    /**
     * post: Agrega la carga dada al tren.
     * @param nuevaCarga: la nueva carga a agregar.
     * @throws RuntimeException si la nueva carga no puede ser soportada por ningún vagón del tren.
     */
    public void agregarCarga(double nuevaCarga) {
        Validaciones.validarNumeroMayorACero(nuevaCarga, "nuevaCarga");
        for (int i = 0; i < indiceVagonActual; i++) {
            if (this.vagones[i].puedeLlevarLaCarga(nuevaCarga)) {
                this.vagones[i].agregarCarga(nuevaCarga);
                return;
            }
        }
        throw new RuntimeException("Ningún vagón soporta tanta carga");
    }

    /**
     * post: Quita la carga indicada del tren.
     * @param carga: la carga a remover.
     * @throws RuntimeException si ningún vagón del tren contiene la suficiente carga como para
     * remover lo indicado.
     */
    public void quitarCarga(double carga) {
        Validaciones.validarNumeroMayorACero(carga, "carga");
        for (int i = 0; i < indiceVagonActual; i++) {
            if (this.vagones[i].tieneSuficienteCarga(carga)) {
                this.vagones[i].quitarCarga(carga);
                return;
            }
        }
        throw new RuntimeException("Ningún vagón tiene suficiente carga para quitar.");
    }

    /**
     * post: Devuelve la longitud total del tren.
     * @return la longitud total del tren.
     */
    public double longitudTotal() {
        return this.obtenerTotal(Vagon::longitud);
    }

    /**
     * post: Devuelve la cantidad máxima total de pasajeros que puede albergar el tren.
     * @return la cantidad máxima de pasajeros del tren.
     */
    public int cantidadMaximaDePasajeros() {
        return (int) this.obtenerTotal(vagon -> (double) vagon.cantidadMaximaDePasajeros());
    }

    /**
     * post: Devuelve la carga máxima que el tren puede llevar.
     * @return la carga máxima total.
     */
    public double cargaMaximaTotal() {
        return this.obtenerTotal(Vagon::cargaMaxima);
    }

    /**
     * post: Devuelve la cantidad de pasajeros restantes que el tren todavía puede llevar.
     * @return la cantidad restante de pasajeros que puede albergar.
     */
    public int cantidadDePasajerosRestantes() {
        return this.cantidadMaximaDePasajeros() - this.cantidadDePasajeros();
    }

    /**
     * post: Devuelve cuánta carga todavía puede llevar el tren.
     * @return la cantidad de carga restante.
     */
    public double cantidadDeCargaRestante() {
        return this.cargaMaximaTotal() - this.cargarActual();
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------

    /**
     * post: Valida que el tren no esté vacío.
     */
    private void validarNoVacio() {
        if (!this.estaVacio()) {
            throw new RuntimeException("El tren no tiene vagones");
        }
    }

    /**
     * post: Valida que se pueda agregar un nuevo vagón al tren.
     */
    private void validarAdicionDeVagon() {
        if (!this.esPosibleAgregarVagon()) {
            throw new RuntimeException("NO se puede agregar el vagón");
        }
    }

    /**
     * post: Devuelve la carga actual que lleva el tren.
     */
    private double cargarActual() {
        return this.obtenerTotal(Vagon::cargaActual);
    }

    /**
     * post: Devuelve la cantidad de pasajeros actual del tren.
     * @return la cantidad actual de pasajeros.
     */
    private int cantidadDePasajeros() {
        return (int) this.obtenerTotal(vagon -> (double) vagon.cantidadDePasajeros());
    }

    /**
     * post: Devuelve la suma de una característica numérica específica de todos los vagones actuales del tren.
     * Esta característica se obtiene aplicando la función de mapeo provista a cada vagón.
     *
     * @param f Función de mapeo (Vagon -> Double) que extrae el valor de la característica
     * a totalizar de cada vagón (por ejemplo, longitud, carga máxima, etc.).
     * @return La suma total de la característica aplicada a todos los vagones agregados al tren.
     */
    private double obtenerTotal(Function<Vagon, Double> f) {
        double total = 0.0;
        for (int i = 0; i < indiceVagonActual; i++) {
            total += f.apply(this.vagones[i]);
        }
        return total;
    }

}