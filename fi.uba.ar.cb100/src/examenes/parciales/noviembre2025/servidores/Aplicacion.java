package examenes.parciales.noviembre2025.servidores;

import validaciones.Validaciones;

import java.util.Objects;

public class Aplicacion implements IApp {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private final String nombre;
    private final double consumoDeRam;
    private final double consumoDeDisco;
    private EstadoDeAplicacion estadoDeAplicacion;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa una aplicación con el nombre, el consumo de RAM y el espacio
     * en disco dado. El estado de la aplicación es APAGADO.
     * pre:
     *  - 'nombre' debe ser no nulo.
     *  - 'consumoDeRam' y 'consumoDeDisco' deben ser mayores a cero.
     * @param nombre: el nombre de la aplicación.
     * @param consumoDeRam: el consumo de RAM de la aplicación.
     * @param consumoDeDisco: el consumo de disco de la aplicación.
     */
    public Aplicacion(String nombre, double consumoDeRam, double consumoDeDisco) {
        Validaciones.validarNotNull(nombre, "nombre");
        Validaciones.validarNumeroMayorACero(consumoDeRam, "consumoDeRam");
        Validaciones.validarNumeroMayorACero(consumoDeDisco, "consumoDeDisco");
        this.nombre = nombre;
        this.consumoDeRam = consumoDeRam;
        this.consumoDeDisco = consumoDeDisco;
        this.estadoDeAplicacion = EstadoDeAplicacion.APAGADA;
    }

    /**
     * post: Constructor de copia. Copia el estado de la aplicación dado por
     * parámetro.
     * @param aplicacion: la aplicación a copiar.
     */
    public Aplicacion(Aplicacion aplicacion) {
        this(aplicacion.getNombre(), aplicacion.getConsumoDeRam(), aplicacion.getConsumoDeDisco());
        this.estadoDeAplicacion = aplicacion.estadoDeAplicacion;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Aplicacion that)) return false;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Ejecuta la aplicación.
     * pre: La aplicación no debe estar ejecutándose.
     * @throws RuntimeException si la aplicación está en ejecución.
     */
    public void ejecutar() {
        this.validarEstadoNoEnEjecucion();
        this.estadoDeAplicacion = EstadoDeAplicacion.EN_EJECUCION;
    }

    /**
     * post: Apaga la aplicación.
     * pre: La aplicación debe estar ejecutándose.
     * @throws RuntimeException si la aplicación NO está en ejecución.
     */
    public void apagar() {
        this.validarEstadoEnEjecucion();
        this.estadoDeAplicacion = EstadoDeAplicacion.APAGADA;
    }

    /**
     * post: Indica si la aplicación está en ejecución.
     * @return verdadero si la aplicación está en ejecución.
     */
    public boolean enEjecucion() {
        return this.estadoDeAplicacion.equals(EstadoDeAplicacion.EN_EJECUCION);
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve el nombre de la aplicación.
     * @return el nombre de la aplicación.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * post: Devuelve el consumo de RAM de la aplicación.
     * @return el consumo de RAM.
     */
    public double getConsumoDeRam() {
        return consumoDeRam;
    }

    /**
     * post: Devuelve el consumo de disco de la aplicación.
     * @return el consumo de disco de la app.
     */
    public double getConsumoDeDisco() {
        return consumoDeDisco;
    }

    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------

    /**
     * post: Valida que la aplicación se esté ejecutando.
     */
    private void validarEstadoEnEjecucion() {
        if (!this.enEjecucion()) {
            throw new RuntimeException("La app NO está en ejecución");
        }
    }

    /**
     * post: Valida que la aplicación NO se esté ejecutando.
     */
    private void validarEstadoNoEnEjecucion() {
        if (this.enEjecucion()) {
            throw new RuntimeException("La app está en ejecución");
        }
    }
}