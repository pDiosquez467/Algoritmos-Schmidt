package examenes.parciales.diciembre2022.resto;

import validaciones.Validaciones;

import java.util.Objects;

public class Mesa {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private final int numero;
    private EstadoMesa estadoMesa;
    private double totalPropina;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa la mesa con el número dado, libre y sin propinas.
     * @param numero: número de mesa.
     */
    public Mesa(int numero) {
        Validaciones.validarNumeroMayorOIgualACero(numero, "'numero'");
        this.numero       = numero;
        this.estadoMesa   = EstadoMesa.LIBRE;
        this.totalPropina = 0;
    }

    /**
     * post: Copia el estado de la mesa dada por parámetro en una
     * nueva instancia.
     * @param mesa: Mesa.
     */
    public Mesa(Mesa mesa) {
        this.numero       = mesa.numero;
        this.estadoMesa   = mesa.estadoMesa;
        this.totalPropina = mesa.totalPropina();
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Mesa mesa)) return false;
        return numero == mesa.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numero);
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Recauda la propina dada.
     * @param propina: propina dada por un cliente.
     */
    public void recaudarPropina(double propina) {
        Validaciones.validarNumeroMayorACero(propina, "'propina'");
        this.totalPropina += propina;
    }

    /**
     * post: Modifica el estado de la mesa.
     * @param estadoMesa: nuevo estado de la mesa.
     * @return la mesa con el estado actualizado.
     */
    public Mesa setEstadoMesa(EstadoMesa estadoMesa) {
        this.estadoMesa = estadoMesa;
        return this;
    }

    /**
     * post: Indica si la mesa está libre.
     * @return verdadero si la mesa está libre.
     */
    public boolean estaLibre() {
        return this.estadoMesa.equals(EstadoMesa.LIBRE);
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve el número de mesa.
     * @return el número de mesa.
     */
    public int numero() {
        return numero;
    }

    /**
     * post: Devuelve el estado de la mesa.
     * @return el estado de la mesa.
     */
    public EstadoMesa estadoMesa() {
        return estadoMesa;
    }

    /**
     * post: Devuelve el total de propinas de la mesa.
     * @return el total de propinas.
     */
    public double totalPropina() {
        return totalPropina;
    }

    /**
     * Valida el estado de la mesa.
     */
    public void validarEstadoOcupada() {
        if (this.estaLibre()) {
            throw new RuntimeException("Mesa LIBRE");
        }
    }
}
