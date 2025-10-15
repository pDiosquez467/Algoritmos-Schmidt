package examenes.parciales.julio2022.noticias;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;

import java.util.Collections;
import java.util.List;

public class Noticia {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final String titulo;
    private final String cuerpo;
    private List<Categoria> categorias;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa la noticia con el título y cuerpo indicados, sin
     * categorias asociadas.
     * @param titulo: el título de la noticia.
     * @param cuerpo: el cuerpo de la noticia.
     */
    public Noticia(String titulo, String cuerpo) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.categorias = new ListaSimplementeEnlazada<>();
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve el título de la noticia.
     * @return el título de la noticia.
     */
    public String titulo() {
        return titulo;
    }

    /**
     * post: Devuelve el cuerpo de la noticia.
     * @return el cuerpo de la noticia.
     */
    public String cuerpo() {
        return cuerpo;
    }

    /**
     * post: Devuelve las categorías asociadas a la noticia.
     * @return la lista de categorías de la noticia.
     */
    public List<Categoria> categorias() {
        return Collections.unmodifiableList(this.categorias);
    }

    /**
     * post: Indica si la noticia contiene la categoría dada entre sus categorías.
     * @param categoria: la categoría dada.
     * @return verdadero si la noticia contiene la categoría dada.
     */
    public boolean contieneLaCategoria(Categoria categoria) {
        return this.categorias().contains(categoria);
    }
}
