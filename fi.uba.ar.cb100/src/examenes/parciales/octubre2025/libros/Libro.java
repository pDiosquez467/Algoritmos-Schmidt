package examenes.parciales.octubre2025.libros;

import validaciones.Validaciones;

import java.util.Objects;

public class Libro {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final String titulo;
    private final double ancho;
    private final double peso;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa un libro con el título, el ancho y el peso indicados.
     * @param titulo: el título del libro.
     * @param ancho: el ancho del libro.
     * @param peso: el peso del libro.
     */
    public Libro(String titulo, double ancho, double peso) {
        Validaciones.validarNotNull(titulo, "titulo");
        Validaciones.validarNotBlank(titulo, "titulo");
        Validaciones.validarNumeroMayorACero(ancho, "ancho");
        Validaciones.validarNumeroMayorACero(peso, "peso");
        this.titulo = titulo;
        this.ancho = ancho;
        this.peso = peso;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Libro libro)) return false;
        return Objects.equals(titulo, libro.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titulo);
    }

    @Override
    public String toString() {
        return "Estante{" +
                "titulo='" + titulo + '\'' +
                ", ancho=" + ancho +
                ", peso=" + peso +
                '}';
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve el título del libro.
     * @return el título del libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * post: Devuelve el ancho del libro.
     * @return el ancho del libro.
     */
    public double getAncho() {
        return ancho;
    }

    /**
     * post: Devuelve el peso del libro.
     * @return el peso del libro.
     */
    public double getPeso() {
        return peso;
    }
}
