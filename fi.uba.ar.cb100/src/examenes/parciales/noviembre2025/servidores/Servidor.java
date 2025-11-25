package examenes.parciales.noviembre2025.servidores;

import validaciones.Validaciones;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Vector;

public class Servidor {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final String ip;
    private final double capacidadDeRam;
    private final double espacioEnDisco;
    private final Vector<IApp> aplicaciones;
    private double espacioOcupadoEnRam;
    private double espacioOcupadoEnDisco;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa un servidor con la IP, la capacidad de RAM y el espacio
     * en disco dado.
     * @param ip: la IP del servidor.
     * @param capacidadDeRam: la capacidad de RAM del servidor.
     * @param espacioEnDisco: el espacio en disco del servidor.
     */
    public Servidor(String ip, double capacidadDeRam, double espacioEnDisco) {
        Validaciones.validarNotNull(ip, "ip");
        Validaciones.validarNumeroMayorACero(capacidadDeRam, "capacidadDeRam");
        Validaciones.validarNumeroMayorACero(espacioEnDisco, "espacioEnDisco");
        this.ip = ip;
        this.capacidadDeRam = capacidadDeRam;
        this.espacioEnDisco = espacioEnDisco;
        this.aplicaciones   = new Vector<>();
        this.espacioOcupadoEnRam   = 0;
        this.espacioOcupadoEnDisco = 0;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Servidor servidor)) return false;
        return Objects.equals(ip, servidor.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ip);
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Guarda en el servidor la aplicación dada.
     * pre: Debe haber suficiente espacio en el disco para guardar la app.
     * @param aplicacion: la aplicación a guardar.
     */
    public void guardar(Aplicacion aplicacion) {
        Validaciones.validarNotNull(aplicacion, "aplicación");
        this.validarEspacioDisponibleEnDisco(aplicacion);
        this.espacioOcupadoEnDisco += aplicacion.getConsumoDeDisco();
        this.aplicaciones.add(aplicacion);
    }

    /**
     * post: Borra la aplicación del servidor.
     * pre: La aplicación debe pertenecer al servidor.
     * @param aplicacion: la aplicación a eliminar.
     */
    public void borrar(Aplicacion aplicacion) {
        IApp aplicacionAlmacenada = this.getAplicacionAlmacenada(aplicacion);
        if (aplicacionAlmacenada.enEjecucion()) {
            throw new RuntimeException("No se puede eliminar una app en ejecución");
        }
        this.aplicaciones.remove(aplicacionAlmacenada);
        this.espacioOcupadoEnDisco -= aplicacion.getConsumoDeDisco();
    }

    /**
     * post: Ejecuta la aplicación dada en el servidor.
     * pre: La aplicación debe pertenecer al servidor.
     * @param aplicacion: la aplicación a ejecutar.
     */
    public void ejecutar(Aplicacion aplicacion) {
        IApp aplicacionAlmacenada =
                this.getAplicacionAlmacenada(aplicacion);
        this.validarEspacioDisponibleEnRam(aplicacionAlmacenada);
        aplicacionAlmacenada.ejecutar();
        this.espacioOcupadoEnRam += aplicacionAlmacenada.getConsumoDeRam();
    }

    /**
     * post: Apaga la aplicación dada, liberando el espacio de RAM que estaba consumiendo.
     * pre:
     * - La aplicación debe pertenecer al servidor.
     * - La aplicación debe estar ejecutándose.
     * @param aplicacion: la aplicación a apagar.
     * @throws RuntimeException si la aplicación NO pertenece al servidor.
     * @throws RuntimeException si la aplicación NO está ejecutándose.
     */
    public void apagar(Aplicacion aplicacion) {
        IApp aplicacionAlmacenada =
                this.getAplicacionAlmacenada(aplicacion);
        aplicacionAlmacenada.apagar();
        this.espacioOcupadoEnRam -= aplicacion.getConsumoDeRam();
    }

    /**
     * post: Devuelve la cantidad de aplicaciones en ejecución en el servidor.
     * @return la cantidad de apps en ejecución.
     */
    public int obtenerCantidadDeAppsEnEjecucion() {
        int total = 0;
        for (IApp aplicacion : this.aplicaciones) {
            if (aplicacion.enEjecucion()) {
                total++;
            }
        }
        return total;
    }

    /**
     * post: Devuelve una copia inmutable de las aplicaciones en el servidor.
     * @return la cantidad de aplicación en ejecución en el servidor.
     */
    public Collection<IApp> getAplicaciones() {
        return Collections.unmodifiableCollection(this.aplicaciones);
    }

    /**
     * post: Devuelve la cantidad de espacio libre en la memoria RAM.
     * @return la cantidad de RAM disponible.
     */
    public double getEspacioLibreEnRam() {
        return this.capacidadDeRam - this.espacioOcupadoEnRam;
    }

    /**
     * post: Devuelve la cantidad de espacio libre en el disco.
     * @return la cantidad de espacio libre en el disco.
     */
    public double getEspacioLibreEnDisco() {
        return this.espacioEnDisco - this.espacioOcupadoEnDisco;
    }

    /**
     * post: Indica si la aplicación pertenece al servidor.
     * @param aplicacion: la aplicación a buscar.
     * @return verdadero si la app pertenece al servidor.
     */
    public boolean pertenece(Aplicacion aplicacion) {
        Validaciones.validarNotNull(aplicacion, "aplicacion");
        return this.getIndiceDeAplicacion(aplicacion) != -1;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve la IP del servidor.
     * @return la IP.
     */
    public String getIp() {
        return ip;
    }

    /**
     * post: Devuelve la capacidad total de RAM del servidor.
     * @return la capacidad de RAM.
     */
    public double getCapacidadDeRam() {
        return capacidadDeRam;
    }

    /**
     * post: Devuelve el espacio total en disco del servidor.
     * @return el espacio en disco.
     */
    public double getEspacioEnDisco() {
        return espacioEnDisco;
    }

    /**
     * post: Devuelve el espacio ocupado en la memoria RAM.
     * @return el espacio ocupado en RAM.
     */
    public double getEspacioOcupadoEnRam() {
        return espacioOcupadoEnRam;
    }

    /**
     * post: Devuelve el espacio ocupado en disco.
     * @return el espacio ocupado en disco.
     */
    public double getEspacioOcupadoEnDisco() {
        return espacioOcupadoEnDisco;
    }

    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------

    private void validarEspacioDisponibleEnRam(IApp aplicacion) {
        if (this.espacioOcupadoEnRam + aplicacion.getConsumoDeRam() > this.capacidadDeRam) {
            throw new RuntimeException("RAM excedida");
        }
    }

    private void validarEspacioDisponibleEnDisco(IApp aplicacion) {
        if (this.espacioOcupadoEnDisco + aplicacion.getConsumoDeDisco() > this.espacioEnDisco) {
            throw new RuntimeException("Disco excedido");
        }
    }

    /**
     * post: Busca el índice de la aplicación en el Vector.
     */
    private int getIndiceDeAplicacion(IApp aplicacion) {
        Validaciones.validarNotNull(aplicacion, "aplicacion");
        for (int i = 0; i < this.aplicaciones.size(); i++) {
            if (this.aplicaciones.get(i).equals(aplicacion)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * post: Busca la referencia interna de la aplicación en el servidor.
     * @throws RuntimeException si la aplicación NO es encontrada.
     */
    private IApp getAplicacionAlmacenada(IApp aplicacion) {
        Validaciones.validarNotNull(aplicacion, "aplicación");
        int indice = this.getIndiceDeAplicacion(aplicacion);
        if (indice == -1) {
            throw new RuntimeException("Aplicación NO encontrada en el Servidor.");
        }
        return this.aplicaciones.get(indice);
    }

}
