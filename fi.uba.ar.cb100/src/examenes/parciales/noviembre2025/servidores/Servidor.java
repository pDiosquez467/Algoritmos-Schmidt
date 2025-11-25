package examenes.parciales.noviembre2025.servidores;

import validaciones.Validaciones;

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
    private final Vector<Aplicacion> aplicaciones;
    private double espacioOcupadoEnRam;
    private double espacioOcupadoEnDisco;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

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

    public void guardar(Aplicacion aplicacion) {
        Validaciones.validarNotNull(aplicacion, "aplicación");
        this.validarEspacioDisponibleEnDisco(aplicacion);
        this.espacioOcupadoEnDisco += aplicacion.getConsumoDeDisco();
        this.aplicaciones.add(aplicacion);
    }

    public void borrar(Aplicacion aplicacion) {
        Aplicacion aplicacionAlmacenada = this.getAplicacionAlmacenada(aplicacion);
        this.aplicaciones.remove(aplicacionAlmacenada);
        this.espacioOcupadoEnDisco -= aplicacion.getConsumoDeDisco();
    }

    public void ejecutar(Aplicacion aplicacion) {
        Aplicacion aplicacionAlmacenada =
                this.getAplicacionAlmacenada(aplicacion);
        this.validarEspacioDisponibleEnRam(aplicacionAlmacenada);
        aplicacionAlmacenada.ejecutar();
        this.espacioOcupadoEnRam += aplicacionAlmacenada.getConsumoDeRam();
    }

    public void apagar(Aplicacion aplicacion) {
        Aplicacion aplicacionAlmacenada =
                this.getAplicacionAlmacenada(aplicacion);
        aplicacionAlmacenada.apagar();
        this.espacioOcupadoEnRam -= aplicacion.getConsumoDeRam();
    }

    public int obtenerCantidadDeAppsEnEjecucion() {
        int total = 0;
        for (Aplicacion aplicacion : this.aplicaciones) {
            if (aplicacion.enEjecucion()) {
                total++;
            }
        }
        return total;
    }

    public Aplicacion[] getAplicaciones() {
        Aplicacion[] copias = new Aplicacion[this.aplicaciones.size()];
        for (int i = 0; i < this.aplicaciones.size(); i++) {
            Aplicacion aplicacionAlmacenada = this.aplicaciones.get(i);
            copias[i] = new Aplicacion(aplicacionAlmacenada);
        }
        return copias;
    }

    public double getEspacioLibreEnRam() {
        return this.capacidadDeRam - this.espacioOcupadoEnRam;
    }

    public double getEspacioLibreEnDisco() {
        return this.espacioEnDisco - this.espacioOcupadoEnDisco;
    }

    public boolean pertenece(Aplicacion aplicacion) {
        Validaciones.validarNotNull(aplicacion, "aplicacion");
        return this.getIndiceDeAplicacion(aplicacion) != -1;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    public String getIp() {
        return ip;
    }

    public double getCapacidadDeRam() {
        return capacidadDeRam;
    }

    public double getEspacioEnDisco() {
        return espacioEnDisco;
    }

    public double getEspacioOcupadoEnRam() {
        return espacioOcupadoEnRam;
    }

    public double getEspacioOcupadoEnDisco() {
        return espacioOcupadoEnDisco;
    }

    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------

    private void validarEspacioDisponibleEnRam(Aplicacion aplicacion) {
        if (this.espacioOcupadoEnRam + aplicacion.getConsumoDeRam() > this.capacidadDeRam) {
            throw new RuntimeException("RAM excedida");
        }
    }

    private void validarEspacioDisponibleEnDisco(Aplicacion aplicacion) {
        if (this.espacioOcupadoEnDisco + aplicacion.getConsumoDeDisco() > this.espacioEnDisco) {
            throw new RuntimeException("Disco excedido");
        }
    }

    private int getIndiceDeAplicacion(Aplicacion aplicacion) {
        Validaciones.validarNotNull(aplicacion, "aplicacion");
        for (int i = 0; i < this.aplicaciones.size(); i++) {
            if (this.aplicaciones.get(i).equals(aplicacion)) {
                return i;
            }
        }
        return -1;
    }

    private Aplicacion getAplicacionAlmacenada(Aplicacion aplicacion) {
        Validaciones.validarNotNull(aplicacion, "aplicación");
        int indice = this.getIndiceDeAplicacion(aplicacion);
        if (indice == -1) {
            throw new RuntimeException("Aplicación NO encontrada en el Servidor.");
        }
        return this.aplicaciones.get(indice);
    }


}
