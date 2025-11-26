package examenes.parciales.octubre2023.canales;

import validaciones.Validaciones;

import java.util.Objects;

public class Canal {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final int numero;
    private EstadoDelCanal estadoDelCanal;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    public Canal(int numero) {
        Validaciones.validarNumeroMayorACero(numero, "numero");
        this.numero = numero;
        this.estadoDelCanal = EstadoDelCanal.ACTIVO;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Canal canal)) return false;
        return numero == canal.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numero);
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    public boolean estaActivo() {
        return this.estadoDelCanal.equals(EstadoDelCanal.ACTIVO);
    }

    public void activar() {
        this.estadoDelCanal = EstadoDelCanal.ACTIVO;
    }

    public void desactivar() {
        this.estadoDelCanal = EstadoDelCanal.INACTIVO;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    public int getNumero() {
        return numero;
    }
}
