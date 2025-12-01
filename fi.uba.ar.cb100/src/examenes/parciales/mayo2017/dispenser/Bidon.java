package examenes.parciales.mayo2017.dispenser;

import validaciones.Validaciones;

public class Bidon {
//INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private double litros;
    private final double cantidadMaximaDeAgua;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    public Bidon(double litros) {
        Validaciones.validarNumeroMayorACero(litros, "litros");
        this.litros = litros;
        this.cantidadMaximaDeAgua = litros;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    public void usar(double litros) {
        Validaciones.validarNumeroMayorACero(litros, "litros");
        this.litros -= Math.max(0, this.litros - litros);
    }

    public boolean estaVacio() {
        return this.litros == 0;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    public double getLitros() {
        return litros;
    }

    public double getCantidadMaximaDeAgua() {
        return cantidadMaximaDeAgua;
    }
}
