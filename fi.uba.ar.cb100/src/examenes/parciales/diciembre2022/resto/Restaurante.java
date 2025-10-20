package examenes.parciales.diciembre2022.resto;

import validaciones.Validaciones;

import java.util.Arrays;

public class Restaurante {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private Mesa[] mesas;
    private double propinaMaximaRecibida;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa el restaurante con la cantidad de mesas dada por parámetro.
     * pre: La cantidad de mesas dada debe ser mayor a cero.
     *
     * @param cantidadTotalDeMesas: cantidad de mesas en total.
     */
    public Restaurante(int cantidadTotalDeMesas) {
        Validaciones.validarNumeroMayorACero(cantidadTotalDeMesas, "'cantidadTotalDeMesas'");
        this.inicializarMesas(cantidadTotalDeMesas);
        this.propinaMaximaRecibida = 0;
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
                mesa.solicitar();
                return mesa;
            }
        }
        throw new RuntimeException("No hay mesas libres");
    }

    /**
     * post: Cierra la mesa cuyo número está dado por parámetro, y le asigna
     * la propina dada.
     *
     * @param numeroDeMesa: número de mesa.
     * @param propina:    propina dada por el cliente.
     * @return la mesa cerrada.
     * @throws RuntimeException si el estado de la mesa no es válido o si
     *                          el número de mesa no existe.
     */
    public Mesa cerrarMesa(int numeroDeMesa, double propina) {
        Validaciones.validarNumeroEntre(numeroDeMesa, 1, this.mesas.length,
                "numeroDeMesa");
        Validaciones.validarNumeroMayorACero(propina, "propina");

        Mesa mesa = this.mesas[numeroDeMesa - 1];
        mesa.cerrar(propina);
        this.actualizarPropinaMaxima(propina);
        return mesa;
    }

    /**
     * post: Devuelve el valor de la mayor propina del restaurante.
     *
     * @return la máxima propina.
     */
    public double obtenerMayorPropina() {
        return this.propinaMaximaRecibida;
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
        return mesaConMasPropina.getNumeroDeMesa();
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
    private void actualizarPropinaMaxima(double propina) {
        if (propina > this.propinaMaximaRecibida) {
            this.propinaMaximaRecibida = propina;
        }
    }

    private void inicializarMesas(int cantidadMesas) {
        this.mesas = new Mesa[cantidadMesas];
        for (int i = 0; i < mesas.length; i++) {
            mesas[i] = new Mesa(i + 1);
        }
    }

}
