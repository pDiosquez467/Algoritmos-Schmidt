package examenes.parciales.colecta;

import validaciones.Validaciones;

/**
 * Representa una colecta de dinero ($) con un monto objetivo y una donación individual
 * máxima aceptada.
 */
public class Colecta {
    private final double montoObjetivo;
    private final double maximaDonacionAceptada;
    private double totalRecaudado;
    private int cantidadDeDonaciones;
    private double donacionMaximaAlMomento;

    /**
     * Constructor.
     * @param montoObjetivo: monto objetivo de la donación.
     * @param maximaDonacionAceptada: máxima donación que la colecta puede aceptar.
     */
    public Colecta(double montoObjetivo, double maximaDonacionAceptada) {
        Validaciones.validarNumeroMayorACero(montoObjetivo, "'montoObjetivo' debe ser mayor a cero");
        Validaciones.validarNumeroMayorACero(maximaDonacionAceptada, "'maximaDonacionAceptada' " +
                "debe ser mayor a cero");

        this.montoObjetivo           = montoObjetivo;
        this.maximaDonacionAceptada  = maximaDonacionAceptada;
        this.totalRecaudado          = 0;
        this.cantidadDeDonaciones    = 0;
        this.donacionMaximaAlMomento = 0;
    }

    /**
     * Devuelve el monto total recaudado actualmente.
     * @return el monto total recaudado.
     */
    public double obtenerRecaudacion() {
        return totalRecaudado;
    }

    /**
     * Agrega el monto donado al total recaudado por la colecta.
     * @param montoDonado: monto donado.
     * @RuntimeException lanza una exception si el monto donado supera al monto máximo aceptado.
     */
    public void donar(double montoDonado) {
        Validaciones.validarNumeroMayorACero(montoDonado, "'montoDonado' debe ser mayor a cero");
        validarMonto(montoDonado);

        totalRecaudado += montoDonado;
        cantidadDeDonaciones++;
        actualizarMaximaDonacion(montoDonado);
    }

    /**
     * Devuelve la cantidad de donaciones recibidas por la colecta.
     * @return la cantidad de donaciones.
     */
    public int contarDonaciones() {
        return cantidadDeDonaciones;
    }

    /**
     * Devuelve la máxima donación recibida al momento en la colecta.
     * @return la máxima donación.
     */
    public double donacionMaxima() {
        return donacionMaximaAlMomento;
    }

    /**
     * Devuelve la recaudación faltante para llegar al monto objetivo.
     * En cado de haber llegado o sobrepasado el monto esperado, devuelve cero.
     * @return la recaudación faltante.
     */
    public double calcularRecaudacionFaltante() {
        return Math.max(0, montoObjetivo - totalRecaudado);
    }

    /**
     * Devuelve el monto objetivo.
     * @return el $ objetivo.
     */
    public double obtenerMontoObjetivo() {
        return montoObjetivo;
    }

    /**
     * Devuelve la máxima donación recibida en la colecta.
     * @return $ máxima.
     */
    public double obtenerMaximaDonacionAceptada() {
        return maximaDonacionAceptada;
    }

    // === HELPERS PRIVADOS ===

    /**
     * Valida que el monto recibido no exceda el monto máximo aceptado.
     * @param montoDonado: monto recibido.
     * @RuntimeException lanza exception si el monto dado excede el máximo aceptado por la colecta.
     */
    private void validarMonto(double montoDonado) {
        if (esMontoExcesivo(montoDonado)) {
            throw new RuntimeException("Máxima donación individual excedida");
        }
    }

    /**
     * Indica si el monto recibido es mayor que el monto máximo aceptado por la colecta.
     * @param montoDonado: monto recibido.
     * @return verdadero si el monto es mayor que el máximo aceptado.
     */
    private boolean esMontoExcesivo(double montoDonado) {
        return montoDonado > maximaDonacionAceptada;
    }

    /**
     * Actualiza la máxima donación recibida en caso de ser necesario.
     * @param montoDonado: monto recibido.
     */
    private void actualizarMaximaDonacion(double montoDonado) {
        if (montoDonado > donacionMaximaAlMomento) {
            donacionMaximaAlMomento = montoDonado;
        }
    }
}
