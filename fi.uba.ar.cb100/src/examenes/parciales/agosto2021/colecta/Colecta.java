package examenes.parciales.agosto2021.colecta;

import validaciones.Validaciones;

import java.util.Objects;

public class Colecta {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final String nombre;
    private final double recaudacionObjetivo;
    private final double maximaDonacionIndividualAceptada;
    private double recaudacionActual;
    private int cantidadDeDonacionesRecibidas;
    private double maximaDonacionIndividualRecibida;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa la colecta con el nombre 'nombre', la recaudación objetivo
     * dada, y la máxima donación individual aceptada.
     * @param nombre: el nombre de la colecta.
     * @param recaudacionObjetivo: la recaudación objetivo.
     * @param maximaDonacionIndividualAceptada: máxima donación individual aceptada.
     */
    public Colecta(String nombre, double recaudacionObjetivo, double maximaDonacionIndividualAceptada) {
        Validaciones.validarNotNull(nombre, "nombre");
        Validaciones.validarVerdadero(!nombre.isEmpty(), "nombre");
        Validaciones.validarNumeroMayorACero(recaudacionObjetivo, "recaudaciónObjetivo");
        Validaciones.validarNumeroMayorACero(maximaDonacionIndividualAceptada, "maximaDonaciónIndividualAceptada");
        this.nombre = nombre;
        this.recaudacionObjetivo = recaudacionObjetivo;
        this.maximaDonacionIndividualAceptada = maximaDonacionIndividualAceptada;
        this.recaudacionActual = 0;
        this.cantidadDeDonacionesRecibidas = 0;
        this.maximaDonacionIndividualRecibida = 0;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Colecta colecta)) return false;
        return Objects.equals(nombre, colecta.nombre) && Objects.equals(recaudacionObjetivo, colecta.recaudacionObjetivo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, recaudacionObjetivo);
    }

    @Override
    public String toString() {
        return "Colecta{" +
                "nombre='" + nombre + '\'' +
                ", recaudaciónObjetivo=" + recaudacionObjetivo +
                ", maximaDonaciónIndividualAceptada=" + maximaDonacionIndividualAceptada +
                ", recaudaciónActual=" + recaudacionActual +
                ", cantidadDeDonacionesRecibidas=" + cantidadDeDonacionesRecibidas +
                ", maximaDonaciónIndividualRecibida=" + maximaDonacionIndividualRecibida +
                '}';
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Agrega la nueva donación a la recaudación actual y actualiza la
     * máxima donación recibida, en caso de ser necesario.
     * pre: 'nuevaDonación' debe ser mayor a cero y menor o igual que la máxima donación
     * aceptada.
     * @param nuevaDonacion: una nueva donación.
     */
    public void donar(double nuevaDonacion) {
        Validaciones.validarNumeroMayorACero(nuevaDonacion, "nuevaDonación");
        this.validarNuevaDonacion(nuevaDonacion);
        this.recaudacionActual += nuevaDonacion;
        this.cantidadDeDonacionesRecibidas++;
        this.actualizarMaximaDonacionRecibida(nuevaDonacion);
    }

    /**
     * post: Devuelve la cantidad de donaciones recibidas.
     * @return la cantidad de donaciones recibidas.
     */
    public int getCantidadDeDonacionesRecibidas() {
        return this.cantidadDeDonacionesRecibidas;
    }

    /**
     * post: Devuelve la máxima donación recibida.
     * @return la máxima donación recibida.
     */
    public double getDonacionMaxima() {
        return this.maximaDonacionIndividualRecibida;
    }

    /**
     * post: Devuelve la recaudación faltante para alcanzar el objetivo.
     * En caso de sobrepasarlo, devuelve cero.
     * @return la recaudación faltante.
     */
    public double calcularRecaudacionFaltante() {
        return Math.max(0, this.recaudacionObjetivo - recaudacionActual);
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve el nombre de la colecta.
     * @return el nombre de la colecta.
     */
    public String nombre() {
        return nombre;
    }

    /**
     * post: Devuelve el monto objetivo de la colecta.
     * @return el objetivo de la colecta.
     */
    public double recaudacionObjetivo() {
        return recaudacionObjetivo;
    }

    /**
     * post: Devuelve la máxima donación individual aceptada por la colecta.
     * @return la máxima donación individual aceptada.
     */
    public Double maximaDonacionIndividualAceptada() {
        return maximaDonacionIndividualAceptada;
    }

    /**
     * post: Valida que la nueva donación recibida no exceda la máxima
     * donación individual permitida.
     * @param nuevaDonacion una nueva donación.
     */
    private void validarNuevaDonacion(double nuevaDonacion) {
        if (nuevaDonacion > this.maximaDonacionIndividualAceptada) {
            throw new RuntimeException("Máxima donación individual excedida");
        }
    }

    /**
     * post: Actualiza la máxima donación recibida por la colecta, en caso
     * de ser necesario.
     * @param nuevaDonacion una nueva donación.
     */
    private void actualizarMaximaDonacionRecibida(double nuevaDonacion) {
        Validaciones.validarNumeroMayorACero(nuevaDonacion, "nuevaDonación");
        if (nuevaDonacion > this.maximaDonacionIndividualRecibida) {
            this.maximaDonacionIndividualRecibida = nuevaDonacion;
        }
    }
}
