package examenes.parciales.mayo2017.dispenser;

import validaciones.Validaciones;

public class DispenserDeAgua {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final double caudalDeAgua;
    private Bidon bidon;
    private int usos;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    public DispenserDeAgua(double caudalDeAgua) {
        Validaciones.validarNumeroMayorACero(caudalDeAgua, "caudalDeAgua");
        this.caudalDeAgua = caudalDeAgua;
        this.bidon = null;
        this.usos  = 0;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    public void agregar(Bidon bidon) {
        Validaciones.validarNotNull(bidon, "bidón");
        this.bidon = bidon;
    }

    public void retirar() {
        if (!this.bidon.estaVacio()) {
            throw new RuntimeException("El bidón actual NO está vacío");
        }
        this.bidon = null;
    }

    public double cantidadDeAguaDisponible() {
        this.validarEstadoOcupado();
        return this.bidon.getLitros();
    }

    public boolean estaDesocupado() {
        return this.bidon == null;
    }

    public void accionarCanilla(double segundos) {
        this.validarEstadoOcupado();
        double totalAguaEnLitros = (this.caudalDeAgua * segundos) / 1000;
        this.bidon.usar(totalAguaEnLitros);
        this.usos++;
    }

    public double promedioDeAguaEntregadaPorVez() {
        validarEstadoOcupado();
        double totalAguaUsada = this.bidon.getCantidadMaximaDeAgua() - this.cantidadDeAguaDisponible();
        return (usos == 0) ? 0 : totalAguaUsada / usos;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------

    private void validarEstadoOcupado() {
        if (this.estaDesocupado()) {
            throw new RuntimeException("NO hay bidón");
        }
    }
}
