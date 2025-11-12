package examenes.parciales.listas.repaso.noticiasV2;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;

import java.util.Collections;
import java.util.List;

public class Noticia {

    private final String titulo;
    private final String cuerpo;
    private final List<Categoria> categorias;

    /**
     * post: Inicializa la noticia con el título y el cuerpo indicados,
     * sin categorías asociadas.
     */
    public Noticia(String titulo, String cuerpo) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.categorias = new ListaSimplementeEnlazada<>();
    }

    /**
     * post: Devuelve el título de la noticia.
     * @return el título de la noticia.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * post: Devuelve el cuerpo de la noticia.
     * @return el cuerpo de la noticia.
     */
    public String getCuerpo() {
        return cuerpo;
    }

    /**
     * post: Devuelve una copia inmutable de las categorías de la noticia.
     * @return las categorías de la noticia.
     */
    public List<Categoria> getCategorias() {
        return Collections.unmodifiableList(this.categorias);
    }
}
