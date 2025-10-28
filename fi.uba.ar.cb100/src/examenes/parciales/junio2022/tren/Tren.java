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

    public void agregarVagon(Vagon vagon) {
        Validaciones.validarNotNull(vagon, "vagón");
        this.validarAdicionDeVagon();
        this.vagones[indiceVagonActual++] = vagon;
    }

    public void quitarVagon() {
        this.validarNoVacio();
        this.vagones[--indiceVagonActual] = null;
    }

    public boolean esPosibleAgregarVagon() {
        return this.indiceVagonActual < this.vagones.length;
    }

    public boolean estaVacio() {
        return this.indiceVagonActual > 0;
    }

    public void agregarPasajero() {
        for (int i = 0; i < indiceVagonActual; i++) {
            if (this.vagones[i].hayLugarDisponible()) {
                this.vagones[i].agregarPasajero();
            }
        }
        throw new RuntimeException("No hay lugar disponible en el tren.");
    }

    public void quitarPasajero() {
        for (int i = 0; i < indiceVagonActual; i++) {
            if (this.vagones[i].tienePasajeros()) {
                this.vagones[i].quitarPasajero();
            }
        }
        throw new RuntimeException("El tren NO tiene pasajeros");
    }

    public void agregarCarga(double nuevaCarga) {
        Validaciones.validarNumeroMayorACero(nuevaCarga, "nuevaCarga");
        for (int i = 0; i < indiceVagonActual; i++) {
            if (this.vagones[i].puedeLlevarLaCarga(nuevaCarga)) {
                this.vagones[i].agregarCarga(nuevaCarga);
            }
        }
        throw new RuntimeException("Ningún vagón soporta tanta carga");
    }

    public void quitarCarga(double carga) {
        Validaciones.validarNumeroMayorACero(carga, "carga");
        for (int i = 0; i < indiceVagonActual; i++) {
            if (this.vagones[i].tieneSuficienteCarga(carga)) {
                this.vagones[i].quitarCarga(carga);
            }
        }
        throw new RuntimeException("Ningún vagón tiene suficiente carga para quitar como la dada");
    }

    public double longitudTotal() {
        return this.obtenerTotal(Vagon::longitud);
    }

    public int capacidadTotalDePasajeros() {
        return (int) this.obtenerTotal(vagon -> (double) vagon.cantidadMaximaDePasajeros());
    }

    public double capacidadTotalDeCarga() {
        return this.obtenerTotal(Vagon::cargaMaxima);
    }

    public int cantidadDePasajerosRestantes() {
        return this.capacidadTotalDePasajeros() - this.cantidadDePasajeros();
    }

    public double capacidadDeCargaRestante() {
        return this.capacidadTotalDeCarga() - this.cargarActual();
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------

    private void validarNoVacio() {
        if (!this.estaVacio()) {
            throw new RuntimeException("El tren no tiene vagones");
        }
    }

    private void validarAdicionDeVagon() {
        if (!this.esPosibleAgregarVagon()) {
            throw new RuntimeException("NO se puede agregar el vagón");
        }
    }

    private double cargarActual() {
        return this.obtenerTotal(Vagon::cargaActual);
    }

    private int cantidadDePasajeros() {
        return (int) this.obtenerTotal(vagon -> (double) vagon.cantidadDePasajeros());
    }

    private double obtenerTotal(Function<Vagon, Double> f) {
        double total = 0.0;
        for (int i = 0; i < indiceVagonActual; i++) {
            total += f.apply(this.vagones[i]);
        }
        return total;
    }

}
