package examenes.parciales.resto;

import validaciones.Validaciones;

import java.util.Objects;

public class MesaDeResto {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final int numero;
    private EstadoDeMesaDeResto estadoDeMesaDeResto;
    private double propinaAcumulada;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa una mesa con el número dado, estado 'LIBRE' y sin propinas.
     * @param numero: el número de la mesa, debe ser mayor a cero.
     * @throws RuntimeException si el número de la mesa es menor a cero.
     */
    public MesaDeResto(int numero) {
        Validaciones.validarNumeroMayorACero(numero, "numero");
        this.numero = numero;
        this.estadoDeMesaDeResto = EstadoDeMesaDeResto.LIBRE;
        this.propinaAcumulada    = 0;
    }

    /**
     * post: Copia el estado de la mesa dada en una nueva mesa.
     * @param mesaDeResto: la mesa a copiar.
     */
    public MesaDeResto(MesaDeResto mesaDeResto) {
        Validaciones.validarNotNull(mesaDeResto, "mesaDeResto");
        this.numero = mesaDeResto.numero;
        this.estadoDeMesaDeResto = mesaDeResto.estadoDeMesaDeResto;
        this.propinaAcumulada = mesaDeResto.propinaAcumulada;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MesaDeResto that)) return false;
        return numero == that.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numero);
    }

    @Override
    public String toString() {
        return "MesaDeResto{" +
                "numero=" + numero +
                ", estadoDeMesaDeResto=" + estadoDeMesaDeResto +
                ", propinaAcumulada=" + propinaAcumulada +
                '}';
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Ocupa la mesa.
     * pre: La mesa debe estar LIBRE.
     * @throws RuntimeException si la mesa está OCUPADA.
     */
    public void ocupar() {
        this.validarMesaLibre();
        this.estadoDeMesaDeResto = EstadoDeMesaDeResto.OCUPADA;
    }

    /**
     * post: Indica si la mesa está LIBRE.
     * @return verdadero si la mesa está libre.
     */
    public boolean estaLibre() {
        return this.estadoDeMesaDeResto.equals(EstadoDeMesaDeResto.LIBRE);
    }

    /**
     * post: Cierra la mesa, guardando la propina dada.
     * @param propina: la propina dada.
     * @return el número de la mesa.
     */
    public int cerrar(double propina) {
        this.liberar();
        this.recaudarPropina(propina);
        return this.numero;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve el número de la mesa.
     * @return el número de la mesa.
     */
    public int numero() {
        return numero;
    }

    /**
     * post: Devuelve la propina acumulada por la mesa.
     * @return la propina acumulada.
     */
    public double propinaAcumulada() {
        return propinaAcumulada;
    }

    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------

    /**
     * post: Valida que la mesa esté LIBRE.
     */
    public void validarMesaLibre() {
        if (!this.estaLibre()) {
            throw new RuntimeException("Mesa OCUPADA");
        }
    }

    /**
     * post: Valida que la mesa esté OCUPADA.
     */
    public void validarMesaOcupada() {
        if (this.estaLibre()) {
            throw new RuntimeException("Mesa LIBRE");
        }
    }

    /**
     * post: Recauda la propina dada.
     * pre: La propina debe ser mayor a cero.
     * @param propina: la propina dada por el cliente.
     */
    private void recaudarPropina(double propina) {
        Validaciones.validarNumeroMayorACero(propina, "propina");
        this.propinaAcumulada += propina;
    }

    /**
     * post: Libera la mesa.
     * pre: La mesa debe estar OCUPADA.
     * @throws RuntimeException si la mesa está LIBRE.
     */
    private void liberar() {
        this.validarMesaOcupada();
        this.estadoDeMesaDeResto = EstadoDeMesaDeResto.LIBRE;
    }

}
