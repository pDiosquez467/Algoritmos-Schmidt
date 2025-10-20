package examenes.parciales.julio2021.smart;

import tdas.vector.Iterador;
import tdas.vector.IteradorImpl;
import tdas.vector.Vector;
import validaciones.Validaciones;

public class SmartTV {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private final int tamanioMaximoDeEspacioEnDisco;
    private int espacioActualUsado;
    private final Vector<App> apps;
    private boolean estaOnline;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    public SmartTV(int tamanioMaximoDeEspacioEnDisco) {
        Validaciones.validarNumeroMayorACero(tamanioMaximoDeEspacioEnDisco, "tamanioMaximoDeEspacioEnDisco");
        this.tamanioMaximoDeEspacioEnDisco = tamanioMaximoDeEspacioEnDisco;
        this.espacioActualUsado = 0;
        this.apps       = new Vector<>();
        this.estaOnline = false;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    public void agregarApp(App app) {
        Validaciones.validarNotNull(app, "app");
        this.validarAppNoInstalada(app);
        this.validarEspacioDisponibleEnElDisco(app.tamanio());
        this.apps.agregar(app);
        this.espacioActualUsado += app.tamanio();
    }

    public App removerApp(App app) {
        Validaciones.validarNotNull(app, "app");
        this.validarAppInstalada(app);
        int indiceApp = this.apps.indiceDe(app);
        App appRemovida = this.apps.remover(indiceApp);
        this.espacioActualUsado -= app.tamanio();
        return appRemovida;
    }

    public void abrirApp(App app) {
        Validaciones.validarNotNull(app, "app");
        this.validarAppInstalada(app);
        this.validarEstadoDeConexionSincronizado(app);
        int indiceApp = this.apps.indiceDe(app);
        this.apps.obtener(indiceApp).abrir();
    }

    public void cerrarApp(App app) {
        Validaciones.validarNotNull(app, "app");
        this.validarAppInstalada(app);
        int indiceApp = this.apps.indiceDe(app);
        this.apps.obtener(indiceApp).cerrar();
    }

    public boolean contieneLaApp(App app) {
        Validaciones.validarNotNull(app, "app");
        return this.apps.contiene(app);
    }

    public void conectarAInternet() {
        this.estaOnline = true;
    }

    public void desconectarAInternet() {
        this.estaOnline = false;
    }

    public void activarSubtitulos(AppDeVideos appDeVideos) {
        Validaciones.validarNotNull(appDeVideos, "app");
        this.validarAppInstalada(appDeVideos);
        this.validarAppAbierta(appDeVideos);
        int indiceApp = this.apps.indiceDe(appDeVideos);
        AppDeVideos appBuscada = (AppDeVideos) this.apps.obtener(indiceApp);

        appBuscada.activarSubtitulos();
    }

    public void desactivarSubtitulos(AppDeVideos appDeVideos) {
        Validaciones.validarNotNull(appDeVideos, "app");
        this.validarAppInstalada(appDeVideos);
        this.validarAppAbierta(appDeVideos);
        int indiceApp = this.apps.indiceDe(appDeVideos);
        AppDeVideos appBuscada = (AppDeVideos) this.apps.obtener(indiceApp);

        appBuscada.desactivarSubtitulos();
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    public int tamanioMaximoDeEspacioEnDisco() {
        return tamanioMaximoDeEspacioEnDisco;
    }

    public int espacioActualUsado() {
        return espacioActualUsado;
    }

    public int getEspacioRestante() {
        return this.tamanioMaximoDeEspacioEnDisco() - this.espacioActualUsado();
    }

    public Vector<App> apps() {
        Vector<App> copias = new Vector<>();
        Iterador<App> it = new IteradorImpl<>(this.apps);
        while (it.haySiguiente()) {
            copias.agregar(new App(it.verActual()));
            it.siguiente();
        }
        return copias;
    }

    public boolean estaOnline() {
        return estaOnline;
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    private void validarAppInstalada(App app) {
        if (!this.contieneLaApp(app)) {
            throw new RuntimeException("App no instalada");
        }
    }

    private void validarAppNoInstalada(App app) {
        if (this.contieneLaApp(app)) {
            throw new RuntimeException("App instalada");
        }
    }

    private void validarAppAbierta(App app) {
        if (!app.estaAbierta()) {
            throw new RuntimeException("App cerrada");
        }
    }

    private void validarEspacioDisponibleEnElDisco(int tamanioExtra) {
        if (this.espacioActualUsado + tamanioExtra > this.tamanioMaximoDeEspacioEnDisco) {
            throw new RuntimeException("Espacio insuficiente");
        }
    }

    private void validarEstadoDeConexionSincronizado(App app) {
        if (!this.estaOnline()) {
            if (!app.funcionaOffline()) {
                throw new RuntimeException("La app no funciona offline");
            }
        }
    }
}
