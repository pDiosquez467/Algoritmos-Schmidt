package examenes.parciales.julio2021.smart;

import validaciones.Validaciones;

import java.util.Objects;

public class App {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private final String nombre;
    private final int tamanio;
    private final boolean funcionaOffline;
    private EstadoDeApp estadoDeApp;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa la app con el nombre, el tamaño dados, e indicando
     * si funciona o no de manera offline.
     *
     * @param nombre:          el nombre de la app.
     * @param tamanio:         el tamaño de la app.
     * @param funcionaOffline: ¿funciona offline?
     */
    public App(String nombre, int tamanio, boolean funcionaOffline) {
        Validaciones.validarNotNull(nombre, "nombre");
        Validaciones.validarVerdadero(!nombre.isBlank(), "nombre");
        Validaciones.validarNumeroMayorACero(tamanio, "tamanio");
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.funcionaOffline = funcionaOffline;
        this.estadoDeApp = EstadoDeApp.CERRADA;
    }

    /**
     * post: Inicializa la app copiando todos los atributos de la app dada.
     *
     * @param app: app que se quiere copiar.
     */
    public App(App app) {
        this(app.nombre(), app.tamanio(), app.funcionaOffline());
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof App app)) return false;
        return Objects.equals(nombre, app.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public String toString() {
        return "App{" +
                "nombre='" + nombre + '\'' +
                ", tamanio=" + tamanio +
                ", funcionaOffline=" + funcionaOffline +
                ", estadoDeApp=" + estadoDeApp +
                '}';
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Abre la app. Si se encuentra abierta, la mantiene abierta.
     */
    public void abrir() {
        this.estadoDeApp = EstadoDeApp.ABIERTA;
    }

    /**
     * post: Cierra la app. Si se encuentra cerrada, la mantiene cerrada.
     */
    public void cerrar() {
        this.estadoDeApp = EstadoDeApp.CERRADA;
    }

    /**
     * post: Indica si la app está abierta.
     *
     * @return verdadero si la app está abierta.
     */
    public boolean estaAbierta() {
        return this.estadoDeApp().equals(EstadoDeApp.ABIERTA);
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve el nombre de la app.
     *
     * @return el nombre de la app.
     */
    public String nombre() {
        return nombre;
    }

    /**
     * post: Devuelve el tamaño de la app.
     *
     * @return el tamaño de la app.
     */
    public int tamanio() {
        return tamanio;
    }

    /**
     * post: Indica si la app funciona offline.
     * @return verdadero si la app funciona offline.
     */
    public boolean funcionaOffline() {
        return funcionaOffline;
    }

    /**
     * post: Devuelve el estado de la app.
     * @return el estado de la app.
     */
    public EstadoDeApp estadoDeApp() {
        return estadoDeApp;
    }

    protected void validarEstadoAbierto() {
        if (!this.estaAbierta()) {
            throw new RuntimeException("App CERRADA");
        }
    }

}
