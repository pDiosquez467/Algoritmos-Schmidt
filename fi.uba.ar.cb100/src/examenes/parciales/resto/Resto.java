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

    /**
     * post: Inicializa un resto con la cantidad de mesas indicada.
     * @param cantidadDeMesas: la cantidad de mesas en el resto.
     * @throws RuntimeException si la cantidad dada es menor o igual a cero.
     */
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

    /**
     * post: Solicita una mesa LIBRE en el restaurant para ocuparla.
     * @throws RuntimeException si no hay mesas LIBRES.
     * @return el número de la mesa disponible.
     */
    public int solicitarMesa() {
        for (MesaDeResto mesaDeResto : this.mesas) {
            if (mesaDeResto.estaLibre()) {
                mesaDeResto.ocupar();
                return mesaDeResto.numero();
            }
        }
        throw new RuntimeException("No hay mesas disponibles");
    }

    /**
     * post: Cierra la mesa cuyo número es indicado, otorgándole la propina
     * indicada.
     * @throws RuntimeException si el número de mesa es inválido, o la mesa
     * no está OCUPADA.
     * @param numeroDeMesa: el número de mesa a cerrar.
     * @param propina: la propina dada; debe ser mayor a cero.
     * @return el número de mesa liberada.
     */
    public int cerrarMesa(int numeroDeMesa, double propina) {
        this.validarNumeroDeMesa(numeroDeMesa);
        Validaciones.validarNumeroMayorACero(propina, "propina");
        MesaDeResto mesaDeResto = this.mesas[numeroDeMesa-1];
        this.actualizarMaximaPropina(propina);
        return mesaDeResto.cerrar(propina);
    }

    /**
     * post: Devuelve el número de mesa con más propina acumulada.
     * @return el número de mesa con más propina acumulada.
     */
    public int mesaConMasPropina() {
        MesaDeResto mesaConMasPropina = null;
        for (MesaDeResto mesaDeResto: this.mesas) {
            if ((mesaConMasPropina == null) ||
                    (mesaDeResto.propinaAcumulada() > mesaConMasPropina.propinaAcumulada())) {
                mesaConMasPropina = mesaDeResto;
            }
        }
        return (mesaConMasPropina == null) ? -1 : mesaConMasPropina.numero();
    }

    /**
     * post: Devuelve el total de propinas acumuladas del resto.
     * @return el total de propinas acumulado.
     */
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

    /**
     * post: Devuelve una copia de las mesas del resto.
     * @return un arreglo de las mesas del resto.
     */
    public MesaDeResto[] mesas() {
        MesaDeResto[] copias = new MesaDeResto[this.mesas.length];
        for (int i = 0; i < this.mesas.length; i++) {
            MesaDeResto mesaDeResto = this.mesas[i];
            copias[i] = new MesaDeResto(mesaDeResto);
        }
        return copias;
    }

    /**
     * post: Devuelve la máxima propina registrada en el resto.
     * @return la máxima propina registrada.
     */
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
        if ((numeroDeMesa <= 0) || (numeroDeMesa > this.mesas.length)) {
            throw new RuntimeException("Número de mesa INVÁLIDO");
        }
    }

    private void actualizarMaximaPropina(double propina) {
        if (propina > this.maximaPropinaRegistrada) {
            this.maximaPropinaRegistrada = propina;
        }
    }

}
