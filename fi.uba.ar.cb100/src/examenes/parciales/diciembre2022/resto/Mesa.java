package examenes.parciales.diciembre2022.resto;

import validaciones.Validaciones;

import java.util.Objects;

public class Mesa {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private final int numeroDeMesa;
    private EstadoMesa estadoMesa;
    private double totalAcumuladoDePropinas;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa la mesa con el número dado, libre y sin propinas.
     * @param numeroDeMesa: número de mesa.
     */
    public Mesa(int numeroDeMesa) {
        Validaciones.validarNumeroMayorOIgualACero(numeroDeMesa, "numeroDeMesa");
        this.numeroDeMesa = numeroDeMesa;
        this.estadoMesa   = EstadoMesa.LIBRE;
        this.totalAcumuladoDePropinas = 0;
    }

    /**
     * post: Copia el estado de la mesa dada por parámetro en una
     * nueva instancia.
     * @param mesa: Mesa.
     */
    public Mesa(Mesa mesa) {
        this.numeroDeMesa = mesa.numeroDeMesa;
        this.estadoMesa   = mesa.estadoMesa;
        this.totalAcumuladoDePropinas = mesa.totalPropina();
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Mesa mesa)) return false;
        return numeroDeMesa == mesa.numeroDeMesa;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroDeMesa);
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "numeroDeMesa=" + numeroDeMesa +
                ", estadoMesa=" + estadoMesa +
                ", totalAcumuladoDePropinas=" + totalAcumuladoDePropinas +
                '}';
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Recauda la propina dada.
     * @param propina: propina dada por un cliente.
     */
    public void recaudarPropina(double propina) {
        Validaciones.validarNumeroMayorACero(propina, "'propina'");
        this.totalAcumuladoDePropinas += propina;
    }

    /**
     * post: Solicita una mesa LIBRE.
     */
    public void solicitar() {
        this.validarEstadoLibre();
        this.estadoMesa = EstadoMesa.OCUPADA;
    }

    /**
     * post: Cierra la mesa, recaudando la propina dada.
     * @param propina: la propina recaudada.
     */
    public void cerrar(double propina) {
        this.validarEstadoOcupada();
        this.recaudarPropina(propina);
        this.estadoMesa = EstadoMesa.LIBRE;
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
    public int getNumeroDeMesa() {
        return numeroDeMesa;
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
        return totalAcumuladoDePropinas;
    }

    /**
     * Valida el estado OCUPADO de la mesa.
     */
    private void validarEstadoOcupada() {
        if (this.estaLibre()) {
            throw new RuntimeException("Mesa LIBRE");
        }
    }

    /**
     * Valida el estado LIBRE de la mesa.
     */
    private void validarEstadoLibre() {
        if (!this.estaLibre()) {
            throw new RuntimeException("Mesa OCUPADA");
        }
    }
}
