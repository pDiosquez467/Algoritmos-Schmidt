package examenes.parciales.julio2022.tren;

import tdas.pila.enlazada.Pila;
import validaciones.Validaciones;

public class Tren {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------

    private final static int NUMERO_MAXIMO_LOCOMOTORAS = 3;
    private final static int NUMERO_MAXIMO_VAGONES_POR_LOCOMOTORA = 10;

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private final Pila<Vagon> vagones;
    private int cantidadLocomotoras;
    private int cantidadMaximaVagones;
    private int cantidadActualDeVagones;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa el tren con 1 locomotora y sin vagones de carga.
     */
    public Tren() {
        this.vagones = new Pila<>();
        this.cantidadLocomotoras   = 1;
        this.cantidadMaximaVagones = this.cantidadLocomotoras *
                NUMERO_MAXIMO_VAGONES_POR_LOCOMOTORA;
        this.cantidadActualDeVagones = 0;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    public void agregarLocomotora() {
        if (this.cantidadLocomotoras == NUMERO_MAXIMO_LOCOMOTORAS) {
            throw new RuntimeException("Capacidad Máxima de locomotoras");
        }
        this.cantidadLocomotoras++;
        this.cantidadMaximaVagones += NUMERO_MAXIMO_VAGONES_POR_LOCOMOTORA;
    }

    /**
     * post: Agrega un vagón al tren cuya carga máxima es la carga dada.
     * @param cargaMaximaVagon: máxima capacidad de carga del vagón.
     */
    public void agregarVagon(double cargaMaximaVagon) {
        Validaciones.validarNumeroMayorACero(cargaMaximaVagon, "cargaMaximaVagón");
        if (this.cantidadActualDeVagones == cantidadMaximaVagones) {
            throw new RuntimeException("Cantidad de vagones máxima");
        }

        this.vagones.apilar(new Vagon(cargaMaximaVagon));
        this.cantidadActualDeVagones++;
    }

    /**
     * post: Quita el último vagón del tren, siempre que esté vacío.
     * @throws RuntimeException si el tren no tiene vagones que quitar
     * o el último vagón no está vacío.
     */
    public Vagon quitarVagon() {
        if (this.getCantidadDeVagones() == 0) {
            throw new RuntimeException("No hay vagones en el tren");
        }
        if (!this.vagones.verTope().estaVacio()) {
            throw new RuntimeException("Último vagón no vacío");
        }

        this.cantidadActualDeVagones--;
        return this.vagones.desapilar();
    }

    /**
     * post: Agrega la carga dada al tren, repartiéndola equitativamente
     * en todos los vagones.
     * @param carga: la carga que debe cargar el tren.
     */
    public void agregarCarga(double carga) {
        if (this.getCantidadDeVagones() == 0) {
            throw new RuntimeException("Tren vacío");
        }
        double cargaPorVagon = carga / this.getCantidadDeVagones();

        Pila<Vagon> temp = new Pila<>();

        while (!this.vagones.estaVacia()) {
            Vagon vagon = this.vagones.desapilar();
            vagon.agregarCarga(cargaPorVagon);
            temp.apilar(vagon);
        }

        while (!temp.estaVacia()) {
            this.vagones.apilar(temp.desapilar());
        }
    }

    /**
     * post: Quita del tren la carga dada, repartiendo la quita equitativamente
     * entre los vagones.
     * @param carga: la carga a quitar.
     */
    public void quitarCarga(double carga) {
        if (this.getCantidadDeVagones() == 0) {
            throw new RuntimeException("Tren vacío");
        }
        double cargaPorVagon = carga / this.getCantidadDeVagones();

        Pila<Vagon> temp = new Pila<>();
        while (!this.vagones.estaVacia()) {
            Vagon vagon = this.vagones.desapilar();
            vagon.quitarCarga(cargaPorVagon);
            temp.apilar(vagon);
        }

        while (!temp.estaVacia()) {
            this.vagones.apilar(temp.desapilar());
        }
    }

    /**
     * post: Devuelve la carga máxima del tren.
     * @return la carga máxima que el tren es capaz de llevar.
     */
    public double getCargaMaxima() {
        double total = 0;
        Pila<Vagon> temp = new Pila<>();
        while (!this.vagones.estaVacia()) {
            Vagon vagon = this.vagones.desapilar();
            total += vagon.cargaMaxima();

            temp.apilar(vagon);
        }

        while (!temp.estaVacia()) {
            this.vagones.apilar(temp.desapilar());
        }
        return total;
    }

    /**
     * post: Devuelve la carga actual del tren.
     * @return la carga actual.
     */
    public double getCargaActual() {
        double total = 0;
        Pila<Vagon> temp = new Pila<>();
        while (!this.vagones.estaVacia()) {
            Vagon vagon = this.vagones.desapilar();
            total += vagon.cargaActual();

            temp.apilar(vagon);
        }

        while (!temp.estaVacia()) {
            this.vagones.apilar(temp.desapilar());
        }
        return total;
    }

    /**
     * post: Devuelve la capacidad restante de carga del tren.
     * @return la capacidad de carga restante.
     */
    public double getCapacidadRestante() {
        return this.getCargaMaxima() - this.getCargaActual();
    }

    /**
     * post: Devuelve la cantidad actual de vagones del tren.
     * @return la cantidad de vagones.
     */
    public int getCantidadDeVagones() {
        return this.cantidadActualDeVagones;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve la cantidad actual de locomotoras del tren.
     * @return la cantidad actual de locomotoras.
     */
    public int cantidadLocomotoras() {
        return cantidadLocomotoras;
    }

    /**
     * post: Devuelve la cantidad máxima de vagones que el tren puede
     * tener.
     * @return la cantidad máxima de vagones del tren.
     */
    public int cantidadMaximaVagones() {
        return cantidadMaximaVagones;
    }

}
