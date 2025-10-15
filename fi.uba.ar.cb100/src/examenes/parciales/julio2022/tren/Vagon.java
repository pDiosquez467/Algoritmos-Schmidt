package examenes.parciales.julio2022.tren;

import validaciones.Validaciones;

public class Vagon {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final double cargaMaxima;
    private double cargaActual;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    public Vagon(double cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
        this.cargaActual = 0;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    public void agregarCarga(double carga) {
        Validaciones.validarNumeroMayorACero(carga, "carga");
        this.cargaActual = Math.min(this.cargaActual + carga,
                this.cargaMaxima);
    }

    public void quitarCarga(double carga) {
        Validaciones.validarNumeroMayorACero(carga, "carga");
        this.cargaActual = Math.max(this.cargaActual - carga, 0);
    }

    public void vaciar() {
        if (this.estaVacio()) {
            throw new RuntimeException("Vagón vacío");
        }
        this.cargaActual = 0;
    }

    public boolean estaVacio() {
        return this.cargaActual == 0;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    public double cargaMaxima() {
        return cargaMaxima;
    }

    public double cargaActual() {
        return cargaActual;
    }
}
