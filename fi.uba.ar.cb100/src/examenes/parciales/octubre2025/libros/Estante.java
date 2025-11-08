package examenes.parciales.octubre2025.libros;

import tdas.vector.Vector;
import validaciones.Validaciones;

public class Estante {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final Vector<Libro> libros;
    private final double ancho;
    private final double pesoMaximoQueSoporta;
    private EstadoDelEstante estadoDelEstante;


    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    public Estante(double ancho, double pesoMaximoQueSoporta) {
        Validaciones.validarNumeroMayorACero(ancho, "ancho");
        Validaciones.validarNumeroMayorACero(pesoMaximoQueSoporta,"pesoMaximoQueSoporta");
        this.ancho = ancho;
        this.pesoMaximoQueSoporta = pesoMaximoQueSoporta;
        this.libros = new Vector<>();
        this.estadoDelEstante = EstadoDelEstante.OK;
    }


    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    public void agregar(Libro libro) {
        Validaciones.validarNotNull(libro, "libro");
        this.validarEstadoOK();
        this.validarEspacioDisponible(libro.getAncho());
        if (this.excedeElPesoMaximoSoportado(libro)) {
            this.estadoDelEstante = EstadoDelEstante.ROTO;
            return;
        }
        this.libros.agregar(libro);
    }

    public Libro quitar(Libro libro) {
        Validaciones.validarNotNull(libro, "libro");
        this.validarEstadoOK();
        int indice = this.libros.indiceDe(libro);
        return (indice == -1) ? null : this.libros.remover(indice);
    }

    public void reparar() {
        this.estadoDelEstante = EstadoDelEstante.OK;
    }

    public Libro buscar(Libro libro) {
        Validaciones.validarNotNull(libro, "libro");
        this.validarEstadoOK();
        for (int i = 0; i < this.libros.size(); i++) {
            Libro libroGuardado = this.libros.obtener(i);
            if (libroGuardado.equals(libro)) {
                return libroGuardado;
            }
        }
        return null;
    }

    public int getCantidadDeLibros() {
        this.validarEstadoOK();
        return this.libros.size();
    }

    public double getPesoActual() {
        this.validarEstadoOK();
        double pesoActual = 0;
        for (int i = 0; i < this.libros.size(); i++) {
            Libro libroGuardado = this.libros.obtener(i);
            pesoActual += libroGuardado.getPeso();
        }
        return pesoActual;
    }

    public double getEspacioOcupado() {
        this.validarEstadoOK();
        double espacioOcupado = 0;
        for (int i = 0; i < this.libros.size(); i++) {
            Libro libroGuardado = this.libros.obtener(i);
            espacioOcupado += libroGuardado.getAncho();
        }
        return espacioOcupado;
    }

    public double getEspacioLibre() {
        this.validarEstadoOK();
        return this.ancho - this.getEspacioOcupado();
    }

    public boolean estaOK() {
        return this.estadoDelEstante.equals(EstadoDelEstante.OK);
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public Libro[] getLibros() {
        Libro[] librosGuardados = new Libro[this.libros.size()];
        for (int i = 0; i < this.libros.size(); i++) {
            librosGuardados[i] = this.libros.obtener(i);
        }
        return librosGuardados;
    }

    public double getAncho() {
        return ancho;
    }

    public double getPesoMaximoQueSoporta() {
        return pesoMaximoQueSoporta;
    }

    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------

    private void validarEstadoOK() {
        if (!this.estaOK()) {
            throw new RuntimeException("Estante NO OK");
        }
    }

    private boolean excedeElPesoMaximoSoportado(Libro libro) {
        Validaciones.validarNotNull(libro, "libro");
        return this.getPesoActual() + libro.getPeso() > this.pesoMaximoQueSoporta;
    }

    private void validarEspacioDisponible(double ancho) {
        Validaciones.validarNumeroMayorACero(ancho, "ancho");
        if (this.getEspacioOcupado() + ancho > this.ancho) {
            throw new RuntimeException("Espacio INSUFICIENTE");
        }
    }
}
