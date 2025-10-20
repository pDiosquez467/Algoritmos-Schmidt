package examenes.parciales.julio2021.noticias;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;

import java.util.List;

public class Noticia {
    private final String titulo;
    private final String cuerpo;
    private final List<Categoria> categorias;

    /**
     * post: Inicializa la noticia con el título y el cuerpo indicados,
     * sin categorías asociadas.
     * @param titulo: el título de la noticia.
     * @param cuerpo : el cuerpo de la noticia.
     */
    public Noticia(String titulo, String cuerpo) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.categorias = new ListaSimplementeEnlazada<>();
    }

    /**
     * post: Devuelve el título de la noticia:
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
     * post: Devuelve la lista de categorías de la noticia.
     * @return la lista de categorías de la noticia.
     */
    public List<Categoria> categorias() {
        return categorias;
    }
}
