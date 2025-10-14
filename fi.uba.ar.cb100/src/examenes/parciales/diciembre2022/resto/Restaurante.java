package examenes.parciales.diciembre2022.resto;

import validaciones.Validaciones;

import java.util.Arrays;

public class Restaurante {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final Mesa[] mesas;
    private double propinaMaxima;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa el restaurante con la cantidad de mesas dada por parámetro.
     * pre: La cantidad de mesas dada debe ser mayor a cero.
     *
     * @param cantidadMesas: cantidad de mesas en total.
     */
    public Restaurante(int cantidadMesas) {
        Validaciones.validarNumeroMayorACero(cantidadMesas, "'cantidadMesas'");
        this.mesas = new Mesa[cantidadMesas];
        this.propinaMaxima = 0;
        for (int i = 0; i < mesas.length; i++) {
            mesas[i] = new Mesa(i + 1);
        }
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Solicita una mesa libre para ser ocupada.
     *
     * @return la mesa libre solicitada
     * @throws RuntimeException lanza una excepción si no hay mesas libres.
     */
    public Mesa solicitarMesa() {
        for (Mesa mesa : this.mesas) {
            if (mesa.estaLibre()) {
                return mesa.setEstadoMesa(EstadoMesa.OCUPADA);
            }
        }
        throw new RuntimeException("No hay mesas libres");
    }

    /**
     * post: Cierra la mesa cuyo número está dado por parámetro, y le asigna
     * la propina dada.
     *
     * @param numeroMesa: número de mesa.
     * @param propina:    propina dada por el cliente.
     * @return la mesa cerrada.
     * @throws RuntimeException si el estado de la mesa no es válido o si
     *                          el número de mesa no existe.
     */
    public Mesa cerrarMesa(int numeroMesa, double propina) {
        Validaciones.validarNumeroEntre(numeroMesa, 1, this.mesas.length,
                "'numeroMesa'");
        Validaciones.validarNumeroMayorACero(propina, "'propina'");

        Mesa mesa = this.mesas[numeroMesa - 1];
        mesa.validarEstadoOcupada();
        mesa.recaudarPropina(propina);
        this.actualizarPropina(propina);
        return mesa.setEstadoMesa(EstadoMesa.LIBRE);
    }

    /**
     * post: Devuelve el valor de la mayor propina del restaurante.
     *
     * @return la máxima propina.
     */
    public double obtenerMayorPropina() {
        return this.propinaMaxima;
    }

    /**
     * post: Devuelve el número de la mesa con mayor propina recaudada.
     *
     * @return el número de la mesa que más propina recaudó.
     */
    public int obtenerMesaConMasPropina() {
        if (this.mesas.length == 0) return 0;

        Mesa mesaConMasPropina = this.mesas[0];
        for (int i = 1; i < this.mesas.length; i++) {
            if (this.mesas[i].totalPropina() > mesaConMasPropina.totalPropina()) {
                mesaConMasPropina = this.mesas[i];
            }
        }
        return mesaConMasPropina.numero();
    }

    /**
     * post: Devuelve el total de las propinas recaudadas en el restaurante.
     *
     * @return el total de propinas.
     */
    public double totalPropinas() {
        return Arrays.stream(this.mesas)
                .mapToDouble(Mesa::totalPropina)
                .sum();
    }

    /**
     * post: Devuelve un arreglo con las mesas del restaurante.
     *
     * @return una copia de las mesas del restaurante.
     */
    public Mesa[] obtenerMesas() {
        Mesa[] copias = new Mesa[this.mesas.length];
        for (int i = 0; i < this.mesas.length; i++) {
            Mesa mesaGuardada = this.mesas[i];
            copias[i] = new Mesa(mesaGuardada);
        }
        return copias;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //MÉTODOS PRIVADOS   -----------------------------------------------------------------------------------------

    /**
     * post: Actualiza la propina más alta, si es necesario.
     * @param propina: nueva propina.
     */
    private void actualizarPropina(double propina) {
        if (propina > this.propinaMaxima) {
            this.propinaMaxima = propina;
        }
    }

}
