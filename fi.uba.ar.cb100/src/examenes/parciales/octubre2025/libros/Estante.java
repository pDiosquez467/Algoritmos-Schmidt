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

    /**
     * post: Inicializa un estante con el ancho y el peso máximo soportado
     * indicados, sin libros asociados.
     *
     * @param ancho: el ancho del estante.
     * @param pesoMaximoQueSoporta: el peso máximo que el estante soporta.
     */
    public Estante(double ancho, double pesoMaximoQueSoporta) {
        Validaciones.validarNumeroMayorACero(ancho, "ancho");
        Validaciones.validarNumeroMayorACero(pesoMaximoQueSoporta, "pesoMaximoQueSoporta");
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

    /**
     * post: Agrega el libro indicado al estante. Si el libro excede
     * el peso soportado por el estante, rompe el estante y lanza una excepción.
     *
     * @param libro: el libro a agregar.
     * @throws RuntimeException si no hay espacio disponible.
     */
    public void agregar(Libro libro) {
        Validaciones.validarNotNull(libro, "libro");
        this.validarEstadoOK();
        this.validarEspacioDisponible(libro.getAncho());
        if (this.excedeElPesoMaximoSoportado(libro)) {
            this.estadoDelEstante = EstadoDelEstante.ROTO;
            throw new RuntimeException("Estante ROTO");
        }
        this.libros.agregar(libro);
    }

    /**
     * post: Remueve el libro indicado del estante.
     *
     * @param libro: el libro a remover.
     * @return el libro removido o null si no lo encontró.
     */
    public Libro quitar(Libro libro) {
        Validaciones.validarNotNull(libro, "libro");
        this.validarEstadoOK();
        int indice = this.libros.indiceDe(libro);
        return (indice == -1) ? null : this.libros.remover(indice);
    }

    /**
     * post: Modifica el estado del estante, reparándolo.
     */
    public void reparar() {
        this.estadoDelEstante = EstadoDelEstante.OK;
    }

    /**
     * post: Busca el libro dado en el estante.
     *
     * @param libro: el libro a buscar.
     * @return el libro buscado o null si no lo encontró.
     */
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

    /**
     * post: Devuelve la cantidad de libros que tiene el estante.
     *
     * @return la cantidad de libros que tiene el estante.
     * @throws RuntimeException si el estante está ROTO.
     */
    public int getCantidadDeLibros() {
        this.validarEstadoOK();
        return this.libros.size();
    }

    /**
     * post: Devuelve el peso actual soportado por el estante.
     * @return el peso actual soportado por el estante.
     * @throws RuntimeException si el estante está ROTO.
     */
    public double getPesoActual() {
        this.validarEstadoOK();
        double pesoActual = 0;
        for (int i = 0; i < this.libros.size(); i++) {
            Libro libroGuardado = this.libros.obtener(i);
            pesoActual += libroGuardado.getPeso();
        }
        return pesoActual;
    }

    /**
     * post: Devuelve el espacio ocupado por los libros.
     * @return el espacio ocupado.
     * @throws RuntimeException si el estante está ROTO.
     */
    public double getEspacioOcupado() {
        this.validarEstadoOK();
        double espacioOcupado = 0;
        for (int i = 0; i < this.libros.size(); i++) {
            Libro libroGuardado = this.libros.obtener(i);
            espacioOcupado += libroGuardado.getAncho();
        }
        return espacioOcupado;
    }

    /**
     * post: Devuelve el espacio libre disponible en el estante.
     * @return el espacio libre.
     * @throws RuntimeException si el estante está ROTO.
     */
    public double getEspacioLibre() {
        this.validarEstadoOK();
        return this.ancho - this.getEspacioOcupado();
    }

    /**
     * post: Indica si el estante está OK.
     * @return verdadero si el estante está OK.
     */
    public boolean estaOK() {
        return this.estadoDelEstante.equals(EstadoDelEstante.OK);
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve un arreglo con los libros del estante.
     * @return un arreglo con los libros guardados.
     */
    public Libro[] getLibros() {
        Libro[] librosGuardados = new Libro[this.libros.size()];
        for (int i = 0; i < this.libros.size(); i++) {
            librosGuardados[i] = this.libros.obtener(i);
        }
        return librosGuardados;
    }

    /**
     * post: Devuelve el ancho del estante.
     * @return el ancho del estante.
     */
    public double getAncho() {
        return ancho;
    }

    /**
     * post: Devuelve el peso máximo que soporta el estante.
     * @return el peso máximo soportado.
     */
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
