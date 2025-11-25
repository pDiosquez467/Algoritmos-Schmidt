package examenes.parciales.noviembre2025.servidores;

import validaciones.Validaciones;

import java.util.Objects;

public class Aplicacion {
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

    public Aplicacion(String nombre, double consumoDeRam, double consumoDeDisco) {
        Validaciones.validarNotNull(nombre, "nombre");
        Validaciones.validarNumeroMayorACero(consumoDeRam, "consumoDeRam");
        Validaciones.validarNumeroMayorACero(consumoDeDisco, "consumoDeDisco");
        this.nombre = nombre;
        this.consumoDeRam = consumoDeRam;
        this.consumoDeDisco = consumoDeDisco;
        this.estadoDeAplicacion = EstadoDeAplicacion.APAGADA;
    }

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

    public void ejecutar() {
        this.validarEstadoNoEnEjecucion();
        this.estadoDeAplicacion = EstadoDeAplicacion.EN_EJECUCION;
    }

    public void apagar() {
        this.validarEstadoEnEjecucion();
        this.estadoDeAplicacion = EstadoDeAplicacion.APAGADA;
    }

    public boolean enEjecucion() {
        return this.estadoDeAplicacion.equals(EstadoDeAplicacion.EN_EJECUCION);
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    public String getNombre() {
        return nombre;
    }

    public double getConsumoDeRam() {
        return consumoDeRam;
    }

    public double getConsumoDeDisco() {
        return consumoDeDisco;
    }

    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------

    private void validarEstadoEnEjecucion() {
        if (!this.enEjecucion()) {
            throw new RuntimeException("La app NO está en ejecución");
        }
    }

    private void validarEstadoNoEnEjecucion() {
        if (this.enEjecucion()) {
            throw new RuntimeException("La app está en ejecución");
        }
    }

}
