package examenes.parciales.resto;

import validaciones.Validaciones;

import java.util.Arrays;
import java.util.Collections;

public class Resto {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private MesaDeResto[] mesas;
    private double maximaPropinaRegistrada;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    public Resto(int cantidadDeMesas) {
        Validaciones.validarNumeroMayorACero(cantidadDeMesas, "cantidadDeMesas");
        this.inicializarMesasDeResto(cantidadDeMesas);
        this.maximaPropinaRegistrada = 0;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /*
    +solicitarMesa(): int
    +cerrarMesa(double propina): int
    +mesaConMasPropina(): int
    +totalPropinas(): double
    * */

    public int solicitarMesa() {
        for (MesaDeResto mesaDeResto : this.mesas) {
            if (mesaDeResto.estaLibre()) {
                mesaDeResto.ocupar();
                return mesaDeResto.numero();
            }
        }
        throw new RuntimeException("No hay mesas disponibles");
    }

    public int cerrarMesa(int numeroDeMesa, double propina) {
        this.validarNumeroDeMesa(numeroDeMesa);
        Validaciones.validarNumeroMayorACero(propina, "propina");
        MesaDeResto mesaDeResto = this.mesas[numeroDeMesa-1];
        this.actualizarMaximaPropina(propina);
        return mesaDeResto.cerrar(propina);
    }

    public int mesaConMasPropina() {
        MesaDeResto mesaConMasPropina = null;
        for (MesaDeResto mesaDeResto: this.mesas) {
            if ((mesaConMasPropina == null) ||
                    (mesaDeResto.propinaAcumulada() > mesaConMasPropina.propinaAcumulada())) {
                mesaConMasPropina = mesaDeResto;
            }
        }
        return (mesaConMasPropina == null) ? 0 : mesaConMasPropina.numero();
    }

    public double totalPropinas() {
        double total = 0.0;
        for (MesaDeResto mesaDeResto: this.mesas) {
            total += mesaDeResto.propinaAcumulada();
        }
        return total;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    public MesaDeResto[] mesas() {
        return null;
    }

    public double maximaPropinaRegistrada() {
        return maximaPropinaRegistrada;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------

    private void inicializarMesasDeResto(int cantidadDeMesas) {
        this.mesas = new MesaDeResto[cantidadDeMesas];
        for (int i = 0; i < this.mesas.length; i++) {
            this.mesas[i] = new MesaDeResto(i+1);
        }
    }

    private void validarNumeroDeMesa(int numeroDeMesa) {
        if ((numeroDeMesa <= 0) || (numeroDeMesa >= this.mesas.length)) {
            throw new RuntimeException("Número de mesa INVÁLIDO");
        }
    }

    private void actualizarMaximaPropina(double propina) {
        if (propina > this.maximaPropinaRegistrada) {
            this.maximaPropinaRegistrada = propina;
        }
    }

}
